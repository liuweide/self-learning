package com.itheima.service.impl;

import com.itheima.dao.IAccountDao;
import com.itheima.factory.BeanFactory;
import com.itheima.service.IAccountService;

/**
 * 账户的业务层实现类
 */
public class AccountServiceImpl implements IAccountService {

//    private IAccountDao accountDao = new AccountDaoImpl();

    private IAccountDao accountDao;

//    private int i = 1;

    @Override
    public void  saveAccount(){
        accountDao = (IAccountDao)BeanFactory.getBean("accountDao");
        int i = 1;
        accountDao.saveAccount();
        System.out.println(i);
        i++;
    }
}
