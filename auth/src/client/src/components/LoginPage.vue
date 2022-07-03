<template>
  <a @click.prevent="login">
    <span>Sign in with Google</span> 
  </a>
  <br/>
  <a @click.prevent="session">
    <span>Check session</span>
  </a>
  <br/>
  <a @click.prevent="trycont">
    <span>Check security</span>
  </a>
  
</template>

<script>
import axios from 'axios'

export default {
	data() {
    return {
      profile: Object,
    }
	},
  methods: {
    login() {
      window.location.href = "http://localhost:8080/sessions/Google/callback";
    },
    async session() {
			try {
				axios.defaults.withCredentials = true
				const response = await axios.get("http://localhost:8080/sessions/me", {withCredentials: true});
				this.profile = response.data;
				console.log(this.profile)
			} catch (e) {
				alert('Error')
			}
		},
		async "trycont"() {
			try {
				axios.defaults.withCredentials = true
				const response = await axios.get("http://localhost:8080/something", {withCredentials: true});
				console.log(response.data)
			} catch (e) {
				alert('Error')
			}
		},
  }
}
</script>