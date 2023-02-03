<template>
  <div>
    <h1 v-if="!isAdmin">You don't have access</h1>
    <div>

    </div>
      <h1>Pending Applications</h1>
      <application-list/>
  </div>
</template>

<script>
import ApplicationList from '../components/ApplicationList.vue'
export default {
  components: { ApplicationList },
  computed: {
    isAdmin() {
      return this.$store.getters.isAdmin;
    },
  }
}
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

h1 {
  width: 100%;
  text-transform: uppercase;
  font-weight: 150;
  text-align: center;
  font-family: 'Oxygen', sans-serif;
  color:  white;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  background-color: rgb(122, 118, 118);
  padding-top: 20px;
  padding-bottom: 20px;
  margin-top: 10px;
  margin-bottom: -10px;
  border: solid white 3px;
  border-bottom: none;
}


@media only screen and (max-width: 1024px) {
 
  h1 {
    font-size: 75%;
    /* width: 75%; */
  }
}

@media only screen and (max-width: 550px) {
  h1 {
    font-size: 50%;
    /* width: 50%; */
  }
}

</style>