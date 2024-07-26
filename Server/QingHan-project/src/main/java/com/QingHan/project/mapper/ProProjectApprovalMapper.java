package com.QingHan.project.mapper;

import java.util.List;
import com.QingHan.project.domain.ProProjectApproval;

/**
 * 项目审核表Mapper接口
 * 
 * @author lbb
 * @date 2024-07-11
 */
public interface ProProjectApprovalMapper 
{
    /**
     * 查询项目审核表
     * 
     * @param approvalId 项目审核表主键
     * @return 项目审核表
     */
    public ProProjectApproval selectProProjectApprovalByApprovalId(Long approvalId);

    /**
     * 查询项目审核表列表
     * 
     * @param proProjectApproval 项目审核表
     * @return 项目审核表集合
     */
    public List<ProProjectApproval> selectProProjectApprovalList(ProProjectApproval proProjectApproval);

    /**
     * 新增项目审核表
     * 
     * @param proProjectApproval 项目审核表
     * @return 结果
     */
    public int insertProProjectApproval(ProProjectApproval proProjectApproval);

    /**
     * 修改项目审核表
     * 
     * @param proProjectApproval 项目审核表
     * @return 结果
     */
    public int updateProProjectApproval(ProProjectApproval proProjectApproval);

    /**
     * 删除项目审核表
     * 
     * @param approvalId 项目审核表主键
     * @return 结果
     */
    public int deleteProProjectApprovalByApprovalId(Long approvalId);

    /**
     * 批量删除项目审核表
     * 
     * @param approvalIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProProjectApprovalByApprovalIds(Long[] approvalIds);
}