package com.QingHan.construction.mapper;

import java.util.List;
import com.QingHan.construction.domain.Teams;

/**
 * 施工队列表Mapper接口
 * 
 * @author fzy
 * @date 2024-07-05
 */
public interface TeamsMapper 
{
    /**
     * 查询施工队列表
     * 
     * @param teamId 施工队列表主键
     * @return 施工队列表
     */
    public Teams selectTeamsByTeamId(Long teamId);

    /**
     * 查询施工队列表列表
     * 
     * @param teams 施工队列表
     * @return 施工队列表集合
     */
    public List<Teams> selectTeamsList(Teams teams);

    /**
     * 新增施工队列表
     * 
     * @param teams 施工队列表
     * @return 结果
     */
    public int insertTeams(Teams teams);

    /**
     * 修改施工队列表
     * 
     * @param teams 施工队列表
     * @return 结果
     */
    public int updateTeams(Teams teams);

    /**
     * 删除施工队列表
     * 
     * @param teamId 施工队列表主键
     * @return 结果
     */
    public int deleteTeamsByTeamId(Long teamId);

    /**
     * 批量删除施工队列表
     * 
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTeamsByTeamIds(Long[] teamIds);
}
