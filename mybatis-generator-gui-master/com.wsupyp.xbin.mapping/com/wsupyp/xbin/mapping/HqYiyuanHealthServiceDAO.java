package com.wsupyp.xbin.mapping;

import com.wsupyp.xbin.model.HqYiyuanHealthService;
import com.wsupyp.xbin.model.HqYiyuanHealthServiceExample;
import org.springframework.stereotype.Repository;

/**
 * HqYiyuanHealthServiceDAO继承基类
 */
@Repository
public interface HqYiyuanHealthServiceDAO extends MyBatisBaseDao<HqYiyuanHealthService, Integer, HqYiyuanHealthServiceExample> {
}