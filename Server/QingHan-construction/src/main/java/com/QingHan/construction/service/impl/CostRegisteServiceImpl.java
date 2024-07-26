package com.QingHan.construction.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.construction.mapper.CostRegisteMapper;
import com.QingHan.construction.domain.CostRegiste;
import com.QingHan.construction.service.ICostRegisteService;

/**
 * 材料核销Service业务层处理
 * 
 * @author yzm
 * @date 2024-07-05
 */
@Service
public class CostRegisteServiceImpl implements ICostRegisteService 
{
    @Autowired
    private CostRegisteMapper costRegisteMapper;

    /**
     * 查询材料核销
     * 
     * @param registartionId 材料核销主键
     * @return 材料核销
     */
    @Override
    public CostRegiste selectCostRegisteByRegistartionId(Long registartionId)
    {
        return costRegisteMapper.selectCostRegisteByRegistartionId(registartionId);
    }

    /**
     * 查询材料核销列表
     * 
     * @param costRegiste 材料核销
     * @return 材料核销
     */
    @Override
    public List<CostRegiste> selectCostRegisteList(CostRegiste costRegiste)
    {
        return costRegisteMapper.selectCostRegisteList(costRegiste);
    }

    /**
     * 新增材料核销
     * 
     * @param costRegiste 材料核销
     * @return 结果
     */
    @Override
    public int insertCostRegiste(CostRegiste costRegiste)
    {
        return costRegisteMapper.insertCostRegiste(costRegiste);
    }

    /**
     * 修改材料核销
     * 
     * @param costRegiste 材料核销
     * @return 结果
     */
    @Override
    public int updateCostRegiste(CostRegiste costRegiste)
    {
        return costRegisteMapper.updateCostRegiste(costRegiste);
    }

    /**
     * 批量删除材料核销
     * 
     * @param registartionIds 需要删除的材料核销主键
     * @return 结果
     */
    @Override
    public int deleteCostRegisteByRegistartionIds(Long[] registartionIds)
    {
        return costRegisteMapper.deleteCostRegisteByRegistartionIds(registartionIds);
    }

    /**
     * 删除材料核销信息
     * 
     * @param registartionId 材料核销主键
     * @return 结果
     */
    @Override
    public int deleteCostRegisteByRegistartionId(Long registartionId)
    {
        return costRegisteMapper.deleteCostRegisteByRegistartionId(registartionId);
    }
}
