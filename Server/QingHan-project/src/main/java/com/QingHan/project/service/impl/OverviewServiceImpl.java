package com.QingHan.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.project.mapper.OverviewMapper;
import com.QingHan.project.domain.Overview;
import com.QingHan.project.service.IOverviewService;

/**
 * 项目基础信息Service业务层处理
 * 
 * @author cph
 * @date 2024-07-11
 */
@Service
public class OverviewServiceImpl implements IOverviewService 
{
    @Autowired
    private OverviewMapper overviewMapper;

    /**
     * 查询项目基础信息
     * 
     * @param id 项目基础信息主键
     * @return 项目基础信息
     */
    @Override
    public Overview selectOverviewById(Long id)
    {
        return overviewMapper.selectOverviewById(id);
    }

    /**
     * 查询项目基础信息列表
     * 
     * @param overview 项目基础信息
     * @return 项目基础信息
     */
    @Override
    public List<Overview> selectOverviewList(Overview overview)
    {
        return overviewMapper.selectOverviewList(overview);
    }

    /**
     * 新增项目基础信息
     * 
     * @param overview 项目基础信息
     * @return 结果
     */
    @Override
    public int insertOverview(Overview overview)
    {
        return overviewMapper.insertOverview(overview);
    }

    /**
     * 修改项目基础信息
     * 
     * @param overview 项目基础信息
     * @return 结果
     */
    @Override
    public int updateOverview(Overview overview)
    {
        return overviewMapper.updateOverview(overview);
    }

    /**
     * 批量删除项目基础信息
     * 
     * @param ids 需要删除的项目基础信息主键
     * @return 结果
     */
    @Override
    public int deleteOverviewByIds(Long[] ids)
    {
        return overviewMapper.deleteOverviewByIds(ids);
    }

    /**
     * 删除项目基础信息信息
     * 
     * @param id 项目基础信息主键
     * @return 结果
     */
    @Override
    public int deleteOverviewById(Long id)
    {
        return overviewMapper.deleteOverviewById(id);
    }
}
