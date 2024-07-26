package com.QingHan.design.service.impl;

import java.util.List;
import com.QingHan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.design.mapper.DesProgressMapper;
import com.QingHan.design.domain.DesProgress;
import com.QingHan.design.service.IDesProgressService;

/**
 * 设计队设计日志Service业务层处理
 * 
 * @author linghy
 * @date 2024-07-17
 */
@Service
public class DesProgressServiceImpl implements IDesProgressService 
{
    @Autowired
    private DesProgressMapper desProgressMapper;

    /**
     * 查询设计队设计日志
     * 
     * @param progressId 设计队设计日志主键
     * @return 设计队设计日志
     */
    @Override
    public DesProgress selectDesProgressByProgressId(Long progressId)
    {
        return desProgressMapper.selectDesProgressByProgressId(progressId);
    }

    /**
     * 查询设计队设计日志列表
     * 
     * @param desProgress 设计队设计日志
     * @return 设计队设计日志
     */
    @Override
    public List<DesProgress> selectDesProgressList(DesProgress desProgress)
    {
        return desProgressMapper.selectDesProgressList(desProgress);
    }

    /**
     * 新增设计队设计日志
     * 
     * @param desProgress 设计队设计日志
     * @return 结果
     */
    @Override
    public int insertDesProgress(DesProgress desProgress)
    {
        desProgress.setCreateTime(DateUtils.getNowDate());
        return desProgressMapper.insertDesProgress(desProgress);
    }

    /**
     * 修改设计队设计日志
     * 
     * @param desProgress 设计队设计日志
     * @return 结果
     */
    @Override
    public int updateDesProgress(DesProgress desProgress)
    {
        return desProgressMapper.updateDesProgress(desProgress);
    }

    /**
     * 批量删除设计队设计日志
     * 
     * @param progressIds 需要删除的设计队设计日志主键
     * @return 结果
     */
    @Override
    public int deleteDesProgressByProgressIds(Long[] progressIds)
    {
        return desProgressMapper.deleteDesProgressByProgressIds(progressIds);
    }

    /**
     * 删除设计队设计日志信息
     * 
     * @param progressId 设计队设计日志主键
     * @return 结果
     */
    @Override
    public int deleteDesProgressByProgressId(Long progressId)
    {
        return desProgressMapper.deleteDesProgressByProgressId(progressId);
    }
}
