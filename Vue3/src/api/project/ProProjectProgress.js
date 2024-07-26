import request from '@/utils/request'

// 查询项目进程列表
export function listProProjectProgress(query) {
  return request({
    url: '/project/ProProjectProgress/list',
    method: 'get',
    params: query
  })
}

// 查询项目进程详细
export function getProProjectProgress(progressId) {
  return request({
    url: '/project/ProProjectProgress/' + progressId,
    method: 'get'
  })
}

// 新增项目进程
export function addProProjectProgress(data) {
  return request({
    url: '/project/ProProjectProgress',
    method: 'post',
    data: data
  })
}

// 修改项目进程
export function updateProProjectProgress(data) {
  return request({
    url: '/project/ProProjectProgress',
    method: 'put',
    data: data
  })
}

// 删除项目进程
export function delProProjectProgress(progressId) {
  return request({
    url: '/project/ProProjectProgress/' + progressId,
    method: 'delete'
  })
}
