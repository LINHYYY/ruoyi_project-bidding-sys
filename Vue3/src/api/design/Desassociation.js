import request from '@/utils/request'

// 查询设计类项目审核列表
export function listDesassociation(query) {
  return request({
    url: '/design/Desassociation/list',
    method: 'get',
    params: query
  })
}

// 查询设计类项目审核详细
export function getDesassociation(relationId) {
  return request({
    url: '/design/Desassociation/' + relationId,
    method: 'get'
  })
}

// 新增设计类项目审核
export function addDesassociation(data) {
  return request({
    url: '/design/Desassociation',
    method: 'post',
    data: data
  })
}

// 修改设计类项目审核
export function updateDesassociation(data) {
  return request({
    url: '/design/Desassociation',
    method: 'put',
    data: data
  })
}

// 删除设计类项目审核
export function delDesassociation(relationId) {
  return request({
    url: '/design/Desassociation/' + relationId,
    method: 'delete'
  })
}
