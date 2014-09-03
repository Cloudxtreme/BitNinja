	function popWindow(popElement){
		//document.getElementById("blanket-" + popElement).style.display = "block";
		$("#blanket-" + popElement).fadeToggle(400);
		$("#pop-window-" + popElement).slideToggle(400);
	}