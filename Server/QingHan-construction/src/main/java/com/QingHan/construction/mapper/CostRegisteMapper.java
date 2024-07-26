package com.QingHan.construction.mapper;

import java.util.List;
import com.QingHan.construction.domain.CostRegiste;

/**
 * 材料核销Mapper接口
 * 
 * @author yzm
 * @date 2024-07-05
 */
public interface CostRegisteMapper 
{
    /**
     * 查询材料核销
     * 
     * @param registartionId 材料核销主键
     * @return 材料核销
     */
    public CostRegiste selectCostRegisteByRegistartionId(Long registartionId);

    /**
     * 查询材料核销列表
     * 
     * @param costRegiste 材料核销
     * @return 材料核销集合
     */
    public List<CostRegiste> selectCostRegisteList(CostRegiste costRegiste);

    /**
     * 新增材料核销
     * 
     * @param costRegiste 材料核销
     * @return 结果
     */
    public int insertCostRegiste(CostRegiste costRegiste);

    /**
     * 修改材料核销
     * 
     * @param costRegiste 材料核销
     * @return 结果
     */
    public int updateCostRegiste(CostRegiste costRegiste);

    /**
     * 删除材料核销
     * 
     * @param registartionId 材料核销主键
     * @return 结果
     */
    public int deleteCostRegisteByRegistartionId(Long registartionId);

    /**
     * 批量删除材料核销
     * 
     * @param registartionIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCostRegisteByRegistartionIds(Long[] registartionIds);
}
