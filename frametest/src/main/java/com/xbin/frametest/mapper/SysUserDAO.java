package com.xbin.frametest.mapper;

import com.xbin.frametest.model.SysUser;
import com.xbin.frametest.model.SysUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * SysUserDAO继承基类
 */
@Mapper
public interface SysUserDAO extends com.xbin.frame.base.MyBatisBaseDao<SysUser, Integer, SysUserExample> {
}