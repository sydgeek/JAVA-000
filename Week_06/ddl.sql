drop table if exists consumer;

/*==============================================================*/
/* Table: consumer                                            */
/*==============================================================*/
create table consumer
(
   id                   char(30) primary key not null comment '客户主键id',
   nickName             varchar(255) not null comment '昵称',
   gender               int(2) comment '性别',
   birthday             datetime comment '出生日期',
   telephoneNum         int not null comment '手机号码',
   registerAddrCode     int(6) not null comment '注册地区域编码'
);

drop table if exists goods;

/*==============================================================*/
/* Table: goods                                               */
/*==============================================================*/
create table goods
(
   id                   char(30) primary key not null comment '商品主键id',
   goodsType            char(2) comment '商品类型',
   goodsName            varchar(255) comment '商品名称',
   primeCost            double(18,2) comment '成本价，元',
   fixedPrice           double(18,2) comment '定价，元'
);

drop table if exists orders;

/*==============================================================*/
/* Table: orders                                               */
/*==============================================================*/
create table orders
(
   id                   char(30) primary key not null comment '订单id',
   consumerId           char(30) not null comment '用户id',
   goodsId              char(30) not null comment '商品id',
   payCost              double(18,2) not null comment '付款金额',
   businessTime         datetime comment '交易时间',
   remark               varchar(255) comment '备注'
);