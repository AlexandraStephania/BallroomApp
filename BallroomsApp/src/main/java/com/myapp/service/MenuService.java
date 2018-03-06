package com.myapp.service;

import java.util.List;

import com.myapp.model.Menu;

public interface MenuService {
	
      public Menu findMenu(Long menuId);
      
      public List<Menu> findAllMenus();
      
      public List<Menu> findBallroomMenus(Long ballroomId);
      
      public Menu add(Menu menu);
      
      public Menu update(Menu menu);
      
      public void delete(Long menuId);
      
      
}
