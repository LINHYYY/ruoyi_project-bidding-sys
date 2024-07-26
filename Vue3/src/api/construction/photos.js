import request from '@/utils/request'

// 查询施工现场列表
export function listPhotos(query) {
  return request({
    url: '/construction/photos/list',
    method: 'get',
    params: query
  })
}

// 查询施工现场详细
export function getPhotos(photoId) {
  return request({
    url: '/construction/photos/' + photoId,
    method: 'get'
  })
}

// 新增施工现场
export function addPhotos(data) {
  return request({
    url: '/construction/photos',
    method: 'post',
    data: data
  })
}

// 修改施工现场
export function updatePhotos(data) {
  return request({
    url: '/construction/photos',
    method: 'put',
    data: data
  })
}

// 删除施工现场
export function delPhotos(photoId) {
  return request({
    url: '/construction/photos/' + photoId,
    method: 'delete'
  })
}
