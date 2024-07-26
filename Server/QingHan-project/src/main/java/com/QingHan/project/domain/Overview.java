package com.QingHan.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 项目基础信息对象 pro_overview
 * 
 * @author cph
 * @date 2024-07-11
 */
public class Overview extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 项目编号 */
    private Long id;

    /** 审核ID */
    @Excel(name = "审核ID")
    private Long approvalId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    private String projectName;

    /** 负责人员ID */
    @Excel(name = "负责人员ID")
    private Long managerId;

    /** 项目预算 */
    @Excel(name = "项目预算")
    private BigDecimal budgetTotal;

    /** 项目启动日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目启动日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 项目结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "项目结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 项目图片 */
    @Excel(name = "项目图片")
    private String imageUrl;

    /** 项目简述 */
    @Excel(name = "项目简述")
    private String briefDesc;

    /** 上传附件文件 */
    @Excel(name = "上传附件文件")
    private String attachmentPath;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setApprovalId(Long approvalId) 
    {
        this.approvalId = approvalId;
    }

    public Long getApprovalId() 
    {
        return approvalId;
    }
    public void setProjectName(String projectName) 
    {
        this.projectName = projectName;
    }

    public String getProjectName() 
    {
        return projectName;
    }
    public void setManagerId(Long managerId) 
    {
        this.managerId = managerId;
    }

    public Long getManagerId() 
    {
        return managerId;
    }
    public void setBudgetTotal(BigDecimal budgetTotal) 
    {
        this.budgetTotal = budgetTotal;
    }

    public BigDecimal getBudgetTotal() 
    {
        return budgetTotal;
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
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }
    public void setBriefDesc(String briefDesc) 
    {
        this.briefDesc = briefDesc;
    }

    public String getBriefDesc() 
    {
        return briefDesc;
    }
    public void setAttachmentPath(String attachmentPath) 
    {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentPath() 
    {
        return attachmentPath;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("approvalId", getApprovalId())
            .append("projectName", getProjectName())
            .append("managerId", getManagerId())
            .append("budgetTotal", getBudgetTotal())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("imageUrl", getImageUrl())
            .append("briefDesc", getBriefDesc())
            .append("attachmentPath", getAttachmentPath())
            .toString();
    }
}
