import axios from 'axios'


export const findProduct = (data) => {
	let lifeData = uni.getStorageSync('lifeData');
	let token = lifeData.vuex_token
	console.log(token);
	if (!token) {
		return false;
	}

	return axios({
		url:  "http://localhost:8080/construction/photos",
		method: "post",
		data:data,
		headers: {  
		        'Authorization': `Bearer ${token}`,
		}  
	})
}