<template>
	<view class="video-swiper" :style="{backgroundColor: bgColor,height: height+'rpx'}">
		<swiper
			class="video-swiper__wrapper"
			:style="{
				height: height+'rpx',
			}"
			@change="change"
			:circular="circular"
			:interval="interval"
			:duration="duration"
			:autoplay="autoplay"
			:current="current"
			:previousMargin="previousMargin+'rpx'"
			:nextMargin="nextMargin+'rpx'"
			:acceleration="acceleration"
			:displayMultipleItems="displayMultipleItems"
			:easingFunction="easingFunction"
		>
			<swiper-item
				class="video-swiper__wrapper__item"
				v-for="(item, index) in list"
				:key="index"
			>
				<view
					class="video-swiper__wrapper__item__wrapper"
					:style="[itemStyle(index)]"
				>
					<image
						class="video-swiper__wrapper__item__wrapper__image"
						v-if="getItemType(item) === 'image'"
						:src="getSource(item)"
						:mode="imgMode"
						@tap="clickHandler(index)"
						:style="{
							height: height+'rpx',
						}"
					></image>
					<video
						class="video-swiper__wrapper__item__wrapper__video"
						v-if="getItemType(item) === 'video'"
						:id="`video-${index}`"
						:enable-progress-gesture="false"
						:src="getSource(item)"
						:poster="getPoster(item)"
						:title="showTitle && item.title ? item.title : ''"
						:style="{
							height: height+'rpx',
						}"
						controls
						@tap="clickHandler(index)"
						:show-fullscreen-btn="false"
						object-fit="fill"
					></video>
				</view>
			</swiper-item>
		</swiper>
		<view class="video-swiper__indicator">
			<slot name="indicator">
				<view class="video-swiper-indicator__wrapper">
					<view
						class="video-swiper-indicator_dot"
						v-for="(item, index) in list.length"
						:key="index"
						:class="[index === currentIndex && 'video-swiper-indicator_active']"
					>
					</view>
				</view>
			</slot>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'video-swiper',
		props: {
			// 列表数组，元素可为字符串，如为对象可通过keyName指定目标属性名
			list: {
			    type: Array,
			    default: []
			},
		    // 是否显示面板指示器
		    indicator: {
		        type: Boolean,
		        default: true
		    },
		    // 指示器非激活颜色
		    indicatorActiveColor: {
		        type: String,
		        default: '#FFFFFF'
		    },
		    // 指示器的激活颜色
		    indicatorInactiveColor: {
		        type: String,
		        default:'rgba(255, 255, 255, 0.35)'
		    },
		    // 指示器样式，可通过bottom，left，right进行定位
		    indicatorStyle: {
		        type: [String, Object],
		        default: 'right'
		    },
		    // 指示器模式，line-线型，dot-点型
		    indicatorMode: {
		        type: String,
		        default: 'dot'
		    },
		    // 是否自动切换
		    autoplay: {
		        type: Boolean,
		        default: false
		    },
		    // 当前所在滑块的 index
		    current: {
		        type: [String, Number],
		        default: '0'
		    },
		    // 滑块自动切换时间间隔
		    interval: {
		        type: [String, Number],
		        default: 5000
		    },
		    // 滑块切换过程所需时间
		    duration: {
		        type: [String, Number],
		        default: 500
		    },
		    // 播放到末尾后是否重新回到开头
		    circular: {
		        type: Boolean,
		        default: true
		    },
		    // 前边距，可用于露出前一项的一小部分，nvue和支付宝不支持
		    previousMargin: {
		        type: [String, Number],
		        default: 0
		    },
		    // 后边距，可用于露出后一项的一小部分，nvue和支付宝不支持
		    nextMargin: {
		        type: [String, Number],
		        default: 0
		    },
		    // 当开启时，会根据滑动速度，连续滑动多屏，支付宝不支持
		    acceleration: {
		        type: Boolean,
		        default: false
		    },
		    // 同时显示的滑块数量，nvue、支付宝小程序不支持
		    displayMultipleItems: {
		        type: Number,
		        default: 1
		    },
		    // 指定swiper切换缓动动画类型，有效值：default、linear、easeInCubic、easeOutCubic、easeInOutCubic
		    // 只对微信小程序有效
		    easingFunction: {
		        type: String,
		        default: 'default'
		    },
		    // list数组中指定对象的目标属性名
		    keyName: {
		        type: String,
		        default:  'url'
		    },
		    // 图片的裁剪模式
		    imgMode: {
		        type: String,
		        default: 'aspectFill'
		    },
		    // 组件高度
		    height: {
		        type: [String, Number],
		        default: 350
		    },
		    // 背景颜色
		    bgColor: {
		        type: String,
		        default: '#f3f4f6'
		    },
		    // 组件圆角，数值或带单位的字符串
		    radius: {
		        type: [String, Number],
		        default: 4
		    },
		    // 是否加载中
		    loading: {
		        type: Boolean,
		        default: false
		    },
		    // 是否显示标题，要求数组对象中有title属性
		    showTitle: {
		        type: Boolean,
		        default: false
		    }
		},
		data() {
			return {
				currentIndex: 0,
			}
		},
		watch: {
			current(val, preVal) {
				if(val === preVal) return;
				this.currentIndex = val; // 和上游数据关联上
			}
		},
		computed: {
			itemStyle() {
				return index => {
					const style = {}
					// #ifndef APP-NVUE || MP-TOUTIAO
					// 左右流出空间的写法不支持nvue和头条
					// 只有配置了此二值，才加上对应的圆角，以及缩放
					if (this.nextMargin && this.previousMargin) {
						style.borderRadius = this.radius+'rpx'
						if (index !== this.currentIndex) style.transform = 'scale(0.92)'
					}
					// #endif
					return style
				}
			}
		},
		methods: {
			getItemType(item) {
              if (typeof item === 'string') return 'image'
              if (typeof item === 'object') {
                if (item.type === 'image') return 'image'
                if (item.type === 'video') return 'video'
                return 'image'
              }
            },
			// 获取目标路径，可能数组中为字符串，对象的形式，额外可指定对象的目标属性名keyName
			getSource(item) {
				if (typeof item === 'string') return item
				if (typeof item === 'object' && this.keyName) return item[this.keyName]
				else console.log('请按格式传递列表参数');
				return ''
			},
			// 轮播切换事件
			change(e) {
				// 当前的激活索引
				const {
					current
				} = e.detail
				this.pauseVideo(this.currentIndex)
				this.currentIndex = current
				this.$emit('change', e.detail)
			},
			// 切换轮播时，暂停视频播放
			pauseVideo(index) {
				const lastItem = this.getSource(this.list[index])
				// 当视频隐藏时，暂停播放
				const video = uni.createVideoContext(`video-${index}`, this)
				video.pause()
			},
			// 当一个轮播item为视频时，获取它的视频海报
			getPoster(item) {
				return typeof item === 'object' && item.poster ? item.poster : ''
			},
			// 点击某个item,点击图片预览，点击视频播放
			clickHandler(index) {
				if(this.list[index].type && this.list[index].type === 'video'){
					return
				}
				let imgArr = []
				this.list.filter(item=>{
					if(item.type == undefined || item.type==='image'){
						imgArr.push(item.url)
					}
				})
				 // 预览图片
				uni.previewImage({
					current: index-1,
					urls: imgArr
				});
				this.$emit('click', index)
			}
		},
	}
