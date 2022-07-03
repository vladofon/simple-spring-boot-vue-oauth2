<template>
  <main role="main" class="inner cover">
    <h1>Welcome </h1>
    <h1 v-if="!isAuthenticated">You are not logged in!</h1>
  </main>
</template>

<script>
export default {
  created() {
    let currentUser = localStorage.getItem("currentUser");
    if (!currentUser || currentUser === {}) {
      this.$store.dispatch("fetchUser");
    }
  },
  computed: {
    isAuthenticated() {
      return this.$store.getters.isAuthenticated;
    }
  }
};
</script>