package com.QingHan.design.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.QingHan.design.domain.DesAssociation;
import com.QingHan.design.service.IDesAssociationService;
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
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 设计类项目审核Controller
 * 
 * @author ly
 * @date 2024-07-17
 */
@RestController
@RequestMapping("/design/Desassociation")
public class DesAssociationController extends BaseController
{
    @Autowired
    private IDesAssociationService desAssociationService;

    /**
     * 查询设计类项目审核列表
     */
    @PreAuthorize("@ss.hasPermi('design:Desassociation:list')")
    @GetMapping("/list")
    public TableDataInfo list(DesAssociation desAssociation)
    {
        startPage();
        List<DesAssociation> list = desAssociationService.selectDesAssociationList(desAssociation);
        return getDataTable(list);
    }

    /**
     * 导出设计类项目审核列表
     */
    @PreAuthorize("@ss.hasPermi('design:Desassociation:export')")
    @Log(title = "设计类项目审核", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DesAssociation desAssociation)
    {
        List<DesAssociation> list = desAssociationService.selectDesAssociationList(desAssociation);
        ExcelUtil<DesAssociation> util = new ExcelUtil<DesAssociation>(DesAssociation.class);
        util.exportExcel(response, list, "设计类项目审核数据");
    }

    /**
     * 获取设计类项目审核详细信息
     */
    @PreAuthorize("@ss.hasPermi('design:Desassociation:query')")
    @GetMapping(value = "/{relationId}")
    public AjaxResult getInfo(@PathVariable("relationId") Long relationId)
    {
        return success(desAssociationService.selectDesAssociationByRelationId(relationId));
    }

    /**
     * 新增设计类项目审核
     */
    @PreAuthorize("@ss.hasPermi('design:Desassociation:add')")
    @Log(title = "设计类项目审核", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DesAssociation desAssociation)
    {
        return toAjax(desAssociationService.insertDesAssociation(desAssociation));
    }

    /**
     * 修改设计类项目审核
     */
    @PreAuthorize("@ss.hasPermi('design:Desassociation:edit')")
    @Log(title = "设计类项目审核", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DesAssociation desAssociation)
    {
        return toAjax(desAssociationService.updateDesAssociation(desAssociation));
    }

    /**
     * 删除设计类项目审核
     */
    @PreAuthorize("@ss.hasPermi('design:Desassociation:remove')")
    @Log(title = "设计类项目审核", businessType = BusinessType.DELETE)
	@DeleteMapping("/{relationIds}")
    public AjaxResult remove(@PathVariable Long[] relationIds)
    {
        return toAjax(desAssociationService.deleteDesAssociationByRelationIds(relationIds));
    }
}
