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

package org.vosao.service.impl;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jabsorb.JSONRPCBridge;
import org.vosao.business.Business;
import org.vosao.common.VosaoContext;
import org.vosao.dao.Dao;
import org.vosao.entity.PluginEntity;
import org.vosao.service.BackService;
import org.vosao.service.LimitedService;
import org.vosao.service.back.CommentService;
import org.vosao.service.back.ConfigService;
import org.vosao.service.back.ContentPermissionService;
import org.vosao.service.back.FieldService;
import org.vosao.service.back.FileService;
import org.vosao.service.back.FolderPermissionService;
import org.vosao.service.back.FolderService;
import org.vosao.service.back.FormService;
import org.vosao.service.back.GroupService;
import org.vosao.service.back.LanguageService;
import org.vosao.service.back.LimitedUserService;
import org.vosao.service.back.MessageService;
import org.vosao.service.back.PageAttributeService;
import org.vosao.service.back.PageService;
import org.vosao.service.back.PicasaService;
import org.vosao.service.back.PluginService;
import org.vosao.service.back.SeoUrlService;
import org.vosao.service.back.StructureService;
import org.vosao.service.back.StructureTemplateService;
import org.vosao.service.back.TagService;
import org.vosao.service.back.TemplateService;
import org.vosao.service.back.UserService;
import org.vosao.service.back.impl.CommentServiceImpl;
import org.vosao.service.back.impl.ConfigServiceImpl;
import org.vosao.service.back.impl.ContentPermissionServiceImpl;
import org.vosao.service.back.impl.FieldServiceImpl;
import org.vosao.service.back.impl.FileServiceImpl;
import org.vosao.service.back.impl.FolderPermissionServiceImpl;
import org.vosao.service.back.impl.FolderServiceImpl;
import org.vosao.service.back.impl.FormServiceImpl;
import org.vosao.service.back.impl.GroupServiceImpl;
import org.vosao.service.back.impl.LanguageServiceImpl;
import org.vosao.service.back.impl.MessageServiceImpl;
import org.vosao.service.back.impl.PageAttributeServiceImpl;
import org.vosao.service.back.impl.PageServiceImpl;
import org.vosao.service.back.impl.PicasaServiceImpl;
import org.vosao.service.back.impl.PluginServiceImpl;
import org.vosao.service.back.impl.SeoUrlServiceImpl;
import org.vosao.service.back.impl.StructureServiceImpl;
import org.vosao.service.back.impl.StructureTemplateServiceImpl;
import org.vosao.service.back.impl.TagServiceImpl;
import org.vosao.service.back.impl.TemplateServiceImpl;
import org.vosao.service.back.impl.LimitedUserServiceImpl;
import org.vosao.service.plugin.PluginServiceManager;

public class LimitedServiceImpl implements LimitedService, Serializable {

	private static final Log log = LogFactory.getLog(LimitedServiceImpl.class);

	private LimitedUserService userService;
	
  public void register(JSONRPCBridge bridge) {
		bridge.registerObject("userService", getUserService());
    registerPluginServices(bridge);
	}
	
  public void unregister(JSONRPCBridge bridge) {
		bridge.unregisterObject("userService");
		unregisterPluginServices(bridge);
	}

  public LimitedUserService getUserService() {
		if (userService == null) {
			userService = new LimitedUserServiceImpl();
		}
		return userService;
	}

  public void setUserService(LimitedUserService bean) {
		userService = bean;		
	}

	private Business getBusiness() {
		return VosaoContext.getInstance().getBusiness();
	}

	private void registerPluginServices(JSONRPCBridge bridge) {
		for (PluginEntity plugin : getDao().getPluginDao().selectEnabled()) {
			try {
				PluginServiceManager manager = getBusiness()
						.getPluginBusiness().getBackServices(plugin);
				if (manager != null) {
					manager.register(bridge);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void unregisterPluginServices(JSONRPCBridge bridge) {
		for (PluginEntity plugin : getDao().getPluginDao().selectEnabled()) {
			try {
				PluginServiceManager manager = getBusiness()
					.getPluginBusiness().getBackServices(plugin);
				if (manager != null) {
					manager.unregister(bridge);
				}				
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	private Dao getDao() {
		return getBusiness().getDao();
	}
	
}
