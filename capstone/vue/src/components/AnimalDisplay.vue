<template>
  <div class="flex-display" v-if="animal.available">
    <h3 class='animal-name'>{{ animal.name }}</h3>
    <p>{{ animal.age }}</p>
    <div class="animal-image"><img :src="animal.imageLink" /></div>
    <router-link class="btn updateAnimal" :to="{ name: 'Edit', params: {petID: animal.id} }">
    <button v-if="isVolunteer">Update</button>
    </router-link>
  </div>
</template>

<script>
export default {
  props: ["animal"],
  computed: {
    isVolunteer() {
      return this.$store.getters.isVolunteer;
    },
  }
};
</script>

<style scoped>

.flex-display {
  display: flex;
  flex-direction: column;
  align-content: center;
  justify-content: center;
  background-color: rgb(122, 118, 118);
  border: 3px solid white;
  width: 18vw;
  flex-grow: 1;
  font-size: 20px;
}

h3 {
  font-weight: 250;
}

.flex-display:hover {
  cursor: pointer;
  transition: all 0.2s ease-in-out;
  box-shadow: 0 0 40px rgb(21, 5, 247);
}
.animal-image {
  height: 10rem;
}

img {
  max-height: 100%;
  max-width: 100%;
  object-fit: scale-down;
  padding-bottom: 1vh;
}
.animal-name {
  text-transform: capitalize;
  color: white;
}
button {
  background-color: cornflowerblue;
  color: white;
  margin-top: 10px;
  padding: 10px 10px;
  display: block;
  /* height: 10px; */
  border-radius: 10px;
  border: solid 1px white;
  text-transform: uppercase;
  font-size: 100%;
  opacity: 0.8;
}

button:hover {
  opacity: 100;
  font-size: 100%;
}


@media only screen and (max-width: 1024px) {
 .flex-display {
   width: 85%;
 }
 button {
   font-size: 75%;
 }
}

@media only screen and (max-width: 550px) {
 .flex-display {
   width: 85%;
 }
 button{
   font-size: 50%;
 }
}

</style>