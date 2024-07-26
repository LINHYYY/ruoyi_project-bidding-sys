package com.QingHan.design.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.design.mapper.DesProAcceptMapper;
import com.QingHan.design.domain.DesProAccept;
import com.QingHan.design.service.IDesProAcceptService;

/**
 * 设计队项目验收Service业务层处理
 * 
 * @author linghy
 * @date 2024-07-17
 */
@Service
public class DesProAcceptServiceImpl implements IDesProAcceptService 
{
    @Autowired
    private DesProAcceptMapper desProAcceptMapper;

    /**
     * 查询设计队项目验收
     * 
     * @param fileId 设计队项目验收主键
     * @return 设计队项目验收
     */
    @Override
    public DesProAccept selectDesProAcceptByFileId(Long fileId)
    {
        return desProAcceptMapper.selectDesProAcceptByFileId(fileId);
    }

    /**
     * 查询设计队项目验收列表
     * 
     * @param desProAccept 设计队项目验收
     * @return 设计队项目验收
     */
    @Override
    public List<DesProAccept> selectDesProAcceptList(DesProAccept desProAccept)
    {
        return desProAcceptMapper.selectDesProAcceptList(desProAccept);
    }

    /**
     * 新增设计队项目验收
     * 
     * @param desProAccept 设计队项目验收
     * @return 结果
     */
    @Override
    public int insertDesProAccept(DesProAccept desProAccept)
    {
        return desProAcceptMapper.insertDesProAccept(desProAccept);
    }

    /**
     * 修改设计队项目验收
     * 
     * @param desProAccept 设计队项目验收
     * @return 结果
     */
    @Override
    public int updateDesProAccept(DesProAccept desProAccept)
    {
        return desProAcceptMapper.updateDesProAccept(desProAccept);
    }

    /**
     * 批量删除设计队项目验收
     * 
     * @param fileIds 需要删除的设计队项目验收主键
     * @return 结果
     */
    @Override
    public int deleteDesProAcceptByFileIds(Long[] fileIds)
    {
        return desProAcceptMapper.deleteDesProAcceptByFileIds(fileIds);
    }

    /**
     * 删除设计队项目验收信息
     * 
     * @param fileId 设计队项目验收主键
     * @return 结果
     */
    @Override
    public int deleteDesProAcceptByFileId(Long fileId)
    {
        return desProAcceptMapper.deleteDesProAcceptByFileId(fileId);
    }
}
