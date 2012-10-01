package org.vosao.dao.cache;

import java.util.List;

import org.vosao.entity.BaseEntity;

/**
 * 
 * @author Alexander Oleynik
 *
 */
public interface QueryCache {

	void putQuery(Class<? extends BaseEntity> clazz, String query, Object[] params, 
			List<? extends BaseEntity> list);

	List<? extends BaseEntity> getQuery(Class<? extends BaseEntity> clazz, String query, Object[] params);
  BaseEntity getQueryOne(Class<? extends BaseEntity> clazz, String query, Object[] params);
	
	void removeQueries(Class<? extends BaseEntity> clazz);
	
}
