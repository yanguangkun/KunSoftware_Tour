package com.kunsoftware;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.kunsoftware.entity.SysMenu;
import com.kunsoftware.service.MenuService;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:applicationContext.xml")
public class MenuServiceTest {

	@Autowired
	private MenuService menuService;
	
	@Test
	public void selectByPrimaryKey(){  

		//SysMenu sysMenu = menuService.selectByPrimaryKey(1);
		
		//System.out.println(sysMenu);
	}
}
