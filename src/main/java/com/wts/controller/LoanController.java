package com.wts.controller;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.tx.Tx;
import com.wts.common.model.Change;
import com.wts.common.model.Loan;
import com.wts.common.model.User;
import com.wts.interceptor.LoginInterceptor;
import com.wts.service.LoanService;
import com.wts.util.IDNumber;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * LoanController class
 *
 * @author wts
 * @date 2019/12/6
 */
public class LoanController extends Controller {
    @Inject
    LoanService loanService;
    private static Logger logger = Logger.getLogger(Loan.class);
    @Before({Tx.class, LoginInterceptor.class})
    public void query() {
        renderJson(Db.paginate(
                getParaToInt("pageCurrent"),
                getParaToInt("pageSize"),
                loanService.getSelectSql(),
                loanService.getFromSql() +
                        loanService.getWhereSql(get("queryStr")) +
                        " ORDER BY loan.id DESC").getList());
    }
    @Before({Tx.class, LoginInterceptor.class})
    public void total() {
        Long count = Db.queryLong("SELECT COUNT(*)" + loanService.getFromSql() +
                loanService.getWhereSql(get("queryStr")));
        renderText(count.toString());
    }
    @Before({Tx.class, LoginInterceptor.class})
    public void get() {
        renderJson(loanService.findById(getParaToInt("id")));
    }
    @Before({Tx.class, LoginInterceptor.class})
    public void add() {
        JSONObject form = JSONObject.parseObject(get("form"));
        if (!IDNumber.availableIDNumber(form.get("number").toString())){
            renderText("身份证号码错误，请核实");
        }else{
            Loan loan = loanService.getLoanByFrom(get("form"));
            loan.save();
            Change change = new Change();
            change.set("loan_id",loan.getId())
                    .set("user_id",((User) getSessionAttr("user")).getId())
                    .set("before","")
                    .set("after",loan.toJson())
                    .set("time",new Date())
                    .save();
            renderText("OK");
        }

    }
    @Before({Tx.class, LoginInterceptor.class})
    public void edit() {
        Loan  loan = loanService.findById(getParaToInt("id"));
        JSONObject form = JSONObject.parseObject(get("form"));
        if (loan == null) {
            renderText("要修改的贷款信息 不存在！");
        } else {
            if (!IDNumber.availableIDNumber(form.get("number").toString())){
                renderText("身份证号码错误，请核实");
            }else{
                Change change = new Change();
                change.set("loan_id",loan.getId())
                        .set("user_id",((User) getSessionAttr("user")).getId())
                        .set("before",loan.toJson())
                        .set("after",loanService.getLoanByFrom(get("form")).toJson())
                        .set("time",new Date()).save();
                loan = loanService.getLoanByFrom(get("form"));
                loan.set("id",getParaToInt("id")).update();
                renderText("OK");
            }
        }
    }
    @Before({Tx.class, LoginInterceptor.class})
    public void del() {
        loanService.del(getParaToInt("id"));
        renderText("OK");
    }

}
