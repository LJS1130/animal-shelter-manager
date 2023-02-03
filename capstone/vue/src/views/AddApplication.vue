<template>
<div>
  <h1 v-if="!isUser">Please sign in before submitting an application</h1>
  <div id="application" v-else>
    <h1> VOLUNTEER APPLICATION</h1>
    <form v-on:submit.prevent="addApplication">
      <div id="grid">
        <h2 id="info1">Are you interested in supporting Ruff Starts, Purrfect Hearts?</h2>
        <div class="below-title">
          <h3 style="text-align:center">
            Start by filling out an application to volunteer for us to review! Please fill out
            all required fields. It may take up to a week to approve to your
            application.
          </h3>
          <p style="text-align:center" class="description">Required fields</p>
        </div>

        <div
          id="error-msg"
          class="status-message error"
          v-show="errorMsg !== ''"
        >
          {{ errorMsg }}
        </div>
        <div id="contact">
          <h2>Contact Info</h2>
          <div class="form-element">
            <label for="name" class="required">Name</label>
            <input
              id="name"
              type="text"
              v-model="contact.contactName"
              required
            />
          </div>
          <br />
          <br />
          <h3>Provide at least one of the following*</h3>
          <div class="form-element">
            <label for="phone">Phone*</label>
            <input id="phone" type="tel" v-model="contact.phone" />
          </div>
          <br />
          <div class="form-element">
            <label for="email">Email*</label>
            <input id="email" type="email" v-model="contact.email" />
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="city">City</label>
            <input id="city" type="text" v-model="contact.city" />
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="state">State</label>
            <select name="state" id="state" v-model="contact.state">
              <!-- https://www.freeformatter.com/usa-state-list-html-select.html -->
              <option value="AL">Alabama</option>
              <option value="AK">Alaska</option>
              <option value="AZ">Arizona</option>
              <option value="AR">Arkansas</option>
              <option value="CA">California</option>
              <option value="CO">Colorado</option>
              <option value="CT">Connecticut</option>
              <option value="DE">Delaware</option>
              <option value="DC">District Of Columbia</option>
              <option value="FL">Florida</option>
              <option value="GA">Georgia</option>
              <option value="HI">Hawaii</option>
              <option value="ID">Idaho</option>
              <option value="IL">Illinois</option>
              <option value="IN">Indiana</option>
              <option value="IA">Iowa</option>
              <option value="KS">Kansas</option>
              <option value="KY">Kentucky</option>
              <option value="LA">Louisiana</option>
              <option value="ME">Maine</option>
              <option value="MD">Maryland</option>
              <option value="MA">Massachusetts</option>
              <option value="MI">Michigan</option>
              <option value="MN">Minnesota</option>
              <option value="MS">Mississippi</option>
              <option value="MO">Missouri</option>
              <option value="MT">Montana</option>
              <option value="NE">Nebraska</option>
              <option value="NV">Nevada</option>
              <option value="NH">New Hampshire</option>
              <option value="NJ">New Jersey</option>
              <option value="NM">New Mexico</option>
              <option value="NY">New York</option>
              <option value="NC">North Carolina</option>
              <option value="ND">North Dakota</option>
              <option value="OH">Ohio</option>
              <option value="OK">Oklahoma</option>
              <option value="OR">Oregon</option>
              <option value="PA">Pennsylvania</option>
              <option value="RI">Rhode Island</option>
              <option value="SC">South Carolina</option>
              <option value="SD">South Dakota</option>
              <option value="TN">Tennessee</option>
              <option value="TX">Texas</option>
              <option value="UT">Utah</option>
              <option value="VT">Vermont</option>
              <option value="VA">Virginia</option>
              <option value="WA">Washington</option>
              <option value="WV">West Virginia</option>
              <option value="WI">Wisconsin</option>
              <option value="WY">Wyoming</option>
            </select>
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="age" class="required">Age</label>
            <input id="age" type="number" v-model="contact.age" required />
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="link">Social Link</label>
            <input id="link" type="url" v-model="contact.socialLink" />
          </div>
          <br />
          <br />
        </div>
        <div id="volunteer-info">
          <h2>Volunteering Info</h2>
          <div class="form-element">
            <label for="hours" class="required">Weekly Hours</label>
            <input
              id="hours"
              type="number"
              v-model="application.weeklyHours"
              required
            />
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="time" class="required">Time of Day</label>
            <select id="time" v-model.number="application.isDay" required>
              <option :value="true">Day(8am-5pm)</option>
              <option :value="false">Night(5pm-9pm)</option>
            </select>
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="animal" class="required">Preferred Animal</label>
            <select
              id="animal"
              v-model.number="application.preferredAnimal"
              required
            >
              <option value="Dog">Dogs</option>
              <option value="Cat">Cats</option>
            </select>
          </div>
          <br />
          <br />
          <div class="form-element">
            <label for="reason">Why would you like to volunteer?</label>
            <textarea id="reason" v-model="application.reason"></textarea>
          </div>
        </div>
        <div id="submit" class="form-element">
          <button class="btn btn-submit">Submit</button>
        </div>
      </div>
    </form>
  </div>
