package module.setup.service;

import java.util.List;

import app.core.domain.model.State;
import module.setup.dto.StateDTO;

public interface StateService {
	
	public void initState();

	public List<State> getStates();
	
	public State findOneByCode(String stateCode);
	
	public List<StateDTO> findAll();
}
