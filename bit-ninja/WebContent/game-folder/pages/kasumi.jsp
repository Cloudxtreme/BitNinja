<script type="text/javascript">
function changeVillageText(text){
	var div = document.getElementById("text");
	div.innerHTML = text;
}
</script>

<h1>${ninja.location.name }</h1>
<div class="map" style="height: 405px;">
	<img style="position: absolute;" alt="" src="imgs/maps/kasumi.png">
	<a href="Control?action=goToAcademy" onmouseover="changeVillageText('Ninja Academy');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:170px;left:270px;" src="imgs/map-icons/academy.png">
	</a>
	<a href="game?page=shop" onmouseover="changeVillageText('Ninja Shop');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:200px;left:55px;" src="imgs/map-icons/smith.png">
	</a>
	<a href="game?page=hospital" onmouseover="changeVillageText('Hospital');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:230px;left:70px;" src="imgs/map-icons/hospital.png">
	</a>
	<a href="game?page=clans" onmouseover="changeVillageText('Village Clans');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:130px;right:100px;" src="imgs/map-icons/clans.png">
	</a>
	<a href="game?page=headquarters" onmouseover="changeVillageText('Headquarters');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:140px;left:100px;" src="imgs/map-icons/headquarters.png">
	</a>
	<a href="game?page=main" onmouseover="changeVillageText('Temple of Heavenly Feathers');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:85px;right:50px;" src="imgs/map-icons/kasumi.png">
	</a>
	<a href="#" onmouseover="changeVillageText('Leave Village');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:175px;left:60px;" src="imgs/map-icons/gates.png">
	</a>
</div>
<div id="text" class="village-link-name">

</div>