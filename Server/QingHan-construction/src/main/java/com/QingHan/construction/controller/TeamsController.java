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
import com.QingHan.construction.domain.Teams;
import com.QingHan.construction.service.ITeamsService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 施工队列表Controller
 * 
 * @author fzy
 * @date 2024-07-05
 */
@RestController
@RequestMapping("/construction/teams")
public class TeamsController extends BaseController
{
    @Autowired
    private ITeamsService teamsService;

    /**
     * 查询施工队列表列表
     */
    @PreAuthorize("@ss.hasPermi('construction:teams:list')")
    @GetMapping("/list")
    public TableDataInfo list(Teams teams)
    {
        startPage();
        List<Teams> list = teamsService.selectTeamsList(teams);
        return getDataTable(list);
    }

    /**
     * 导出施工队列表列表
     */
    @PreAuthorize("@ss.hasPermi('construction:teams:export')")
    @Log(title = "施工队列表", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Teams teams)
    {
        List<Teams> list = teamsService.selectTeamsList(teams);
        ExcelUtil<Teams> util = new ExcelUtil<Teams>(Teams.class);
        util.exportExcel(response, list, "施工队列表数据");
    }

    /**
     * 获取施工队列表详细信息
     */
    @PreAuthorize("@ss.hasPermi('construction:teams:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return success(teamsService.selectTeamsByTeamId(teamId));
    }

    /**
     * 新增施工队列表
     */
    @PreAuthorize("@ss.hasPermi('construction:teams:add')")
    @Log(title = "施工队列表", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Teams teams)
    {
        return toAjax(teamsService.insertTeams(teams));
    }

    /**
     * 修改施工队列表
     */
    @PreAuthorize("@ss.hasPermi('construction:teams:edit')")
    @Log(title = "施工队列表", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Teams teams)
    {
        return toAjax(teamsService.updateTeams(teams));
    }

    /**
     * 删除施工队列表
     */
    @PreAuthorize("@ss.hasPermi('construction:teams:remove')")
    @Log(title = "施工队列表", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        return toAjax(teamsService.deleteTeamsByTeamIds(teamIds));
    }
}
