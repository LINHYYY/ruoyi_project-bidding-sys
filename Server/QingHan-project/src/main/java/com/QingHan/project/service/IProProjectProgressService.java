package com.QingHan.project.service;

import java.util.List;
import com.QingHan.project.domain.ProProjectProgress;

/**
 * 项目进程Service接口
 * 
 * @author cph
 * @date 2024-07-12
 */
public interface IProProjectProgressService 
{
    /**
     * 查询项目进程
     * 
     * @param progressId 项目进程主键
     * @return 项目进程
     */
    public ProProjectProgress selectProProjectProgressByProgressId(Long progressId);

    /**
     * 查询项目进程列表
     * 
     * @param proProjectProgress 项目进程
     * @return 项目进程集合
     */
    public List<ProProjectProgress> selectProProjectProgressList(ProProjectProgress proProjectProgress);

    /**
     * 新增项目进程
     * 
     * @param proProjectProgress 项目进程
     * @return 结果
     */
    public int insertProProjectProgress(ProProjectProgress proProjectProgress);

    /**
     * 修改项目进程
     * 
     * @param proProjectProgress 项目进程
     * @return 结果
     */
    public int updateProProjectProgress(ProProjectProgress proProjectProgress);

    /**
     * 批量删除项目进程
     * 
     * @param progressIds 需要删除的项目进程主键集合
     * @return 结果
     */
    public int deleteProProjectProgressByProgressIds(Long[] progressIds);

    /**
     * 删除项目进程信息
     * 
     * @param progressId 项目进程主键
     * @return 结果
     */
    public int deleteProProjectProgressByProgressId(Long progressId);
}
