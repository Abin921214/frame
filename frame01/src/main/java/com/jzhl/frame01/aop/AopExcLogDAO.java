package com.jzhl.frame01.aop;

import com.jzhl.frame01.common.base.MyBatisBaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * AopExcLogDAO继承基类
 */
@Mapper
public interface AopExcLogDAO extends MyBatisBaseDao<AopExcLog, Integer, AopExcLogExample> {
}