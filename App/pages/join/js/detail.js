import axios from 'axios'
/*
const urlMap = {
  '施工队': "http://localhost:8080/construction/association",
  '设计队': "http://localhost:8080/design/des_association"
};
*/
export const findProduct = () => {
	let lifeData = uni.getStorageSync('lifeData');
	let token = lifeData.vuex_token
	console.log(token);
	if (!token) {
		return false;
	}
	// 根据 payType 从 urlMap 中获取对应的 URL
	//let url = urlMap[data.payType]; // 默认 URL
	return axios({
		url: "http://localhost:8080/project/overview/list",
		method: "get",
		headers: {  
		        'Authorization': `Bearer ${token}`,
		}  
	})
}

export const findConstrusion = () => {
	let lifeData = uni.getStorageSync('lifeData');
	let token = lifeData.vuex_token
	console.log(token);
	if (!token) {
		return false;
	}
	// 根据 payType 从 urlMap 中获取对应的 URL
	//let url = urlMap[data.payType]; // 默认 URL
	return axios({
		url: "http://localhost:8080/construction/progress",
		method: "get",
		headers: {  
		        'Authorization': `Bearer ${token}`,
		}  
	})
}