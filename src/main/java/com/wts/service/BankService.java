package com.wts.service;

import com.wts.common.model.Bank;

import java.util.List;

public class BankService {
    private Bank dao = new Bank().dao();

    public List<Bank> getAll() {
        return dao.find( "SELECT * FROM bank");
    }
}
