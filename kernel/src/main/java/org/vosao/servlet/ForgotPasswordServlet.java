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

package org.vosao.servlet;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.vosao.common.AccessDeniedException;
import org.vosao.common.VosaoContext;
import org.vosao.entity.PageEntity;
import org.vosao.entity.UserEntity;
import org.vosao.filter.AuthenticationFilter;
import org.vosao.utils.DateUtil;

/**
 * 
 * @author Alexander Oleynik
 *
 */
public class ForgotPasswordServlet extends AbstractServlet {

  private Integer getVersion(HttpServletRequest request) {
    try {
      return Integer.valueOf(request.getParameter("version"));
    }
    catch (NumberFormatException e) {
      return null;
    }
  }

  private PageEntity getPage(String url, HttpServletRequest request) 
      throws AccessDeniedException {
    Integer version = getVersion(request);
    PageEntity page;
    if (getBusiness().getContentPermissionBusiness().getPermission(url, 
        VosaoContext.getInstance().getUser()).isDenied()) {
      throw new AccessDeniedException();
    }
    if (version == null) {
          page = getDao().getPageDao().getByUrl(url);
          if (page == null) {
            page = getBusiness().getPageBusiness().getRestPage(url);
          }
    }
    else {
          page = getDao().getPageDao().getByUrlVersion(url, version);
    }
    return page;
  }

  protected boolean isLoggedIn(final HttpServletRequest request) {
    return VosaoContext.getInstance().getSession().getString(
        AuthenticationFilter.USER_SESSION_ATTR) != null;
  }

  private void renderPage(HttpServletRequest request, 
      HttpServletResponse response, final PageEntity page, String url) 
      throws IOException {
    String contentType = StringUtils.isEmpty(page.getContentType()) ?
        "text/html" : page.getContentType();
    response.setContentType(contentType);
//    response.setCharacterEncoding("UTF-8");
    Writer out = response.getWriter();
    String language = getBusiness().getLanguage();
    String content = getBusiness().getPageBusiness().render(page, language);
    out.write(content);
    if (!isLoggedIn(request) && page.isCached()) {
      getSystemService().getPageCache().put(url, language, content, 
          contentType);
    }
  }
  
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String key = request.getParameter("key");
		UserEntity user = getDao().getUserDao().getByKey(key);
		if (user == null || user.isDisabled()) {
//			RequestDispatcher dispatcher = getServletContext()
//					.getRequestDispatcher("/passwordchangeerror");
//			dispatcher.forward(request,response);
      PageEntity page = null;
      try {
        page = getPage("/passwordchangeerror", request);
      } catch (AccessDeniedException e) {
      }
      if (page != null) {
        renderPage(request, response, page, "/passwordchangeerror");
        return;
      }

		}
		else {
		  logger.info ( "Found user: " + user + ", key: " + key );
			user.setForgotPasswordKey(null);
			getDao().getUserDao().save(user);
			VosaoContext.getInstance().getSession().set(
					AuthenticationFilter.USER_SESSION_ATTR, user.getEmail());

//	     RequestDispatcher dispatcher = getServletContext()
//	          .getRequestDispatcher("/confirmpassword");
//	      
//	      dispatcher.forward(request,response);

      PageEntity page = null;
      try {
        page = getPage("/confirmpassword", request);
      } catch (AccessDeniedException e) {
        RequestDispatcher dispatcher = getServletContext()
            .getRequestDispatcher("/passwordchangeerror");
        
        dispatcher.forward(request,response);
      }
      if (page != null) {
        renderPage(request, response, page, "/confirmpassword");
        return;
      }

		}
	}
	
}