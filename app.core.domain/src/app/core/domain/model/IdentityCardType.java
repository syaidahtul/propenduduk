package app.core.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import app.core.model.EntityLocalization;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "identity_card_type", uniqueConstraints = {
		@UniqueConstraint(name = "identity_type_uq_code", columnNames = "code") })
public class IdentityCardType extends EntityLocalization {

	private static final long serialVersionUID = 1L;
	
	public IdentityCardType(String code) {
		super.setCode(code);
	}

}
