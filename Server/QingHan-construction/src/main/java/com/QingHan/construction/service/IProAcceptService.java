package com.QingHan.construction.service;

import java.util.List;
import com.QingHan.construction.domain.ProAccept;

/**
 * 施工验收表管理Service接口
 * 
 * @author yzm
 * @date 2024-07-08
 */
public interface IProAcceptService 
{
    /**
     * 查询施工验收表管理
     * 
     * @param fileId 施工验收表管理主键
     * @return 施工验收表管理
     */
    public ProAccept selectProAcceptByFileId(Long fileId);

    /**
     * 查询施工验收表管理列表
     * 
     * @param proAccept 施工验收表管理
     * @return 施工验收表管理集合
     */
    public List<ProAccept> selectProAcceptList(ProAccept proAccept);

    /**
     * 新增施工验收表管理
     * 
     * @param proAccept 施工验收表管理
     * @return 结果
     */
    public int insertProAccept(ProAccept proAccept);

    /**
     * 修改施工验收表管理
     * 
     * @param proAccept 施工验收表管理
     * @return 结果
     */
    public int updateProAccept(ProAccept proAccept);

    /**
     * 批量删除施工验收表管理
     * 
     * @param fileIds 需要删除的施工验收表管理主键集合
     * @return 结果
     */
    public int deleteProAcceptByFileIds(Long[] fileIds);

    /**
     * 删除施工验收表管理信息
     * 
     * @param fileId 施工验收表管理主键
     * @return 结果
     */
    public int deleteProAcceptByFileId(Long fileId);
}
