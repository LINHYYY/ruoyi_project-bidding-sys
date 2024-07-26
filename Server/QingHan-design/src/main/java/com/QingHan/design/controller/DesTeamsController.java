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
import com.QingHan.design.domain.DesTeams;
import com.QingHan.design.service.IDesTeamsService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 设计队列表Controller
 * 
 * @author linghy
 * @date 2024-07-17
 */
@RestController
@RequestMapping("/design/teams")
public class DesTeamsController extends BaseController
{
    @Autowired
    private IDesTeamsService desTeamsService;

    /**
     * 查询设计队列表列表
     */
    @PreAuthorize("@ss.hasPermi('design:teams:list')")
    @GetMapping("/list")
    public TableDataInfo list(DesTeams desTeams)
    {
        startPage();
        List<DesTeams> list = desTeamsService.selectDesTeamsList(desTeams);
        return getDataTable(list);
    }

    /**
     * 导出设计队列表列表
     */
    @PreAuthorize("@ss.hasPermi('design:teams:export')")
    @Log(title = "设计队列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DesTeams desTeams)
    {
        List<DesTeams> list = desTeamsService.selectDesTeamsList(desTeams);
        ExcelUtil<DesTeams> util = new ExcelUtil<DesTeams>(DesTeams.class);
        util.exportExcel(response, list, "设计队列表数据");
    }

    /**
     * 获取设计队列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('design:teams:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return success(desTeamsService.selectDesTeamsByTeamId(teamId));
    }

    /**
     * 新增设计队列表
     */
    @PreAuthorize("@ss.hasPermi('design:teams:add')")
    @Log(title = "设计队列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DesTeams desTeams)
    {
        return toAjax(desTeamsService.insertDesTeams(desTeams));
    }

    /**
     * 修改设计队列表
     */
    @PreAuthorize("@ss.hasPermi('design:teams:edit')")
    @Log(title = "设计队列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DesTeams desTeams)
    {
        return toAjax(desTeamsService.updateDesTeams(desTeams));
    }

    /**
     * 删除设计队列表
     */
    @PreAuthorize("@ss.hasPermi('design:teams:remove')")
    @Log(title = "设计队列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        return toAjax(desTeamsService.deleteDesTeamsByTeamIds(teamIds));
    }
}
