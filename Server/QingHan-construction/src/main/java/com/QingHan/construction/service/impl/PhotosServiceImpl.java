package com.QingHan.construction.service.impl;

import java.util.List;

import com.QingHan.construction.domain.Photos;
import com.QingHan.construction.mapper.PhotosMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.construction.service.IPhotosService;

/**
 * 施工现场Service业务层处理
 * 
 * @author yzm
 * @date 2024-07-04
 */
@Service
public class PhotosServiceImpl implements IPhotosService 
{
    @Autowired
    private PhotosMapper photosMapper;

    /**
     * 查询施工现场
     * 
     * @param photoId 施工现场主键
     * @return 施工现场
     */
    @Override
    public Photos selectPhotosByPhotoId(Long photoId)
    {
        return photosMapper.selectPhotosByPhotoId(photoId);
    }

    /**
     * 查询施工现场列表
     * 
     * @param photos 施工现场
     * @return 施工现场
     */
    @Override
    public List<Photos> selectPhotosList(Photos photos)
    {
        return photosMapper.selectPhotosList(photos);
    }

    /**
     * 新增施工现场
     * 
     * @param photos 施工现场
     * @return 结果
     */
    @Override
    public int insertPhotos(Photos photos)
    {
        return photosMapper.insertPhotos(photos);
    }

    /**
     * 修改施工现场
     * 
     * @param photos 施工现场
     * @return 结果
     */
    @Override
    public int updatePhotos(Photos photos)
    {
        return photosMapper.updatePhotos(photos);
    }

    /**
     * 批量删除施工现场
     * 
     * @param photoIds 需要删除的施工现场主键
     * @return 结果
     */
    @Override
    public int deletePhotosByPhotoIds(Long[] photoIds)
    {
        return photosMapper.deletePhotosByPhotoIds(photoIds);
    }

    /**
     * 删除施工现场信息
     * 
     * @param photoId 施工现场主键
     * @return 结果
     */
    @Override
    public int deletePhotosByPhotoId(Long photoId)
    {
        return photosMapper.deletePhotosByPhotoId(photoId);
    }

}
