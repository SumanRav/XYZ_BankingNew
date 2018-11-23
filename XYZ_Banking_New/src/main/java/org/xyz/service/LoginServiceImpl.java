package org.xyz.service;

import org.xyz.dao.ILoginDao;
import org.xyz.dao.LoginDaoImpl;
import org.xyz.model.CustomerBean;
import org.xyz.model.LoginBean;

public class LoginServiceImpl implements ILoginService{

	private ILoginDao loginDao=new LoginDaoImpl();
	
	@Override
	public CustomerBean isValidLogin(LoginBean loginBean) {
		return loginDao.isValidLogin(loginBean);
	}

}
