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

package org.vosao.dao.impl;



import java.util.ArrayList;
import java.util.List;

import org.vosao.dao.BaseDaoImpl;
import org.vosao.dao.TagDao;
import org.vosao.entity.TagEntity;

import siena.Query;

public class TagDaoImpl extends BaseDaoImpl<TagEntity> 
		implements TagDao {

	public TagDaoImpl() {
		super(TagEntity.class);
	}

	@Override
	public TagEntity getByName(final Long parent, final String name) {
		Query<TagEntity> q = newQuery();
		if( parent!= null )
		  q.filter("parent", parent);
		q.filter("name", name);
		return selectOne(q, "getByName", params(parent, name));
	}

	@Override
	public List<TagEntity> selectByParent(final Long parent) {
		Query<TagEntity> q = newQuery();
		if( parent != null )
		  q.filter("parent", parent);
		List<TagEntity> lt = select(q, "selectByParent", params(parent));
		if( parent == null ){
		  List<TagEntity> rlt = new ArrayList<TagEntity>();
		  for( TagEntity t : lt ){
		    if( t.parent==null )
		      rlt.add(t);
		  }
		  return rlt;
		}
		return lt;
	}

}
