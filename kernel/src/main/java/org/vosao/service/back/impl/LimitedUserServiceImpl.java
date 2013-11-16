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

package org.vosao.service.back.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.vosao.common.BCrypt;
import org.vosao.entity.UserEntity;
import org.vosao.enums.UserRole;
import org.vosao.i18n.Messages;
import org.vosao.service.ServiceResponse;
import org.vosao.service.back.LimitedUserService;
import org.vosao.service.back.UserService;
import org.vosao.service.impl.AbstractServiceImpl;
import org.vosao.service.vo.UserVO;
import org.vosao.utils.ParamUtil;

/**
 * 
 * @author Alexander Oleynik
 *
 */
public class LimitedUserServiceImpl extends AbstractServiceImpl implements LimitedUserService  {

	public List<UserVO> select() {
		return UserVO.create(getDao().getUserDao().select());
	}

	public ServiceResponse save(Map<String, String> vo) {
		UserEntity user = null;
		Long id = null;
		
		if (!StringUtils.isEmpty(vo.get("id"))) {
		  id = Long.valueOf(vo.get("id"));
			user = getDao().getUserDao().getById(id);
		}
		
    if(!getBusiness().getUser().getId().equals(id)) {
      return ServiceResponse.createErrorResponse(
          Messages.get("errors_occured"), Arrays.asList("Attempted changing a different user") );
    }

		if (user == null) {
			user = new UserEntity();
		}
		user.setName(vo.get("name"));
		if (!StringUtils.isEmpty(vo.get("email"))) {
			user.setEmail(vo.get("email").toLowerCase());
		}
		if (!StringUtils.isEmpty(vo.get("password"))) {
			user.setPassword(BCrypt.hashpw(vo.get("password"), 
					BCrypt.gensalt()));
		}
		else if( user.password == null ) {
      return ServiceResponse.createErrorResponse(
          Messages.get("errors_occured"), Arrays.asList("Password cannot be empty") );
		}
		  
		if (!StringUtils.isEmpty(vo.get("role"))) {
			user.setRole(UserRole.valueOf(vo.get("role")));
		}
		if (!StringUtils.isEmpty(vo.get("timezone"))) {
			user.setTimezone(vo.get("timezone"));
		}
		if (!StringUtils.isEmpty(vo.get("disabled"))) {
			user.setDisabled(ParamUtil.getBoolean(vo.get("disabled"), false));
		}
		
    List<String> errors = getBusiness().getUserBusiness()
				.validateBeforeUpdate(user);
		
		if (errors.isEmpty()) {
			getDao().getUserDao().save(user);
			return ServiceResponse.createSuccessResponse(
					Messages.get("user.success_save"));
		}
		else {
			return ServiceResponse.createErrorResponse(
					Messages.get("errors_occured"), errors);
		}
	}

	public UserEntity getLoggedIn() {
		return getBusiness().getUser();
	}

	public List<String> getTimezones() {
		List<String> result = new ArrayList<String>();
		String[] ids = TimeZone.getAvailableIDs();
		Arrays.sort(ids);
		for (String id : ids) {
			result.add(id);
		}
		return result;
	}

}
