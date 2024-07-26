import axios from 'axios'


export const test = (data) => {
	return axios({
		url: "http://localhost:8080/project/overview/list",
		method: "post",
		data:data,
		headers: {  
				isToken: false,
		        'Content-Type': 'application/json;charset=UTF-8'
		    }  
	})
}
