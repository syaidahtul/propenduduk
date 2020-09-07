package module.setup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import app.core.annotation.Menu;
import app.core.annotation.MenuItem;
import app.core.registry.Module;
import app.core.utils.AppConstant;
import module.setup.service.StateService;

@Component("SetupModule")
@DependsOn({ "MenuMgmtModule" })
@Menu({ @MenuItem(id = 3000L, sortOrder = 30, isParent = true, parentId = AppConstant.MENU_HOME_ID, name = "Setup", description = "Setup", function = "")

})
public class SetupModule extends Module {

	@Value("${setup.state.sabah.name}")
	private String SABAH;

	@Value("${setup.state.sabah.name}")
	private String MALAYSIA;

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
