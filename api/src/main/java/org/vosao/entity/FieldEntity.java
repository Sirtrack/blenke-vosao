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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang.StringUtils;
import org.vosao.enums.FieldType;



public class FieldEntity extends BaseEntityImpl {

	public static class Option {
		public String value;
		public boolean selected;
		
		public Option(String value, boolean selected) {
			super();
			this.value = value;
			this.selected = selected;
		}

		public String getValue() {
			return value;
		}

		public boolean isSelected() {
			return selected;
		}
	}

	
	public static final long serialVersionUID = 2L;

	public Long formId;
	public String name;
	public String title;
	public FieldType fieldType;
	public boolean mandatory;
	public String values;
	public String defaultValue;
	public int height;
	public int width;
	public int index;
	public String regex;
	public String regexMessage;

	public FieldEntity() {
		height = 1;
		width = 20;
	}
	
	public FieldEntity(Long formId, String name, String title,
			FieldType fieldType, boolean optional, String defaultValue) {
		this();
		this.formId = formId;
		this.name = name;
		this.title = title;
		this.fieldType = fieldType;
		this.mandatory = optional;
		this.defaultValue = defaultValue;
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	public boolean isMandatory() {
		return mandatory;
	}

	public void setMandatory(boolean optional) {
		this.mandatory = optional;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public List<Option> getOptions() {
		List<Option> result = new ArrayList<Option>();
		String[] opts = getValues().split("\n");
		for (String opt : opts) {
			String val = opt.replace("*", "");
			boolean selected = opt.charAt(0) == '*';
			result.add(new Option(val, selected));
		}
		return result;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
	}

	public String getRegexMessage() {
		return regexMessage;
	}

	public void setRegexMessage(String regexMessage) {
		this.regexMessage = regexMessage;
		parseRegexMessage();
	}

	public Map<String, String> messages;
	
	public void parseRegexMessage() {
		messages = new HashMap<String, String>();
		if (!StringUtils.isEmpty(getRegexMessage())) {
			String[] tokens = getRegexMessage().split("::");
			for (String token : tokens) {
				String code = token.substring(0, 2); 
				String msg = token.substring(2);
				messages.put(code, msg);
			}
		}
	}
	
	public String getRegexMessage(String language) {
		if (messages == null) {
			parseRegexMessage();
		}
		return messages.get(language);
	}
	
}
