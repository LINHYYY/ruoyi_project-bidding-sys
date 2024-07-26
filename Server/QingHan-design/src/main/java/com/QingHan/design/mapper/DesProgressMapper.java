package com.QingHan.design.mapper;

import java.util.List;
import com.QingHan.design.domain.DesProgress;

/**
 * 设计队设计日志Mapper接口
 * 
 * @author linghy
 * @date 2024-07-17
 */
public interface DesProgressMapper 
{
    /**
     * 查询设计队设计日志
     * 
     * @param progressId 设计队设计日志主键
     * @return 设计队设计日志
     */
    public DesProgress selectDesProgressByProgressId(Long progressId);

    /**
     * 查询设计队设计日志列表
     * 
     * @param desProgress 设计队设计日志
     * @return 设计队设计日志集合
     */
    public List<DesProgress> selectDesProgressList(DesProgress desProgress);

    /**
     * 新增设计队设计日志
     * 
     * @param desProgress 设计队设计日志
     * @return 结果
     */
    public int insertDesProgress(DesProgress desProgress);

    /**
     * 修改设计队设计日志
     * 
     * @param desProgress 设计队设计日志
     * @return 结果
     */
    public int updateDesProgress(DesProgress desProgress);

    /**
     * 删除设计队设计日志
     * 
     * @param progressId 设计队设计日志主键
     * @return 结果
     */
    public int deleteDesProgressByProgressId(Long progressId);

    /**
     * 批量删除设计队设计日志
     * 
     * @param progressIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDesProgressByProgressIds(Long[] progressIds);
}
