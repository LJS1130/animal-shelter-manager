import axios from 'axios';

export default {

  addApplication(application) {
    return axios.post('/submit', application);
  },

  getPending() {
    return axios.get('applications/P');
  },

  putApprove(id) {
    return axios.put('applications/' + id + "/approve")
  },

  putDeny(id) {
    return axios.put('applications/' + id + "/deny")
  },

  getContact() {
    return axios.get('/volunteers')
  }
}
