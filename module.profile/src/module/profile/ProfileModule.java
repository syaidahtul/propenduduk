package module.profile;

import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import app.core.annotation.Menu;
import app.core.annotation.MenuItem;
import app.core.registry.Module;
import app.core.utils.AppConstant;

@Component("ProfileModule")
@DependsOn({ "MenuMgmtModule" })
@Menu({ @MenuItem(id = 4000L, sortOrder = 40, isParent = true, parentId = AppConstant.MENU_HOME_ID, name = "Profile", description = "Profile", function = "")

})
public class ProfileModule extends Module {

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public String getModuleName() {
		return "[MODULE] User Profile";
	}

}
