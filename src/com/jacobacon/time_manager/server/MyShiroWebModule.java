package com.jacobacon.time_manager.server;

import javax.servlet.ServletContext;

import org.apache.shiro.config.Ini;
import org.apache.shiro.guice.web.ShiroWebModule;
import org.apache.shiro.realm.text.IniRealm;

import com.google.inject.Provides;

public class MyShiroWebModule extends ShiroWebModule{
	MyShiroWebModule(ServletContext sc) {
		super(sc);
	}
	
	@Override
	protected void configureShiroWeb() {
		
		
		
		
	}
	
	@Provides
	Ini loadShiroIni() {
		return Ini.fromResourcePath("classpath:shiro.ini");
		
	}

}
