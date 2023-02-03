<template>
  <div class="contact">
    <div class="contacts">
      <div class="loading" v-if="isLoading">
        <img src="../assets/cat_loading.gif" />
      </div>
      <contact-display
        v-for="contact in contacts"
        :key="contact.id"
        :contact="contact"
        
      />
    </div>
  </div>
</template>

<script>
import ContactDisplay from './ContactDisplay.vue';
import applicationService from '../services/ApplicationService';

export default {
  components: {
    ContactDisplay
  },
  data() {
    return {
      contacts: [],
      isLoading: true,
    };
  },
  created() {
    applicationService.getContact().then((response) => {
      this.contacts = response.data;
      this.isLoading = false;
    });
  },
};
</script>

<style scoped>

.contacts {
  background-color: rgb(122, 118, 118);
  text-align: center;
  font-family: 'Oxygen', sans-serif;
}
.contact {
  color: lightgray
}
</style>