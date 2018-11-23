package org.xyz.service;

import org.xyz.model.CustomerBean;
import org.xyz.model.LoginBean;

public interface ILoginService {

	public CustomerBean isValidLogin(LoginBean loginBean);
}
