<template>
  <div class="home">
    <div class="animal-filter" :class="$store.state.filter">
      <h1 id="title">Adoptable &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;Animals &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</h1>
      <!-- <animal-filter type="All" />
      <animal-filter type="dog" />
      <animal-filter type="cat" /> -->
    </div>
    <div class="flex-display">
      <animal-filter type="All" />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      <animal-filter type="dog" />&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
      <animal-filter type="cat" />
    </div>
    <animal-list />
  </div>
</template>

<script>
import AnimalFilter from "../components/AnimalFilter.vue";
import AnimalList from "../components/AnimalList.vue";
export default {
  components: { AnimalFilter, AnimalList },
  name: "home",
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");
.flex-display {
  display:flex;
  justify-content: center;
  cursor: pointer;
  font-size: 50px;
  font-family: "Oxygen", sans-serif;
  background-color: rgb(122, 118, 118);
  border: solid 3px white;
  border-top: none;
  color: lightgray;
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center center;
}

.animal-filter {
  padding-left: 50px;
  display: flex;
  justify-content: space-evenly;
  cursor: pointer;
  font-size: 50px;
  display: block;
  text-align: center;
  font-family: "Oxygen", sans-serif;
  text-align: left;
  background-repeat: no-repeat;
  background-size: contain;
  background-position: center center;
  background-color: rgb(122, 118, 118);
  border: solid 3px white;
  border-bottom: none;
  color: lightgray;
  /* width: 85%; */
  height: 185px;
}

.animal-filter {
  display: flex;
  align-content: start;
}
#title {
  display: flex;
  color: white;
  font-size: 75px;
  font-weight: 150;
  text-transform: uppercase;
}
.animal-filter.All {
  background-image: url("../assets/cat_and_dog.png");
}

.animal-filter.dog {
  background-image: url("../assets/dog.png");
}

.animal-filter.cat {
  background-image: url("../assets/cat.png");
}

@media only screen and (max-width: 1024px) {
 .flex-display {
    font-size: 50%;
  }
  #title {
    font-size: 90%;
    padding-top: 5%;
  }

  .animal-filter {
    padding-left: 3%;

  }
}

@media only screen and (max-width: 550px) {
 .flex-display {
    font-size: 50%;
  }
  #title {
    font-size: 60%;
    padding-top: 3%;
    padding-left: 3%;
  }
  .animal-filter {
    padding-left: 3%;
  }
}
</style>