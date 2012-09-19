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
public class PageAttributeEntity extends BaseEntityImpl {

	public static final long serialVersionUID = 8L;

	public String pageUrl;
	public String name;
	public String title;
	public String defaultValue;
	public boolean inherited;

	public String toString() {
		return "PageAttributeEntity(" + pageUrl + ", " + name + ", inherited: " + inherited + ")";
	}
	
	public PageAttributeEntity() {
	}
	
	public PageAttributeEntity(String pageUrl, String name, String title,
			String defaultValue, boolean inherited) {
		super();
		this.pageUrl = pageUrl;
		this.name = name;
		this.title = title;
		this.defaultValue = defaultValue;
		this.inherited = inherited;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public boolean isInherited() {
		return inherited;
	}

	public void setInherited(boolean inherited) {
		this.inherited = inherited;
	}
	
}
