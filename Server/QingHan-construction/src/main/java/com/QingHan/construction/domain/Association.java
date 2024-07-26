package com.QingHan.construction.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 施工项目状态对象 con_association
 *
 * @author fzy
 * @date 2024-07-09
 */
public class Association extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 关联表ID */
    private Long relationId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;

    private String projectName;
    /** 施工队ID */
    @Excel(name = "施工队ID")
    private Long teamId;

    /** 项目开始日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目开始日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 项目结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 预计开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedStartdate;

    /** 预计结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date estimatedEnddate;

    /** 项目计划内容 */
    @Excel(name = "项目计划内容")
    private String planContent;

    /** 项目负责人 */
    @Excel(name = "项目负责人")
    private String responsiblePerson;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    public void setRelationId(Long relationId)
    {
        this.relationId = relationId;
    }

    public Long getRelationId()
    {
        return relationId;
    }
    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }

    public Long getProjectId()
    {
        return projectId;
    }
    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId()
    {
        return teamId;
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
    public void setEstimatedStartdate(Date estimatedStartdate)
    {
        this.estimatedStartdate = estimatedStartdate;
    }

    public Date getEstimatedStartdate()
    {
        return estimatedStartdate;
    }
    public void setEstimatedEnddate(Date estimatedEnddate)
    {
        this.estimatedEnddate = estimatedEnddate;
    }

    public Date getEstimatedEnddate()
    {
        return estimatedEnddate;
    }
    public void setPlanContent(String planContent)
    {
        this.planContent = planContent;
    }

    public String getPlanContent()
    {
        return planContent;
    }
    public void setResponsiblePerson(String responsiblePerson)
    {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson()
    {
        return responsiblePerson;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getStatus()
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("relationId", getRelationId())
            .append("projectId", getProjectId())
            .append("teamId", getTeamId())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("estimatedStartdate", getEstimatedStartdate())
            .append("estimatedEnddate", getEstimatedEnddate())
            .append("planContent", getPlanContent())
            .append("createTime", getCreateTime())
            .append("responsiblePerson", getResponsiblePerson())
            .append("status", getStatus())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
