package com.xbin.frametest.controller;

import com.alibaba.druid.util.MySqlUtils;
import com.xbin.frame.bean.BaseResult;
import com.xbin.frame.bean.Pages;
import com.xbin.frametest.model.SysUser;
import com.xbin.frametest.model.SysUserExample;
import com.xbin.frametest.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import com.xbin.frame.base.*;
import com.xbin.frame.utils.*;

@RestController
@RequestMapping("/test")
@Api(tags = "Test接口")
public class SysUserController extends ApiBaseControlller<SysUser, SysUserExample, SysUserService>  implements BaseOperation<SysUser, SysUserExample> {

    @Override
    public SysUserExample selectBefore(SysUser sysUser) throws Exception {
        SysUserExample sysUserExample = new SysUserExample();
        SysUserExample.Criteria criteria = sysUserExample.createCriteria();
        criteria.andDelFlagEqualTo("0");
        return sysUserExample;
    }

    @Override
    public void selectAfter(SysUser sysUser) throws Exception {

    }

    @Override
    public void selectAfter(List<SysUser> sysUsers) throws Exception {

    }

    @Override
    public void selectAfter(Pages<SysUser> pages) throws Exception {

    }

    @Override
    public BaseResult addBefore(SysUser sysUser) throws Exception {
        return null;
    }

    @Override
    public void addAfter(SysUser sysUser) throws Exception {

    }

    @Override
    public BaseResult updateBefore(SysUser sysUser) throws Exception {
        return null;
    }

    @Override
    public void updateAfter(SysUser sysUser) throws Exception {

    }

    @Override
    public BaseResult deleteBefore(SysUser sysUser) throws Exception {
        return null;
    }

    @Override
    public void deleteAfter(SysUser sysUser) throws Exception {

    }

    @Override
    public BaseResult deleteTrueCheck(Integer id) throws Exception {
        return null;
    }

    @Override
    public void deleteTrueAfter() throws Exception {

    }
}
