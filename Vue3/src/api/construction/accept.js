import request from '@/utils/request'

// 查询施工验收表管理列表
export function listAccept(query) {
  return request({
    url: '/construction/accept/list',
    method: 'get',
    params: query
  })
}

// 查询施工验收表管理详细
export function getAccept(fileId) {
  return request({
    url: '/construction/accept/' + fileId,
    method: 'get'
  })
}

// 新增施工验收表管理
export function addAccept(data) {
  return request({
    url: '/construction/accept',
    method: 'post',
    data: data
  })
}

// 修改施工验收表管理
export function updateAccept(data) {
  return request({
    url: '/construction/accept',
    method: 'put',
    data: data
  })
}

// 删除施工验收表管理
export function delAccept(fileId) {
  return request({
    url: '/construction/accept/' + fileId,
    method: 'delete'
  })
}
