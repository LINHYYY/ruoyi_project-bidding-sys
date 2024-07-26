package com.QingHan.construction.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.QingHan.common.annotation.Excel;
import com.QingHan.common.core.domain.BaseEntity;

/**
 * 施工现场对象 con_photos
 * 
 * @author yzm
 * @date 2024-07-04
 */
public class Photos extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 图片ID */
    @Excel(name = "图片ID")
    private Long photoId;

    /** 项目ID */
    @Excel(name = "项目ID")
    private Long projectId;
    @Excel(name = "项目名称")
    private String projectName; // 项目名称属性
    /** 施工队ID */
    @Excel(name = "施工队ID")
    private Long teamId;

    /**施工队名称*/
    private String teamName;
    @Excel(name = "施工队负责人名称")
    private String teamLeaderName; // 施工队负责人名称
    @Excel(name = "施工队联系方式")
    private String teamContactNumber; // 施工队联系方式
    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;
    @Excel(name = "上传图片用户")
    private String userName; // 用户名称

    /** 图片存储地址 */
    @Excel(name = "图片存储地址")
    private String photoUrl;

    /** 上传时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "上传时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    public void setPhotoId(Long photoId) 
    {
        this.photoId = photoId;
    }

    public Long getPhotoId() 
    {
        return photoId;
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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setPhotoUrl(String photoUrl) 
    {
        this.photoUrl = photoUrl;
    }

    public String getPhotoUrl() 
    {
        return photoUrl;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    // 为新属性添加Getter和Setter方法
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectName() {
        return projectName;
    }
    // 为新属性添加Getter和Setter方法
    public void setTeamLeaderName(String teamLeaderName) {
        this.teamLeaderName = teamLeaderName;
    }

    public String getTeamLeaderName() {
        return teamLeaderName;
    }

    public void setTeamContactNumber(String teamContactNumber) {
        this.teamContactNumber = teamContactNumber;
    }

    public String getTeamContactNumber() {
        return teamContactNumber;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
    public void setTeamName(String teamName) {
        this.teamName=teamName;
    }
    public String getTeamName(){
        return teamName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId", getPhotoId())
            .append("projectId", getProjectId())
            .append("teamId", getTeamId())
            .append("userId", getUserId())
            .append("photoUrl", getPhotoUrl())
            .append("uploadTime", getUploadTime())
            .toString();
    }
}
