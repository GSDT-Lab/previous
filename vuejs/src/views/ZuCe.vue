<template>
  <div class="container">
    <form action="#" class="register-form" @submit.prevent="handleRegister">
      <h2>注册</h2>
      <input
        type="text"
        name="username"
        placeholder="用户名"
        v-model="username"
        required
      />
      <input
        type="password"
        name="password"
        placeholder="密码"
        v-model="password"
        required
      />
      <input
        type="password"
        name="password2"
        placeholder="确认密码"
        v-model="password2"
        required
      />
      <button type="submit">注册</button>
      <button @click="goToLogin">返回登录</button>
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
      password2: '',
      message: ''
    }
  },
  methods: {
    goToLogin () {
      // 跳转到登录页
      this.$router.push('/')
    },
    async handleRegister () {
      // 检查两次密码是否一致
      if (this.password !== this.password2) {
        this.message = '两次密码不一致，请重新输入'
        return
      }

      try {
        const response = await fetch('http://localhost:8080/api/register', {
          method: 'POST',
          headers: { 'Content-Type': 'application/json' },
          body: JSON.stringify({
            username: this.username,
            password: this.password
          })
        })

        if (response.ok) {
          const data = await response.json()
          if (data.success) {
            this.message = '注册成功！即将跳转到登录页面...'
            setTimeout(() => {
              this.$router.push('/')
            }, 2000) // 2秒后跳转到登录页
          } else {
            this.message = data.message || '注册失败，请重试'
          }
        } else {
          this.message = `注册失败，服务器返回状态码: ${response.status}`
        }
      } catch (error) {
        console.error('注册请求失败:', error)
        this.message = '注册请求失败，请检查网络连接'
      }
    }
  }
}
</script>

<style>
/* 样式部分保持不变 */
html,
body {
  margin: 0;
  font-family: "PingFang SC", "Microsoft Yahei", sans-serif;
}

.container {
  width: 100vw;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: url("../../注册界面/background.webp") fixed no-repeat;
  background-size: cover;
}

.register-form {
  width: 240px;
  height: 260px;
  display: flex;
  flex-direction: column;
  padding: 40px;
  text-align: center;
  position: relative;
  z-index: 100;
  background: inherit;
  border-radius: 18px;
  overflow: hidden;
}

.register-form::before {
  content: "";
  width: calc(100% + 20px);
  height: calc(100% + 20px);
  position: absolute;
  top: -10px;
  left: -10px;
  overflow: hidden;
  background: inherit;
  box-shadow: inset 0 0 0 200px rgba(255, 255, 255, 0.25);
  filter: blur(2px);
  z-index: -1;
}

.register-form h2 {
  font-size: 18px;
  font-weight: 400;
  color: black;
}

.register-form input,
.register-form button {
  margin: 6px 0;
  height: 36px;
  border: none;
  background-color: rgba(255, 255, 255, 0.3);
  border-radius: 4px;
  padding: 0 14px;
  color: black;
}

.register-form input::placeholder {
  color: black;
}

.register-form button:focus,
.register-form input:focus {
  outline: 0;
}

.register-form button {
  margin-top: 24px;
  background-color: cornflowerblue;
  color: aliceblue;
  position: relative;
  overflow: hidden;
  transition: 0.4s;
}

.register-form button:hover {
  background-color: cornflowerblue;
}

.register-form button::before,
.register-form button::after {
  content: "";
  display: block;
  width: 80px;
  height: 100%;
  background: rgba(179, 255, 210, 0.5);
  opacity: 0.5;
  position: absolute;
  top: 0;
  left: 0;
  transform: skew(-15deg);
  filter: blur(35px);
  overflow: hidden;
  transform: translateX(-100px);
}
.register-form button:hover::after {
  width: 40px;
  background: rgba(179, 255, 210, 0.3);
  left: 60px;
  opacity: 0;
  filter: blur(5px);
}

.register-form button:hover::before {
  transition: 1s;
  transform: translateX(320px);
  opacity: 0.7;
}

.register-form button:hover::after {
  transition: 1s;
  transform: translateX(320px);
  opacity: 1;
}
</style>
