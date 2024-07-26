package com.QingHan.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 项目进程对象 pro_project_progress
 * 
 * @author cph
 * @date 2024-07-12
 */
public class ProProjectProgress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 进程记录id */
    private Long progressId;

    /** 投标详细信息id */
    @Excel(name = "投标详细信息id")
    private Long detailId;

    /** 项目当前状态 */
    @Excel(name = "项目当前状态")
    private Long status;

    /** 投标人id */
    @Excel(name = "投标人id")
    private Long bidderId;

    /** 施工队ID */
    @Excel(name = "施工队ID")
    private Long teamId;

    /** 负责人员ID */
    @Excel(name = "负责人员ID")
    private Long managerId;

    /** 当前阶段描述 */
    @Excel(name = "当前阶段描述")
    private String currentStage;

    /** 项目开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 项目结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    public void setProgressId(Long progressId) 
    {
        this.progressId = progressId;
    }

    public Long getProgressId() 
    {
        return progressId;
    }
    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setBidderId(Long bidderId) 
    {
        this.bidderId = bidderId;
    }

    public Long getBidderId() 
    {
        return bidderId;
    }
    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setManagerId(Long managerId) 
    {
        this.managerId = managerId;
    }

    public Long getManagerId() 
    {
        return managerId;
    }
    public void setCurrentStage(String currentStage) 
    {
        this.currentStage = currentStage;
    }

    public String getCurrentStage() 
    {
        return currentStage;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("progressId", getProgressId())
            .append("detailId", getDetailId())
            .append("status", getStatus())
            .append("bidderId", getBidderId())
            .append("teamId", getTeamId())
            .append("managerId", getManagerId())
            .append("currentStage", getCurrentStage())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .toString();
    }
}
