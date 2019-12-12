package com.xbin.frametest.aop;

import com.xbin.frame.base.MyBatisBaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * AopExcLogDAO继承基类
 */
@Mapper
public interface AopExcLogDAO extends MyBatisBaseDao<AopExcLog, Integer, AopExcLogExample> {
}