package app.core.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import app.core.model.EntityLocalization;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Table(name = "gender", uniqueConstraints = {
		@UniqueConstraint(name = "gender_uq_code", columnNames = "code") })
public class Gender extends EntityLocalization {

	private static final long serialVersionUID = 1L;

}
