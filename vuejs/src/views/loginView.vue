<!-- <template>
    <div class="container">
      <form action="#" class="login-form" @submit.prevent="handleLogin">
          <h2>登录</h2>
          <input type="text" name="username" placeholder="用户名" v-model="username" required>
          <input type="password" name="password" placeholder="密码" v-model="password" required>
          <label for="captcha">验证码：</label>
          <img :src="captchaImage" alt="验证码" id="captcha-img" width="150" height="30"/>
          <input type="text" v-model="captcha" placeholder="输入验证码" required />
          <button type="submit">登录</button>
          <button @click="goToRegister">注册</button>
      </form>
      <div id="message">{{ message }}</div>
  </div>
</template> -->
<template>
  <div class="container">
    <form action="#" class="login-form" @submit.prevent="handleLogin">
        <h2>登录</h2>
        <input type="text" name="username" placeholder="用户名" v-model="username" required>
        <input type="password" name="password" placeholder="密码" v-model="password" required>
        <button type="submit">登录</button>
        <button @click="goToRegister">注册</button>
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
      message: ''
    }
  },
  methods: {
    goToRegister () {
      this.$router.push('/zuce')
    },
    async handleLogin () {
      const response = await fetch('http://localhost:8080/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: this.username, password: this.password })
      })
      // const response = { ok: true }
      if (response.ok) {
        const { token } = await response.json()
        localStorage.setItem('token', token)
        // window.location.href = 'https://www.bilibili.com/'
        this.$router.push('/wuweiqieyue')
      } else {
        this.message = '登录失败'
      }
    }
  }
}
</script>
<!-- <script>
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
    goToRegister () {
      this.$router.push('/zuce')
    },
    async handleLogin () {
      const response = await fetch('/api/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username: this.username, password: this.password })
      })
      const response = { ok: true }
      const fakeToken = 'fake_token_value'
      if (response.ok) {
      if (response.ok) {
      const { token } = await response.json()
      localStorage.setItem('token', token)
        window.location.href = 'https://www.bilibili.com/'
        this.$router.push('/wuweiqieyue')
      } else {
        this.message = '登录失败'
      }
    }
  }
}
</script> -->

<style>

.container{
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: url("../../登录界面/background.webp") fixed no-repeat;
  background-size: cover;
}

.login-form{
  width: 240px;
  height: 280px;
  display: flex;
  flex-direction: column;
  padding: 40px;
  text-align: center;
  position:relative;
  z-index: 100;
  background: inherit;
  border-radius: 18px;
  overflow: hidden;
}

.login-form::before{
  content: "";
  width: calc(100% + 20px);
  height: calc(100% + 20px);
  position: absolute;
  top: -10px;
  left: -10px;
  overflow: hidden;
  background: inherit;
  box-shadow: inset 0 0 0 200px rgba(255, 255, 255,0.25);
  filter: blur(2px);
  z-index: -1;
}

.login-form h2{
  font-size: 18px;
  font-weight: 400;
  color: black;
}

.login-form input,
.login-form button{
  margin: 6px 0;
  height: 36px;
  border: none;
  background-color: rgba(255, 255, 255,0.3);
  border-radius: 4px;
  padding: 0 14px;
  color: black;
}
.login-form input::placeholder{
  color: antiquewhite;
}
.login-form button:focus,
.login-form input:focus{
  outline:0;
}

.login-form button{
  margin-top: 24px;
  background-color: cornflowerblue;
  color:aliceblue;
  position: relative;
  overflow: hidden;
  transition: 0.4s;
}

.login-form button:hover{
  background-color: cornflowerblue;
}

.login-form button::before,
.login-form button::after{
  content: "";
  display: block;
  width: 80px;
  height: 100%;
  background: rgba(179, 255, 210, 0.5);
  opacity: 0.5;
  position:absolute;
  top: 0;
  left: 0;
  transform: skew(-15deg);
  filter: blur(35px);
  overflow: hidden;
  transform: translateX(-100px);
}

.login-form button:hover::after{
  width: 40px;
  background: rgba(179, 255, 210, 0.3);
  left: 60px;
  opacity: 0;
  filter: blur(5px);
}
.login-form button:hover::before{
  transition: 1s;
  transform: translateX(320px);
  opacity: 0.7s;
}
.login-form button:hover::after{
  transition: 1s;
  transform: translateX(320px);
  opacity: 1s;
}

</style>
