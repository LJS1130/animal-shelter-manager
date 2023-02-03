<template>
<div class="main1">
  <div class="applications">
    <div class="loading" v-if="isLoading">
        <img src="../assets/cat_loading.gif" />
      </div>
      <h1 v-if="pendingApps">No applications to review</h1>
    <application-display v-for="application in applications" v-bind:key="application.application.id"
        :application="application" v-else/>
  </div>
</div>

  
</template>

<script>
import ApplicationDisplay from './ApplicationDisplay.vue';
import applicationService from "../services/ApplicationService";

export default {
  components: {
    ApplicationDisplay
  },
  data() {
    return {
      applications: [],
      isLoading: true,
    }
  },
  computed: {
    pendingApps() {
      return this.applications.length === 0;
    }
  },
  created() {
    this.refreshApplications();
  },
  methods: {
    refreshApplications() {
      applicationService.getPending().then((response) => {
      this.applications = response.data;
      this.isLoading = false;
    });
    }
    
  }
};
</script>

<style scoped>


h1 {
  font-family: "oxygen", sans-serif;
  color: white;
  font-weight: 150;
  text-align: center;
  background-color: rgb(122, 118, 118);
  margin-top: 0;
  border: solid white 3px;
  border-top: 0;
  padding-bottom: 15px;
}

div.main1 div.well-display {
  display: flex;
  justify-content: space-around;
}

div.main1 div.well-display div.well {
  display: inline-block;
  width: 15%;
  border: 3px white solid;
  text-align: center;
  margin: 0.25rem;
}

div.main1 div.well-display div.well span.amount {
  color: rgb(65, 64, 64);
  display: block;
  font-size: 2.5rem;
}

div.main1 div.well-display div.well {
  cursor: pointer;
}

@media only screen and (max-width: 1024px) {
  h1 {
    /* width: 75%; */
    font-size: 75%;
  }
}

@media only screen and (max-width: 550px) {
  h1 {
    /* width: 50%; */
    font-size: 50%;
  }
}
</style>
