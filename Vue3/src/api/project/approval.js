import request from '@/utils/request'

// 查询项目审核表列表
export function listApproval(query) {
  return request({
    url: '/project/approval/list',
    method: 'get',
    params: query
  })
}

// 查询项目审核表详细
export function getApproval(approvalId) {
  return request({
    url: '/project/approval/' + approvalId,
    method: 'get'
  })
}

// 新增项目审核表
export function addApproval(data) {
  return request({
    url: '/project/approval',
    method: 'post',
    data: data
  })
}

// 修改项目审核表
export function updateApproval(data) {
  return request({
    url: '/project/approval',
    method: 'put',
    data: data
  })
}

// 删除项目审核表
export function delApproval(approvalId) {
  return request({
    url: '/project/approval/' + approvalId,
    method: 'delete'
  })
}
