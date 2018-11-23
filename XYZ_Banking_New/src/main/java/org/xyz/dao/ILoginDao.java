package org.xyz.dao;

import org.xyz.model.CustomerBean;
import org.xyz.model.LoginBean;

public interface ILoginDao {

	public CustomerBean isValidLogin(LoginBean loginBean);
}
