<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 style="text-align:center" id="page-title" class="h3 mb-3 font-weight-normal">Sign Up</h1>
      <div style="text-align:center" id="validation-error" class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
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
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
      <div id="have">
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      </div>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Oxygen&display=swap');
#register {
  display: block;
  text-align: center;
  font-family: 'Oxygen', sans-serif;
}

#validation-error {
  color:white;
  padding-top: 0px;
  padding-bottom: 15px;
  font-size: 20px;
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
  padding: 60px 60px;
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

#username, #password, #confirmPassword {
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
  margin-top: 3%;
  color: white;
}

</style>
