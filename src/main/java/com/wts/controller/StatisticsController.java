package com.wts.controller;

import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.wts.common.model.Department;
import com.wts.service.BankService;
import com.wts.service.DepartmentService;
import com.wts.service.LoanService;
import com.wts.service.TypeService;

import java.util.List;

/**
 * StatisticsController class
 *
 * @author wts
 * @date 2019/12/6
 */
public class StatisticsController extends Controller {
    @Inject
    LoanService loanService;
    @Inject
    DepartmentService departmentService;
    @Inject
    BankService bankService;
    @Inject
    TypeService typeService;
    public void get() {
        setAttr("departmentList", departmentService.getAll());
        String [][][]count=new String[19][21][6];
        for (int i = 0; i < count.length; i++) {
            for (int j = 0; j < count[i].length; j++) {
                for (int k = 0; k < count[i][j].length; k++) {
                    count[i][j][k] = loanService.count(i, j, k);
                }
            }
        }
        String [][][]money=new String[19][21][6];
        for (int i = 0; i < money.length; i++) {
            for (int j = 0; j < money[i].length; j++) {
                for (int k = 0; k < money[i][j].length; k++) {
                    money[i][j][k] = loanService.money(i, j, k);
                }
            }
        }
        setAttr("count", count);
        setAttr("money", money);
        render("/print/Statistics.html");
    }
}
