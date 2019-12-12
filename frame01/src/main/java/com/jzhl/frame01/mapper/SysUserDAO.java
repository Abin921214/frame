package com.jzhl.frame01.mapper;

import com.jzhl.frame01.common.base.MyBatisBaseDao;
import com.jzhl.frame01.model.SysUser;
import com.jzhl.frame01.model.SysUserExample;
import org.apache.ibatis.annotations.Mapper;

/**
 * SysUserDAO继承基类
 */
@Mapper
public interface SysUserDAO extends MyBatisBaseDao<SysUser, Integer, SysUserExample> {
}