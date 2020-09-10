package app.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import app.core.domain.service.DomainInitializationService;
import app.core.registry.Module;

@Component("DomainModule")
public class DomainModule extends Module {

	@Autowired
	private DomainInitializationService service;

	@Value("${domain.default.role.list}")
	private String defaultRoles;
	
	@Override
	protected void init() throws Exception {
		service.initAppRoles(defaultRoles);
	}

	@Override
	public String getModuleName() {
		return "[CORE] Domain Module ";
	}

}