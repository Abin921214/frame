package com.jzhl.frame01.controller;

import cn.jiguang.common.resp.ResponseWrapper;
import com.jzhl.frame01.common.base.ApiBaseControlller;
import com.jzhl.frame01.common.base.BaseOperation;
import com.jzhl.frame01.common.bean.BaseResult;
import com.jzhl.frame01.common.bean.Pages;
import com.jzhl.frame01.common.callback.JpushCallBack;
import com.jzhl.frame01.common.service.JpushService;
import com.jzhl.frame01.model.SysUser;
import com.jzhl.frame01.model.SysUserExample;
import com.jzhl.frame01.service.SysUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 测试 controller
 * @author xiaobin
 */
@RestController
@RequestMapping("/test")
@Api(tags = "Test接口")
public class TestController extends ApiBaseControlller<SysUser, SysUserExample, SysUserService>  implements BaseOperation<SysUser, SysUserExample> {

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
