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
import com.QingHan.construction.domain.Association;
import com.QingHan.construction.service.IAssociationService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 施工项目状态Controller
 * 
 * @author fzy
 * @date 2024-07-09
 */
@RestController
@RequestMapping("/construction/association")
public class AssociationController extends BaseController
{
    @Autowired
    private IAssociationService associationService;

    /**
     * 查询施工项目状态列表
     */
    @PreAuthorize("@ss.hasPermi('construction:association:list')")
    @GetMapping("/list")
    public TableDataInfo list(Association association)
    {
        startPage();
        List<Association> list = associationService.selectAssociationList(association);
        return getDataTable(list);
    }

    /**
     * 导出施工项目状态列表
     */
    @PreAuthorize("@ss.hasPermi('construction:association:export')")
    @Log(title = "施工项目状态", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Association association)
    {
        List<Association> list = associationService.selectAssociationList(association);
        ExcelUtil<Association> util = new ExcelUtil<Association>(Association.class);
        util.exportExcel(response, list, "施工项目状态数据");
    }

    /**
     * 获取施工项目状态详细信息
     */
    @PreAuthorize("@ss.hasPermi('construction:association:query')")
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId)
    {
        return success(associationService.selectAssociationByRelationId(relationId));
    }

    /**
     * 新增施工项目状态
     */
    @PreAuthorize("@ss.hasPermi('construction:association:add')")
    @Log(title = "施工项目状态", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Association association)
    {
        return toAjax(associationService.insertAssociation(association));
    }

    /**
     * 修改施工项目状态
     */
    @PreAuthorize("@ss.hasPermi('construction:association:edit')")
    @Log(title = "施工项目状态", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Association association)
    {
        return toAjax(associationService.updateAssociation(association));
    }

    /**
     * 删除施工项目状态
     */
    @PreAuthorize("@ss.hasPermi('construction:association:remove')")
    @Log(title = "施工项目状态", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds)
    {
        return toAjax(associationService.deleteAssociationByRelationIds(relationIds));
    }
}
