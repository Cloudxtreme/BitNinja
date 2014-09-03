function changeText(text,targetId,milisec){
	var target = document.getElementById(targetId);
	var output = text.split("");
	var i = 0;
	function show(){
		if(i<output.length){
			target.innerHTML = target.innerHTML + output[i];
			i=i+1;
		}
	}
	var timer = setInterval(show,milisec);
}