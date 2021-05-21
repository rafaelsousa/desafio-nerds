<template>
  <div id="app">
    <list-person/>
    <add-person/>
  </div>
</template>

<script>
import ListPerson from './components/Person/List'
import AddPerson from './components/Person/Add'

export default {
  name: 'App',
  components: {
    ListPerson,
    AddPerson
  }
}
</script>

<style>
</style>
