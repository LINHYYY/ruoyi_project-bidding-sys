package com.QingHan.construction.service;

import java.util.List;
import com.QingHan.construction.domain.Progress;

/**
 * 施工队日志Service接口
 * 
 * @author linghy
 * @date 2024-07-10
 */
public interface IProgressService 
{
    /**
     * 查询施工队日志
     * 
     * @param progressId 施工队日志主键
     * @return 施工队日志
     */
    public Progress selectProgressByProgressId(Long progressId);

    /**
     * 查询施工队日志列表
     * 
     * @param progress 施工队日志
     * @return 施工队日志集合
     */
    public List<Progress> selectProgressList(Progress progress);

    /**
     * 新增施工队日志
     * 
     * @param progress 施工队日志
     * @return 结果
     */
    public int insertProgress(Progress progress);

    /**
     * 修改施工队日志
     * 
     * @param progress 施工队日志
     * @return 结果
     */
    public int updateProgress(Progress progress);

    /**
     * 批量删除施工队日志
     * 
     * @param progressIds 需要删除的施工队日志主键集合
     * @return 结果
     */
    public int deleteProgressByProgressIds(Long[] progressIds);

    /**
     * 删除施工队日志信息
     * 
     * @param progressId 施工队日志主键
     * @return 结果
     */
    public int deleteProgressByProgressId(Long progressId);
}
