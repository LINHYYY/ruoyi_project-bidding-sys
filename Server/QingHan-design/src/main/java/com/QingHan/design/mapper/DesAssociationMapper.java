package com.QingHan.design.mapper;

import java.util.List;
import com.QingHan.design.domain.DesAssociation;

/**
 * 设计类项目审核Mapper接口
 * 
 * @author ly
 * @date 2024-07-17
 */
public interface DesAssociationMapper 
{
    /**
     * 查询设计类项目审核
     * 
     * @param relationId 设计类项目审核主键
     * @return 设计类项目审核
     */
    public DesAssociation selectDesAssociationByRelationId(Long relationId);

    /**
     * 查询设计类项目审核列表
     * 
     * @param desAssociation 设计类项目审核
     * @return 设计类项目审核集合
     */
    public List<DesAssociation> selectDesAssociationList(DesAssociation desAssociation);

    /**
     * 新增设计类项目审核
     * 
     * @param desAssociation 设计类项目审核
     * @return 结果
     */
    public int insertDesAssociation(DesAssociation desAssociation);

    /**
     * 修改设计类项目审核
     * 
     * @param desAssociation 设计类项目审核
     * @return 结果
     */
    public int updateDesAssociation(DesAssociation desAssociation);

    /**
     * 删除设计类项目审核
     * 
     * @param relationId 设计类项目审核主键
     * @return 结果
     */
    public int deleteDesAssociationByRelationId(Long relationId);

    /**
     * 批量删除设计类项目审核
     * 
     * @param relationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDesAssociationByRelationIds(Long[] relationIds);
}
