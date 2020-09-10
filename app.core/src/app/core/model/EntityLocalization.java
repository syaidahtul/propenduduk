package app.core.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class EntityLocalization extends EntityCodeActive {

	private static final long serialVersionUID = 1L;

	@Column(name = "malay_desc", length = 20)
	private String malayDesc;

	public EntityLocalization() {
		super();
	}

	public String getMalayDesc() {
		return malayDesc;
	}

	public void setMalayDesc(String malayDesc) {
		this.malayDesc = malayDesc;
	}

}
