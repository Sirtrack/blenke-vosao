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

package org.vosao.entity;

/**
 * @author Alexander Oleynik
 */
public class ContentEntity extends BaseEntityImpl {

	private static final long serialVersionUID = 8L;

	public String parentClass;
	public Long parentKey;
	public String languageCode;
	public String content;
	
	public ContentEntity() {
		content = "";
	}
	
	public ContentEntity(String parentClass, Long parentKey, 
			String languageCode, String content) {
		this();
		this.parentClass = parentClass;
		this.parentKey = parentKey;
		this.languageCode = languageCode;
		setContent(content);
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getParentClass() {
		return parentClass;
	}

	public void setParentClass(String parentClass) {
		this.parentClass = parentClass;
	}

	public Long getParentKey() {
		return parentKey;
	}

	public void setParentKey(Long parentKey) {
		this.parentKey = parentKey;
	}

	public String getLanguageCode() {
		return languageCode;
	}

	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	
}
