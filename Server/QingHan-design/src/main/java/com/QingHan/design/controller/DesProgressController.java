package com.QingHan.design.controller;

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
import com.QingHan.design.domain.DesProgress;
import com.QingHan.design.service.IDesProgressService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 设计队设计日志Controller
 * 
 * @author linghy
 * @date 2024-07-17
 */
@RestController
@RequestMapping("/design/Desprogress")
public class DesProgressController extends BaseController
{
    @Autowired
    private IDesProgressService desProgressService;

    /**
     * 查询设计队设计日志列表
     */
    @PreAuthorize("@ss.hasPermi('design:Desprogress:list')")
    @GetMapping("/list")
    public TableDataInfo list(DesProgress desProgress)
    {
        startPage();
        List<DesProgress> list = desProgressService.selectDesProgressList(desProgress);
        return getDataTable(list);
    }

    /**
     * 导出设计队设计日志列表
     */
    @PreAuthorize("@ss.hasPermi('design:Desprogress:export')")
    @Log(title = "设计队设计日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DesProgress desProgress)
    {
        List<DesProgress> list = desProgressService.selectDesProgressList(desProgress);
        ExcelUtil<DesProgress> util = new ExcelUtil<DesProgress>(DesProgress.class);
        util.exportExcel(response, list, "设计队设计日志数据");
    }

    /**
     * 获取设计队设计日志详细信息
     */
    @PreAuthorize("@ss.hasPermi('design:Desprogress:query')")
    @GetMapping(value = "/{progressId}")
    public AjaxResult getInfo(@PathVariable("progressId") Long progressId)
    {
        return success(desProgressService.selectDesProgressByProgressId(progressId));
    }

    /**
     * 新增设计队设计日志
     */
    @PreAuthorize("@ss.hasPermi('design:Desprogress:add')")
    @Log(title = "设计队设计日志", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DesProgress desProgress)
    {
        return toAjax(desProgressService.insertDesProgress(desProgress));
    }

    /**
     * 修改设计队设计日志
     */
    @PreAuthorize("@ss.hasPermi('design:Desprogress:edit')")
    @Log(title = "设计队设计日志", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DesProgress desProgress)
    {
        return toAjax(desProgressService.updateDesProgress(desProgress));
    }

    /**
     * 删除设计队设计日志
     */
    @PreAuthorize("@ss.hasPermi('design:Desprogress:remove')")
    @Log(title = "设计队设计日志", businessType = BusinessType.DELETE)
	@DeleteMapping("/{progressIds}")
    public AjaxResult remove(@PathVariable Long[] progressIds)
    {
        return toAjax(desProgressService.deleteDesProgressByProgressIds(progressIds));
    }
}
