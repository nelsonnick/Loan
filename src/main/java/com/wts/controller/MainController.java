package com.wts.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.wts.common.model.*;
import com.wts.interceptor.LoginInterceptor;
import com.wts.service.DepartmentService;
import com.wts.service.*;

import java.util.List;

/**
 * MainController class
 *
 * @author wts
 * @date 2019/12/6
 */
public class MainController extends Controller {
    @Inject
    UserService userService;
    @Inject
    DepartmentService departmentService;
    @Inject
    BankService bankService;
    @Inject
    TypeService typeService;
    public void index() {
        render("/login/index.html");
    }
    public void home() {
        render("/loan/dist/index.html");
    }
    public void login() {
        getResponse().addHeader("Access-Control-Allow-Origin", "*");
        User user = userService.goLogin(get("login"),get("pass"));
        if (user != null) {
            setSessionAttr("user", user);
            redirect("/home");
        } else {
            redirect("/");
        }
    }
    public void logout() {
        setSessionAttr("user", null);
        redirect("/");
    }
    public void getDepartment() {
        List<Department> departments = departmentService.getAll();
        String str = "";
        for (Department department : departments) {
            str = str + "{value: '" + department.getId() + "',label:'" + department.getName() + "'},";
        }
        str = str.substring(0, str.length() - 1);
        renderJson("[" + str + "]");
    }
    public void getBank() {
        List<Bank> banks =  bankService.getAll();
        String str = "";
        for (Bank bank : banks) {
            str = str + "{value: '" + bank.getId() + "',label:'" + bank.getName() + "'},";
        }
        str = str.substring(0, str.length() - 1);
        renderJson("[" + str + "]");
    }
    public void getType() {
        List<Type> types =  typeService.getAll();
        String str = "";
        for (Type type : types) {
            str = str + "{value: '" + type.getId() + "',label:'" + type.getName() + "'},";
        }
        str = str.substring(0, str.length() - 1);
        renderJson("[" + str + "]");
    }
    @Before(LoginInterceptor.class)
    public void getUser() {
        if (getSessionAttr("user").equals("") || getSessionAttr("user") == null) {
            renderText("无法识别");
        } else {
            renderJson(((User) getSessionAttr("user")));
        }
    }
    @Before(LoginInterceptor.class)
    public void getName() {
        renderText(((User) getSessionAttr("user")).getName());
    }
}
