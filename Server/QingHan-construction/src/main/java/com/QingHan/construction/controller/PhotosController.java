package com.QingHan.construction.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.QingHan.construction.domain.Photos;
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
import com.QingHan.construction.service.IPhotosService;
import com.QingHan.common.utils.poi.ExcelUtil;
import com.QingHan.common.core.page.TableDataInfo;

/**
 * 施工现场Controller
 * 
 * @author yzm
 * @date 2024-07-04
 */
@RestController
@RequestMapping("/construction/photos")
public class PhotosController extends BaseController
{
    @Autowired
    private IPhotosService photosService;

    /**
     * 查询施工现场列表
     */
    @PreAuthorize("@ss.hasPermi('construction:photos:list')")
    @GetMapping("/list")
    public TableDataInfo list(Photos photos)
    {
        startPage();
        List<Photos> list = photosService.selectPhotosList(photos);
        return getDataTable(list);
    }

    /**
     * 导出施工现场列表
     */
    @PreAuthorize("@ss.hasPermi('construction:photos:export')")
    @Log(title = "施工现场", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Photos photos)
    {
        List<Photos> list = photosService.selectPhotosList(photos);
        ExcelUtil<Photos> util = new ExcelUtil<Photos>(Photos.class);
        util.exportExcel(response, list, "施工现场数据");
    }

    /**
     * 获取施工现场详细信息
     */
    @PreAuthorize("@ss.hasPermi('construction:photos:query')")
    @GetMapping(value = "/{photoId}")
    public AjaxResult getInfo(@PathVariable("photoId") Long photoId)
    {
        return success(photosService.selectPhotosByPhotoId(photoId));
    }

    /**
     * 新增施工现场
     */
    @PreAuthorize("@ss.hasPermi('construction:photos:add')")
    @Log(title = "施工现场", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Photos photos)
    {
        return toAjax(photosService.insertPhotos(photos));
    }

    /**
     * 修改施工现场
     */
    @PreAuthorize("@ss.hasPermi('construction:photos:edit')")
    @Log(title = "施工现场", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Photos photos)
    {
        return toAjax(photosService.updatePhotos(photos));
    }

    /**
     * 删除施工现场
     */
    @PreAuthorize("@ss.hasPermi('construction:photos:remove')")
    @Log(title = "施工现场", businessType = BusinessType.DELETE)
	@DeleteMapping("/{photoIds}")
    public AjaxResult remove(@PathVariable Long[] photoIds)
    {
        return toAjax(photosService.deletePhotosByPhotoIds(photoIds));
    }
}
