package org.xyz.service;

import org.xyz.dao.IRegisterDao;
import org.xyz.dao.RegisterDaoImpl;
import org.xyz.model.CustomerBean;

public class RegisterServiceImpl implements IRegisterService{
	IRegisterDao registerDao = new RegisterDaoImpl();
	
	@Override
	public boolean registerCustomer(CustomerBean customerBean) {
		return registerDao.registerCustomer(customerBean);
	}
}
