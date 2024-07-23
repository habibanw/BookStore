<script setup lang="ts"></script>

<style scoped>
footer {
  background-color: #023e8a;
  display: flex;
  gap: 1em;
  color: white;
  justify-content: space-between;
  margin-top: 10px;
}

.links,
.social-media-icons {
  flex-direction: column;
  display: inline-block;
  font-family: "Poppins", sans-serif;
  font-size: 20px;
}

a.links {
  color: white;
  padding-right: 15px;
  text-decoration: none;
}


.contacts {
  padding-right: 5px;
  padding-left: 5px;
  text-decoration: none;
}

.social-media-icons {
  gap: 0.25em;
  color: white;
}

.fa-facebook {
  color: white;
  font-size: 30px;
  width: 20px;
  margin: 5px 2px;
  text-decoration: none;
  border-radius: 50%;
}

.fa-twitter {
  color: white;
  font-size: 30px;
  width: 30px;
  text-decoration: none;
  margin: 5px 2px;
  border-radius: 40%;
}

.fa-instagram {
  color: white;
  font-size: 30px;
  width: 20px;
  text-decoration: none;
  margin: 5px 2px;
  border-radius: 50%;
}

</style>

<template>
  <footer class="container">
    <strong>The Reading Room 2023 &reg;</strong>

    <section class="links">
      <a class="contacts" href="index.html">About</a>
      <a class="contacts" href="index.html">Contact</a>
      <a class="contacts" href="index.html">Directions</a>
    </section>

    <section class="social-media-icons">
      <a href="#" class="fa fa-twitter"></a>
      <a href="#" class="fa fa-facebook"></a>
      <a href="#" class="fa fa-instagram"></a>
    </section>

  </footer>



</template>
//(-1) cart count not aligned with cart icon
// Consider removing text decorations (underline) from links on the welcome Page and on the footer
//on the category page. Consider fixing the large space between the footer and book images on the category page.
//