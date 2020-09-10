package app.core.usermgmt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import app.core.annotation.Menu;
import app.core.annotation.MenuItem;
import app.core.annotation.Permission;
import app.core.domain.model.Function;
import app.core.domain.model.Role;
import app.core.domain.model.RoleFunction;
import app.core.domain.model.RoleFunctionPK;
import app.core.domain.model.User;
import app.core.domain.model.UserRole;
import app.core.domain.model.UserRoleId;
import app.core.registry.Module;
import app.core.usermgmt.service.UserMgmtService;
import app.core.utils.AppConstant;
import app.core.utils.MD5Utils;

@Component("UserMgmtModule")
@Menu({ @MenuItem(id = 2000L, sortOrder = 20, isParent = true, parentId = AppConstant.MENU_HOME_ID, name = "User Management", description = "User Setup", function = "")})
public class UserMgmtModule extends Module {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserMgmtModule.class);

	@Permission(name = "Secured Landing Page", path = "/secured")
	public static final String FUNC_LANDING = "CU00.LANDING";
	
	@Autowired
	private UserMgmtService userMgmtService;

	@Value("${usermgmt.default.admin.firstname}")
	private String defaultAdminFirstName;

	@Value("${usermgmt.default.admin.lastname}")
	private String defaultAdminLastName;

	@Value("${usermgmt.default.admin.username}")
	private String defaultAdminUsername;

	@Value("${usermgmt.default.admin.password}")
	private String defaultAdminPassword;

	@Value("${usermgmt.default.admin.passwordexpiryday}")
	private Long defaultAdminPasswordExpiryDay;

	@Value("${usermgmt.default.admin.maxinvalidlogincount}")
	private Long defaultAdminMaxInvalidLoginCount;

	@Value("${usermgmt.default.admin.sessiontimeoutinminute}")
	private Long defaultAdminSessionTimeoutInMinute;

	@Value("${usermgmt.default.admin.role}")
	private String defaultAdminRoleName;

	@Value("${usermgmt.default.admin.function.list}")
	private String defaultAdminFunctionList;

	@Override
	protected void init() throws Exception {
		// Initialization
		userMgmtService.initContextVarType();
		userMgmtService.resetConnectedFlag();

		// Create role
		Long roleId = -1L;
		if (!StringUtils.isEmpty(defaultAdminRoleName)) {
			Role role = new Role();
			role.setName(defaultAdminRoleName);
			role.setDescription(defaultAdminRoleName);
			role.setSortOrder(1L);
			roleId = userMgmtService.createRoleIfNotFound(role);
			LOGGER.info("Default system role created ? Role Id = " + roleId);
		}

		// Create role function
		if (roleId != -1L) {
			if (!StringUtils.isEmpty(defaultAdminFunctionList)) {
				String[] functionList = defaultAdminFunctionList.split(",");
				for (String function : functionList) {
					RoleFunctionPK roleFunctionPK = new RoleFunctionPK();
					roleFunctionPK.setFunctionCode(function);
					roleFunctionPK.setRoleId(roleId);
					RoleFunction roleFunction = new RoleFunction();
					roleFunction.setPk(roleFunctionPK);
					roleFunction.setFunction(new Function(function));
					roleFunction.setRole(new Role(roleId));
					roleFunction.setReadable(true);
					roleFunction.setCreateable(true);
					roleFunction.setUpdateable(true);
					roleFunction.setDeleteable(true);
					roleFunctionPK = userMgmtService.createRoleFunctionIfNotFound(roleFunction);
					if (roleFunctionPK != null) {
						LOGGER.info("Default system role function created. Role Id = " + roleFunctionPK.getRoleId()
								+ ", Function Code = " + roleFunctionPK.getFunctionCode());
					} else {
						LOGGER.info("Default system role function NOT created !");
					}
				}
			}
		}

		// Create user
		User user = new User();
		user.setUsername(defaultAdminUsername);
		String salt = MD5Utils.getNewSaltValue();
		user.setSalt(salt);
		user.setPassword(MD5Utils.encrypt(salt, defaultAdminPassword));
		user.setDateFormat("dd/MM/yyyy");
		user.setConnectedFlag(false);
		user.setActiveFlag(true);
		user.setFirstName(defaultAdminFirstName);
		user.setLastName(defaultAdminLastName);
		user.setForceChangePwdFlag(false);
		user.setPwdExpiryDays(defaultAdminPasswordExpiryDay);
		user.setInvalidLoginCount(0L);
		user.setMaxInvalidLoginCount(defaultAdminMaxInvalidLoginCount);
		user.setSessionTimeoutMinutes(defaultAdminSessionTimeoutInMinute);
		user.setCreateUserId(0L);
		user.setCreateDate(new Date());
		Long userId = userMgmtService.createUserIfNotFound(user);
		LOGGER.info("Default system user created ? User Id = " + userId);

		// Create user role
		if (roleId != -1L && userId != -1L) {
			UserRoleId userRolePK = new UserRoleId();
			userRolePK.setRoleId(roleId);
			userRolePK.setUserId(userId);
			UserRole userRole = new UserRole();
			userRole.setId(userRolePK);
			userRole.setRole(new Role(roleId));
			userRole.setUser(new User(userId));
			userRole.setStartDate(new Date());
			userRolePK = userMgmtService.createUserRoleIfNotFound(userRole);
			LOGGER.info("Default system user role created ? User Id = " + userRolePK.getUserId() + ", Role Id = "
					+ userRolePK.getRoleId());
		} else {
			LOGGER.info("Default system user role NOT created !");
		}
	}

	@Override
	public String getModuleName() {
		return "[CORE] User Management Module";
	}
}
