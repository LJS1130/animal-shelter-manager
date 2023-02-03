<template>
<div>
  <h1 v-if="!isVolunteer">You don't have access</h1>
  <div class="main">
    <h1>Directory</h1>
    <contact-list/>
  </div>
</div>
  
</template>

<script>
import ContactList from '../components/ContactList.vue';
export default {
  components: { ContactList },
  computed: {
    isVolunteer() {
      return this.$store.getters.isVolunteer;
    },
  }
}
</script>

<style scoped>

h1 {
   text-transform: uppercase;
   font-weight: 150;
   text-decoration: underline;
   text-decoration-color: cornflowerblue;
}

div.main {
  text-transform: capitalize;
  background-color: rgb(122, 118, 118);
  text-align: center;
  font-family: 'Oxygen', sans-serif;
  color:  white;
  border: solid white 3px;
  padding-top: 10px;
}

@media only screen and (max-width: 1024px) {
  h1 {
    font-size: 90%;
  }

}

@media only screen and (max-width: 550px) {
  h1 {
    font-size: 90%;
  }

}

</style>