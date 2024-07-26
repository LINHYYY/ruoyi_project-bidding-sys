import axios from 'axios'
export const code = () => {
    return axios({
        url: "http://localhost:8080/captchaImage",
        methods: "get",
		withCredentials: true 
    });
};

export const Login = (data) => {
	return axios({
		url: "http://localhost:8080/login",
		method: "post",
		data
	})
}

export const register = (formData) => {
	return axios({
		url: "/api/register",
		method: "post",
		formData
	})
}



export const getInfo = () => {
	let lifeData = uni.getStorageSync('lifeData');
	let token = lifeData.vuex_token
	console.log(token);
	if (!token) {
		return false;
	}
	return axios({
		url: 'http://localhost:8080/getInfo',
		method: 'get',
		headers: {
			'Authorization': `Bearer ${token}`, // 根据你的API需求，可能只是'token': token
		}  
	})
}
