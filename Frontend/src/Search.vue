<template>
  <div id="search">
    <v-layout column align-center>
      <v-layout row>
        <v-container grid-list-md text-xs-center>
          <v-layout row wrap>
              <v-flex xs12>
                <v-btn v-on:click="fetchUsers" :disabled="disabled" color="primary">Users</v-btn>
              </v-flex>
          </v-layout>
        </v-container>
        <v-chip disabled v-show="currentUser" color="primary" text-color="white">
          <v-avatar>
            <v-icon>account_circle</v-icon>
          </v-avatar>
          User: {{ currentUser }}
        </v-chip>
      </v-layout>
        <v-data-table
          v-show="currentUserRecommendations.length"
          v-bind:headers="headers"
          :items="currentUserRecommendations"
          hide-actions
          class="elevation-5 dataTable"
        >
        <template slot="items" slot-scope="props">
          <td class="text-xs-right">{{ props.item.id }}</td>
          <td class="text-xs-right">{{ props.item.name }}</td>
          <td class="text-xs-right">{{ props.item.rating }}</td>
        </template>
      </v-data-table>
      <v-data-table
          v-show="currentMovieRecommendations.length"
          v-bind:headers="movieHeaders"
          :items="currentMovieRecommendations"
          hide-actions
          class="elevation-5 dataTable"
        >
        <template slot="items" slot-scope="props">
          <td class="text-xs-right">{{ props.item.movie }}</td>
          <td class="text-xs-right">{{ props.item.rating }}</td>
        </template>
      </v-data-table>
        <ul class="list">
          <li v-for="(res, i) in users" :key="i">
              <a href="#" v-on:click="fetchUserBasedEuclidean(res.id)">{{ res.username }}</a>
          </li>
        </ul>
    </v-layout>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  data () {
    return {
      headers: [
        { text: 'ID', value: 'id' },
        { text: 'Name', value: 'name' },
        { text: 'Rating', value: 'rating' },
      ],
      movieHeaders: [
        { text: 'Movie', value: 'movie' },
        { text: 'Rating', value: 'rating' },
      ],
      users: [],
      currentUser: null,
      currentUserRecommendations: [],
      currentMovieRecommendations: [],
      disabled: false
    }
  },
  methods: {
    fetchUsers () {
      this.disabled = true
      this.currentUser = null,
      this.currentUserRecommendations = []
      this.currentMovieRecommendations = []
      axios.get(`http://localhost:2525/users`)
      .then(response => {
        this.users = []
        this.disabled = false
        console.log(response)
        response.data.payload.forEach(element => {
          this.users.push(element)
        });
      })
    },
    fetchUserBasedEuclidean(id) {
      axios.get(`http://localhost:2525/euclidean/user?user=${id}`)
      .then(response => {
        this.users = []
        this.currentUser = id
        this.disabled = false
        console.log(response)
        response.data.payload.forEach(element => {
          this.currentUserRecommendations.push(element)
        });
      })

      axios.get(`http://localhost:2525/euclidean/item?user=${id}`)
      .then(response => {
        this.users = []
        this.currentUser = id
        this.disabled = false
        console.log(response)
        response.data.payload.forEach(element => {
          this.currentMovieRecommendations.push(element)
        });
      })
    },
  }
}
</script>

<style lang="stylus" scoped>
.list
  width: 50%
  text-align: left
  list-style: none
  margin-top: 20px

.dataTable
  width: 50%

.chip
  margin: auto 
  height: 30px
</style>
