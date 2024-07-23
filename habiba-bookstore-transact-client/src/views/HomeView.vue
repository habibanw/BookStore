<script setup lang="ts">
import TheHomeCategoryList from "@/components/TheHomeCategoryList.vue";
</script>

<style scoped>
.home-page {
  display: flex;
}
.container {
  margin: 0 auto;

}

.exploreButton {
  display: flex;
  justify-content: center;
  margin-top: -10px;
  margin-bottom: 20px;
}

.cta {
  display: flex;
  padding: 10px 40px;
  text-decoration: none;
  font-family: "Poppins", sans-serif;
  font-size: 40px;
  color: white;
  background: #3C8802;
  transition: 1s;
  box-shadow: 6px 6px 0 black;
  transform: skewX(-15deg);
}

.cta:focus {
  outline: none;
}

.cta:hover {
  transition: 0.5s;
  box-shadow: 10px 10px 0 #023e8a;
}

.cta span:nth-child(2) {
  transition: 0.5s;
  margin-right: 0px;
}

.cta:hover span:nth-child(2) {
  transition: 0.5s;
  margin-right: 45px;
}

span {
  transform: skewX(15deg);
}

span:nth-child(2) {
  width: 20px;
  margin-left: 30px;
  position: relative;
  top: 12%;
}

/**************SVG****************/

path.one {
  transition: 0.4s;
  transform: translateX(-60%);
}

.cta:hover path.one {
  transform: translateX(0%);
  animation: color_anim 1s infinite 0.6s;
}



/* SVG animations */

@keyframes color_anim {
  0% {
    fill: white;
  }
  50% {
    fill: #023e8a;
  }
  100% {
    fill: white;
  }
}


</style>
<template>

  <div class="hero">
    <img src="/category-images/libraryv1.jpeg" alt="Hero Image" />
  </div>


  <div class="welcomeText">
    <h2>Welcome to The Reading Room</h2>
    <p>A Sanctuary for Book Lovers</p>
  </div>

  <div class="home-page">

    <section class="category-images container">
      <the-home-category-list></the-home-category-list>
    </section>
  </div>

  <div class="exploreButton">
    <a class="cta" href="/HabibBookstoreTransact/category/Comedy">
      <span>Explore Bookstore</span>
      <span>
            <svg width="66px" height="43px" viewBox="0 0 66 43" version="1.1" xmlns="http://www.w3.org/2000/svg">
              <g id="arrow" stroke="none" stroke-width="1" fill="none" fill-rule="evenodd">
                <path class="one" d="M40.1543933,3.89485454 L43.9763149,0.139296592 C44.1708311,-0.0518420739 44.4826329,-0.0518571125 44.6771675,0.139262789 L65.6916134,20.7848311 C66.0855801,21.1718824 66.0911863,21.8050225 65.704135,22.1989893 C65.7000188,22.2031791 65.6958657,22.2073326 65.6916762,22.2114492 L44.677098,42.8607841 C44.4825957,43.0519059 44.1708242,43.0519358 43.9762853,42.8608513 L40.1545186,39.1069479 C39.9575152,38.9134427 39.9546793,38.5968729 40.1481845,38.3998695 C40.1502893,38.3977268 40.1524132,38.395603 40.1545562,38.3934985 L56.9937789,21.8567812 C57.1908028,21.6632968 57.193672,21.3467273 57.0001876,21.1497035 C56.9980647,21.1475418 56.9959223,21.1453995 56.9937605,21.1432767 L40.1545208,4.60825197 C39.9574869,4.41477773 39.9546013,4.09820839 40.1480756,3.90117456 C40.1501626,3.89904911 40.1522686,3.89694235 40.1543933,3.89485454 Z" fill="#FFFFFF"></path>
              </g>
            </svg>
          </span>
    </a>

  </div>


</template>
