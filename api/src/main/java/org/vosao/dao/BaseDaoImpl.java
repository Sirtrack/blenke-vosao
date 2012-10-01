/**
 * Vosao CMS. Simple CMS for Google App Engine.
 * 
 * Copyright (C) 2009-2010 Vosao development team.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * email: vosao.dev@gmail.com
 */

package org.vosao.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vosao.common.VosaoContext;
import org.vosao.entity.BaseEntityImpl;

import siena.Model;
import siena.Query;
import siena.core.async.QueryAsync;
import siena.core.async.SienaFuture;

public class BaseDaoImpl<T extends BaseEntityImpl> extends AbstractDaoImpl implements BaseDao<T>{
	
	protected static final Log logger = LogFactory.getLog(
			BaseDaoImpl.class);

	private static int QUERY_LIMIT = 100000;
	private static int CHUNK_SIZE = 1000;
	
	private Class<T> clazz;
	private String kind;

	public BaseDaoImpl(Class<T> aClass) {
		clazz = aClass;
		kind = createKind();
	}
	
	public BaseDaoImpl(Class<T> aClass, String aKind) {
		clazz = aClass;
		kind = aKind;
	}

//	private DatastoreService getDatastore() {
//		return getSystemService().getDatastore();
//	}

	@Override
	public void clearCache() {
		try {
			getQueryCache().removeQueries(clazz);
		}
		catch (Exception e) {
			logger.error("clearCache " + clazz.getName() + " " + e.getMessage());
		}
	}

	@Override
	public T getById(Long id) {
		if (id == null || id <= 0) {
			return null;
		}
		T model = (T) getEntityCache().getEntity(clazz, id);
		if (model != null) {
			return model;
		}
    
		return Model.getByKey(clazz, id);
	}

	@Override
	public List<T> getById(List<Long> ids) {
		if (ids == null) {
			return Collections.EMPTY_LIST;
		}
		
		List<Long> keys = new ArrayList<Long>();

		List<T> result = new ArrayList<T>();
		for (Long id : ids) {
			if (id != null && id > 0) {
				T model = (T) getEntityCache().getEntity(clazz, id);
				if (model != null) {
					result.add(model);
				}
				else {
					keys.add(id);
				}
			}
		}
		getDao().getDaoStat().incGetCalls();
		List<T> models = Model.batch(clazz).getByKeys(keys);
		    
		for (T model : models) {
			getEntityCache().putEntity(clazz, model.getId(), model);
		}
		result.addAll(models);
		return result;
	}

	@Override
	public void remove(Long id) {
		if (id == null) {
			return;
		}
		getEntityCache().removeEntity(clazz, id);
		getQueryCache().removeQueries(clazz);

 		Model.getByKey(clazz, id).delete();
	}

	@Override
	public void remove(List<Long> ids) {
	  List<T> lt = new ArrayList<T>();
		for (Long id : ids) {
			getEntityCache().removeEntity(clazz, id);
		}
    getQueryCache().removeQueries(clazz);
    Model.batch(clazz).deleteByKeys(ids);
	}

	@Override
	public void removeAll() {
		removeSelected(newQuery());
	}

	protected void removeSelected(Query<T> query) {
		getQueryCache().removeQueries(clazz);
		getDao().getDaoStat().incQueryCalls();
		
		QueryAsync<T> q = query.async().paginate(CHUNK_SIZE).stateful();
		
		SienaFuture<List<T>> future = q.fetchKeys();
		List<T> ts = future.get();
    while( ts.size() > 0 ){
      future = q.nextPage().fetchKeys();
      Model.batch(clazz).delete(ts);
      ts = future.get();
    }
	}
	
	@Override
	public T saveNoAudit(T model) {
		return save(model, false);
	}
	
	@Override
	public T save(T model) {
		return save(model, true);
	}
		
	private T save(T model, boolean audit) {
		getQueryCache().removeQueries(clazz);
		T entity = null;
		if (model.getId() != null) {
			getDao().getDaoStat().incGetCalls();
			entity = Model.getByKey(clazz, model.id);
			getEntityCache().removeEntity(clazz, model.getId());

			if (entity != null) {
	      model.id = entity.id;
	    }
		}
		else {
       model.setCreateUserEmail(getCurrentUserEmail());
    } 
		
		if (audit) {
		  model.setModDate(new Date());
		  model.setModUserEmail(getCurrentUserEmail());
		}
		model.save();
		
		return model;
	}


  @Override
  public List<T> select() {
    List<T> result = (List<T>) getQueryCache().getQuery(clazz, 
        clazz.getName(), null);
    if (result == null) {
      Query<T> q = newQuery();
      result = selectNotCache(q);
      getQueryCache().putQuery(clazz, clazz.getName(), null, 
          (List<T>)result);
    }
    return result;
  }

  protected List<T> select(Query query, String queryId, Object[] params) {
    return select(query, queryId, QUERY_LIMIT, params);
  }

  protected T selectOne(Query query, String queryId, int queryLimit, 
      Object[] params) {
    T result = (T) getQueryCache().getQueryOne(clazz, queryId, params);
    
    if (result == null ) {
      getDao().getDaoStat().incQueryCalls();
      result = selectOneNotCache(query);
      if(result == null)
        return null;
      
      List<T> list =  new ArrayList<T>();
      list.add(result);
      getQueryCache().putQuery(clazz, queryId, params, list);      
    }

    return result;
  }

  protected List<T> select(Query<T> query, String queryId, int queryLimit, 
      Object[] params) {
    List<T> result = (List<T>) getQueryCache().getQuery(clazz, queryId, 
        params);
    if (result == null) {
      getDao().getDaoStat().incQueryCalls();
      result = selectNotCache(query);
      getQueryCache().putQuery(clazz, queryId, params, 
          (List<T>)result);      
    }
    return result;
  }

  protected T selectOneNotCache(Query<T> query) {
    getDao().getDaoStat().incQueryCalls();
    return query.get();
  }

  protected List<T> selectNotCache(Query<T> query) {
    getDao().getDaoStat().incQueryCalls();

//    List<Entity> entities = new ArrayList<Entity>();
//    Query<T> q = query.paginate(CHUNK_SIZE).stateful();
//    List<T> ts = q.fetchKeys();
//    for (Entity entity : p.asIterable(FetchOptions.Builder.withChunkSize(CHUNK_SIZE))) {
//      entities.add(entity);
//    }
//    return createModels(entities);
    List<T> m = query.fetchKeys();
    Model.batch(clazz).get(m);
    
    return m;
  }
  
  
	protected T selectOne(Query<T> query, String queryId, Object[] params) {
		return selectOne(query, queryId, QUERY_LIMIT, params);
	}
		
	protected Object[] params(Object...objects) {
		return objects;
	}

	@Override
	public String getKind() {
		return kind;
	}

	private String createKind() {
    String name = clazz.getName();
    return name.substring(name.lastIndexOf('.') + 1);
	}
	
	private String getCurrentUserEmail() {
		return VosaoContext.getInstance().getUser() == null ? "guest" 
				: VosaoContext.getInstance().getUser().getEmail();
	}
	
	@Override
	public int count() {
		return Model.all(clazz).count();
	}

	public Query<T> newQuery(){
	  return Model.all(clazz);
	}

}
