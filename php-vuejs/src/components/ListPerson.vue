<template>
  <div>
    <table width="100%" border="1">
      <thead>
      <tr>
        <th>Nome</th>
        <th>Email</th>
        <th>Birthday</th>
      </tr>
      </thead>
      <tr v-for="person in list.data" :key="person.id">
        <td>{{ person.name }}</td>
        <td>{{ person.email }}</td>
        <td>{{ person.birthday }}</td>
      </tr>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: "ListPerson.vue",
  data: function () {
    return {
      api_url: null,
      list: []
    }
  },
  created() {
    this.api_url = process.env.VUE_APP_API_URL
    axios.get( this.api_url + "person")
        .then(res => {
          this.list = res.data
        })
  }
}
</script>

<style scoped>

</style>