package com.jzhl.frame01.common.base;

import java.util.Date;
import java.util.List;

/**
 * 基础信息对象
 * @author xiaobin
 */
public class BaseEntity<T> {
    public static String NOT = "暂无";

    /**
     * id
     */
    private  Integer id;

    /**
     * 创建人id
     */
    private Integer buildUserid;

    /**
     * 创建时间
     */
    private Date buildTime;

    /**
     * 修改人id
     */
    private Integer updateUserid;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 状态
     * 0正常
     * 1作废
     */
    private String status;

    /**
     * 删除状态
     * 0正常
     * 1删除
     */
    private String delFlag;

    /**
     * 版本号 用于乐观锁
     */
    private Integer version;

    //创建人名称
    private String buildUserName;

    //修改人名称
    private String updateUserName;

    /**
     * 小区名称
     */
    private String heNameStr;

    private String companyNameStr;


    /*** Tree 树状结构* @return*/
    private Integer parentId;

    private List<T> children;

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<T> getChildren() {
        return children;
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    /*** Tree 树状结构* @return*/


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBuildUserid() {
        return buildUserid;
    }

    public void setBuildUserid(Integer buildUserid) {
        this.buildUserid = buildUserid;
    }

    public Date getBuildTime() {
        return buildTime;
    }

    public void setBuildTime(Date buildTime) {
        this.buildTime = buildTime;
    }

    public Integer getUpdateUserid() {
        return updateUserid;
    }

    public void setUpdateUserid(Integer updateUserid) {
        this.updateUserid = updateUserid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getBuildUserName() {
        return buildUserName;
    }

    public void setBuildUserName(String buildUserName) {
        this.buildUserName = buildUserName;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getHeNameStr() {
        return heNameStr;
    }

    public void setHeNameStr(String heNameStr) {
        this.heNameStr = heNameStr;
    }

    public String getCompanyNameStr() {
        return companyNameStr;
    }

    public void setCompanyNameStr(String companyNameStr) {
        this.companyNameStr = companyNameStr;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", buildUserid=" + buildUserid +
                ", buildTime=" + buildTime +
                ", updateUserid=" + updateUserid +
                ", updateTime=" + updateTime +
                ", status='" + status + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", version=" + version +
                ", buildUserName='" + buildUserName + '\'' +
                ", updateUserName='" + updateUserName + '\'' +
                ", heNameStr='" + heNameStr + '\'' +
                ", companyNameStr='" + companyNameStr + '\'' +
                '}';
    }
}
