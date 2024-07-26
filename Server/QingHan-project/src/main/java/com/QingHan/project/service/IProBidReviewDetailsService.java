package com.QingHan.project.service;

import java.util.List;
import com.QingHan.project.domain.ProBidReviewDetails;

/**
 * 投标审核详细信息Service接口
 * 
 * @author cph
 * @date 2024-07-12
 */
public interface IProBidReviewDetailsService 
{
    /**
     * 查询投标审核详细信息
     * 
     * @param detailId 投标审核详细信息主键
     * @return 投标审核详细信息
     */
    public ProBidReviewDetails selectProBidReviewDetailsByDetailId(Long detailId);

    /**
     * 查询投标审核详细信息列表
     * 
     * @param proBidReviewDetails 投标审核详细信息
     * @return 投标审核详细信息集合
     */
    public List<ProBidReviewDetails> selectProBidReviewDetailsList(ProBidReviewDetails proBidReviewDetails);

    /**
     * 新增投标审核详细信息
     * 
     * @param proBidReviewDetails 投标审核详细信息
     * @return 结果
     */
    public int insertProBidReviewDetails(ProBidReviewDetails proBidReviewDetails);

    /**
     * 修改投标审核详细信息
     * 
     * @param proBidReviewDetails 投标审核详细信息
     * @return 结果
     */
    public int updateProBidReviewDetails(ProBidReviewDetails proBidReviewDetails);

    /**
     * 批量删除投标审核详细信息
     * 
     * @param detailIds 需要删除的投标审核详细信息主键集合
     * @return 结果
     */
    public int deleteProBidReviewDetailsByDetailIds(Long[] detailIds);

    /**
     * 删除投标审核详细信息信息
     * 
     * @param detailId 投标审核详细信息主键
     * @return 结果
     */
    public int deleteProBidReviewDetailsByDetailId(Long detailId);
}
