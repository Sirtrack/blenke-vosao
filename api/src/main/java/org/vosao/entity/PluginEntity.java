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









public class PluginEntity extends BaseEntityImpl {
	
	public static final long serialVersionUID = 3L;

	public String name;
	public String title;
	public String description;
	public String website;
	public String configStructure;
	public String configData;
	public String entryPointClass;
	public String configURL;
	public String pageHeader;
	public String version;
	public boolean disabled;

	public PluginEntity() {
		configStructure = "";
		configData = "";
		pageHeader = "";
		disabled = false;
    }
    
	public PluginEntity(String name, String title, String configStructure,
    		String configData) {
		this();
		this.name = name;
		this.title = title;
		setConfigStructure(configStructure);
		setConfigData(configData);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getConfigStructure() {
		return configStructure;
	}

	public void setConfigStructure(String configStructure) {
		this.configStructure = configStructure;
	}

	public String getConfigData() {
		return configData;
	}

	public void setConfigData(String configData) {
		this.configData = configData;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getEntryPointClass() {
		return entryPointClass;
	}

	public void setEntryPointClass(String entryPointClass) {
		this.entryPointClass = entryPointClass;
	}
	
	public String getConfigURL() {
		return configURL;
	}

	public void setConfigURL(String configURL) {
		this.configURL = configURL;
	}

	public String getPageHeader() {
		return pageHeader;
	}

	public void setPageHeader(String pageHeader) {
		this.pageHeader = pageHeader;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

}
