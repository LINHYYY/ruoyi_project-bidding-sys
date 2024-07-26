package com.QingHan.design.mapper;

import java.util.List;
import com.QingHan.design.domain.DesProAccept;

/**
 * 设计队项目验收Mapper接口
 * 
 * @author linghy
 * @date 2024-07-17
 */
public interface DesProAcceptMapper 
{
    /**
     * 查询设计队项目验收
     * 
     * @param fileId 设计队项目验收主键
     * @return 设计队项目验收
     */
    public DesProAccept selectDesProAcceptByFileId(Long fileId);

    /**
     * 查询设计队项目验收列表
     * 
     * @param desProAccept 设计队项目验收
     * @return 设计队项目验收集合
     */
    public List<DesProAccept> selectDesProAcceptList(DesProAccept desProAccept);

    /**
     * 新增设计队项目验收
     * 
     * @param desProAccept 设计队项目验收
     * @return 结果
     */
    public int insertDesProAccept(DesProAccept desProAccept);

    /**
     * 修改设计队项目验收
     * 
     * @param desProAccept 设计队项目验收
     * @return 结果
     */
    public int updateDesProAccept(DesProAccept desProAccept);

    /**
     * 删除设计队项目验收
     * 
     * @param fileId 设计队项目验收主键
     * @return 结果
     */
    public int deleteDesProAcceptByFileId(Long fileId);

    /**
     * 批量删除设计队项目验收
     * 
     * @param fileIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDesProAcceptByFileIds(Long[] fileIds);
}
