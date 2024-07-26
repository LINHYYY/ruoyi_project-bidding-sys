package com.QingHan.design.mapper;

import java.util.List;
import com.QingHan.design.domain.DesTeams;

/**
 * 设计队列表Mapper接口
 * 
 * @author linghy
 * @date 2024-07-17
 */
public interface DesTeamsMapper 
{
    /**
     * 查询设计队列表
     * 
     * @param teamId 设计队列表主键
     * @return 设计队列表
     */
    public DesTeams selectDesTeamsByTeamId(Long teamId);

    /**
     * 查询设计队列表列表
     * 
     * @param desTeams 设计队列表
     * @return 设计队列表集合
     */
    public List<DesTeams> selectDesTeamsList(DesTeams desTeams);

    /**
     * 新增设计队列表
     * 
     * @param desTeams 设计队列表
     * @return 结果
     */
    public int insertDesTeams(DesTeams desTeams);

    /**
     * 修改设计队列表
     * 
     * @param desTeams 设计队列表
     * @return 结果
     */
    public int updateDesTeams(DesTeams desTeams);

    /**
     * 删除设计队列表
     * 
     * @param teamId 设计队列表主键
     * @return 结果
     */
    public int deleteDesTeamsByTeamId(Long teamId);

    /**
     * 批量删除设计队列表
     * 
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteDesTeamsByTeamIds(Long[] teamIds);
}
