import axios from 'axios'


export const findProduct = () => {
	let lifeData = uni.getStorageSync('lifeData');
	let token = lifeData.vuex_token
	console.log(token);
	if (!token) {
		return false;
	}
	return axios({
		url: "http://localhost:8080/system/notice/list",
		method: "get",
		headers: {  
		        'Authorization': `Bearer ${token}`,
		}  
	})
}