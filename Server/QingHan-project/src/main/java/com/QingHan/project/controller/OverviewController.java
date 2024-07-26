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
import com.QingHan.project.domain.Overview;
import com.QingHan.project.service.IOverviewService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 项目基础信息Controller
 * 
 * @author cph
 * @date 2024-07-11
 */
@RestController
@RequestMapping("/project/overview")
public class OverviewController extends BaseController
{
    @Autowired
    private IOverviewService overviewService;

    /**
     * 查询项目基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:overview:list')")
    @GetMapping("/list")
    public TableDataInfo list(Overview overview)
    {
        startPage();
        List<Overview> list = overviewService.selectOverviewList(overview);
        return getDataTable(list);
    }

    /**
     * 导出项目基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('project:overview:export')")
    @Log(title = "项目基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Overview overview)
    {
        List<Overview> list = overviewService.selectOverviewList(overview);
        ExcelUtil<Overview> util = new ExcelUtil<Overview>(Overview.class);
        util.exportExcel(response, list, "项目基础信息数据");
    }

    /**
     * 获取项目基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('project:overview:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(overviewService.selectOverviewById(id));
    }

    /**
     * 新增项目基础信息
     */
    @PreAuthorize("@ss.hasPermi('project:overview:add')")
    @Log(title = "项目基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Overview overview)
    {
        return toAjax(overviewService.insertOverview(overview));
    }

    /**
     * 修改项目基础信息
     */
    @PreAuthorize("@ss.hasPermi('project:overview:edit')")
    @Log(title = "项目基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Overview overview)
    {
        return toAjax(overviewService.updateOverview(overview));
    }

    /**
     * 删除项目基础信息
     */
    @PreAuthorize("@ss.hasPermi('project:overview:remove')")
    @Log(title = "项目基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(overviewService.deleteOverviewByIds(ids));
    }
}
