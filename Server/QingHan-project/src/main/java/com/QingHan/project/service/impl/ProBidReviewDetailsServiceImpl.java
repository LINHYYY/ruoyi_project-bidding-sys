package com.QingHan.project.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.project.mapper.ProBidReviewDetailsMapper;
import com.QingHan.project.domain.ProBidReviewDetails;
import com.QingHan.project.service.IProBidReviewDetailsService;

/**
 * 投标审核详细信息Service业务层处理
 * 
 * @author cph
 * @date 2024-07-12
 */
@Service
public class ProBidReviewDetailsServiceImpl implements IProBidReviewDetailsService 
{
    @Autowired
    private ProBidReviewDetailsMapper proBidReviewDetailsMapper;

    /**
     * 查询投标审核详细信息
     * 
     * @param detailId 投标审核详细信息主键
     * @return 投标审核详细信息
     */
    @Override
    public ProBidReviewDetails selectProBidReviewDetailsByDetailId(Long detailId)
    {
        return proBidReviewDetailsMapper.selectProBidReviewDetailsByDetailId(detailId);
    }

    /**
     * 查询投标审核详细信息列表
     * 
     * @param proBidReviewDetails 投标审核详细信息
     * @return 投标审核详细信息
     */
    @Override
    public List<ProBidReviewDetails> selectProBidReviewDetailsList(ProBidReviewDetails proBidReviewDetails)
    {
        return proBidReviewDetailsMapper.selectProBidReviewDetailsList(proBidReviewDetails);
    }

    /**
     * 新增投标审核详细信息
     * 
     * @param proBidReviewDetails 投标审核详细信息
     * @return 结果
     */
    @Override
    public int insertProBidReviewDetails(ProBidReviewDetails proBidReviewDetails)
    {
        return proBidReviewDetailsMapper.insertProBidReviewDetails(proBidReviewDetails);
    }

    /**
     * 修改投标审核详细信息
     * 
     * @param proBidReviewDetails 投标审核详细信息
     * @return 结果
     */
    @Override
    public int updateProBidReviewDetails(ProBidReviewDetails proBidReviewDetails)
    {
        return proBidReviewDetailsMapper.updateProBidReviewDetails(proBidReviewDetails);
    }

    /**
     * 批量删除投标审核详细信息
     * 
     * @param detailIds 需要删除的投标审核详细信息主键
     * @return 结果
     */
    @Override
    public int deleteProBidReviewDetailsByDetailIds(Long[] detailIds)
    {
        return proBidReviewDetailsMapper.deleteProBidReviewDetailsByDetailIds(detailIds);
    }

    /**
     * 删除投标审核详细信息信息
     * 
     * @param detailId 投标审核详细信息主键
     * @return 结果
     */
    @Override
    public int deleteProBidReviewDetailsByDetailId(Long detailId)
    {
        return proBidReviewDetailsMapper.deleteProBidReviewDetailsByDetailId(detailId);
    }
}
