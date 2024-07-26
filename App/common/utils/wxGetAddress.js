// import amapFile from '../../libs/amap-wx.130.js'

// const myAmapFun = new amapFile.AMapWX({
// 	// 申请的高德key值
// 	key: '4ef3b3b42d8bb823529908dc93414127'
// }); //创建一个实例化对象
 
// export default function wxGetAddress({longitude,latitude}) {
//     //根据传递进来经纬度进行反解析，调用的是高德给的方法
// 	return new Promise((resolve, reject) => {
// 		myAmapFun.getRegeo({
// 			location: `${longitude},${latitude}`,
// 			success: (res) => {
// 				resolve(res[0])
// 			},
// 			fail: (err) => {
// 				resolve(null)
// 			}
// 		})
// 	})
// }