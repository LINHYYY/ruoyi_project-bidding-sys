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
import com.QingHan.construction.domain.CostRegiste;
import com.QingHan.construction.service.ICostRegisteService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 材料核销Controller
 * 
 * @author yzm
 * @date 2024-07-05
 */
@RestController
@RequestMapping("/construction/registe")
public class CostRegisteController extends BaseController
{
    @Autowired
    private ICostRegisteService costRegisteService;

    /**
     * 查询材料核销列表
     */
    @PreAuthorize("@ss.hasPermi('construction:registe:list')")
    @GetMapping("/list")
    public TableDataInfo list(CostRegiste costRegiste)
    {
        startPage();
        List<CostRegiste> list = costRegisteService.selectCostRegisteList(costRegiste);
        return getDataTable(list);
    }

    /**
     * 导出材料核销列表
     */
    @PreAuthorize("@ss.hasPermi('construction:registe:export')")
    @Log(title = "材料核销", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CostRegiste costRegiste)
    {
        List<CostRegiste> list = costRegisteService.selectCostRegisteList(costRegiste);
        ExcelUtil<CostRegiste> util = new ExcelUtil<CostRegiste>(CostRegiste.class);
        util.exportExcel(response, list, "材料核销数据");
    }

    /**
     * 获取材料核销详细信息
     */
    @PreAuthorize("@ss.hasPermi('construction:registe:query')")
    @GetMapping(value = "/{registartionId}")
    public AjaxResult getInfo(@PathVariable("registartionId") Long registartionId)
    {
        return success(costRegisteService.selectCostRegisteByRegistartionId(registartionId));
    }

    /**
     * 新增材料核销
     */
    @PreAuthorize("@ss.hasPermi('construction:registe:add')")
    @Log(title = "材料核销", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CostRegiste costRegiste)
    {
        return toAjax(costRegisteService.insertCostRegiste(costRegiste));
    }

    /**
     * 修改材料核销
     */
    @PreAuthorize("@ss.hasPermi('construction:registe:edit')")
    @Log(title = "材料核销", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CostRegiste costRegiste)
    {
        return toAjax(costRegisteService.updateCostRegiste(costRegiste));
    }

    /**
     * 删除材料核销
     */
    @PreAuthorize("@ss.hasPermi('construction:registe:remove')")
    @Log(title = "材料核销", businessType = BusinessType.DELETE)
	@DeleteMapping("/{registartionIds}")
    public AjaxResult remove(@PathVariable Long[] registartionIds)
    {
        return toAjax(costRegisteService.deleteCostRegisteByRegistartionIds(registartionIds));
    }
}
