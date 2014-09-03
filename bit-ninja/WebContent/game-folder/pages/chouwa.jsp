<script type="text/javascript">
function changeVillageText(text){
	var div = document.getElementById("text");
	div.innerHTML = text;
}
</script>

<h1>${ninja.location.name }</h1>
<div class="map" style="height: 405px;">
	<img style="position: absolute;" alt="" src="imgs/maps/chouwa.png">
	<a href="game?page=academy" onmouseover="changeVillageText('Ninja Academy');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:160px;left:260px;" src="imgs/map-icons/academy.png">
	</a>
	<a href="game?page=shop" onmouseover="changeVillageText('Ninja Shop');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:180px;left:65px;" src="imgs/map-icons/smith.png">
	</a>
	<a href="game?page=hospital" onmouseover="changeVillageText('Hospital');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:225px;left:55px;" src="imgs/map-icons/hospital.png">
	</a>
	<a href="game?page=clans" onmouseover="changeVillageText('Village Clans');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:120px;right:75px;" src="imgs/map-icons/clans.png">
	</a>
	<a href="game?page=headquarters" onmouseover="changeVillageText('Headquarters');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:160px;left:100px;" src="imgs/map-icons/headquarters.png">
	</a>
	<a href="game?page=main" onmouseover="changeVillageText('Temple of the Order');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:50px;right:120px;" src="imgs/map-icons/chouwa.png">
	</a>
	<a href="#" onmouseover="changeVillageText('Leave Village');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:265px;right:131px;" src="imgs/map-icons/gates.png">
	</a>
</div>
<div id="text" class="village-link-name">

</div>