package app.core.usermgmt.service;

import java.util.List;

import app.core.domain.dto.PersonDTO;
import app.core.domain.dto.UserDTO;
import app.core.domain.model.IdentityCardType;
import app.core.domain.model.NationalityStatus;

public interface UserRegistrationService {

	public List<IdentityCardType> findAllIdentityType();
	
	public List<NationalityStatus> findAllNationalityStatus();

	public void registerPerson(PersonDTO person, UserDTO userDTO);

}
