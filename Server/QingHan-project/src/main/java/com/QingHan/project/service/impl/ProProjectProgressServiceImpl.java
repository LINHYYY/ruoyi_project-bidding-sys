package com.QingHan.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.project.mapper.ProProjectProgressMapper;
import com.QingHan.project.domain.ProProjectProgress;
import com.QingHan.project.service.IProProjectProgressService;

/**
 * 项目进程Service业务层处理
 * 
 * @author cph
 * @date 2024-07-12
 */
@Service
public class ProProjectProgressServiceImpl implements IProProjectProgressService 
{
    @Autowired
    private ProProjectProgressMapper proProjectProgressMapper;

    /**
     * 查询项目进程
     * 
     * @param progressId 项目进程主键
     * @return 项目进程
     */
    @Override
    public ProProjectProgress selectProProjectProgressByProgressId(Long progressId)
    {
        return proProjectProgressMapper.selectProProjectProgressByProgressId(progressId);
    }

    /**
     * 查询项目进程列表
     * 
     * @param proProjectProgress 项目进程
     * @return 项目进程
     */
    @Override
    public List<ProProjectProgress> selectProProjectProgressList(ProProjectProgress proProjectProgress)
    {
        return proProjectProgressMapper.selectProProjectProgressList(proProjectProgress);
    }

    /**
     * 新增项目进程
     * 
     * @param proProjectProgress 项目进程
     * @return 结果
     */
    @Override
    public int insertProProjectProgress(ProProjectProgress proProjectProgress)
    {
        return proProjectProgressMapper.insertProProjectProgress(proProjectProgress);
    }

    /**
     * 修改项目进程
     * 
     * @param proProjectProgress 项目进程
     * @return 结果
     */
    @Override
    public int updateProProjectProgress(ProProjectProgress proProjectProgress)
    {
        return proProjectProgressMapper.updateProProjectProgress(proProjectProgress);
    }

    /**
     * 批量删除项目进程
     * 
     * @param progressIds 需要删除的项目进程主键
     * @return 结果
     */
    @Override
    public int deleteProProjectProgressByProgressIds(Long[] progressIds)
    {
        return proProjectProgressMapper.deleteProProjectProgressByProgressIds(progressIds);
    }

    /**
     * 删除项目进程信息
     * 
     * @param progressId 项目进程主键
     * @return 结果
     */
    @Override
    public int deleteProProjectProgressByProgressId(Long progressId)
    {
        return proProjectProgressMapper.deleteProProjectProgressByProgressId(progressId);
    }
}
