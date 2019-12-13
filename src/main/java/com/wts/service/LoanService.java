package com.wts.service;

import com.alibaba.fastjson.JSONObject;
import com.jfinal.plugin.activerecord.Db;
import com.wts.common.model.Loan;
import com.wts.util.IDNumber;

public class LoanService {
    private Loan dao = new Loan().dao();

    public Loan findById(int id) {
        return dao.findById(id);
    }

    public String getWhereSql(String json) {
        JSONObject queryStr = JSONObject.parseObject(json);
        String departmentSql="";
        String bankSql="";
        String typeSql="";
        String stateSql = "";
        String moneySql="";
        String monthSql = "";
        if (!queryStr.getString("department_id").equals("")){
            departmentSql = " AND department.id = '" + queryStr.get("department_id") + "' ";
        }
        if (!queryStr.getString("bank_id").equals("")){
            bankSql = " AND bank.id = '" + queryStr.get("bank_id") + "' ";
        }
        if (!queryStr.getString("type_id").equals("")){
            typeSql = " AND type.id = '" + queryStr.get("type_id") + "' ";
        }
        if (!queryStr.getString("state").equals("")){
            stateSql = " AND loan.state = '" + queryStr.get("state") + "' ";
        }
        if (!queryStr.getString("money").equals("")){
            moneySql = " AND loan.money = '" + queryStr.get("money") + "' ";
        }
        if (!queryStr.getString("month").equals("")){
            monthSql = " AND loan.month = '" + queryStr.get("month") + "' ";
        }
        return "WHERE " +
                "loan.name LIKE '%" + queryStr.get("name") + "%' " +
                "AND loan.number LIKE '%" +  queryStr.get("number") + "%' " +
                "AND loan.business LIKE '%" +  queryStr.get("business") + "%' " +
                "AND loan.phone LIKE '%" +  queryStr.get("phone") + "%' " +
        departmentSql + bankSql + typeSql + stateSql + moneySql + monthSql;
    }

    public String getFromSql(){
        return " FROM loan LEFT JOIN department ON loan.department_id = department.id LEFT JOIN bank ON loan.bank_id = bank.id  LEFT JOIN type ON loan.type_id = type.id ";
    }

    public String getSelectSql() {
        return "SELECT loan.id, loan.time, loan.money,loan.month,loan.base,loan.business,loan.address,loan.address,loan.project,loan.name,loan.number,loan.birth,loan.phone,loan.spouse,loan.phone2,loan.code,loan.startTime,loan.endTime," +
                "bank.name AS bank, type.name AS type, department.name AS department, " +
                "CASE loan.sex WHEN '0' THEN '未知' WHEN '1' THEN '男' WHEN '2' THEN '女' ELSE '性别错误' END AS sex," +
                "CASE loan.marriage WHEN '1' THEN '未婚' WHEN '2' THEN '已婚' WHEN '3' THEN '离异' WHEN '4' THEN '丧偶' ELSE '婚姻错误' END AS marriage," +
                "CASE loan.state WHEN '1' THEN '未处理' WHEN '2' THEN '被退回' WHEN '3' THEN '已审批' WHEN '4' THEN '已放款' WHEN '5' THEN '已还款' ELSE '状态错误' END AS state ";
    }

    public Loan getLoanByFrom(String json) {
        JSONObject form = JSONObject.parseObject(json);
        Loan loan = new Loan();
        loan.set("department_id",form.get("department_id"))
                .set("bank_id",form.get("bank_id"))
                .set("type_id",form.get("type_id"))
                .set("name",form.get("name"))
                .set("number",form.get("number"))
                .set("phone",form.get("phone"))
                .set("marriage",form.get("marriage"))
                .set("spouse",form.get("spouse"))
                .set("phone2",form.get("phone2"))
                .set("state",form.get("state"))
                .set("money",form.get("money"))
                .set("month",form.get("month"))
                .set("base",form.get("base"))
                .set("time",form.get("time"))
                .set("business",form.get("business"))
                .set("address",form.get("address"))
                .set("project",form.get("project"))
                .set("remark",form.get("remark"))
                .set("code",form.get("code"))
                .set("sex", IDNumber.getSex(form.get("number").toString()))
                .set("birth",IDNumber.getBirthDate(form.get("number").toString()));
        if (!form.get("startTime").equals("")){
            loan.set("startTime",form.get("startTime"));
        } else {
            loan.set("startTime","");
        }
        if (!form.get("endTime").equals("")){
            loan.set("endTime",form.get("endTime"));
        } else {
            loan.set("endTime","");
        }
        return loan;
    }

    public void del(int id){
        dao.deleteById(id);
    }

    private String getSql(int department_id, int money, int state){
        String sql="";
        if(department_id!=0|| money!=0||state!=0){
            sql = sql + "WHERE";
        }
        String departmentSql = "";
        if (department_id!=0){
            departmentSql = " department_id=" + department_id;
            if (sql.endsWith("WHERE")){
                sql = sql  + departmentSql;
            }else{
                sql = sql + " AND " + departmentSql;
            }
        }
        String moneySql = "";
        if (money!=0){
            moneySql = " money=" + money;
            if (sql.endsWith("WHERE")){
                sql = sql  + moneySql;
            }else{
                sql = sql + " AND " + moneySql;
            }
        }
        String stateSql = "";
        if (state!=0){
            stateSql = " state=" + state;
            if (sql.endsWith("WHERE")){
                sql = sql  + stateSql;
            }else{
                sql = sql + " AND " + stateSql;
            }
        }
        return sql;
    }

    public String count(int department_id, int money, int state){
        String sql = "SELECT COUNT(*) FROM loan " + getSql(department_id,money,state);
        return Db.queryLong(sql).toString();
    }
    public String money(int department_id, int money, int state){
        String sql = "SELECT SUM(money) FROM loan " + getSql(department_id,money,state);
        try {
            return Db.queryLong(sql).toString();
        }catch (NullPointerException ex){
            return "0";
        }
    }

}
