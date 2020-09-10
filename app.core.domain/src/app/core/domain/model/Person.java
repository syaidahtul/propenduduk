package app.core.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import app.core.model.EntityHistory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter	
@Entity
@Table(name = "person", uniqueConstraints = {
		@UniqueConstraint(name = "person_uq_identityNo", columnNames = "identity_no"),
		@UniqueConstraint(name = "person_uq_email", columnNames = "email") })
public class Person extends EntityHistory {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "person_generator")
	@SequenceGenerator(name="person_generator", sequenceName = "person_seq", allocationSize=1)
	@Column(name = "id", nullable = false)
	private long id;

	@Column(name = "full_name", nullable = false)
	private String fullName;

	@Column(name = "second_name")
	private String secondName;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "identity_card_type", referencedColumnName = "code", nullable = false)
	private IdentityCardType identityType;

	@Column(name = "identity_no", nullable = false)
	private String identityNo;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gender", referencedColumnName = "code", nullable = true)
	private Gender gender;

    @Column(name = "date_of_birth", nullable = true)
	private Date dateOfBirth;

    @Column(name = "place_of_birth", nullable = true)
	private String placeOfBirth;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_status", referencedColumnName = "code", nullable = true)
	private NationalityStatus nationalityStatus;

	@Column(name = "phone_no", nullable = true)
	private String phoneNo;

	@Column(name = "mobile_no", nullable = true)
	private String mobileNo;

	@Column(name = "email", nullable = true)
	private String email;

}
