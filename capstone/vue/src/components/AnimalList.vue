<template>
  <div>
    <div class="loading" v-if="isLoading">
      <img src="../assets/cat_loading.gif" />
    </div>
    <div class="animal-display">
      <animal-display
        class="animal"
        v-for="animal in filteredAnimals"
        v-bind:key="animal.id"
        :animal="animal"
      />
    </div>
  </div>
</template>

<script>
import animalService from "../services/AnimalService";
import AnimalDisplay from "./AnimalDisplay";
export default {
  components: {
    AnimalDisplay,
  },
  data() {
    return {
      animals: [],
      isLoading: true,
    };
  },
  created() {
    animalService.getAnimals().then((response) => {
      this.animals = response.data;
      this.isLoading = false;
    });
  },
  computed: {
    filteredAnimals() {
      const animalsFilter = this.$store.state.filter;
      return this.animals.filter((animal) => {
        return animalsFilter === "All" ? true : animalsFilter === animal.type;
      });
    },
  },
};
</script>

<style>
.loading {
  text-align: center;
}
</style>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

.animal-display {
  display: grid;
  grid-template-columns: 1fr 1fr 1fr 1fr 1fr;
  justify-items: center;
  align-items: center;
  font-family: "Oxygen", sans-serif;
  color: lightgray;
}

#title {
  text-align: center;
}

h1 {
  font-family: "Oxygen", sans-serif;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  color: rgb(77, 76, 76);
}
h3,
p {
  font-family: "Oxygen", sans-serif;
  color: lightgray;
}

.animal {
  margin: 1%;
  padding: 1.5%;
  text-align: center;
}

.animal a:link,
.animal a:visited {
  color: rgb(243, 243, 243);
  text-decoration: none;
}

@media only screen and (max-width: 1024px) {
  .animal-display {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr 1fr;
  }
}

@media only screen and (max-width: 550px) {
  .animal-display {
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
  }
}
</style>