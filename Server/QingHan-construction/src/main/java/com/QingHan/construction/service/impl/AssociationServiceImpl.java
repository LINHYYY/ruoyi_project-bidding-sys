package com.QingHan.construction.service.impl;

import java.util.List;
import com.QingHan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.construction.mapper.AssociationMapper;
import com.QingHan.construction.domain.Association;
import com.QingHan.construction.service.IAssociationService;

/**
 * 施工项目状态Service业务层处理
 * 
 * @author fzy
 * @date 2024-07-09
 */
@Service
public class AssociationServiceImpl implements IAssociationService 
{
    @Autowired
    private AssociationMapper associationMapper;

    /**
     * 查询施工项目状态
     * 
     * @param relationId 施工项目状态主键
     * @return 施工项目状态
     */
    @Override
    public Association selectAssociationByRelationId(Long relationId)
    {
        return associationMapper.selectAssociationByRelationId(relationId);
    }

    /**
     * 查询施工项目状态列表
     * 
     * @param association 施工项目状态
     * @return 施工项目状态
     */
    @Override
    public List<Association> selectAssociationList(Association association)
    {
        return associationMapper.selectAssociationList(association);
    }

    /**
     * 新增施工项目状态
     * 
     * @param association 施工项目状态
     * @return 结果
     */
    @Override
    public int insertAssociation(Association association)
    {
        association.setCreateTime(DateUtils.getNowDate());
        return associationMapper.insertAssociation(association);
    }

    /**
     * 修改施工项目状态
     * 
     * @param association 施工项目状态
     * @return 结果
     */
    @Override
    public int updateAssociation(Association association)
    {
        association.setUpdateTime(DateUtils.getNowDate());
        return associationMapper.updateAssociation(association);
    }

    /**
     * 批量删除施工项目状态
     * 
     * @param relationIds 需要删除的施工项目状态主键
     * @return 结果
     */
    @Override
    public int deleteAssociationByRelationIds(Long[] relationIds)
    {
        return associationMapper.deleteAssociationByRelationIds(relationIds);
    }

    /**
     * 删除施工项目状态信息
     * 
     * @param relationId 施工项目状态主键
     * @return 结果
     */
    @Override
    public int deleteAssociationByRelationId(Long relationId)
    {
        return associationMapper.deleteAssociationByRelationId(relationId);
    }
}
