package com.QingHan.construction.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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
import com.QingHan.construction.domain.ProAccept;
import com.QingHan.construction.service.IProAcceptService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;
import org.springframework.core.io.Resource;

/**
 * 施工验收表管理Controller
 * 
 * @author yzm
 * @date 2024-07-08
 */
@RestController
@RequestMapping("/construction/accept")
public class ProAcceptController extends BaseController
{
    @Autowired
    private IProAcceptService proAcceptService;

    /**
     * 查询施工验收表管理列表
     */
    @PreAuthorize("@ss.hasPermi('construction:accept:list')")
    @GetMapping("/list")
    public TableDataInfo list(ProAccept proAccept)
    {
        startPage();
        List<ProAccept> list = proAcceptService.selectProAcceptList(proAccept);
        return getDataTable(list);
    }

    /**
     * 导出施工验收表管理列表
     */
    @PreAuthorize("@ss.hasPermi('construction:accept:export')")
    @Log(title = "施工验收表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ProAccept proAccept)
    {
        List<ProAccept> list = proAcceptService.selectProAcceptList(proAccept);
        ExcelUtil<ProAccept> util = new ExcelUtil<ProAccept>(ProAccept.class);
        util.exportExcel(response, list, "施工验收表管理数据");
    }

    /**
     * 获取施工验收表管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('construction:accept:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long fileId)
    {
        return success(proAcceptService.selectProAcceptByFileId(fileId));
    }

    /**
     * 新增施工验收表管理
     */
    @PreAuthorize("@ss.hasPermi('construction:accept:add')")
    @Log(title = "施工验收表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ProAccept proAccept)
    {
        return toAjax(proAcceptService.insertProAccept(proAccept));
    }

    /**
     * 修改施工验收表管理
     */
    @PreAuthorize("@ss.hasPermi('construction:accept:edit')")
    @Log(title = "施工验收表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ProAccept proAccept)
    {
        return toAjax(proAcceptService.updateProAccept(proAccept));
    }

    /**
     * 删除施工验收表管理
     */
    @PreAuthorize("@ss.hasPermi('construction:accept:remove')")
    @Log(title = "施工验收表管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds)
    {
        return toAjax(proAcceptService.deleteProAcceptByFileIds(fileIds));
    }

    @GetMapping("/download/{fileId}")
    public ResponseEntity<Resource> downloadFile(@PathVariable Long fileId) {
        ProAccept proAccept = proAcceptService.selectProAcceptByFileId(fileId);
        if (proAccept == null || proAccept.getFileUrl() == null) {
            throw new RuntimeException("文件不存在，请检查fileId是否正确");
        }

        // 构造文件的完整路径
        String realPath = "D:/ruoyi/uploadPath" + proAccept.getFileUrl(); // 确保这是文件的实际路径
        Resource resource = new FileSystemResource(realPath);
        if (!resource.exists() || !resource.isReadable()) {
            throw new RuntimeException("文件不存在或不可读: " + realPath);
        }

        try {
            // 设置HTTP响应头
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + proAccept.getFileName());
            headers.add(HttpHeaders.CONTENT_TYPE, "application/octet-stream; charset=UTF-8");

            // 返回ResponseEntity对象
            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("文件路径非法: " + realPath, e);
        }
    }
}
