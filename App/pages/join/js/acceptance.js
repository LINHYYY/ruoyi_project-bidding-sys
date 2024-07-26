import axios from 'axios'

// 定义一个对象，将不同的 payType 映射到不同的 URL
const urlMap = {
  '施工类项目': "http://localhost:8080/construction/accept",
  '设计类项目': "http://localhost:8080/design/accept"
};

export const findProduct = (data) => {
	let lifeData = uni.getStorageSync('lifeData');
	let token = lifeData.vuex_token
	console.log(token);
	if (!token) {
		return false;
	}

    // 根据 payType 从 urlMap 中获取对应的 URL
    let url = urlMap[data.payType]; // 默认 URL
	
	return axios({
		url: url,
		method: "post",
		data:data,
		headers: {  
		        'Authorization': `Bearer ${token}`,
		}  
	})
}