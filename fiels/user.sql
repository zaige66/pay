######################### 用户表
-- 创建数据库
CREATE  DATABASE pay;
USE  pay;
-- 创建表
CREATE TABLE pay_user(
  `uid` int(12) AUTO_INCREMENT,
  `username` VARCHAR(20) NOT NULL ,
  `password` VARCHAR(32) NOT NULL ,
  `money` DOUBLE DEFAULT "0" COMMENT '余额',

  primary KEY (id)
)ENGINE=innoDB DEFAULT CHARSET=utf8;
-- 插入测试数据
INSERT into pay_user values
(0,"admin","123456",100);

SELECT * from pay_user;


######################## 订单表
USE pay;
CREATE TABLE order_record(
  keyid INT(12) AUTO_INCREMENT,
  orderId VARCHAR(50) NOT NULL COMMENT '订单号',
  customerId int(12) NOT NULL COMMENT '商户id',
  customerOrderId VARCHAR(50) NOT NULL COMMENT '商户传来的订单号',
  uid int(12) NOT NULL COMMENT '用户id',
  money DOUBLE COMMENT '订单金额',
  state TINYINT(4) COMMENT '订单状态 0：未支付 1：已支付 2：已完成 3：支付失败',
  createDate DATETIME COMMENT '订单创建时间',
  payTime DATETIME COMMENT '支付时间',

  PRIMARY KEY (keyid)
)ENGINE=innoDB DEFAULT CHARSET=utf8;


####################### 客户表
USE pay;
CREATE TABLE customer (
  customerId int(12) NOT NULL COMMENT '商户id',
  customerName VARCHAR(50) NOT NULL COMMENT '商户名称',
  primaryKey VARCHAR(50) NOT NULL COMMENT '商户的凭证key',
  redirectUrl VARCHAR(100) NOT NULL COMMENT '商户回调地址',

  PRIMARY KEY (customerId)
)ENGINE=innoDB DEFAULT CHARSET=utf8;