package com.QingHan.project.service;

import java.util.List;
import com.QingHan.project.domain.Overview;

/**
 * 项目基础信息Service接口
 * 
 * @author cph
 * @date 2024-07-11
 */
public interface IOverviewService 
{
    /**
     * 查询项目基础信息
     * 
     * @param id 项目基础信息主键
     * @return 项目基础信息
     */
    public Overview selectOverviewById(Long id);

    /**
     * 查询项目基础信息列表
     * 
     * @param overview 项目基础信息
     * @return 项目基础信息集合
     */
    public List<Overview> selectOverviewList(Overview overview);

    /**
     * 新增项目基础信息
     * 
     * @param overview 项目基础信息
     * @return 结果
     */
    public int insertOverview(Overview overview);

    /**
     * 修改项目基础信息
     * 
     * @param overview 项目基础信息
     * @return 结果
     */
    public int updateOverview(Overview overview);

    /**
     * 批量删除项目基础信息
     * 
     * @param ids 需要删除的项目基础信息主键集合
     * @return 结果
     */
    public int deleteOverviewByIds(Long[] ids);

    /**
     * 删除项目基础信息信息
     * 
     * @param id 项目基础信息主键
     * @return 结果
     */
    public int deleteOverviewById(Long id);
}
