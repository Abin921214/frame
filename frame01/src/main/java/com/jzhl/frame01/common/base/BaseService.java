package com.jzhl.frame01.common.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzhl.frame01.common.bean.Pages;
import com.jzhl.frame01.common.bean.ServiceResult;
import com.jzhl.frame01.common.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 基础公共 service
 * @param <Model>
 * @param <PK>
 * @param <E>
 * @author xiaobin
 */
public abstract class BaseService<Model extends BaseEntity, PK extends Integer, E extends BaseExample> {

    @Autowired(required = false)
    public MyBatisBaseDao<Model, PK, E> dao;

    /**
     * 数据分页查询
     * @param page 分页查询参数
     * @param example  分页条件
     * @param isBlObs  是否大字段查询  大字段【true】
     * @param isReally 真分页查询还是假分页查询【假分页查询，先按条件全部查询数据，在分页】
     * @return
     * @throws Exception
     */
    public ServiceResult selectPage(Pages<Model> page, E example, Boolean isBlObs, Boolean isReally) throws Exception{
        try{
            if(page.getNowPage() <= 0){
                return new ServiceResult("当前页应大于零");
            }

            if(isReally){

                page.setSumRow((int)dao.countByExample(example));
                example.setLimit(page.getPageSize());
                example.setOffset(page.getStartLimit().longValue());

                List<Model> tempList = null;
                if(isBlObs){
                    tempList =  dao.selectByExampleWithBLOBs(example);
                }else{
                    tempList =  dao.selectByExample(example);
                }

                page.setList(tempList);
            }else{

                page.setSumRow((int)dao.countByExample(example));
                PageHelper.startPage(page.getNowPage(), page.getPageSize());

                List<Model> tempList = null;
                if(isBlObs){
                    tempList =  dao.selectByExampleWithBLOBs(example);
                }else{
                    tempList =  dao.selectByExample(example);
                }

                //用PageInfo对结果进行包装
                PageInfo<Model> pageInfo = new PageInfo<Model>(tempList);
                page.setList(tempList);
            }
            return new ServiceResult("查询成功",page);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 单个查询 根据条件查询
     * @param example  查询条件
     * @param isBlObs  是否大字段查询  大字段【true】
     * @return
     * @throws Exception
     */
    public ServiceResult selectOne(E example, Boolean isBlObs) throws Exception{
        try {
            List<Model> tempList = null;
            if(isBlObs){
                tempList =  dao.selectByExampleWithBLOBs(example);
            }else{
                tempList =  dao.selectByExample(example);
            }

            if(MyUtil.isNotEmpty(tempList) && tempList.size() > 0){
                return new ServiceResult("查询成功",tempList.get(0));
            }else{
                return new ServiceResult("查询失败,数据不存在");
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 根据ID 单个查询
     * @param id id查询
     * @return 数据
     * @throws Exception
     */
    public ServiceResult selectOne(PK id) throws Exception{
        try {
            if(MyUtil.isEmpty(id)){
                return new ServiceResult("查询失败,id不存在");
            }
            Model temp = dao.selectByPrimaryKey(id);
            if(temp == null){
                return new ServiceResult("查询失败,数据不存在");
            }

            String delFlag = temp.getDelFlag();
            if("0".equals(delFlag)){
                return new ServiceResult("查询成功",temp);
            }else{
                return new ServiceResult("查询失败,数据不存在");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * list查询
     * @param example  查询条件
     * @param isBlObs  是否大字段查询  大字段【true】
     * @return
     * @throws Exception
     */
    public ServiceResult selectList(E example, Boolean isBlObs) throws Exception{
        try {
            List<Model> tempList = null;
            if(isBlObs){
                tempList = dao.selectByExampleWithBLOBs(example);
            }else{
                tempList = dao.selectByExample(example);
            }
            return new ServiceResult("查询成功",tempList);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 查询数量
     * @param example 查询条件
     * @return
     * @throws Exception
     */
    public ServiceResult selectCount(E example) throws Exception{
        try{
            return new ServiceResult("操作成功",(int)dao.countByExample(example));
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 插入单条数据   如果需要获取insert 成功之后的id 则需要在 对应的 mapper xml中找寻到 insert语句 添加 useGeneratedKeys="true" keyProperty="id"
     * @param model 数据
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult insert(Model model) throws Exception{
        try {

            model.setBuildTime(new Date());

            int count = dao.insertSelective(model);
            if(count == 1){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * 修改数据 根据条件批量修改数据 无version乐观锁判定
     * @param model 数据
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult update(Model model, E example) throws Exception{
        try {

            model.setUpdateTime(new Date());

            int count = dao.updateByExampleSelective(model, example);
            if(count > 0){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 修改数据 根据id version乐观锁判定
     * @param model 数据
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult updateById(Model model) throws Exception{
        try {

            if(MyUtil.isEmpty(model.getId())){
                return new ServiceResult("参数错误, ID不能为空");
            }

            model.setUpdateTime(new Date());

            //如果使用到-乐观锁操作。
            if(!MyUtil.isEmpty(model.getVersion())){
                ServiceResult<Model> serviceResult = selectOne((PK)model.getId());
                if(serviceResult.getSuccess()){
                    if(!serviceResult.getData().getVersion().equals(model.getVersion())){
                        return new ServiceResult("数据已被修改, 请刷新页面在尝试操作");
                    }
                    model.setVersion(serviceResult.getData().getVersion() +1);
                }else{
                    return new ServiceResult("目标数据不存在");
                }
            }

            int count = dao.updateByPrimaryKeySelective(model);
            if(count == 1){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 启用禁用数据 根据id version乐观锁判定
     * @param model   启用禁用对象
     * @param isEnable 是否启用 启用 true, 禁用 false
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult enableAndForbidden(Model model, Boolean isEnable) throws Exception{
        try {

            if(MyUtil.isEmpty(model.getId())){
                return new ServiceResult("参数错误, ID不能为空");
            }

            model.setUpdateTime(new Date());
            if(isEnable){
                model.setStatus("0");
            }else{
                model.setStatus("1");
            }

            //如果使用到-乐观锁操作。
            if(!MyUtil.isEmpty(model.getVersion())){
                ServiceResult<Model> serviceResult = selectOne((PK)model.getId());
                if(serviceResult.getSuccess()){
                    if(!serviceResult.getData().getVersion().equals(model.getVersion())){
                        return new ServiceResult("数据已被修改, 请刷新页面在尝试操作");
                    }
                    model.setVersion(serviceResult.getData().getVersion() +1);
                }else{
                    return new ServiceResult("目标数据不存在");
                }
            }

            int count = dao.updateByPrimaryKeySelective(model);
            if(count == 1){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 启用禁用数据 根据条件批量操作  无version乐观锁判定
     * @param model   启用禁用对象
     * @param example 启用禁用条件
     * @param isEnable 是否启用 启用 true, 禁用 false
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult batchEnableAndForbidden(Model model, E example, Boolean isEnable) throws Exception{
        try {

            model.setUpdateTime(new Date());
            if(isEnable){
                model.setStatus("0");
            }else{
                model.setStatus("1");
            }

            int count = dao.updateByExampleSelective(model, example);
            if(count > 0){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 删除数据(伪删除) 根据id  version乐观锁判定
     * @param model   删除对象
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult delete(Model model) throws Exception{
        try {

            if(MyUtil.isEmpty(model.getId())){
                return new ServiceResult("参数错误, ID不能为空");
            }

            model.setUpdateTime(new Date());
            model.setDelFlag("1");

            //如果使用到-乐观锁操作。
            if(!MyUtil.isEmpty(model.getVersion())){
                ServiceResult<Model> serviceResult = selectOne((PK)model.getId());
                if(serviceResult.getSuccess()){
                    if(!serviceResult.getData().getVersion().equals(model.getVersion())){
                        return new ServiceResult("数据已被修改, 请刷新页面在尝试操作");
                    }
                    model.setVersion(serviceResult.getData().getVersion() +1);
                }else{
                    return new ServiceResult("目标数据不存在");
                }
            }

            int count = dao.updateByPrimaryKeySelective(model);
            if(count == 1){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 删除数据(伪删除)  按条件操作批量操作 无version乐观锁判定
     * @param model   删除对象
     * @param example 删除条件
     * @return
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult batchDelete(Model model, E example) throws Exception{
        try {

            model.setUpdateTime(new Date());
            model.setDelFlag("1");

            int count = dao.updateByExampleSelective(model, example);
            if(count > 0){
                return new ServiceResult("操作成功",model);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 删除数据(真删除) 无version乐观锁判定 根据ID判定
     * @param id 删除条件
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public ServiceResult deleteTrue(PK id) throws Exception{
        try {
            int count = dao.deleteByPrimaryKey(id);
            if(count == 1){
                return new ServiceResult("操作成功",true);
            }else{
                return new ServiceResult("操作失败,数据异常");
            }
        }catch (Exception e){
            throw e;
        }
    }


}
