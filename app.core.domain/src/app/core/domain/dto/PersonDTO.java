package app.core.domain.dto;

import java.util.Date;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
public class PersonDTO {
	
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
