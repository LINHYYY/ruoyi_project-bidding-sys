package com.QingHan.design.service.impl;

import java.util.List;
import com.QingHan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.design.mapper.DesAssociationMapper;
import com.QingHan.design.domain.DesAssociation;
import com.QingHan.design.service.IDesAssociationService;

/**
 * 设计类项目审核Service业务层处理
 * 
 * @author ly
 * @date 2024-07-17
 */
@Service
public class DesAssociationServiceImpl implements IDesAssociationService 
{
    @Autowired
    private DesAssociationMapper desAssociationMapper;

    /**
     * 查询设计类项目审核
     * 
     * @param relationId 设计类项目审核主键
     * @return 设计类项目审核
     */
    @Override
    public DesAssociation selectDesAssociationByRelationId(Long relationId)
    {
        return desAssociationMapper.selectDesAssociationByRelationId(relationId);
    }

    /**
     * 查询设计类项目审核列表
     * 
     * @param desAssociation 设计类项目审核
     * @return 设计类项目审核
     */
    @Override
    public List<DesAssociation> selectDesAssociationList(DesAssociation desAssociation)
    {
        return desAssociationMapper.selectDesAssociationList(desAssociation);
    }

    /**
     * 新增设计类项目审核
     * 
     * @param desAssociation 设计类项目审核
     * @return 结果
     */
    @Override
    public int insertDesAssociation(DesAssociation desAssociation)
    {
        desAssociation.setCreateTime(DateUtils.getNowDate());
        return desAssociationMapper.insertDesAssociation(desAssociation);
    }

    /**
     * 修改设计类项目审核
     * 
     * @param desAssociation 设计类项目审核
     * @return 结果
     */
    @Override
    public int updateDesAssociation(DesAssociation desAssociation)
    {
        desAssociation.setUpdateTime(DateUtils.getNowDate());
        return desAssociationMapper.updateDesAssociation(desAssociation);
    }

    /**
     * 批量删除设计类项目审核
     * 
     * @param relationIds 需要删除的设计类项目审核主键
     * @return 结果
     */
    @Override
    public int deleteDesAssociationByRelationIds(Long[] relationIds)
    {
        return desAssociationMapper.deleteDesAssociationByRelationIds(relationIds);
    }

    /**
     * 删除设计类项目审核信息
     * 
     * @param relationId 设计类项目审核主键
     * @return 结果
     */
    @Override
    public int deleteDesAssociationByRelationId(Long relationId)
    {
        return desAssociationMapper.deleteDesAssociationByRelationId(relationId);
    }
}
