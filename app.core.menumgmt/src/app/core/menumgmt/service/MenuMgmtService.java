package app.core.menumgmt.service;

import java.util.List;

import com.github.dandelion.datatables.core.ajax.DataSet;
import com.github.dandelion.datatables.core.ajax.DatatablesCriterias;

import app.core.domain.model.Function;
import app.core.domain.model.MenuItem;
import app.core.exception.BaseApplicationException;
import app.core.menumgmt.dto.MenuItemDTO;

public interface MenuMgmtService {

	// Grid
	public DataSet<MenuItemDTO> getMenuItem(DatatablesCriterias criterias) throws BaseApplicationException;

	// New
	public Long createMenu(MenuItem menuItem);

	public List<MenuItem> getMenuItem();

	// Edit
	public MenuItem getMenuEdit(Long menuId);

	public void updateMenu(MenuItem menuItem);

	// Delete
	public void deleteMenu(MenuItem menuItem);

	public MenuItem getMenuItemByFunction(Function function);
}
