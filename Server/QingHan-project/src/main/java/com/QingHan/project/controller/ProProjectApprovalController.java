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
import com.QingHan.project.domain.ProProjectApproval;
import com.QingHan.project.service.IProProjectApprovalService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 项目审核表Controller
 * 
 * @author lbb
 * @date 2024-07-11
 */
@RestController
@RequestMapping("/project/approval")
public class ProProjectApprovalController extends BaseController
{
    @Autowired
    private IProProjectApprovalService proProjectApprovalService;

    /**
     * 查询项目审核表列表
     */
    @PreAuthorize("@ss.hasPermi('project:approval:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProProjectApproval proProjectApproval)
    {
        startPage();
        List<ProProjectApproval> list = proProjectApprovalService.selectProProjectApprovalList(proProjectApproval);
        return getDataTable(list);
    }

    /**
     * 导出项目审核表列表
     */
    @PreAuthorize("@ss.hasPermi('project:approval:export')")
    @Log(title = "项目审核表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProProjectApproval proProjectApproval)
    {
        List<ProProjectApproval> list = proProjectApprovalService.selectProProjectApprovalList(proProjectApproval);
        ExcelUtil<ProProjectApproval> util = new ExcelUtil<ProProjectApproval>(ProProjectApproval.class);
        util.exportExcel(response, list, "项目审核表数据");
    }

    /**
     * 获取项目审核表详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:approval:query')")
    @GetMapping(value = "/{approvalId}")
    public AjaxResult getInfo(@PathVariable("approvalId") Long approvalId)
    {
        return success(proProjectApprovalService.selectProProjectApprovalByApprovalId(approvalId));
    }

    /**
     * 新增项目审核表
     */
    @PreAuthorize("@ss.hasPermi('project:approval:add')")
    @Log(title = "项目审核表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProProjectApproval proProjectApproval)
    {
        return toAjax(proProjectApprovalService.insertProProjectApproval(proProjectApproval));
    }

    /**
     * 修改项目审核表
     */
    @PreAuthorize("@ss.hasPermi('project:approval:edit')")
    @Log(title = "项目审核表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProProjectApproval proProjectApproval)
    {
        return toAjax(proProjectApprovalService.updateProProjectApproval(proProjectApproval));
    }

    /**
     * 删除项目审核表
     */
    @PreAuthorize("@ss.hasPermi('project:approval:remove')")
    @Log(title = "项目审核表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{approvalIds}")
    public AjaxResult remove(@PathVariable Long[] approvalIds)
    {
        return toAjax(proProjectApprovalService.deleteProProjectApprovalByApprovalIds(approvalIds));
    }
}
