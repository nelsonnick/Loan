package com.wts.service;

import com.wts.common.model.Type;

import java.util.List;

public class TypeService {
    private Type dao = new Type().dao();

    public List<Type> getAll() {
        return dao.find( "SELECT * FROM type");
    }
}
