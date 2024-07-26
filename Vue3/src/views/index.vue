<style scoped>
@keyframes slideBackground {
  0% {
    background-image: url('..\assets\images\20220424004525223175.jpg');
  }
  50% {
    background-image: url('..\assets\images\20220424004434133714.jpg');
  }
  100% {
    background-image: url('..\assets\images\20220424004351173556.jpg');
  }
}
.app-container {
  animation: slideBackground 15s linear infinite;
  background-size: 100% 90%; /* 高度为100%，宽度自适应 */
  background-position: center;
  background-repeat: no-repeat;
  opacity: 1;
  position: relative;
  height: 100vh;
}
</style>


<template>
  <div class="app-container home">

    <el-divider />
  </div>
</template>

<script setup name="Index">
const version = ref('3.8.7')

function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

