package com.QingHan.project.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.QingHan.common.annotation.Log;
import com.QingHan.common.core.controller.BaseController;
import com.QingHan.common.core.domain.AjaxResult;
import com.QingHan.common.enums.BusinessType;
import com.QingHan.project.domain.ProBidReviewDetails;
import com.QingHan.project.service.IProBidReviewDetailsService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 投标审核详细信息Controller
 * 
 * @author cph
 * @date 2024-07-12
 */
@RestController
@RequestMapping("/project/bid_review_details")
public class ProBidReviewDetailsController extends BaseController
{
    @Autowired
    private IProBidReviewDetailsService proBidReviewDetailsService;

    /**
     * 查询投标审核详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:bid_review_details:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProBidReviewDetails proBidReviewDetails)
    {
        startPage();
        List<ProBidReviewDetails> list = proBidReviewDetailsService.selectProBidReviewDetailsList(proBidReviewDetails);
        return getDataTable(list);
    }

    /**
     * 导出投标审核详细信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:bid_review_details:export')")
    @Log(title = "投标审核详细信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProBidReviewDetails proBidReviewDetails)
    {
        List<ProBidReviewDetails> list = proBidReviewDetailsService.selectProBidReviewDetailsList(proBidReviewDetails);
        ExcelUtil<ProBidReviewDetails> util = new ExcelUtil<ProBidReviewDetails>(ProBidReviewDetails.class);
        util.exportExcel(response, list, "投标审核详细信息数据");
    }

    /**
     * 获取投标审核详细信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:bid_review_details:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(proBidReviewDetailsService.selectProBidReviewDetailsByDetailId(detailId));
    }

    /**
     * 新增投标审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:bid_review_details:add')")
    @Log(title = "投标审核详细信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProBidReviewDetails proBidReviewDetails)
    {
        return toAjax(proBidReviewDetailsService.insertProBidReviewDetails(proBidReviewDetails));
    }

    /**
     * 修改投标审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:bid_review_details:edit')")
    @Log(title = "投标审核详细信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProBidReviewDetails proBidReviewDetails)
    {
        return toAjax(proBidReviewDetailsService.updateProBidReviewDetails(proBidReviewDetails));
    }

    /**
     * 删除投标审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:bid_review_details:remove')")
    @Log(title = "投标审核详细信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(proBidReviewDetailsService.deleteProBidReviewDetailsByDetailIds(detailIds));
    }
}
