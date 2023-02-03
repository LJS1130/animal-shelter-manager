<template>
<div>
  <h1 v-if="!isVolunteer">You don't have access</h1>
  <div id="animal" v-else>
    <div class="status-message error" v-show="errorMsg !== ''">
      {{ errorMsg }}
    </div>
    <form v-on:submit.prevent="addAnimal">
      <h1 id="animal-title">Add/update an Adoptable Pet</h1>
      <div>
        <div class="form-element">
            <label for="Type" class="required">Type:</label>
            <select id="Type" v-model="pet.type" required>
              <option value="dog">Dog</option>
              <option value="cat">Cat</option>
            </select>
          </div><br/><br/>
        <div class="form-element">
          <label for="name">Name:</label>
          <input type="text" v-model="pet.name" required/>
        </div><br><br>
        <div class="form-element">
          <label for="age">Age:</label>
          <input type="number" v-model="pet.age" required/>
        </div><br><br>
        <div class="form-element">
          <label for="sex">Sex:</label>
          <input type="text" v-model="pet.sex"  required/>
        </div><br><br>
        <div class="form-element">
          <label for="breed">Breed:</label>
          <input type="text" v-model="pet.breed" />
        </div><br><br>
        <div class="form-element">
          <label for="size">Size (S/M/L/XL/XXL):</label>
          <input type="text" v-model="pet.size" />
        </div><br><br>
        <div class="good-with">
            <label>Good with:</label>
          
          <div class="form-element">
            <label for="isGoodWithDogs" class="required">Dogs?</label>
            <select id="isGoodWithDogs" v-model.number="pet.goodWithDogs" required>
              <option :value="true">✅</option>
              <option :value="false">❌</option>
            </select>
          </div>
          <div class="form-element">
            <label for="isGoodWithCats" class="required">Cats?</label>
            <select id="isGoodWithCats" v-model.number="pet.goodWithCats" required>
              <option :value="true">✅</option>
              <option :value="false">❌</option>
            </select>
          </div>
          <div class="form-element">
            <label for="isGoodWithKids" class="required">Kids?</label>
            <select id="isGoodWithKids" v-model.number="pet.goodWithKids" required>
              <option :value="true">✅</option>
              <option :value="false">❌</option>
            </select>
          </div>
        </div><br><br>

        <div class="form-element">
          <label for="imageLink">Image Link:</label>
          <input type="text" v-model="pet.imageLink" required/>
        </div><br/><br/>
        <div class="form-element">
            <label for="availability" class="required">Availability:</label>
            <select id="availability" v-model="pet.available" required>
              <option :value="true">Available for Adoption</option>
              <option :value="false">Pet Has Been Adopted</option>
            </select>
          </div><br/><br/>
          <div class="form-element">
          <label for="userID">User Number associated with adoption:</label>
          <input type="number" v-model="pet.userID"/>
        </div>
      </div>
      <div id="submit" class="form-element">
        <button class="btn btn-submit">Submit</button>
      </div>
    </form>
  </div>
</div>
  
</template>

<script>
// import AnimalForm from "../components/AnimalForm.vue";
import animalService from "../services/AnimalService";

export default {
  name: "add-animal",
  props: {
    petID: {
      type: Number,
      default: 0
    }
  },
  data() {
    return {
      errorMsg: "",
      pet: {
        name: "",
        type: "",
        age: 0,
        sex: "",
        breed: "",
        size: "",
        imageLink: "",
        goodWithDogs: null,
        goodWithCats: null,
        goodWithKids: null,
        available: true,
        userID: null,
      },
    };
  },
  computed: {
    isVolunteer() {
      return this.$store.getters.isVolunteer;
    }
  },
  methods: {
    addAnimal() {
        const newPet = {
            name: this.pet.name,
            type: this.pet.type,
            age: this.pet.age,
            sex: this.pet.sex,
            breed: this.pet.breed,
            size: this.pet.size,
            imageLink: this.pet.imageLink,
            goodWithDogs: this.pet.goodWithDogs,
            goodWithCats: this.pet.goodWithCats,
            goodWithKids: this.pet.goodWithKids,
            available: this.pet.available
        };

      if (this.petID === 0) {
          //ADD
      animalService
        .addAnimal(newPet)
        .then((response) => {
          if (response.status === 201) {
            this.goBack();
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
      } else {
          //EDIT
        newPet.id = this.petID;
        animalService.editAnimal(newPet).then((response) => {
            if (response.status === 200) {
                this.goBack();
            }
        })
        .catch((error) => {
            this.handleErrorResponse(error, "editing");
        })
      }
    },
    resetForm() {
      this.newPet = {};
    },
    goBack() {
      this.$router.push({ name: "home" });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " +
          verb +
          " pet. Response received was '" +
          error.response.statusText +
          "'.";
      } else if (error.request) {
        this.errorMsg = "Error " + verb + " pet. Server could not be reached.";
      } else {
        this.errorMsg = "Error " + verb + " pet. Request could not be created.";
      }
    },
  },
  created() {
      if (this.petID != 0) {
          animalService.getAnimal(this.petID).then((response) => {
              this.pet = response.data;
          })
          .catch((error) => {
              if (error.response && error.response.status === 404) {
            alert(
              "Pet not available. This pet may have been deleted or you have entered an invalid pet ID."
            );
            this.goBack();
          }
          });
      }
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

#animal {
  display: block;
  font-family: "Oxygen", sans-serif;
}

.good-with {
  width: 103%;
  height: 100%;
  display: flex;
  justify-items: space-between;
  align-items: baseline;
}
.good-with > div {
  flex: 1;
  
}
form {
  display: inline-block;
  text-align: left;
  border: 3px solid white;
  position: relative;
  height: 100%;
  width: 100%;
  margin: auto;
  padding: 2vw 3vw;
  background-color: rgb(122, 118, 118);
}

#animal-title {
  text-transform: uppercase;
  font-weight: 150;
  /* padding-bottom: 40px; */
  color: white;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  font-family: "Oxygen", sans-serif;
  text-align: center;
}

h2 {
  color: lightgray;
}
label {
  font-family: "Oxygen", sans-serif;
  font-size: 25px;
  color: lightgray;
  padding: 10px;
}
.form-element input,
textarea {
  width: 100%;
  padding: 22px 20px;
  display: block;
  height: 21px;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.1);
  border: 2px solid rgba(255, 255, 255, 0);
  overflow: hidden;
  margin-top: 15px;
  color: white;
}

select {
  display: flex;
  align-content: center;
  width: 100%;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 10px;
  display: block;
  height: 45px;
  border-radius: 10px;
  color: white;
  margin-top: 15px;
}

option {
  color: black;
}

.form-element textarea {
  height: 120px;
}

button {
  background-color: cornflowerblue;
  color: white;
  width: 100%;
  padding: 10px 20px;
  display: block;
  height: 39px;
  border-radius: 10px;
  margin-top: 30px;
  border: none;
  text-transform: uppercase;
  opacity: 0.8;
}

.good-with{
  width: 100%;
}
button:hover {
  opacity: 100;
  font-size: 100%;
}

#availability {
  width: 100%;
  font-size: 20px;
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