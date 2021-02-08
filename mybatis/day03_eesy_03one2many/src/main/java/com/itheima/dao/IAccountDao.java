package com.itheima.dao;

import com.itheima.domain.Account;
import com.itheima.domain.AccountUser;

import java.util.List;

public interface IAccountDao {

    List<Account> findAll();

    List<AccountUser> findAllAccount();
}
