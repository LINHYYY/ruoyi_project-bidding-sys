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
import com.QingHan.design.domain.DesProAccept;
import com.QingHan.design.service.IDesProAcceptService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 设计队项目验收Controller
 * 
 * @author linghy
 * @date 2024-07-17
 */
@RestController
@RequestMapping("/design/Desaccept")
public class DesProAcceptController extends BaseController
{
    @Autowired
    private IDesProAcceptService desProAcceptService;

    /**
     * 查询设计队项目验收列表
     */
    @PreAuthorize("@ss.hasPermi('design:Desaccept:list')")
    @GetMapping("/list")
    public TableDataInfo list(DesProAccept desProAccept)
    {
        startPage();
        List<DesProAccept> list = desProAcceptService.selectDesProAcceptList(desProAccept);
        return getDataTable(list);
    }

    /**
     * 导出设计队项目验收列表
     */
    @PreAuthorize("@ss.hasPermi('design:Desaccept:export')")
    @Log(title = "设计队项目验收", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DesProAccept desProAccept)
    {
        List<DesProAccept> list = desProAcceptService.selectDesProAcceptList(desProAccept);
        ExcelUtil<DesProAccept> util = new ExcelUtil<DesProAccept>(DesProAccept.class);
        util.exportExcel(response, list, "设计队项目验收数据");
    }

    /**
     * 获取设计队项目验收详细信息
     */
    @PreAuthorize("@ss.hasPermi('design:Desaccept:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(desProAcceptService.selectDesProAcceptByFileId(fileId));
    }

    /**
     * 新增设计队项目验收
     */
    @PreAuthorize("@ss.hasPermi('design:Desaccept:add')")
    @Log(title = "设计队项目验收", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DesProAccept desProAccept)
    {
        return toAjax(desProAcceptService.insertDesProAccept(desProAccept));
    }

    /**
     * 修改设计队项目验收
     */
    @PreAuthorize("@ss.hasPermi('design:Desaccept:edit')")
    @Log(title = "设计队项目验收", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DesProAccept desProAccept)
    {
        return toAjax(desProAcceptService.updateDesProAccept(desProAccept));
    }

    /**
     * 删除设计队项目验收
     */
    @PreAuthorize("@ss.hasPermi('design:Desaccept:remove')")
    @Log(title = "设计队项目验收", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(desProAcceptService.deleteDesProAcceptByFileIds(fileIds));
    }
}
