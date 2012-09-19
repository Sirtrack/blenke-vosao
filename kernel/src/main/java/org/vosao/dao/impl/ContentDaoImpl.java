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

import java.util.List;

import org.vosao.dao.BaseDaoImpl;
import org.vosao.dao.ContentDao;
import org.vosao.entity.ContentEntity;

import siena.core.async.QueryAsync;


public class ContentDaoImpl extends BaseDaoImpl<ContentEntity> 
		implements ContentDao {

	public ContentDaoImpl() {
		super(ContentEntity.class);
	}

	@Override
	public List<ContentEntity> select(final String parentClass, 
			final Long parentKey) {
		QueryAsync q = newQuery();
		q.filter("parentClass", parentClass);
		q.filter("parentKey", parentKey);
		return select(q, "select", params(parentClass, parentKey));
	}
	
	@Override
	public ContentEntity getByLanguage(final String parentClass, 
			final Long parentKey, final String language) {
		QueryAsync q = newQuery();
		q.filter("parentClass", parentClass);
		q.filter("parentKey", parentKey);
		q.filter("languageCode", language);
		return selectOne(q, "getByLanguage", params(parentClass, parentKey, 
				language));
	}

	@Override
	public void removeById(String className, Long id) {
		QueryAsync q = newQuery();
		q.filter("parentClass", className);
		q.filter("parentKey", id);
		removeSelected(q);
	}
	
}
