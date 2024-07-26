import request from '@/utils/request'

// 查询施工队列表列表
export function listTeams(query) {
  return request({
    url: '/construction/teams/list',
    method: 'get',
    params: query
  })
}

// 查询施工队列表详细
export function getTeams(teamId) {
  return request({
    url: '/construction/teams/' + teamId,
    method: 'get'
  })
}

// 新增施工队列表
export function addTeams(data) {
  return request({
    url: '/construction/teams',
    method: 'post',
    data: data
  })
}

// 修改施工队列表
export function updateTeams(data) {
  return request({
    url: '/construction/teams',
    method: 'put',
    data: data
  })
}

// 删除施工队列表
export function delTeams(teamId) {
  return request({
    url: '/construction/teams/' + teamId,
    method: 'delete'
  })
}
