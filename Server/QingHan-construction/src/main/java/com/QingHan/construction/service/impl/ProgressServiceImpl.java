package com.QingHan.construction.service.impl;

import java.util.List;
import com.QingHan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.construction.mapper.ProgressMapper;
import com.QingHan.construction.domain.Progress;
import com.QingHan.construction.service.IProgressService;

/**
 * 施工队日志Service业务层处理
 * 
 * @author linghy
 * @date 2024-07-10
 */
@Service
public class ProgressServiceImpl implements IProgressService 
{
    @Autowired
    private ProgressMapper progressMapper;

    /**
     * 查询施工队日志
     * 
     * @param progressId 施工队日志主键
     * @return 施工队日志
     */
    @Override
    public Progress selectProgressByProgressId(Long progressId)
    {
        return progressMapper.selectProgressByProgressId(progressId);
    }

    /**
     * 查询施工队日志列表
     * 
     * @param progress 施工队日志
     * @return 施工队日志
     */
    @Override
    public List<Progress> selectProgressList(Progress progress)
    {
        return progressMapper.selectProgressList(progress);
    }

    /**
     * 新增施工队日志
     * 
     * @param progress 施工队日志
     * @return 结果
     */
    @Override
    public int insertProgress(Progress progress)
    {
        progress.setCreateTime(DateUtils.getNowDate());
        return progressMapper.insertProgress(progress);
    }

    /**
     * 修改施工队日志
     * 
     * @param progress 施工队日志
     * @return 结果
     */
    @Override
    public int updateProgress(Progress progress)
    {
        return progressMapper.updateProgress(progress);
    }

    /**
     * 批量删除施工队日志
     * 
     * @param progressIds 需要删除的施工队日志主键
     * @return 结果
     */
    @Override
    public int deleteProgressByProgressIds(Long[] progressIds)
    {
        return progressMapper.deleteProgressByProgressIds(progressIds);
    }

    /**
     * 删除施工队日志信息
     * 
     * @param progressId 施工队日志主键
     * @return 结果
     */
    @Override
    public int deleteProgressByProgressId(Long progressId)
    {
        return progressMapper.deleteProgressByProgressId(progressId);
    }
}
