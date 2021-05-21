<template>
  <div>
    <button @click="toggleAdd">Add</button>
    <div v-if="addButton">
      <p>
        <label>Name:</label>
        <input v-model="form.name">
      </p>
      <p>
        <label>E-mail:</label>
        <input v-model="form.email">
      </p>
      <p>
        <label>Birthday:</label>
        <input v-model="form.birthday">
      </p>
      <p><button @click="save">Save</button> </p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "AddPerson.vue",
  data: function () {
    return {
      api_url: null,
      addButton: false,
      form: {
        name: '',
        email: '',
        birthday: ''
      }
    }
  },
  created() {
    this.api_url = process.env.VUE_APP_API_URL
  },
  methods: {
    toggleAdd(){
      this.addButton = !this.addButton
    },
    save() {
      axios.post( this.api_url + "person", this.form)
          .then(res => {
            console.log(res)
          })
    }
  }
}
</script>

<style scoped>

</style>