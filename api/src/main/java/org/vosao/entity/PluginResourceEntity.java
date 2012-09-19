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



import com.google.appengine.api.datastore.Blob;


public class PluginResourceEntity extends BaseEntityImpl {
	
	public static final long serialVersionUID = 2L;

	public String pluginName;
	public byte[] data;
	public String url;
	
    public PluginResourceEntity() {
    }

    public PluginResourceEntity(String plugin, String anUrl, byte[] content) {
		this();
		pluginName = plugin;
		this.url = anUrl;
		this.data = content;
	}

	public byte[] getContent() {
		return data;
	}
	
	public void setContent(byte[] content) {
		this.data = content;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String fileId) {
		this.url = fileId;
	}

	public String getPluginName() {
		return pluginName;
	}

	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	
}
