package module.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import app.core.annotation.Menu;
import app.core.annotation.MenuItem;
import app.core.annotation.Permission;
import app.core.registry.Module;
import app.core.utils.AppConstant;
import module.setup.service.StateService;

@Component("SetupModule")
@DependsOn({ "MenuMgmtModule" })
@Menu({ @MenuItem(id = 3000L, sortOrder = 30, isParent = true, parentId = AppConstant.MENU_HOME_ID, name = "Setup", description = "Setup", function = ""),
	@MenuItem(id = 3001L, sortOrder = 1, isParent = true, parentId = 3000L, name = "State", description = "State", function = ""),
	@MenuItem(id = 3001L, sortOrder = 1, isParent = true, parentId = 3000L, name = "Ethnic", description = "Ethnic", function = "")
})
public class SetupModule extends Module {
	
	// MD: Master Data
	
	@Permission(name = "State", path = "/secured/setup/state")
	public static final String FUNC_SETUP_STATE = "MD01.STATE";
	
	@Permission(name = "Ethnic", path = "/secured/setup/ethnic")
	public static final String FUNC_SETUP_ETHNIC = "MD01.ETHNIC";

	@Autowired
	private StateService stateService;

	@Override
	protected void init() throws Exception {
		stateService.initState();
	}

	@Override
	public String getModuleName() {
		return "[MODULE] Master Data Setup Module";
	}
}
