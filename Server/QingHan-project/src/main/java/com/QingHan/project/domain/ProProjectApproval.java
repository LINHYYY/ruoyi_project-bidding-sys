package com.QingHan.project.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 项目审核表对象 pro_project_approval
 * 
 * @author lbb
 * @date 2024-07-11
 */
public class ProProjectApproval extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 审核ID */
    private Long approvalId;

    /** 审核人ID */
    @Excel(name = "审核人ID")
    private Long approverId;

    /** 提交人ID */
    @Excel(name = "提交人ID")
    private Long submitterId;

    /** 项目图片 */
    @Excel(name = "项目图片")
    private String imageUrl;

    /** 上传附件文件 */
    @Excel(name = "上传附件文件")
    private String attachmentPath;

    /** 提交审核的日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交审核的日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitDate;

    /** 审核完成的日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核完成的日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date approveDate;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private String approvalStatus;

    public void setApprovalId(Long approvalId) 
    {
        this.approvalId = approvalId;
    }

    public Long getApprovalId() 
    {
        return approvalId;
    }
    public void setApproverId(Long approverId) 
    {
        this.approverId = approverId;
    }

    public Long getApproverId() 
    {
        return approverId;
    }
    public void setSubmitterId(Long submitterId) 
    {
        this.submitterId = submitterId;
    }

    public Long getSubmitterId() 
    {
        return submitterId;
    }
    public void setImageUrl(String imageUrl) 
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() 
    {
        return imageUrl;
    }
    public void setAttachmentPath(String attachmentPath) 
    {
        this.attachmentPath = attachmentPath;
    }

    public String getAttachmentPath() 
    {
        return attachmentPath;
    }
    public void setSubmitDate(Date submitDate) 
    {
        this.submitDate = submitDate;
    }

    public Date getSubmitDate() 
    {
        return submitDate;
    }
    public void setApproveDate(Date approveDate) 
    {
        this.approveDate = approveDate;
    }

    public Date getApproveDate() 
    {
        return approveDate;
    }
    public void setApprovalStatus(String approvalStatus) 
    {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatus() 
    {
        return approvalStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("approvalId", getApprovalId())
            .append("approverId", getApproverId())
            .append("submitterId", getSubmitterId())
            .append("imageUrl", getImageUrl())
            .append("attachmentPath", getAttachmentPath())
            .append("submitDate", getSubmitDate())
            .append("approveDate", getApproveDate())
            .append("approvalStatus", getApprovalStatus())
            .toString();
    }
}
