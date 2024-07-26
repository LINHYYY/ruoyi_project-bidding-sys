import axios from 'axios'
// export const code = () => {
//     return axios({
//         url: "http://localhost:8080/captchaImage",
//         methods: "get",
// 		withCredentials: true 
//     });
// };

// export const Login = (data) => {
// 	return axios({
// 		url: "http://localhost:8080/login",
// 		method: "post",
// 		data
// 	})
// }

export const register = (data) => {
	return axios({
		url: "http://localhost:8080/register",
		method: "post",
		data:data,
		headers: {
			isToken: false,
			'Content-Type': 'application/json;charset=UTF-8'} 
	})
}
