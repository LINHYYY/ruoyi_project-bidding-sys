import request from '@/utils/request'

// 查询施工项目状态列表
export function listAssociation(query) {
  return request({
    url: '/construction/association/list',
    method: 'get',
    params: query
  })
}

// 查询施工项目状态详细
export function getAssociation(relationId) {
  return request({
    url: '/construction/association/' + relationId,
    method: 'get'
  })
}

// 新增施工项目状态
export function addAssociation(data) {
  return request({
    url: '/construction/association',
    method: 'post',
    data: data
  })
}

// 修改施工项目状态
export function updateAssociation(data) {
  return request({
    url: '/construction/association',
    method: 'put',
    data: data
  })
}

// 删除施工项目状态
export function delAssociation(relationId) {
  return request({
    url: '/construction/association/' + relationId,
    method: 'delete'
  })
}
