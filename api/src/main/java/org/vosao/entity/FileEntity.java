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

import java.util.Date;

import org.vosao.utils.FolderUtil;




public class FileEntity extends BaseEntityImpl {

	public static final long serialVersionUID = 5L;

	public static final String[] IMAGE_EXTENSIONS = {"jpg","jpeg","png","ico",
	"gif"};

	public String title;
	public String filename;	
	public Long folderId;	
	public String mimeType;
	public Date lastModifiedTime;
	public Integer size;

	public FileEntity() {
	}
	
	public FileEntity(String aTitle, String aName, Long aFolderId,
			String aMimeType, Date aMdttime, int aSize) {
		this();
		title = aTitle;
		filename = aName;
		folderId = aFolderId;
		mimeType = aMimeType;
		lastModifiedTime = aMdttime;
		size = aSize;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public Long getFolderId() {
		return folderId;
	}

	public void setFolderId(Long folderId) {
		this.folderId = folderId;
	}

	public String getFilename() {
		return filename;
	}
	
	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(Date aLastModifiedTime) {
		this.lastModifiedTime = aLastModifiedTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isImage() {
		for (String ext : IMAGE_EXTENSIONS) {
			if (FolderUtil.getFileExt(getFilename()).equals(ext)) {
				return true;
			}
		}
		return false;
	}

}
