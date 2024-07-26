package com.QingHan.project.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 投标审核详细信息对象 pro_bid_review_details
 * 
 * @author cph
 * @date 2024-07-12
 */
public class ProBidReviewDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 投标详细信息id */
    private Long detailId;

    /** 项目编号 */
    private Long id;

    /** 成本填报 */
    @Excel(name = "成本填报")
    private BigDecimal costReporting;

    /** 分成金额 */
    @Excel(name = "分成金额")
    private BigDecimal profitEstimate;

    /** 分成比例 */
    @Excel(name = "分成比例")
    private BigDecimal bonusRatio;

    /** 投标人id */
    @Excel(name = "投标人id")
    private Long bidderId;

    /** 电话号码 */
    @Excel(name = "电话号码")
    private String phoneNumber;

    /** 投标文件 */
    @Excel(name = "投标文件")
    private String bidDocument;

    /** 是否为敏感资料 */
    @Excel(name = "是否为敏感资料")
    private Integer sensitiveFlag;

    /** 预计项目启动日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计项目启动日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 预计项目结束日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "预计项目结束日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date reviewTime;

    /** 投标审核状态 */
    @Excel(name = "投标审核状态")
    private String bidReviewStatus;

    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
    }
    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCostReporting(BigDecimal costReporting) 
    {
        this.costReporting = costReporting;
    }

    public BigDecimal getCostReporting() 
    {
        return costReporting;
    }
    public void setProfitEstimate(BigDecimal profitEstimate) 
    {
        this.profitEstimate = profitEstimate;
    }

    public BigDecimal getProfitEstimate() 
    {
        return profitEstimate;
    }
    public void setBonusRatio(BigDecimal bonusRatio) 
    {
        this.bonusRatio = bonusRatio;
    }

    public BigDecimal getBonusRatio() 
    {
        return bonusRatio;
    }
    public void setBidderId(Long bidderId) 
    {
        this.bidderId = bidderId;
    }

    public Long getBidderId() 
    {
        return bidderId;
    }
    public void setPhoneNumber(String phoneNumber) 
    {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() 
    {
        return phoneNumber;
    }
    public void setBidDocument(String bidDocument) 
    {
        this.bidDocument = bidDocument;
    }

    public String getBidDocument() 
    {
        return bidDocument;
    }
    public void setSensitiveFlag(Integer sensitiveFlag) 
    {
        this.sensitiveFlag = sensitiveFlag;
    }

    public Integer getSensitiveFlag() 
    {
        return sensitiveFlag;
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
    public void setReviewTime(Date reviewTime) 
    {
        this.reviewTime = reviewTime;
    }

    public Date getReviewTime() 
    {
        return reviewTime;
    }
    public void setBidReviewStatus(String bidReviewStatus) 
    {
        this.bidReviewStatus = bidReviewStatus;
    }

    public String getBidReviewStatus() 
    {
        return bidReviewStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("id", getId())
            .append("costReporting", getCostReporting())
            .append("profitEstimate", getProfitEstimate())
            .append("bonusRatio", getBonusRatio())
            .append("bidderId", getBidderId())
            .append("phoneNumber", getPhoneNumber())
            .append("bidDocument", getBidDocument())
            .append("sensitiveFlag", getSensitiveFlag())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("reviewTime", getReviewTime())
            .append("bidReviewStatus", getBidReviewStatus())
            .toString();
    }
}
