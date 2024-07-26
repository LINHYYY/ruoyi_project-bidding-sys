package com.QingHan.construction.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.construction.mapper.ProAcceptMapper;
import com.QingHan.construction.domain.ProAccept;
import com.QingHan.construction.service.IProAcceptService;

/**
 * 施工验收表管理Service业务层处理
 * 
 * @author yzm
 * @date 2024-07-08
 */
@Service
public class ProAcceptServiceImpl implements IProAcceptService 
{
    @Autowired
    private ProAcceptMapper proAcceptMapper;

    /**
     * 查询施工验收表管理
     * 
     * @param fileId 施工验收表管理主键
     * @return 施工验收表管理
     */
    @Override
    public ProAccept selectProAcceptByFileId(Long fileId)
    {
        return proAcceptMapper.selectProAcceptByFileId(fileId);
    }

    /**
     * 查询施工验收表管理列表
     * 
     * @param proAccept 施工验收表管理
     * @return 施工验收表管理
     */
    @Override
    public List<ProAccept> selectProAcceptList(ProAccept proAccept)
    {
        return proAcceptMapper.selectProAcceptList(proAccept);
    }

    /**
     * 新增施工验收表管理
     * 
     * @param proAccept 施工验收表管理
     * @return 结果
     */
    @Override
    public int insertProAccept(ProAccept proAccept)
    {
        return proAcceptMapper.insertProAccept(proAccept);
    }

    /**
     * 修改施工验收表管理
     * 
     * @param proAccept 施工验收表管理
     * @return 结果
     */
    @Override
    public int updateProAccept(ProAccept proAccept)
    {
        return proAcceptMapper.updateProAccept(proAccept);
    }

    /**
     * 批量删除施工验收表管理
     * 
     * @param fileIds 需要删除的施工验收表管理主键
     * @return 结果
     */
    @Override
    public int deleteProAcceptByFileIds(Long[] fileIds)
    {
        return proAcceptMapper.deleteProAcceptByFileIds(fileIds);
    }

    /**
     * 删除施工验收表管理信息
     * 
     * @param fileId 施工验收表管理主键
     * @return 结果
     */
    @Override
    public int deleteProAcceptByFileId(Long fileId)
    {
        return proAcceptMapper.deleteProAcceptByFileId(fileId);
    }
}
