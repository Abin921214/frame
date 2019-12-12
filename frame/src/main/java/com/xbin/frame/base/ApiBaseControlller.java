package com.xbin.frame.base;

import com.xbin.frame.bean.BaseResult;
import com.xbin.frame.bean.Pages;
import com.xbin.frame.bean.ServiceResult;
import com.xbin.frame.utils.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 公共基础接口  基于 MyBatis gui 代码生成工具【restful接口风格】
 * @param <Model>
 * @param <Example>
 * @param <Service>
 * @author xiaobin
 * @author chenlong
 */
public class ApiBaseControlller<Model extends BaseEntity, Example extends BaseExample, Service extends BaseService> implements BaseOperation<Model, Example> {

    @Autowired(required = false)
    Service baseService;

    /**
     * restful 根据ID通用信息单个查询
     */
    @GetMapping("/{id}")
    @OperLog(operModul = "通用单个查询", operType = "select", operDesc = "通用单个查询")
    public BaseResult get(@PathVariable Integer id) throws Exception{
        try {
            if(MyUtil.isEmpty(id)){
                return new BaseResult(0,"查询失败,id不能为空");
            }
            ServiceResult<Model> result = baseService.selectOne(id);
            if(result.getSuccess()){
                Model m = result.getData();
                selectAfter(m);
                return new BaseResult<Model>(1,"ok", m);
            }
            return new BaseResult(0,result.getMsg());
        }catch (Exception e){
            throw  e;
        }
    }