</div>
  
</template>

<script>
import applicationService from "../services/ApplicationService";
export default {
  name: "add-application",
  data() {
    return {
      errorMsg: "",
      contact: {
        contactName: "",
        phone: "",
        email: "",
        city: "",
        state: "",
        age: null,
        socialLink: "",
      },
      application: {
        weeklyHours: null,
        isDay: null,
        preferredAnimal: null,
        reason: "",
      },
    };
  },
  methods: {
    addApplication() {
      if (this.contact.phone == "" && this.contact.email == "") {
        this.errorMsg = "Please enter a phone and/or email";
        return;
      }
      const applicationDTO = {
        contact: this.contact,
        application: this.application,
      };
      applicationService
        .addApplication(applicationDTO)
        .then((response) => {
          if (response.status === 201) {
            this.goBack();
          }
        })
        .catch((error) => {
          this.handleErrorResponse(error, "adding");
        });
    },
    resetForm() {
      this.newContact = {};
    },
    goBack() {
      this.$router.push({ name: "home" });
    },
    handleErrorResponse(error, verb) {
      if (error.response) {
        this.errorMsg =
          "Error " + verb + " application." + error.response.statusText + "'.";
      } else if (error.request) {
        this.errorMsg =
          "Error " + verb + " application. Server could not be reached.";
      } else {
        this.errorMsg =
          "Error " + verb + " application. Request could not be created.";
      }
    },
  },
  computed: {
    isUser() {
      return this.$store.getters.isUser;
    },
  }
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Oxygen&display=swap");

/* #application {
  display: flex;
  justify-content: flex-end;
} */

h1 {
  background-color: rgb(122, 118, 118);
  font-size: 35px;
  color: white;
  text-align: center;
  text-transform: uppercase;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  margin-top: 0px;
  margin-bottom: 0px;
  padding-top: 20px;
  padding-bottom: 20px;
  border: solid white 3px;
  border-bottom: none;
  /* width: 50%; */
}

#info1 {
  grid-area: info1;
  font-size: 25px;
  color: white;
  text-align: center;
  text-transform: uppercase;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
}

.below-title {
  grid-area: below;
  margin-bottom: 30px;
}

#info2 {
  grid-area: info2;
  font-size: 25px;
  color: white;
  text-align: center;
  
}

.description {
  grid-area: description;
  color: white;
}

.description::before {
  content: "*";
  color: red;
}


#error-msg {
  grid-area: error;
  text-align: center;
  background-color: red;
  color: white;
  border-radius: 10px;
}

#contact {
  grid-area: contact;
}

#volunteer-info {
  grid-area: volunteer;
}

#submit {
  grid-area: submit;
}

#grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-areas:
    "info1   info1"
    "below   below"
    "error   error"
    "contact volunteer"
    "submit  submit";
}

.required::after {
  content: "*";
  color: red;
}

#application {
  display: block;
  text-align: center;
  font-family: "Oxygen", sans-serif;
}

form {
  display: inline-block;
  margin-left: auto;
  margin-right: auto;
  text-align: left;
  border: 3px solid white;
  border-top: none;
  position: relative;
  height: 100%;
  width: 100%;
  padding: 1vw;
  /* margin: auto; */
  background-color: rgb(122, 118, 118);
}

#app-title {
  text-transform: uppercase;
  font-weight: 150;
  padding-bottom: 40px;
  color: white;
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  font-size: 32px;
  /* text-align: center; */
}
h2 {
  text-decoration: underline;
  text-decoration-color: cornflowerblue;
  color: white;
}

h3 {
  color: lightgray;
  
}

.form-element label {
  color: lightgray;
}

.form-element input,
textarea {
  width: 90%;
  padding: 20px 20px;
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
  width: 90%;
  background-color: rgba(255, 255, 255, 0.1);
  padding: 0px 20px;
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

button:hover {
  opacity: 100;
  font-size: 100%;
}

@media only screen and (max-width: 1024px) {
  form {
    padding-left: 3%;
    padding-right: 1%;
  }
  h1 {
    margin-bottom: -1%;
  }
}
@media only screen and (max-width: 550px) {
  form {
    padding-left: 3%;
    padding-right: 1%;
  }
  h1 {
    margin-bottom: -1%;
  }
}

</style>