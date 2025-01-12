<!-- <template>
  <div id="app">
    <nav>
      <router-link to="/jiemianView">Home</router-link> |
      <router-link to="/homepageView">About</router-link>
    </nav>
    <router-view/>
  </div>
</template> -->

<!-- <style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style> -->
<!-- <template>
  <div id="app">

    <h1>登录</h1>
    <form id="loginForm">
        <input type="text" placeholder="用户名" id="username" required />
        <input type="password" placeholder="密码" id="password" required />

        <label for="captcha">验证码：</label>
        <img src="/captcha" alt="验证码" id="captcha-img" />
        <input type="text" id="captcha" placeholder="输入验证码" required />

        <button type="submit">登录</button>
    </form>
    <div id="message"></div>
  </div>
</template>

<script>
document.getElementById('loginForm').addEventListener('submit', async (e) => {
  e.preventDefault()
  const username = document.getElementById('username').value
  const password = document.getElementById('password').value
  const captcha = document.getElementById('captcha').value

  const response = await fetch('/login', {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ username, password, captcha })// 包含验证码
  })

  if (response.ok) {
    const { token } = await response.json()
    // 将 token 存储在 localStorage 或 sessionStorage
    localStorage.setItem('token', token)
    window.location.href = '/homepage'// 登录成功后跳转到首页
  } else {
    document.getElementById('message').innerText = '登录失败'
  }
})
</script> -->
<!-- <template>
  <div id="">
    <h1>登录</h1>
    <form @submit.prevent="handleLogin">
      <input type="text" placeholder="用户名" v-model="username" required />
      <input type="password" placeholder="密码" v-model="password" required />

      <label for="captcha">验证码：</label>
      <img :src="captchaImage" alt="验证码" id="captcha-img" />
      <input type="text" v-model="captcha" placeholder="输入验证码" required />

      <button type="submit">登录</button> -->
      <!-- <router-link to="/homepageView">测试跳转页面功能</router-link> |
      <router-view/> -->
    <!-- </form>
    <div id="message">{{ message }}</div>
  </div>
</template> -->
<template>
  <div id="app">
    <nav>
      <!-- <router-link to="/loginView">Home</router-link> |
      <router-link to="/homepageView">About</router-link> -->
    </nav>
    <router-view/>
  </div>
</template>
