package com.QingHan.construction.service.impl;

import java.util.List;
import com.QingHan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.construction.mapper.TeamsMapper;
import com.QingHan.construction.domain.Teams;
import com.QingHan.construction.service.ITeamsService;

/**
 * 施工队列表Service业务层处理
 * 
 * @author fzy
 * @date 2024-07-05
 */
@Service
public class TeamsServiceImpl implements ITeamsService 
{
    @Autowired
    private TeamsMapper teamsMapper;

    /**
     * 查询施工队列表
     * 
     * @param teamId 施工队列表主键
     * @return 施工队列表
     */
    @Override
    public Teams selectTeamsByTeamId(Long teamId)
    {
        return teamsMapper.selectTeamsByTeamId(teamId);
    }

    /**
     * 查询施工队列表列表
     * 
     * @param teams 施工队列表
     * @return 施工队列表
     */
    @Override
    public List<Teams> selectTeamsList(Teams teams)
    {
        return teamsMapper.selectTeamsList(teams);
    }

    /**
     * 新增施工队列表
     * 
     * @param teams 施工队列表
     * @return 结果
     */
    @Override
    public int insertTeams(Teams teams)
    {
        teams.setCreateTime(DateUtils.getNowDate());
        return teamsMapper.insertTeams(teams);
    }

    /**
     * 修改施工队列表
     * 
     * @param teams 施工队列表
     * @return 结果
     */
    @Override
    public int updateTeams(Teams teams)
    {
        teams.setUpdateTime(DateUtils.getNowDate());
        return teamsMapper.updateTeams(teams);
    }

    /**
     * 批量删除施工队列表
     * 
     * @param teamIds 需要删除的施工队列表主键
     * @return 结果
     */
    @Override
    public int deleteTeamsByTeamIds(Long[] teamIds)
    {
        return teamsMapper.deleteTeamsByTeamIds(teamIds);
    }

    /**
     * 删除施工队列表信息
     * 
     * @param teamId 施工队列表主键
     * @return 结果
     */
    @Override
    public int deleteTeamsByTeamId(Long teamId)
    {
        return teamsMapper.deleteTeamsByTeamId(teamId);
    }
}
