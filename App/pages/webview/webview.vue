<template>
  <view v-if="src">
    <web-view :webview-styles="wbStyles" :src="src" :fullscreen="false" />
  </view>
</template>
 
<script>
export default {
  data() {
    return {
      title: '',
      src: '',
      wbStyles: {
        width: '100%',
        height: '100%',
      },
    }
  },
  onLoad(option) {
	  console.log(option.title);
    this.title = option?.title
    this.src = option?.src
  },
}
</script>