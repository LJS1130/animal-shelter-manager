<template>
  <div id="login" class="text-center">
    <form class="form-signin" @submit.prevent="login">
      <h1 style="text-align:center" id="page-title" class="h3 mb-3 font-weight-normal">Sign In</h1>
      <div style="text-align:center"
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >
      <p style="text-align:center"> Thank you for registering, please sign in.</p></div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <br>
      <br>
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <button type="submit">Sign in</button>
      <div id="need">
      <router-link :to="{ name: 'register' }">Need an account?</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  components: {},
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    }
  }
};
</script>

<style scoped>
/* @import url('https://fonts.googleapis.com/css2?family=Montserrat:wght@100&display=swap'); */
@import url('https://fonts.googleapis.com/css2?family=Oxygen&display=swap');
#login {
  display: block;
  text-align: center;
  font-family: 'Oxygen', sans-serif;
}

.alert {
  color: white;
  font-size: 20px;
  padding-bottom: 15px;
  text-decoration: underline;
}

form {
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
  text-align: left;
  border: 3px solid #f1f1f1;
  position: relative;
  height: 560px;
  width: 400px;
  margin: auto;
  padding: 30px 60px;
  background-color: rgb(122, 118, 118);
}

#page-title {
  text-transform: uppercase;
  font-weight: 150;
  color: white;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
}

.sr-only {
  color: lightgray;
}

#username, #password {
  width: 89%;
  padding: 10px 20px;
  display: block;
  height: 15px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.1);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  color: white;
}

button {
  background-color: cornflowerblue;
  color: white;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 39px;
  border-radius: 10px;
  margin-top: 30px;
  border: none;
  text-transform: uppercase;
  opacity: 0.8;
}

button:hover {
  opacity: 100;
  font-size: 100%;
}

a {
  display: block;
  text-align: center;
  position: relative;
  margin-top: 10%;
  color: white;
}
</style>