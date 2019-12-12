package com.jzhl.frame01.aop;

import java.io.Serializable;

/**
 * aop_oper_log
 * @author 
 */
public class AopOperLogWithBLOBs extends AopOperLog implements Serializable {
    /**
     * 请求参数
     */
    private String operRequParam;

    /**
     * 返回参数
     */
    private String operRespParam;

    private static final long serialVersionUID = 1L;

    public String getOperRequParam() {
        return operRequParam;
    }

    public void setOperRequParam(String operRequParam) {
        this.operRequParam = operRequParam;
    }

    public String getOperRespParam() {
        return operRespParam;
    }

    public void setOperRespParam(String operRespParam) {
        this.operRespParam = operRespParam;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        AopOperLogWithBLOBs other = (AopOperLogWithBLOBs) that;
        return (this.getOperId() == null ? other.getOperId() == null : this.getOperId().equals(other.getOperId()))
            && (this.getOperModul() == null ? other.getOperModul() == null : this.getOperModul().equals(other.getOperModul()))
            && (this.getOperType() == null ? other.getOperType() == null : this.getOperType().equals(other.getOperType()))
            && (this.getOperDesc() == null ? other.getOperDesc() == null : this.getOperDesc().equals(other.getOperDesc()))
            && (this.getOperMethod() == null ? other.getOperMethod() == null : this.getOperMethod().equals(other.getOperMethod()))
            && (this.getOperUri() == null ? other.getOperUri() == null : this.getOperUri().equals(other.getOperUri()))
            && (this.getOperIp() == null ? other.getOperIp() == null : this.getOperIp().equals(other.getOperIp()))
            && (this.getOperVer() == null ? other.getOperVer() == null : this.getOperVer().equals(other.getOperVer()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDelFlag() == null ? other.getDelFlag() == null : this.getDelFlag().equals(other.getDelFlag()))
            && (this.getBuildUserid() == null ? other.getBuildUserid() == null : this.getBuildUserid().equals(other.getBuildUserid()))
            && (this.getBuildTime() == null ? other.getBuildTime() == null : this.getBuildTime().equals(other.getBuildTime()))
            && (this.getUpdateUserid() == null ? other.getUpdateUserid() == null : this.getUpdateUserid().equals(other.getUpdateUserid()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getOperRequParam() == null ? other.getOperRequParam() == null : this.getOperRequParam().equals(other.getOperRequParam()))
            && (this.getOperRespParam() == null ? other.getOperRespParam() == null : this.getOperRespParam().equals(other.getOperRespParam()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getOperId() == null) ? 0 : getOperId().hashCode());
        result = prime * result + ((getOperModul() == null) ? 0 : getOperModul().hashCode());
        result = prime * result + ((getOperType() == null) ? 0 : getOperType().hashCode());
        result = prime * result + ((getOperDesc() == null) ? 0 : getOperDesc().hashCode());
        result = prime * result + ((getOperMethod() == null) ? 0 : getOperMethod().hashCode());
        result = prime * result + ((getOperUri() == null) ? 0 : getOperUri().hashCode());
        result = prime * result + ((getOperIp() == null) ? 0 : getOperIp().hashCode());
        result = prime * result + ((getOperVer() == null) ? 0 : getOperVer().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDelFlag() == null) ? 0 : getDelFlag().hashCode());
        result = prime * result + ((getBuildUserid() == null) ? 0 : getBuildUserid().hashCode());
        result = prime * result + ((getBuildTime() == null) ? 0 : getBuildTime().hashCode());
        result = prime * result + ((getUpdateUserid() == null) ? 0 : getUpdateUserid().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getOperRequParam() == null) ? 0 : getOperRequParam().hashCode());
        result = prime * result + ((getOperRespParam() == null) ? 0 : getOperRespParam().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", operRequParam=").append(operRequParam);
        sb.append(", operRespParam=").append(operRespParam);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}