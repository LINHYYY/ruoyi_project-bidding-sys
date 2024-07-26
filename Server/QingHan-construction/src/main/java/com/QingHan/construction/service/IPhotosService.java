package com.QingHan.construction.service;

import java.util.List;

import com.QingHan.construction.domain.Photos;

/**
 * 施工现场Service接口
 * 
 * @author yzm
 * @date 2024-07-04
 */
public interface IPhotosService 
{
    /**
     * 查询施工现场
     * 
     * @param photoId 施工现场主键
     * @return 施工现场
     */
    public Photos selectPhotosByPhotoId(Long photoId);

    /**
     * 查询施工现场列表
     * 
     * @param photos 施工现场
     * @return 施工现场集合
     */
    public List<Photos> selectPhotosList(Photos photos);

    /**
     * 新增施工现场
     * 
     * @param photos 施工现场
     * @return 结果
     */
    public int insertPhotos(Photos photos);

    /**
     * 修改施工现场
     * 
     * @param photos 施工现场
     * @return 结果
     */
    public int updatePhotos(Photos photos);

    /**
     * 批量删除施工现场
     * 
     * @param photoIds 需要删除的施工现场主键集合
     * @return 结果
     */
    public int deletePhotosByPhotoIds(Long[] photoIds);

    /**
     * 删除施工现场信息
     * 
     * @param photoId 施工现场主键
     * @return 结果
     */
    public int deletePhotosByPhotoId(Long photoId);
}
