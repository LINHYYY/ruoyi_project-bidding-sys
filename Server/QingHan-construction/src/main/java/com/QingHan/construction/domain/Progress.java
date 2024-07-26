package com.QingHan.construction.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 施工队日志对象 con_progress
 * 
 * @author linghy
 * @date 2024-07-10
 */
public class Progress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 进度ID */
    @Excel(name = "进度ID")
    private Long progressId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;
    private String projectName;

    /** 预计施工日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计施工日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date constructionDate;

    /** 每日施工计划 */
    @Excel(name = "每日施工计划")
    private String dailyContent;

    /** 施工问题描述 */
    @Excel(name = "施工问题描述")
    private String progressDescription;

    /** 施工进度描述 */
    @Excel(name = "施工进度描述")
    private String constructionDescription;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    public void setProgressId(Long progressId) 
    {
        this.progressId = progressId;
    }

    public Long getProgressId() 
    {
        return progressId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setConstructionDate(Date constructionDate) 
    {
        this.constructionDate = constructionDate;
    }

    public Date getConstructionDate() 
    {
        return constructionDate;
    }
    public void setDailyContent(String dailyContent) 
    {
        this.dailyContent = dailyContent;
    }

    public String getDailyContent() 
    {
        return dailyContent;
    }
    public void setProgressDescription(String progressDescription) 
    {
        this.progressDescription = progressDescription;
    }

    public String getProgressDescription() 
    {
        return progressDescription;
    }
    public void setConstructionDescription(String constructionDescription) 
    {
        this.constructionDescription = constructionDescription;
    }

    public String getConstructionDescription() 
    {
        return constructionDescription;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("progressId", getProgressId())
            .append("projectId", getProjectId())
            .append("constructionDate", getConstructionDate())
            .append("dailyContent", getDailyContent())
            .append("progressDescription", getProgressDescription())
            .append("constructionDescription", getConstructionDescription())
            .append("uploadTime", getUploadTime())
            .append("createTime", getCreateTime())
            .toString();
    }
}
