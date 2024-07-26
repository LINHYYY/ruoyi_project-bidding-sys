package com.QingHan.design.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 设计队列表对象 des_teams
 * 
 * @author linghy
 * @date 2024-07-17
 */
public class DesTeams extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 施工队ID */
    @Excel(name = "施工队ID")
    private Long teamId;

    /** 施工队名称 */
    @Excel(name = "施工队名称")
    private String teamName;

    /** 资质等级 */
    @Excel(name = "资质等级")
    private String qualificationLevel;

    /** 负责人 */
    @Excel(name = "负责人")
    private String responsiblePerson;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactInfo;

    public void setTeamId(Long teamId) 
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }
    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
    }
    public void setQualificationLevel(String qualificationLevel) 
    {
        this.qualificationLevel = qualificationLevel;
    }

    public String getQualificationLevel() 
    {
        return qualificationLevel;
    }
    public void setResponsiblePerson(String responsiblePerson) 
    {
        this.responsiblePerson = responsiblePerson;
    }

    public String getResponsiblePerson() 
    {
        return responsiblePerson;
    }
    public void setContactInfo(String contactInfo) 
    {
        this.contactInfo = contactInfo;
    }

    public String getContactInfo() 
    {
        return contactInfo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamId", getTeamId())
            .append("teamName", getTeamName())
            .append("qualificationLevel", getQualificationLevel())
            .append("responsiblePerson", getResponsiblePerson())
            .append("contactInfo", getContactInfo())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
