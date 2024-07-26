import request from '@/utils/request'

// 查询投标审核详细信息列表
export function listBid_review_details(query) {
  return request({
    url: '/project/bid_review_details/list',
    method: 'get',
    params: query
  })
}

// 查询投标审核详细信息详细
export function getBid_review_details(detailId) {
  return request({
    url: '/project/bid_review_details/' + detailId,
    method: 'get'
  })
}

// 新增投标审核详细信息
export function addBid_review_details(data) {
  return request({
    url: '/project/bid_review_details',
    method: 'post',
    data: data
  })
}

// 修改投标审核详细信息
export function updateBid_review_details(data) {
  return request({
    url: '/project/bid_review_details',
    method: 'put',
    data: data
  })
}

// 删除投标审核详细信息
export function delBid_review_details(detailId) {
  return request({
    url: '/project/bid_review_details/' + detailId,
    method: 'delete'
  })
}
