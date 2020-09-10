package web.core.model;

import java.util.List;

import app.core.domain.dto.PersonDTO;
import app.core.domain.dto.UserDTO;
import app.core.domain.model.IdentityCardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationForm extends AbstractForm {

	private static final long serialVersionUID = 1L;

	private List<IdentityCardType> identityTypes;
	
	private PersonDTO person;

	private UserDTO user;

}
