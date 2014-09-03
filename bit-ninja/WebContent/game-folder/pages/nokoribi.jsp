<script type="text/javascript">
function changeVillageText(text){
	var div = document.getElementById("text");
	div.innerHTML = text;
}
</script>

<h1>${ninja.location.name }</h1>
<div class="map" style="height: 405px;">
	<img style="position: absolute;" alt="" src="imgs/maps/nokoribi.png">
	<a href="Control?action=goToAcademy" onmouseover="changeVillageText('Ninja Academy');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:220px;left:330px;" src="imgs/map-icons/academy.png">
	</a>
	<a href="game?page=shop" onmouseover="changeVillageText('Ninja Shop');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:160px;left:175px;" src="imgs/map-icons/smith.png">
	</a>
	<a href="game?page=hospital" onmouseover="changeVillageText('Hospital');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:200px;right:40px;" src="imgs/map-icons/hospital.png">
	</a>
	<a href="game?page=clans" onmouseover="changeVillageText('Village Clans');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:180px;left:220px;" src="imgs/map-icons/clans.png">
	</a>
	<a href="game?page=headquarters" onmouseover="changeVillageText('Headquarters');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:255px;right:100px;" src="imgs/map-icons/headquarters.png">
	</a>
	<a href="game?page=main" onmouseover="changeVillageText('Temple of Ashes');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:90px;right:17px;" src="imgs/map-icons/nokoribi.png">
	</a>
	<a href="#" onmouseover="changeVillageText('Leave Village');" onmouseout="changeVillageText('');">
		<img class="village-link" style="top:265px;right:400px;" src="imgs/map-icons/gates.png">
	</a>
</div>
<div id="text" class="village-link-name">

</div>