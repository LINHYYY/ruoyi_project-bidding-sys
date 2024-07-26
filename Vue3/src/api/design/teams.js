import request from '@/utils/request'

// 查询设计队列表列表
export function listTeams(query) {
  return request({
    url: '/design/teams/list',
    method: 'get',
    params: query
  })
}

// 查询设计队列表详细
export function getTeams(teamId) {
  return request({
    url: '/design/teams/' + teamId,
    method: 'get'
  })
}

// 新增设计队列表
export function addTeams(data) {
  return request({
    url: '/design/teams',
    method: 'post',
    data: data
  })
}

// 修改设计队列表
export function updateTeams(data) {
  return request({
    url: '/design/teams',
    method: 'put',
    data: data
  })
}

// 删除设计队列表
export function delTeams(teamId) {
  return request({
    url: '/design/teams/' + teamId,
    method: 'delete'
  })
}
