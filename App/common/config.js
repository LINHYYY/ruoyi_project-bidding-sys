// 全局配置文件
// let baseUrl = 'http://192.168.0.108:8088';
// let staticUrl = 'http://192.168.0.108:8088';
let baseUrl = 'https://sourcebyte.vip';
let staticUrl = 'https://sourcebyte.vip';
// 版本号 用于更新
let version = 1;
// vuex_version版本号 用于显示
let vuex_version = '1.0.' + version;
// 是否需要热更新（后台自动更新）
let flag_hot = false;

export default {
	baseUrl: baseUrl,
	staticUrl:staticUrl,
	version: version,
	vuex_version: vuex_version,
}

