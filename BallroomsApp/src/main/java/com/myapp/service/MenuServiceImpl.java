package com.myapp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.exception.RecordNotFoundException;
import com.myapp.model.Menu;
import com.myapp.repository.MenuRepository;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

	private static final Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu findMenu(Long menuId) {
		Menu menu = menuRepository.findOne(menuId);
		return menu;
	}

	@Override
	public List<Menu> findAllMenus() {
		return menuRepository.findAll();
	}

	@Override
	public List<Menu> findBallroomMenus(Long ballroomId) {

		return menuRepository.findByBallroomId(ballroomId);
	}

	@Override
	public Menu add(Menu menu) {
		menuRepository.save(menu);
		return menu;
	}

	@Override
	public Menu update(Menu menu) {
		Menu existingMenu = menuRepository.findOne(menu.getId());
		if (existingMenu == null) {
			String errorMessage = "Menu with id " + menu.getId() + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}
		return menuRepository.save(menu);
	}

	@Override
	public void delete(Long menuId) {
		Menu menu = menuRepository.findOne(menuId);
		logger.debug("Delete menu with id: " + menuId);
		if (menu != null) {
			menuRepository.delete(menuId);
		} else {
			String errorMessage = "menu with id " + menuId + " not found";
			logger.error(errorMessage);
			throw new RecordNotFoundException(errorMessage);
		}

	}

}
