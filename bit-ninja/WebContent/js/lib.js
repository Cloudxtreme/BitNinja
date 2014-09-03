window.onload = function(){
	/*var x = 0;
    setInterval(function(){
        x+=1;
        if(x != 450) //background image width
        	$('body').css('background-position', x + 'px 0');
        else
        	x = 0;
    }, 50);
    */
    
	if($(window).width() < 500){
		$('#nav-trigger').prop('checked',true)
	}
}

function setTimer(seconds){
	var initTitle = document.title;
	var sec = seconds;
	var min = 0;
	var hrs = 0;
	if(sec > 59){
		min = parseInt(sec / 60);
		sec = sec % 60;
	}
	if(min > 60){
		hrs = parseInt(min / 60);
		min = min % 60;
	}
	var timerDisplay = document.getElementById('timer-display');
	if(timerDisplay != null){
		var timeString = "";
		var timer = setInterval(function(){
			sec--;
			if(sec < 0){
				if(min <= 0){
					if(hrs <= 0){
						timerDisplay.innerHTML = "";
						document.title = initTitle;
						done();
						clearInterval(timer);
						exit;
					}else{
						hrs--;
						min = 59;
						sec = 59;
					}
				}else{
					min--;
					sec = 59;
				}
			}
			timeString = "[";
			timeString += (hrs > 9) ? hrs : ("0" + hrs);
			timeString += ":";
			timeString += (min > 9) ? min : ("0" + min);
			timeString += ":";
			timeString += (sec > 9) ? sec : ("0" + sec);
			timeString += "]";
			timerDisplay.innerHTML = timeString;
			document.title = timeString + " " + initTitle;
		},1000);
	}
}