import request from '@/utils/request'

// 查询设计队项目验收列表
export function listDesaccept(query) {
  return request({
    url: '/design/Desaccept/list',
    method: 'get',
    params: query
  })
}

// 查询设计队项目验收详细
export function getDesaccept(fileId) {
  return request({
    url: '/design/Desaccept/' + fileId,
    method: 'get'
  })
}

// 新增设计队项目验收
export function addDesaccept(data) {
  return request({
    url: '/design/Desaccept',
    method: 'post',
    data: data
  })
}

// 修改设计队项目验收
export function updateDesaccept(data) {
  return request({
    url: '/design/Desaccept',
    method: 'put',
    data: data
  })
}

// 删除设计队项目验收
export function delDesaccept(fileId) {
  return request({
    url: '/design/Desaccept/' + fileId,
    method: 'delete'
  })
}
