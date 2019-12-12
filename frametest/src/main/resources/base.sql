DROP TABLE IF EXISTS `aop_oper_log`;
CREATE TABLE `aop_oper_log` (
  `oper_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `oper_modul` varchar(64) NOT NULL COMMENT '功能模块',
  `oper_type` varchar(64) NOT NULL COMMENT '操作类型',
  `oper_desc` varchar(500) NOT NULL DEFAULT '' COMMENT '操作描述',
  `oper_requ_param` text COMMENT '请求参数',
  `oper_resp_param` text COMMENT '返回参数',

  `oper_method` varchar(255) NOT NULL DEFAULT '' COMMENT '操作方法',
  `oper_uri` varchar(255) NOT NULL DEFAULT '' COMMENT '操作URL',
  `oper_ip` varchar(64) NOT NULL DEFAULT '' COMMENT '操作IP',
  `oper_ver` varchar(64) NOT NULL DEFAULT '' COMMENT '操作版本',

  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0存在 1删除）',
  `build_userid` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `build_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_userid` int(11) NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',

  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='aop日志操作表';

DROP TABLE IF EXISTS `aop_exc_log`;
CREATE TABLE `aop_exc_log` (
  `exc_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `exc_requ_param` text COMMENT '请求参数',
  `exc_name` varchar(64) NOT NULL COMMENT '异常名称',
  `exc_message` text COMMENT '异常信息',

  `oper_method` varchar(255) NOT NULL DEFAULT '' COMMENT '操作方法',
  `oper_uri` varchar(255) NOT NULL DEFAULT '' COMMENT '操作URL',
  `oper_ip` varchar(64) NOT NULL DEFAULT '' COMMENT '操作IP',
  `oper_ver` varchar(64) NOT NULL DEFAULT '' COMMENT '操作版本',

  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `del_flag` char(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0存在 1删除）',
  `build_userid` int(11) NOT NULL DEFAULT '0' COMMENT '创建人id',
  `build_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `update_userid` int(11) NOT NULL DEFAULT '0' COMMENT '修改人id',
  `update_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `remark` varchar(500) NOT NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`exc_id`)
) ENGINE=InnoDB CHARSET=utf8 COMMENT='aop日志异常表';