    /**
     * restful 通用列表查询 根据是否传递 nowPage > 0判断是否分页
     */
    @GetMapping("/")
    @OperLog(operModul = "通用列表查询信息", operType = "select", operDesc = "通用列表查询信息")
    public BaseResult list(@ModelAttribute Pages<Model> page, @ModelAttribute Model model) throws Exception {
        try {
            //列表查询时加载与验证方法
            Example example = selectBefore(model);
            if(example == null){
                return new BaseResult(0,"查询条件未初始化");
            }

            //存在分页处理
            if(page != null){
                if(page.getNowPage() > 0){
                    ServiceResult<Model> result = baseService.selectPage(page,example,false,true);
                    page = result.getDataPage();

                    //列表查询后的操作
                    selectAfter(page.getList());

                    return new BaseResult<Pages<Model>>(1,"查询成功", page);
                }
            }

            ServiceResult serviceResult = baseService.selectList(example, false);
            if(!serviceResult.getSuccess()){
                return new BaseResult(0,serviceResult.getMsg());
            }

            List<Model> list = serviceResult.getDataList();

            //列表查询后的操作
            selectAfter(list);

            return new BaseResult<List<Model>>(1,"查询成功",list);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * restful 通用列表[大字段]查询 根据是否传递 nowPage > 0判断是否分页
     */
    @GetMapping("/blobs/")
    @OperLog(operModul = "通用列表查询信息【大字段查询】", operType = "select", operDesc = "通用列表查询信息【大字段查询】")
    public BaseResult listWithBlObs(@ModelAttribute Pages<Model> page, @ModelAttribute Model model) throws Exception {
        try {
            //列表查询时加载与验证方法
            Example example = selectBefore(model);
            if(example == null){
                return new BaseResult(0,"查询条件未初始化");
            }

            if(page != null){
                if(page.getNowPage() > 0){
                    ServiceResult<Model> result = baseService.selectPage(page,example,true,true);
                    page = result.getDataPage();

                    //列表查询后的操作
                    selectAfter(page.getList());

                    return new BaseResult<Pages<Model>>(1,"查询成功", page);
                }
            }

            ServiceResult serviceResult = baseService.selectList(example, true);
            if(!serviceResult.getSuccess()){
                return new BaseResult(0,serviceResult.getMsg());
            }

            List<Model> list = serviceResult.getDataList();

            //列表查询后的操作
            selectAfter(list);

            return new BaseResult<List<Model>>(1,"查询成功",list);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * restful 通用新增信息
     */
    @PostMapping("/")
    @OperLog(operModul = "通用新增信息", operType = "add", operDesc = "通用新增信息")
    public BaseResult add(@ModelAttribute @Valid Model model, BindingResult bindingResult) throws Exception {
        try {
            //新增是通过 Valid 校验
            BaseResult resultValid = MyUtil.apiControllerValid(bindingResult);
            if(resultValid != null && resultValid.getCode() == 0){
                return resultValid;
            }

            //新增时加载与验证方法
            BaseResult result = addBefore(model);
            if(result != null && result.getCode() == 0){
                return result;
            }

            ServiceResult<Model> serviceResult = baseService.insert(model);
            if(!serviceResult.getSuccess()){
                return new BaseResult(0,serviceResult.getMsg());
            }

            model = serviceResult.getData();

            //新增时结尾方法
            addAfter(model);

            return new BaseResult(1,"新增成功",model);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * restful 通用修改信息
     */
    @PutMapping("/{id}")
    @OperLog(operModul = "通用修改", operType = "update", operDesc = "通用修改")
    public BaseResult update(@PathVariable Integer id, @ModelAttribute @Valid Model model, BindingResult bindingResult) throws Exception {
        try {

            //新增是通过 Valid 校验
            BaseResult resultValid = MyUtil.apiControllerValid(bindingResult);
            if(resultValid != null && resultValid.getCode() == 0){
                return resultValid;
            }

            if(MyUtil.isEmpty(model.getId())){
                return new BaseResult(0,"参数错误, ID不能为空");
            }
            //修改时加载与验证方法
            BaseResult result = updateBefore(model);
            if(result != null && result.getCode() == 0){
                return result;
            }

            ServiceResult<Model> serviceResult = baseService.updateById(model);
            if(!serviceResult.getSuccess()){
                return new BaseResult(0,serviceResult.getMsg());
            }

            model = serviceResult.getData();

            //修改时结尾方法
            updateAfter(model);

            return new BaseResult(1,"修改成功",model);
        }catch (Exception e){
            throw e;
        }
    }


    /**
     * restful 通用删除【更新删除】
     */
    @PutMapping("/delete/{id}")
    @OperLog(operModul = "通用删除信息【更新删除】", operType = "update", operDesc = "通用删除信息【更新删除】")
    public BaseResult delete(@PathVariable Integer id, @ModelAttribute Model model) throws Exception{
        try {
            if(MyUtil.isEmpty(model.getId())){
                return new BaseResult(0,"参数错误, ID不能为空");
            }
            //删除时验证方法
            BaseResult result = deleteBefore(model);
            if(result != null && result.getCode() == 0){
                return result;
            }

            ServiceResult<Model> serviceResult = baseService.delete(model);
            if(!serviceResult.getSuccess()){
                return new BaseResult(0,serviceResult.getMsg());
            }

            model = serviceResult.getData();

            //删除时结尾方法
            deleteAfter(model);

            return new BaseResult(1,"删除成功",model);
        }catch (Exception e){
            throw e;
        }
    }

    /**
     * restful 通用删除【彻底删除】
     */
    @DeleteMapping("/{id}")
    @OperLog(operModul = "通用删除信息【彻底删除】", operType = "update", operDesc = "通用删除信息【彻底删除】")
    public BaseResult deleteTrue(@PathVariable Integer id) throws Exception{
        try {
            if(MyUtil.isEmpty(id)){
                return new BaseResult(0,"参数错误, ID不能为空");
            }

            //删除时验证方法
            BaseResult result = deleteTrueCheck(id);
            if(result != null && result.getCode() == 0){
                return result;
            }

            ServiceResult<Model> serviceResult = baseService.deleteTrue(id);
            if(!serviceResult.getSuccess()){
                return new BaseResult(0,serviceResult.getMsg());
            }

            //删除时结尾方法
            deleteTrueAfter();

            return new BaseResult(1,"删除成功");
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public Example selectBefore(Model model) throws Exception {
        return null;
    }

    @Override
    public void selectAfter(Model model) throws Exception {

    }

    @Override
    public void selectAfter(List<Model> models) throws Exception {

    }

    @Override
    public void selectAfter(Pages<Model> pages) throws Exception {

    }

    @Override
    public BaseResult addBefore(Model model) throws Exception {
        return null;
    }

    @Override
    public void addAfter(Model model) throws Exception {

    }

    @Override
    public BaseResult updateBefore(Model model) throws Exception {
        return null;
    }

    @Override
    public void updateAfter(Model model) throws Exception {

    }

    @Override
    public BaseResult deleteBefore(Model model) throws Exception {
        return null;
    }

    @Override
    public void deleteAfter(Model model) throws Exception {

    }

    @Override
    public BaseResult deleteTrueCheck(Integer id) throws Exception {
        return null;
    }

    @Override
    public void deleteTrueAfter() throws Exception {

    }
}
