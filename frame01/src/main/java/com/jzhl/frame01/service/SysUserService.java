package com.jzhl.frame01.service;

import com.jzhl.frame01.common.base.BaseService;
import com.jzhl.frame01.mapper.SysUserDAO;
import com.jzhl.frame01.model.SysUser;
import com.jzhl.frame01.model.SysUserExample;
import org.springframework.stereotype.Service;

/**
 * 用户service层
 * @author xiaobin
 */
@Service
public class SysUserService extends BaseService<SysUser, Integer, SysUserExample> {
}