</script>

<style lang="scss" scoped>
	.video-swiper {
		display: flex;
		justify-content: center;
		align-items: center;
		position: relative;
		overflow: hidden;

		&__wrapper {
			flex: 1;

			&__item {
				flex: 1;

				&__wrapper {
					display: flex;
					position: relative;
					overflow: hidden;
					transition: transform 0.3s;
					flex: 1;

					&__image {
						flex: 1;
					}

					&__video {
						flex: 1;
					}

					&__title {
						position: absolute;
						background-color: rgba(0, 0, 0, 0.3);
						bottom: 0;
						left: 0;
						right: 0;
						font-size: 28rpx;
						padding: 12rpx 24rpx;
						color: #FFFFFF;
						flex: 1;
					}
				}
			}
		}

		&__indicator {
			position: absolute;
			bottom: 10px;
		}
	}
	
	.video-swiper-indicator {
		&__wrapper {
			display: flex;
			.video-swiper-indicator_dot{
				width: 5px;
				height: 5px;
				border-radius: 100px;
				margin: 0 4px;
				background-color: #fff;
			}
			
			.video-swiper-indicator_active{
				width: 12px;
			}
		}
	}
</style>
<!-- 
// test.vue 调用案例
<template>
	<view>
		<VideoSwiper 
			:list="list"
			keyName="url"
			:autoplay="false">
		</VideoSwiper>
	</view>
</template>

<script>
	import VideoSwiper from "./video-swiper.vue"
	export default {
		components: {
		    "VideoSwiper":VideoSwiper
		},
		data() {
			return {
				list: [
					{
						url: 'https://cdn.uviewui.com/uview/resources/video.mp4',
						title: '昨夜星辰昨夜风，画楼西畔桂堂东',
						poster: 'https://cdn.uviewui.com/uview/swiper/swiper1.png',
						type:'video'
					},
					{
						url: 'https://cdn.uviewui.com/uview/swiper/swiper2.png',
						title: '身无彩凤双飞翼，心有灵犀一点通'
					},{
						url: 'https://cdn.uviewui.com/uview/swiper/swiper3.png',
						title: '谁念西风独自凉，萧萧黄叶闭疏窗，沉思往事立残阳'
					},
				],
			}
		},
	}
</script> -->
