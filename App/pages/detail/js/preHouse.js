import axios from 'axios'

// 定义一个对象，将不同的 payType 映射到不同的 URL
const urlMap = {
  '合伙人': "http://localhost:8080/project/bid_review_details",
  '施工队': "http://localhost:8080/construction/association",
  '设计队': "http://localhost:8080/design/des_association"
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