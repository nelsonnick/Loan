SELECT
	loan.id AS '序号',
	department.name AS '所属部门',
	loan.name AS '人员姓名',
	loan.number AS '证件号码',
	loan.birth AS '出生日期',
    CASE loan.sex WHEN '0' THEN '未知' WHEN '1' THEN '男' WHEN '2' THEN '女' ELSE '性别错误' END AS '人员性别',
	loan.phone AS '联系电话',
	loan.business AS '公司名称',
	loan.address AS '经营地点',
	loan.project AS '经营项目',
	loan.money AS '贷款金额',
	loan.month AS '贷款期限',
	loan.base AS '贴息比例',
    CASE loan.state WHEN '0' THEN '未处理' WHEN '1' THEN '被退回' WHEN '2' THEN '已审批' WHEN '3' THEN '已放款' WHEN '4' THEN '已回款' ELSE '状态错误' END AS '贷款状态',
    CASE loan.marriage WHEN '1' THEN '未婚' WHEN '2' THEN '已婚' WHEN '3' THEN '离异' WHEN '4' THEN '丧偶' ELSE '婚姻错误' END AS '婚姻状态',
	loan.spouse AS '配偶姓名',
	loan.phone2 AS '配偶电话',
	loan.time AS '申请日期',
	bank.name AS '放款银行',
	type.name AS '申请类型',
	loan.remark AS '备注'
FROM loan LEFT JOIN department ON loan.department_id = department.id LEFT JOIN bank ON loan.bank_id = bank.id LEFT JOIN type ON loan.type_id = type.id