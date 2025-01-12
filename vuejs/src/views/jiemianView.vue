<template>
    <div id="">
      <h1>登录</h1>
      <form @submit.prevent="handleLogin">
        <input type="text" placeholder="用户名" v-model="username" required />
        <input type="password" placeholder="密码" v-model="password" required />

        <label for="captcha">验证码：</label>
        <img :src="captchaImage" alt="验证码" id="captcha-img" />
        <input type="text" v-model="captcha" placeholder="输入验证码" required />

        <button type="submit">登录</button>
        <router-link to="/homepageView">测试跳转页面功能</router-link> |
        <router-view/>
      </form>
      <div id="message">{{ message }}</div>
    </div>
  </template>

<script>
export default {
  data () {
    return {
      username: '',
      password: '',
      captcha: '',
      message: '',
      captchaImage: '/captcha' // 这里是验证码图片的URL
    }
  },
  methods: {
    async handleLogin () {
      const response = await fetch('/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: this.username, password: this.password, captcha: this.captcha })
      })

      if (response.ok) {
        const { token } = await response.json()
        localStorage.setItem('token', token)
        window.location.href = '/homepage'// 登录成功后跳转到首页
      } else {
        this.message = '登录失败'
      }
    }
  }
}
</script>

  <style>
  /* 可以添加一些样式来美化表单 */
  </style>
