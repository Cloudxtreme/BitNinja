<script type="text/javascript">
function changeVillageText(text){
	var div = document.getElementById("text");
	div.innerHTML = text;
}
</script>

<h1>${ninja.location.name }</h1>
<div class="map" style="height: 405px;">
	<img style="position: absolute;" alt="" src="imgs/maps/noukon.png">
	<a href="game?page=academy" onmouseover="changeVillageText('Ninja Academy');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:100px;left:370px;" src="imgs/map-icons/academy.png">
	</a>
	<a href="game?page=shop" onmouseover="changeVillageText('Ninja Shop');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:115px;left:215px;" src="imgs/map-icons/smith.png">
	</a>
	<a href="game?page=hospital" onmouseover="changeVillageText('Hospital');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:120px;left:326px;" src="imgs/map-icons/hospital.png">
	</a>
	<a href="game?page=clans" onmouseover="changeVillageText('Village Clans');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:110px;right:195px;" src="imgs/map-icons/clans.png">
	</a>
	<a href="game?page=headquarters" onmouseover="changeVillageText('Headquarters');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:90px;right:190px;" src="imgs/map-icons/headquarters.png">
	</a>
	<a href="game?page=main" onmouseover="changeVillageText('Tower of Mirages');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:12px;right:70px;" src="imgs/map-icons/noukon.png">
	</a>
	<a href="#" onmouseover="changeVillageText('Leave Village');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:247px;left:36px;" src="imgs/map-icons/docks.png">
	</a>
</div>
<div id="text" class="village-link-name">

</div>