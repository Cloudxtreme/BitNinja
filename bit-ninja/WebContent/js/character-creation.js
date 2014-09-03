function updateAttributes(){
		var quality = document.getElementById("quality");
		var str = document.getElementById("Strength");
		var spd = document.getElementById("Speed");
		var res = document.getElementById("Resistance");
		var end = document.getElementById("Endurance");
		var inl = document.getElementById("Intelligence");
		var wis = document.getElementById("Wisdom");

		switch(quality.value){
		case "Calm":
			str.value = 0;
			spd.value = 0;
			res.value = 0;
			end.value = 2;
			inl.value = 2;
			wis.value = 2;
			break;
		case "Centered":
			str.value = 1;
			spd.value = 1;
			res.value = 1;
			end.value = 1;
			inl.value = 1;
			wis.value = 1;
			break;
		case "Fast":
			str.value = 2;
			spd.value = 4;
			res.value = 0;
			end.value = 0;
			inl.value = 0;
			wis.value = 0;
			break;
		case "Nervous":
			str.value = 2;
			spd.value = 2;
			res.value = 2;
			end.value = 0;
			inl.value = 0;
			wis.value = 0;
			break;
		case "Resilient":
			str.value = 0;
			spd.value = 0;
			res.value = 4;
			end.value = 2;
			inl.value = 0;
			wis.value = 0;
			break;
		case "Smart":
			str.value = 0;
			spd.value = 0;
			res.value = 0;
			end.value = 0;
			inl.value = 4;
			wis.value = 2;
			break;
		case "Strong":
			str.value = 4;
			spd.value = 0;
			res.value = 1;
			end.value = 1;
			inl.value = 0;
			wis.value = 0;
			break;
		case "Wise":
			str.value = 0;
			spd.value = 0;
			res.value = 0;
			end.value = 2;
			inl.value = 0;
			wis.value = 4;
			break;
		}
	}
function changeStep(step){
	var steps = new Array();
	steps[steps.length] = document.getElementById("step-I");
	steps[steps.length] = document.getElementById("step-II");
	steps[steps.length] = document.getElementById("step-III");
	steps[steps.length] = document.getElementById("step-IV");
	var contents = new Array();
	contents[contents.length] = document.getElementById("content-I");
	contents[contents.length] = document.getElementById("content-II");
	contents[contents.length] = document.getElementById("content-III");
	contents[contents.length] = document.getElementById("content-IV");
	var content = document.getElementById("chooser-content");

	steps[step].className = "step step-active";
	for(var x in steps){
		steps[x].style.width = "60px";
		contents[x].style.display = "none";
		}
	steps[0].innerHTML = "I";
	steps[1].innerHTML = "II";
	steps[2].innerHTML = "III";
	steps[3].innerHTML = "IV";		
	switch(step){
	case 0:
		steps[0].innerHTML = "I : Name";
		steps[0].style.width = "80px";
		contents[0].style.display = "block";
		break;
	case 1:
		steps[1].innerHTML = "II : Village & Background";
		steps[1].style.width = "200px";
		contents[1].style.display = "block";
		break;
	case 2:
		steps[2].innerHTML = "III : Attributes";
		steps[2].style.width = "110px";
		contents[2].style.display = "block";
		break;
	case 3:
		steps[3].innerHTML = "IV : Game Mode";
		steps[3].style.width = "125px";
		contents[3].style.display = "block";
		break;
	}
	for(var x in steps){ if(x != step) steps[x].className = "step"; }
}

	