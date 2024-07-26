import request from '@/utils/request'

// 查询设计队设计日志列表
export function listDesprogress(query) {
  return request({
    url: '/design/Desprogress/list',
    method: 'get',
    params: query
  })
}

// 查询设计队设计日志详细
export function getDesprogress(progressId) {
  return request({
    url: '/design/Desprogress/' + progressId,
    method: 'get'
  })
}

// 新增设计队设计日志
export function addDesprogress(data) {
  return request({
    url: '/design/Desprogress',
    method: 'post',
    data: data
  })
}

// 修改设计队设计日志
export function updateDesprogress(data) {
  return request({
    url: '/design/Desprogress',
    method: 'put',
    data: data
  })
}

// 删除设计队设计日志
export function delDesprogress(progressId) {
  return request({
    url: '/design/Desprogress/' + progressId,
    method: 'delete'
  })
}
