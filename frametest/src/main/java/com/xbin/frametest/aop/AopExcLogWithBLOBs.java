package com.xbin.frametest.aop;

import java.io.Serializable;

/**
 * aop_exc_log
 * @author 
 */
public class AopExcLogWithBLOBs extends AopExcLog implements Serializable {
    /**
     * 请求参数
     */
    private String excRequParam;

    /**
     * 异常信息
     */
    private String excMessage;

    private static final long serialVersionUID = 1L;

    public String getExcRequParam() {
        return excRequParam;
    }

    public void setExcRequParam(String excRequParam) {
        this.excRequParam = excRequParam;
    }

    public String getExcMessage() {
        return excMessage;
    }

    public void setExcMessage(String excMessage) {
        this.excMessage = excMessage;
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
        AopExcLogWithBLOBs other = (AopExcLogWithBLOBs) that;
        return (this.getExcId() == null ? other.getExcId() == null : this.getExcId().equals(other.getExcId()))
            && (this.getExcName() == null ? other.getExcName() == null : this.getExcName().equals(other.getExcName()))
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
            && (this.getExcRequParam() == null ? other.getExcRequParam() == null : this.getExcRequParam().equals(other.getExcRequParam()))
            && (this.getExcMessage() == null ? other.getExcMessage() == null : this.getExcMessage().equals(other.getExcMessage()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getExcId() == null) ? 0 : getExcId().hashCode());
        result = prime * result + ((getExcName() == null) ? 0 : getExcName().hashCode());
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
        result = prime * result + ((getExcRequParam() == null) ? 0 : getExcRequParam().hashCode());
        result = prime * result + ((getExcMessage() == null) ? 0 : getExcMessage().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", excRequParam=").append(excRequParam);
        sb.append(", excMessage=").append(excMessage);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}