package com.QingHan.design.service.impl;

import java.util.List;
import com.QingHan.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.QingHan.design.mapper.DesTeamsMapper;
import com.QingHan.design.domain.DesTeams;
import com.QingHan.design.service.IDesTeamsService;

/**
 * 设计队列表Service业务层处理
 * 
 * @author linghy
 * @date 2024-07-17
 */
@Service
public class DesTeamsServiceImpl implements IDesTeamsService 
{
    @Autowired
    private DesTeamsMapper desTeamsMapper;

    /**
     * 查询设计队列表
     * 
     * @param teamId 设计队列表主键
     * @return 设计队列表
     */
    @Override
    public DesTeams selectDesTeamsByTeamId(Long teamId)
    {
        return desTeamsMapper.selectDesTeamsByTeamId(teamId);
    }

    /**
     * 查询设计队列表列表
     * 
     * @param desTeams 设计队列表
     * @return 设计队列表
     */
    @Override
    public List<DesTeams> selectDesTeamsList(DesTeams desTeams)
    {
        return desTeamsMapper.selectDesTeamsList(desTeams);
    }

    /**
     * 新增设计队列表
     * 
     * @param desTeams 设计队列表
     * @return 结果
     */
    @Override
    public int insertDesTeams(DesTeams desTeams)
    {
        desTeams.setCreateTime(DateUtils.getNowDate());
        return desTeamsMapper.insertDesTeams(desTeams);
    }

    /**
     * 修改设计队列表
     * 
     * @param desTeams 设计队列表
     * @return 结果
     */
    @Override
    public int updateDesTeams(DesTeams desTeams)
    {
        desTeams.setUpdateTime(DateUtils.getNowDate());
        return desTeamsMapper.updateDesTeams(desTeams);
    }

    /**
     * 批量删除设计队列表
     * 
     * @param teamIds 需要删除的设计队列表主键
     * @return 结果
     */
    @Override
    public int deleteDesTeamsByTeamIds(Long[] teamIds)
    {
        return desTeamsMapper.deleteDesTeamsByTeamIds(teamIds);
    }

    /**
     * 删除设计队列表信息
     * 
     * @param teamId 设计队列表主键
     * @return 结果
     */
    @Override
    public int deleteDesTeamsByTeamId(Long teamId)
    {
        return desTeamsMapper.deleteDesTeamsByTeamId(teamId);
    }
}
