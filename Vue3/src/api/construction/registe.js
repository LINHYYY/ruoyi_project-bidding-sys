import request from '@/utils/request'

// 查询材料核销列表
export function listRegiste(query) {
  return request({
    url: '/construction/registe/list',
    method: 'get',
    params: query
  })
}

// 查询材料核销详细
export function getRegiste(registartionId) {
  return request({
    url: '/construction/registe/' + registartionId,
    method: 'get'
  })
}

// 新增材料核销
export function addRegiste(data) {
  return request({
    url: '/construction/registe',
    method: 'post',
    data: data
  })
}

// 修改材料核销
export function updateRegiste(data) {
  return request({
    url: '/construction/registe',
    method: 'put',
    data: data
  })
}

// 删除材料核销
export function delRegiste(registartionId) {
  return request({
    url: '/construction/registe/' + registartionId,
    method: 'delete'
  })
}
