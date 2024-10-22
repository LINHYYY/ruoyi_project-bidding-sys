package com.QingHan.construction.service;

import java.util.List;
import com.QingHan.construction.domain.Association;

/**
 * 施工项目状态Service接口
 * 
 * @author fzy
 * @date 2024-07-09
 */
public interface IAssociationService 
{
    /**
     * 查询施工项目状态
     * 
     * @param relationId 施工项目状态主键
     * @return 施工项目状态
     */
    public Association selectAssociationByRelationId(Long relationId);

    /**
     * 查询施工项目状态列表
     * 
     * @param association 施工项目状态
     * @return 施工项目状态集合
     */
    public List<Association> selectAssociationList(Association association);

    /**
     * 新增施工项目状态
     * 
     * @param association 施工项目状态
     * @return 结果
     */
    public int insertAssociation(Association association);

    /**
     * 修改施工项目状态
     * 
     * @param association 施工项目状态
     * @return 结果
     */
    public int updateAssociation(Association association);

    /**
     * 批量删除施工项目状态
     * 
     * @param relationIds 需要删除的施工项目状态主键集合
     * @return 结果
     */
    public int deleteAssociationByRelationIds(Long[] relationIds);

    /**
     * 删除施工项目状态信息
     * 
     * @param relationId 施工项目状态主键
     * @return 结果
     */
    public int deleteAssociationByRelationId(Long relationId);
}
