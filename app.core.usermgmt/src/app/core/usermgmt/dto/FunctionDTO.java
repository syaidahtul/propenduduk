package app.core.usermgmt.dto;

import app.core.dto.DTOBase;

public class FunctionDTO extends DTOBase {

	private static final long serialVersionUID = 1L;

	// Field to display in data table
	private String code;
	private String path;
	private String name;
	private String description;

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
