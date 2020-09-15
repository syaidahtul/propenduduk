package web.module.setup.model;

import app.core.domain.dto.EntityCodeDTO;
import lombok.Getter;
import lombok.Setter;
import web.core.model.AbstractForm;

@Getter
@Setter
public class GenericEntityCodeForm extends AbstractForm {
	
	private static final long serialVersionUID = 1L;

	private String[] selected;
	
	private EntityCodeDTO entity;

}
