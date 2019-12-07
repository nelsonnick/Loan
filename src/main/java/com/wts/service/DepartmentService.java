package com.wts.service;

import com.wts.common.model.Department;

import java.util.List;

public class DepartmentService {
    private Department dao = new Department().dao();

    public List<Department> getAll() {
        return dao.find( "SELECT * FROM department");
    }
}
