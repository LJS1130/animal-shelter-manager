<template>
  <div>
    <animal-form v-bind:petID="parseInt($route.params.petID)" />
  </div>
</template>

<script>
import AnimalForm from "../components/AnimalForm.vue";
export default {
  components: {
    AnimalForm,
  },
};
</script>

<style>
</style>