package com.eqvypay.service.moneymanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eqvypay.persistence.IPersonalActivity;
import com.eqvypay.persistence.PersonalActivity;

@Service
public class MoneyManagerFactory {

    // static instance of MoneyManage factory class.
    private static MoneyManagerFactory moneyManagerFactory = null;

    // reference of the money manager data manipulation interface.
    @Autowired
    private IMoneyManagerDataManipulation managerDataManipulation;

    // reference of the money manager repository class.
    @Autowired
    private MoneyManagerRepository moneyManagerRepository;

    // reference of the personal activity class.
    private IPersonalActivity personalActivity;

    // returns the instance of the MoneyManagerFactory class.
    public static MoneyManagerFactory getInstance() {
        if (moneyManagerFactory == null) {
            moneyManagerFactory = new MoneyManagerFactory();
        }
        return moneyManagerFactory;
    }

    // return the reference of the MoneyManageFactory class.
    // this is the lazy method.
    public static MoneyManagerFactory getMoneyManagerFactory() {
        return moneyManagerFactory;
    }

    // return the reference of the IMoneyManagerDataManipulation interface.
    public IMoneyManagerDataManipulation getManagerDataManipulation() {
        return managerDataManipulation;
    }

    // return the reference of the MoneyManagerRepository class.
    public MoneyManagerRepository getMoneyManagerRepository() {
        return moneyManagerRepository;
    }

    // return the reference of the IPersonalActivity.
    public IPersonalActivity getPersonalActivity() {
        personalActivity = new PersonalActivity();
        return personalActivity;
    }

}
