package com.QingHan.construction.controller;

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
import com.QingHan.construction.domain.Progress;
import com.QingHan.construction.service.IProgressService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 施工队日志Controller
 * 
 * @author linghy
 * @date 2024-07-10
 */
@RestController
@RequestMapping("/construction/progress")
public class ProgressController extends BaseController
{
    @Autowired
    private IProgressService progressService;

    /**
     * 查询施工队日志列表
     */
    @PreAuthorize("@ss.hasPermi('construction:progress:list')")
    @GetMapping("/list")
    public TableDataInfo list(Progress progress)
    {
        startPage();
        List<Progress> list = progressService.selectProgressList(progress);
        return getDataTable(list);
    }

    /**
     * 导出施工队日志列表
     */
    @PreAuthorize("@ss.hasPermi('construction:progress:export')")
    @Log(title = "施工队日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Progress progress)
    {
        List<Progress> list = progressService.selectProgressList(progress);
        ExcelUtil<Progress> util = new ExcelUtil<Progress>(Progress.class);
        util.exportExcel(response, list, "施工队日志数据");
    }

    /**
     * 获取施工队日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('construction:progress:query')")
    @GetMapping(value = "/{progressId}")
    public AjaxResult getInfo(@PathVariable("progressId") Long progressId)
    {
        return success(progressService.selectProgressByProgressId(progressId));
    }

    /**
     * 新增施工队日志
     */
    @PreAuthorize("@ss.hasPermi('construction:progress:add')")
    @Log(title = "施工队日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Progress progress)
    {
        return toAjax(progressService.insertProgress(progress));
    }

    /**
     * 修改施工队日志
     */
    @PreAuthorize("@ss.hasPermi('construction:progress:edit')")
    @Log(title = "施工队日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Progress progress)
    {
        return toAjax(progressService.updateProgress(progress));
    }

    /**
     * 删除施工队日志
     */
    @PreAuthorize("@ss.hasPermi('construction:progress:remove')")
    @Log(title = "施工队日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{progressIds}")
    public AjaxResult remove(@PathVariable Long[] progressIds)
    {
        return toAjax(progressService.deleteProgressByProgressIds(progressIds));
    }
}
