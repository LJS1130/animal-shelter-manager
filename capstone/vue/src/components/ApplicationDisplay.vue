<template>
  <div class="application" v-bind:key="application.application.id" v-if="!isHidden">
    <div class="centered">
      <h3 style="text-align:center">{{ application.contact.contactName }}, {{ application.contact.age }}</h3>
    </div>
    <div class="info">
      <p style="text-align:center">Hours: {{ application.application.weeklyHours }}</p>
      <div>
        <p style="text-align:center" v-if="application.application.isDay">Time: Day</p>
        <p style="text-align:center" v-else>Time: Night</p>
      </div>
      <p style="text-align:center">Animal: {{ application.application.preferredAnimal }}</p>
    </div>
    <div class="centered">
      <p style="text-align:center">{{ application.application.reason }}</p>
    </div>
    
    <div class="flex-display">
      <button @click="onApprove()">Approve</button> 
      <button @click="onDeny()">Deny</button>
    </div>
  </div>
</template>

<script>
import applicationService from "../services/ApplicationService";
export default {
  props: ["application"],
  data() {
    return {
      isHidden: false
    }
    
  },
  methods: {
    onApprove() {
      applicationService.putApprove(this.application.application.id).then(() => {
        this.$parent.refreshApplications();
    });
      
    },
    onDeny() {
      applicationService.putDeny(this.application.application.id).then(() => {
        this.$parent.refreshApplications();
    });
    }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

div {
  background-color: rgb(122, 118, 118);
  width: 100%;
  display: flex;
  justify-content: center;
}
div.info {
  width: 100%;
  display: flex;
}
div.info > * {
  display: flex;
  justify-content: center;
  flex: 1;
  text-align: center;
}

div.application {
  border: 3px white solid;
  border-top: none;
  padding: 1rem;
  width: 100%;
  display: block;
  text-align: center;
  font-family: "Oxygen", sans-serif;
}

div.application p {
  margin: 20px;
}

div.application h3 {
  display: inline-block;
}

h3 {
  color: lightgray;
  font-size: 125%;
  text-align: center;
}
p {
  color: lightgray;
  font-size: 115%;
}
.flex-display {
  display: flex;
  flex-direction: column;
  align-items: space-between;
  justify-content: center;
  width: 100%;

}
button {
  background-color: cornflowerblue;
  color: white;
  width: 20%;
  padding: 10px 10px;
  display: block;
  height: 39px;
  border-radius: 10px;
  border: solid 1px white;
  text-transform: uppercase;
  font-size: 100%;
  opacity: 0.8;
}

button:hover {
  opacity: 100;
  font-size: 110%;
}

@media only screen and (max-width: 1024px) {
   div, div.info, div.application {
    width: 75%;
  }
  button {
  font-size: 75%;
  }
  button:hover {
    font-size: 75%;
  }
  .flex-display {
    width: 75%;
  }
}

@media only screen and (max-width: 550px) {
  div, div.info, div.application {
    width: 50%;
  }
  button {
  width: 60%;
  font-size: 50%;
  }
  button:hover {
    font-size: 50%;
  }
  .flex-display {
    width: 50%;
  }
  div {
    flex-direction: column;
  }
}



</style>
