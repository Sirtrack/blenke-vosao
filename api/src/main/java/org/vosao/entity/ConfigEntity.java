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

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;


public class ConfigEntity extends BaseEntityImpl {

	private static final long serialVersionUID = 2L;

	public String googleAnalyticsId;
	public String siteEmail;
	public String siteDomain;
	public String editExt;
	public boolean enableRecaptcha;
	public String recaptchaPrivateKey;
	public String recaptchaPublicKey;
	public String commentsEmail;
	public String commentsTemplate;
	public String version;
	public String siteUserLoginUrl;
	public boolean enablePicasa;
	public String picasaUser;
	public String picasaPassword;
	public boolean enableCkeditor;
	public String attributesJSON;
	public String defaultTimezone;
	public String defaultLanguage;
	public String site404Url;
	public String sessionKey;

	public ConfigEntity() {
		commentsTemplate = "";
		enableCkeditor = true;
		defaultLanguage = "en";
	}

	public String getGoogleAnalyticsId() {
		return googleAnalyticsId;
	}

	public void setGoogleAnalyticsId(String googleAnalyticsId) {
		this.googleAnalyticsId = googleAnalyticsId;
	}

	public String getSiteEmail() {
		return siteEmail;
	}

	public void setSiteEmail(String siteEmail) {
		this.siteEmail = siteEmail;
	}

	public String getSiteDomain() {
		return siteDomain;
	}

	public void setSiteDomain(String siteDomain) {
		this.siteDomain = siteDomain;
	}

	public String getEditExt() {
		return editExt;
	}

	public void setEditExt(String editExt) {
		this.editExt = editExt;
	}

	public String getRecaptchaPrivateKey() {
		return recaptchaPrivateKey;
	}

	public void setRecaptchaPrivateKey(String recaptchaPrivateKey) {
		this.recaptchaPrivateKey = recaptchaPrivateKey;
	}

	public String getRecaptchaPublicKey() {
		return recaptchaPublicKey;
	}

	public void setRecaptchaPublicKey(String recaptchaPublicKey) {
		this.recaptchaPublicKey = recaptchaPublicKey;
	}

	public String getCommentsEmail() {
		return commentsEmail;
	}

	public void setCommentsEmail(String commentsEmail) {
		this.commentsEmail = commentsEmail;
	}

	public String getCommentsTemplate() {
		return commentsTemplate;
	}

	public void setCommentsTemplate(String commentsTemplate) {
		this.commentsTemplate = commentsTemplate;
	}

	public boolean isEnableRecaptcha() {
		return enableRecaptcha;
	}

	public void setEnableRecaptcha(boolean enableRecaptcha) {
		this.enableRecaptcha = enableRecaptcha;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getSiteUserLoginUrl() {
		return siteUserLoginUrl;
	}

	public void setSiteUserLoginUrl(String siteUserLoginUrl) {
		this.siteUserLoginUrl = siteUserLoginUrl;
	}

	public boolean isEnablePicasa() {
		return enablePicasa;
	}

	public void setEnablePicasa(boolean enablePicasa) {
		this.enablePicasa = enablePicasa;
	}

	public String getPicasaUser() {
		return picasaUser;
	}

	public void setPicasaUser(String picasaUser) {
		this.picasaUser = picasaUser;
	}

	public String getPicasaPassword() {
		return picasaPassword;
	}

	public void setPicasaPassword(String picasaPassword) {
		this.picasaPassword = picasaPassword;
	}

	public boolean isEnableCkeditor() {
		return enableCkeditor;
	}

	public void setEnableCkeditor(boolean value) {
		this.enableCkeditor = value;
	}

	public String getAttributesJSON() {
		return attributesJSON;
	}

    public void setAttributesJSON(String value) {
    	attributesJSON = value;
    }

    private transient Map<String, String> attributes;
	
    public Map<String, String> getAttributes() {
    	if (attributes == null) 
    	{
    		attributes = new HashMap<String, String>();
    		parseAttributes();
    	}
    	return attributes;
    }

    private void parseAttributes() {
		if (StringUtils.isEmpty(attributesJSON)) {
			return;
		}
		try {
			JSONObject obj = new JSONObject(attributesJSON);
			Iterator<String> attributeIter = obj.keys();
			while (attributeIter.hasNext()) {
				String attrName = attributeIter.next();
				getAttributes().put(attrName, obj.getString(attrName));
			}
		} catch (org.json.JSONException e) {
			logger.error("Config atributes parsing problem: " /*+ attributes*/);
		}
    }

    private void updateAttributes() {
    	attributesJSON = new JSONObject(getAttributes()).toString();
    }
    
    public void setAttribute(String name, String value) {
    	getAttributes().put(name, value);
    	updateAttributes();
    }
    
    public String getAttribute(String name) {
    	return getAttributes().get(name);
    }
    
    public void removeAttribute(String name) {
    	getAttributes().remove(name);
        updateAttributes();	
    }

    public void removeAttributes(List<String> names) {
    	for (String name : names) getAttributes().remove(name);
        updateAttributes();	
    }

    public String getDefaultTimezone() {
		return defaultTimezone;
	}

	public void setDefaultTimezone(String defaultTimezone) {
		this.defaultTimezone = defaultTimezone;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

	public String getSite404Url() {
		return site404Url;
	}

	public void setSite404Url(String site404Url) {
		this.site404Url = site404Url;
	}

	public String getSessionKey() {
		return sessionKey;
	}

	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
}
