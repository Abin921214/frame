package com.jzhl.frame01.common.base;

import com.jzhl.frame01.common.base.BaseEntity;
import com.jzhl.frame01.common.base.BaseExample;
import com.jzhl.frame01.common.bean.BaseResult;
import com.jzhl.frame01.common.bean.Pages;

import java.util.List;

/**
 * 实现在 公共 base congtroller中对数据处理需要用到的方法
 * @author xiaobin
 */
public interface BaseOperation<Model extends BaseEntity, Example extends BaseExample>{

    /**
     * 用作初始化查询条件【不在事务的保护范围之内】
     */
    public Example selectBefore(Model model) throws Exception;

    /**
     * 查询之后的操作，可以对数据操作  适用于一个  【不在事务的保护范围之内】
     */
    public void selectAfter(Model model) throws Exception;

    /**
     * 查询之后的操作，可以对数据操作  适用于List 【不在事务的保护范围之内】
     */
    public void selectAfter(List<Model> models) throws Exception;

    /**
     * 查询之后的操作，可以对数据操作  适用于分页  【不在事务的保护范围之内】
     */
    public void selectAfter(Pages<Model> pages) throws Exception;

    /**
     * 添加项目前进行的操作 【不在事务的保护范围之内】
     * @param model
     */
    public BaseResult addBefore(Model model) throws Exception;

    /**
     * 添加项目后进行的操作 【不在事务的保护范围之内】
     * @param model
     */
    public void addAfter(Model model) throws Exception;

    /**
     * 更新前项目进行的操作 【不在事务的保护范围之内】
     * @param model
     */
    public BaseResult updateBefore(Model model) throws Exception;

    /**
     * 更新后项目进行的操作 【不在事务的保护范围之内】
     * @param model
     */
    public void updateAfter(Model model) throws Exception;

    /**
     * 删除前验证【更新删除】 【不在事务的保护范围之内】
     */
    public BaseResult  deleteBefore(Model model) throws Exception;

    /**
     * 删除之后的操作【更新删除】 【不在事务的保护范围之内】
     */
    public void deleteAfter(Model model) throws Exception;

    /**
     * 删除前验证【彻底从数据库中删除】 【不在事务的保护范围之内】
     */
    public BaseResult  deleteTrueCheck(Integer id) throws Exception;

    /**
     * 删除之后的操作【彻底从数据库中删除】 【不在事务的保护范围之内】
     */
    public void deleteTrueAfter() throws Exception;

}
