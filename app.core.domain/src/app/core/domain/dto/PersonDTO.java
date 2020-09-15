package app.core.domain.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PersonDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String fullName;

	private String secondName;

	private String identityType;

	private String identityNo;

	private String gender;

	private Date dateOfBirth;

	private String placeOfBirth;

	private String nationalityStatus;

	private String phoneNo;

	private String mobileNo;

	private String email;
}
