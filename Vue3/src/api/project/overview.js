import request from '@/utils/request'

// 查询项目基础信息列表
export function listOverview(query) {
  return request({
    url: '/project/overview/list',
    method: 'get',
    params: query
  })
}

// 查询项目基础信息详细
export function getOverview(id) {
  return request({
    url: '/project/overview/' + id,
    method: 'get'
  })
}

// 新增项目基础信息
export function addOverview(data) {
  return request({
    url: '/project/overview',
    method: 'post',
    data: data
  })
}

// 修改项目基础信息
export function updateOverview(data) {
  return request({
    url: '/project/overview',
    method: 'put',
    data: data
  })
}

// 删除项目基础信息
export function delOverview(id) {
  return request({
    url: '/project/overview/' + id,
    method: 'delete'
  })
}
