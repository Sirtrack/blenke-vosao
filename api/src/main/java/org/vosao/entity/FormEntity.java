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
 * 
 * @author Alexander Oleynik
 *
 */
public class FormEntity extends BaseEntityImpl {

	public static final long serialVersionUID = 2L;

	public String name;
	public String title;
	public String email;
	public String letterSubject;
	public String sendButtonTitle;
	public boolean showResetButton;
	public String resetButtonTitle;
	public boolean enableCaptcha;
	public boolean enableSave;

	public FormEntity() {
	}
	
	public FormEntity(String aName, String aEmail, String aTitle, 
			String aSubject) {
		this();
		name = aName;
		email = aEmail;
		title = aTitle;
		letterSubject = aSubject;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLetterSubject() {
		return letterSubject;
	}

	public void setLetterSubject(String letterSubject) {
		this.letterSubject = letterSubject;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSendButtonTitle() {
		return sendButtonTitle;
	}

	public void setSendButtonTitle(String sendButtonTitle) {
		this.sendButtonTitle = sendButtonTitle;
	}

	public boolean isShowResetButton() {
		return showResetButton;
	}

	public void setShowResetButton(boolean showResetButton) {
		this.showResetButton = showResetButton;
	}

	public String getResetButtonTitle() {
		return resetButtonTitle;
	}

	public void setResetButtonTitle(String resetButtonTitle) {
		this.resetButtonTitle = resetButtonTitle;
	}

	public boolean isEnableCaptcha() {
		return enableCaptcha;
	}

	public void setEnableCaptcha(boolean enableCaptcha) {
		this.enableCaptcha = enableCaptcha;
	}

	public boolean isEnableSave() {
		return enableSave;
	}

	public void setEnableSave(boolean enableSave) {
		this.enableSave = enableSave;
	}
	
}
