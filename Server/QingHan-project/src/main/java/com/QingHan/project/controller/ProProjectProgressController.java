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
import com.QingHan.project.domain.ProProjectProgress;
import com.QingHan.project.service.IProProjectProgressService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 项目进程Controller
 * 
 * @author cph
 * @date 2024-07-12
 */
@RestController
@RequestMapping("/project/ProProjectProgress")
public class ProProjectProgressController extends BaseController
{
    @Autowired
    private IProProjectProgressService proProjectProgressService;

    /**
     * 查询项目进程列表
     */
    @PreAuthorize("@ss.hasPermi('project:ProProjectProgress:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProProjectProgress proProjectProgress)
    {
        startPage();
        List<ProProjectProgress> list = proProjectProgressService.selectProProjectProgressList(proProjectProgress);
        return getDataTable(list);
    }

    /**
     * 导出项目进程列表
     */
    @PreAuthorize("@ss.hasPermi('project:ProProjectProgress:export')")
    @Log(title = "项目进程", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProProjectProgress proProjectProgress)
    {
        List<ProProjectProgress> list = proProjectProgressService.selectProProjectProgressList(proProjectProgress);
        ExcelUtil<ProProjectProgress> util = new ExcelUtil<ProProjectProgress>(ProProjectProgress.class);
        util.exportExcel(response, list, "项目进程数据");
    }

    /**
     * 获取项目进程详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:ProProjectProgress:query')")
    @GetMapping(value = "/{progressId}")
    public AjaxResult getInfo(@PathVariable("progressId") Long progressId)
    {
        return success(proProjectProgressService.selectProProjectProgressByProgressId(progressId));
    }

    /**
     * 新增项目进程
     */
    @PreAuthorize("@ss.hasPermi('project:ProProjectProgress:add')")
    @Log(title = "项目进程", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProProjectProgress proProjectProgress)
    {
        return toAjax(proProjectProgressService.insertProProjectProgress(proProjectProgress));
    }

    /**
     * 修改项目进程
     */
    @PreAuthorize("@ss.hasPermi('project:ProProjectProgress:edit')")
    @Log(title = "项目进程", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProProjectProgress proProjectProgress)
    {
        return toAjax(proProjectProgressService.updateProProjectProgress(proProjectProgress));
    }

    /**
     * 删除项目进程
     */
    @PreAuthorize("@ss.hasPermi('project:ProProjectProgress:remove')")
    @Log(title = "项目进程", businessType = BusinessType.DELETE)
	@DeleteMapping("/{progressIds}")
    public AjaxResult remove(@PathVariable Long[] progressIds)
    {
        return toAjax(proProjectProgressService.deleteProProjectProgressByProgressIds(progressIds));
    }
}
