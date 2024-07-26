import request from '@/utils/request'

// 查询施工队日志列表
export function listProgress(query) {
  return request({
    url: '/construction/progress/list',
    method: 'get',
    params: query
  })
}

// 查询施工队日志详细
export function getProgress(progressId) {
  return request({
    url: '/construction/progress/' + progressId,
    method: 'get'
  })
}

// 新增施工队日志
export function addProgress(data) {
  return request({
    url: '/construction/progress',
    method: 'post',
    data: data
  })
}

// 修改施工队日志
export function updateProgress(data) {
  return request({
    url: '/construction/progress',
    method: 'put',
    data: data
  })
}

// 删除施工队日志
export function delProgress(progressId) {
  return request({
    url: '/construction/progress/' + progressId,
    method: 'delete'
  })
}
