package com.xbin.frametest.aop;

import org.apache.ibatis.annotations.Mapper;
import com.xbin.frame.base.MyBatisBaseDao;

/**
 * AopOperLogDAO继承基类
 */
@Mapper
public interface AopOperLogDAO extends MyBatisBaseDao<AopOperLog, Integer, AopOperLogExample> {
}