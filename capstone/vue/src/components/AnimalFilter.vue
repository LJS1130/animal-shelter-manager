<template>
  <div class="well" :class="[{ filtered: $store.state.filter == type }, type]">
    <span v-on:click="updateFilter()">{{ type }}</span>
  </div>
</template>

<script>
export default {
  props: ["type"],
  methods: {
    updateFilter() {
      this.$store.commit("UPDATE_FILTER", this.type);
    },
  },
};
</script>

<style>
.well {
  display: block;
  text-transform: uppercase;
  text-align: center;
  width: 150px;
  margin-top: 5px;
  margin-bottom: 5px;
  font-size: 35px;
}

.well:hover{
  letter-spacing: 5px;
  border-bottom: 5px solid cornflowerblue;;  
  transition: all 0.3s ease-in-out;
  
}

.filtered{
  letter-spacing: 5px;
  border-bottom: 5px solid cornflowerblue;  
  transition: all 0.3s ease-in-out;
}

@media only screen and (max-width: 1024px) {
 .well {
   width: 75%;
 }
}

@media only screen and (max-width: 550px) {
 .well {
   width: 75%;
 }
}

</style>