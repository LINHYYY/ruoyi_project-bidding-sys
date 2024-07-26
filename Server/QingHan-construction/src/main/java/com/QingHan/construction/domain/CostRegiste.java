package com.QingHan.construction.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 材料核销对象 con_cost_registe
 * 
 * @author yzm
 * @date 2024-07-05
 */
public class CostRegiste extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 登记表ID */
    private Long registartionId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;
    @Excel(name = "项目名称")
    private String projectName;
    /** 成本类型 */
    @Excel(name = "成本类型")
    private String costType;

    /** 成本项 */
    @Excel(name = "成本项")
    private String costItem;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal amount;

    /** 登记时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "登记时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    public void setRegistartionId(Long registartionId) 
    {
        this.registartionId = registartionId;
    }

    public Long getRegistartionId() 
    {
        return registartionId;
    }
    public void setProjectId(Long projectId) 
    {
        this.projectId = projectId;
    }

    public Long getProjectId() 
    {
        return projectId;
    }
    public void setCostType(String costType) 
    {
        this.costType = costType;
    }

    public String getCostType() 
    {
        return costType;
    }
    public void setCostItem(String costItem) 
    {
        this.costItem = costItem;
    }

    public String getCostItem() 
    {
        return costItem;
    }
    public void setAmount(BigDecimal amount) 
    {
        this.amount = amount;
    }

    public BigDecimal getAmount() 
    {
        return amount;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setUpdateDate(Date updateDate) 
    {
        this.updateDate = updateDate;
    }

    public Date getUpdateDate() 
    {
        return updateDate;
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
            .append("registartionId", getRegistartionId())
            .append("projectId", getProjectId())
            .append("costType", getCostType())
            .append("costItem", getCostItem())
            .append("amount", getAmount())
            .append("createDate", getCreateDate())
            .append("updateDate", getUpdateDate())
            .toString();
    }
}
