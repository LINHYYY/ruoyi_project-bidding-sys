package com.QingHan.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.project.mapper.ProProjectApprovalMapper;
import com.QingHan.project.domain.ProProjectApproval;
import com.QingHan.project.service.IProProjectApprovalService;

/**
 * 项目审核表Service业务层处理
 * 
 * @author lbb
 * @date 2024-07-11
 */
@Service
public class ProProjectApprovalServiceImpl implements IProProjectApprovalService 
{
    @Autowired
    private ProProjectApprovalMapper proProjectApprovalMapper;

    /**
     * 查询项目审核表
     * 
     * @param approvalId 项目审核表主键
     * @return 项目审核表
     */
    @Override
    public ProProjectApproval selectProProjectApprovalByApprovalId(Long approvalId)
    {
        return proProjectApprovalMapper.selectProProjectApprovalByApprovalId(approvalId);
    }

    /**
     * 查询项目审核表列表
     * 
     * @param proProjectApproval 项目审核表
     * @return 项目审核表
     */
    @Override
    public List<ProProjectApproval> selectProProjectApprovalList(ProProjectApproval proProjectApproval)
    {
        return proProjectApprovalMapper.selectProProjectApprovalList(proProjectApproval);
    }

    /**
     * 新增项目审核表
     * 
     * @param proProjectApproval 项目审核表
     * @return 结果
     */
    @Override
    public int insertProProjectApproval(ProProjectApproval proProjectApproval)
    {
        return proProjectApprovalMapper.insertProProjectApproval(proProjectApproval);
    }

    /**
     * 修改项目审核表
     * 
     * @param proProjectApproval 项目审核表
     * @return 结果
     */
    @Override
    public int updateProProjectApproval(ProProjectApproval proProjectApproval)
    {
        return proProjectApprovalMapper.updateProProjectApproval(proProjectApproval);
    }

    /**
     * 批量删除项目审核表
     * 
     * @param approvalIds 需要删除的项目审核表主键
     * @return 结果
     */
    @Override
    public int deleteProProjectApprovalByApprovalIds(Long[] approvalIds)
    {
        return proProjectApprovalMapper.deleteProProjectApprovalByApprovalIds(approvalIds);
    }

    /**
     * 删除项目审核表信息
     * 
     * @param approvalId 项目审核表主键
     * @return 结果
     */
    @Override
    public int deleteProProjectApprovalByApprovalId(Long approvalId)
    {
        return proProjectApprovalMapper.deleteProProjectApprovalByApprovalId(approvalId);
    }
}
