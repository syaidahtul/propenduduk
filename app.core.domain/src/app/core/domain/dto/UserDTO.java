package app.core.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

	private Long id;

	private String tempPassword;

	private String username;

	private String salt;

	private String password;

	private String firstName;

	private String lastName;

	private String hintQuestion;

	private String hintAnswer;

	private Long invalidLoginCount;

	private Long maxInvalidLoginCount;

	private Long invalidChangePwdCount;

	private Date pwdUpdateDate;

	private Long pwdExpiryDays;

	private Long sessionTimeoutMinutes;

	private String ipAddress;

	private Date lastAccessTime;

	private Boolean connectedFlag;

	private Boolean activeFlag;

	private Boolean forceChangePwdFlag;

	private String dateFormat;

	private boolean pwdChanged;

	private String confirmPassword;

	private Date lastLoginTime;

	private Long signatureStoredFileId;

	private String jobTitle;

	private Long hintAnswerGuessCount;

	private String appUserTypeSrcId;

	private String homePageUrl;

	private String sessionId;

	private String email;

}
