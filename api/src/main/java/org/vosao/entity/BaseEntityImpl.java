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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.vosao.utils.DateUtil;

import siena.Generator;
import siena.Id;
import siena.Model;

public abstract class BaseEntityImpl extends Model implements BaseEntity {

	protected static final Log logger = LogFactory.getLog(
			BaseEntityImpl.class);

  @Id(Generator.AUTO_INCREMENT)
  public Long id;

	public String createUserEmail;
	public Date createDate;
	public String modUserEmail;
	public Date modDate;

	public BaseEntityImpl() {
		createDate = new Date();
		modDate = createDate;
		createUserEmail = "";
		modUserEmail = "";
	}
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
	  this.id = id;
	}


	@Override
	public boolean isNew() {
		return id == null;
	}
	
	@Override
	public void copy(BaseEntity entity) {
//		Key myKey = getKey(); 
//		Entity buf = new Entity("tmp");
//		entity.save(buf);
//		load(buf);
//		setKey(myKey);
	}
	
	public boolean equals(Object object) {
		if (object instanceof BaseEntity
				&& object.getClass().equals(this.getClass())) {
			BaseEntity entity = (BaseEntity) object;
			if (getId() == null && entity.getId() == null) {
				return true;
			}
			if (getId() != null && getId().equals(entity.getId())) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String getCreateUserEmail() {
		return createUserEmail;
	}

	@Override
	public void setCreateUserEmail(String createUserEmail) {
		this.createUserEmail = createUserEmail;
	}

	@Override
	public Date getCreateDate() {
		return createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String getModUserEmail() {
		return modUserEmail;
	}

	@Override
	public void setModUserEmail(String modUserEmail) {
		this.modUserEmail = modUserEmail;
	}

	@Override
	public Date getModDate() {
		return modDate;
	}

	@Override
	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	@Override
	public String getModDateString() {
		return DateUtil.toString(modDate);
	}

	@Override
	public String getCreateDateString() {
		return DateUtil.toString(createDate);
	}
	
	@Override
	public String getModDateTimeString() {
		return DateUtil.dateTimeToString(modDate);
	}

	@Override
	public String getCreateDateTimeString() {
		return DateUtil.dateTimeToString(createDate);
	}
}
