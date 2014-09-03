<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:if test="${empty user }">
	<c:redirect url="/"></c:redirect>
</c:if>
<c:if test="${not empty battle}">
	<c:redirect url="bit-ninja/battle/"></c:redirect>
</c:if>
<c:set var="refreshTimer" value="${(ninja.level * 20) + 280 }" scope="request" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Bit Ninja</title>
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/menu.css">
	<link rel="icon" type="image/png" href="imgs/favicon2.png">
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/character-creation.js"></script>
	<script type="text/javascript" src="js/changeText.js"></script>
	<script type="text/javascript" src="js/popWindow.js"></script>
	<script type="text/javascript" src="js/lib.js"></script>
	<script type="text/javascript">
	function updateVillageDescription(){
		var village = document.getElementById("village");
		var description = document.getElementById("village-description");
		switch(village.value){
		<c:forEach items="${villages}" var="village">
		case "${village.id}":
			description.innerHTML = "<img alt='' src='imgs/icons/"
				+ "${village.id}" 
				+ ".png' style='float: left; margin-right: 5px;' width='100px' height='100px'> "
			+"${village.description}";
			break;
		</c:forEach>
		}
		updateBackground();
	}

	function updateBackground(){
		var background = document.getElementById("background");
		var description = document.getElementById("background-description");
		switch(background.value){
		<c:forEach items="${backgrounds}" var="background">
			case "${background.id}":description.innerHTML = "${background.description}";
			break;
		</c:forEach>}
	}
	
	window.onload = function(){
		<c:if test="${not empty lastMissionRefresh and not empty refreshTimer}">
		<c:if test="${param.page eq 'headquarters' and empty actionRemainingTime}">
		setTimer(${refreshTimer - lastMissionRefresh});
		</c:if>
		</c:if>
		
		<c:if test="${not empty actionRemainingTime and actionRemainingTime gt 0}">
		setTimer(${actionRemainingTime});
		</c:if>
	}
	
	function done(){
		var missionDiv = document.getElementById("mission-finish-display");
		var refreshDiv = document.getElementById("refresh-mission-button");
		if(refreshDiv != null){
			refreshDiv.innerHTML = '<a class="btn btn-blue" href="Control?action=refreshMissions"> Refresh Missions </a>';
		}else if(missionDiv != null){
			missionDiv.innerHTML = '<a class="btn btn-blue" href="Control?action=finish"> Finish Mission </a>';
		}
	}
	
	</script>
</head>
<body>
	<div class="navigation">
		<div class="logo-img" style="height: 100px;margin-bottom:16px;">
			<img>
		</div>
		<ul class="menu">
			<li class="menu-heading">Character</li>
			<c:if test="${not empty ninja }">
			<li><a class="character-name" href="game?page=ninja">${ninja.name }</a><li>
			</c:if>
			<c:if test="${user.canCreateNinja() }">
			<li><a href="game?page=create">Create Character</a></li>
			</c:if>
			<li><a href="game?page=select">Select Character</a></li>
			<li class="menu-heading">Menu</li>
			<c:choose>
			<c:when test="${not empty action.mission }">
			<li><a href="game">Current Mission</a></li>
			</c:when>
			<c:when test="${not empty ninja }">
			<li><a href="game?page=index">${ninja.location.name }</a></li>
			</c:when>
			<c:otherwise>
			<li><a href="game?page=index">Homepage</a></li>
			</c:otherwise>
			</c:choose>
			<li><a href="#">Options</a></li>
			<li><a href="Control?action=logout">Logout (${user.name })</a></li>
		</ul>
	</div>
	
	<input type="checkbox" id="nav-trigger" class="nav-trigger" />
	<label for="nav-trigger">&#9776;</label>
	
	<div class="wrapper">
	<div class="header">
		<img alt="" src="imgs/header.png">
	</div>
	<c:if test="${not empty msgType }">
		<div class="message ${msgType }">
			${msgMessage }
		</div>
	</c:if>
	<div class="container">
		<c:choose>
			
			<c:when test="${param.page eq 'create' }">
			<h1>Character Creation</h1>
			<form action="/bit-ninja/Control?action=add&object=ninja" method="post">
			<div class="section">
				<div class="title">Form</div>
				<div class="content">
					<p><b>Character Name:</b></p>
					<input class="input-form" name="bit-ninja-name" type="text" maxlength="45" size="45">
					<p><b>Starting Village:</b></p>
					<select name="bit-ninja-village" onchange="updateVillageDescription();" id="village">
						<c:forEach items="${villages }" var="village">
						<option value="${village.id }">${village.name }</option>
						</c:forEach>
					</select>
					<div id="village-description" class="description" >
						<img alt="" src="imgs/icons/1.png" style="float: left; margin-right: 5px;" width="100px" height="100px">
						${villages[0].description }
					</div>
					<p>Starting Quality:
						<select id="quality" onchange="updateAttributes();">
							<option value="Calm">Calm</option>
							<option value="Centered">Centered</option>
							<option value="Fast">Fast</option>
							<option value="Nervous">Nervous</option>
							<option value="Resilient">Resilient</option>
							<option value="Smart">Smart</option>
							<option value="Strong">Strong</option>
							<option value="Wise">Wise</option>
						</select>
					</p>
					<p>Attributes:</p>
					<table class="display-table display-only" >
						<tr>
							<th>Strength</th>
							<td><input id="Strength" type="text" name="bit-ninja-strength" readonly="readonly" value="0"></td>
						</tr>
						<tr>
							<th>Speed</th>
							<td><input id="Speed" type="text" name="bit-ninja-speed" readonly="readonly" value="0"></td>
						</tr>
						<tr>
							<th>Resistance</th>
							<td><input id="Resistance" type="text" name="bit-ninja-resistance" readonly="readonly" value="0"></td>
						</tr>
						<tr>
							<th>Endurance</th>
							<td><input id="Endurance" type="text" name="bit-ninja-endurance" readonly="readonly" value="2"></td>
						</tr>
						<tr>
							<th>Intelligence</th>
							<td><input id="Intelligence" type="text" name="bit-ninja-intelligence" readonly="readonly" value="2"></td>
						</tr>
						<tr>
							<th>Wisdom</th>
							<td><input id="Wisdom" type="text" name="bit-ninja-wisdom" readonly="readonly" value="2"></td>
						</tr>
					</table>
					<p><input class="btn btn-red" type="submit" value="Start your journey!"></p>
				</div>
			</div>
			
			
			
			
			</form>
			</c:when>
			
			<c:when test="${param.page eq 'select' }">
				<h1>Select Your Ninja</h1>
				<c:if test="${empty user.ninjas }">
					You don't have any ninjas yet. <a href="game?page=create">Create one here!</a>
				</c:if>
				<c:if test="${not empty user.ninjas  }">
					<c:forEach items="${user.ninjas }" var="ninja">
					<a class="select" href="/bit-ninja/Control?action=selectNinja&ninja=${ninja.id }">
						${ninja.name } - level ${ninja.level } - ${ninja.location.name }
					</a>
					</c:forEach>
				</c:if>
			</c:when>
			
			<c:when test="${not empty ninja }">
				<c:choose>					
					<c:when test="${param.page  eq 'inventory'}">
						<h1>Inventory</h1>
						This is where you conrol you character's items.
					</c:when>
					
					<c:when test="${param.page  eq 'ninja'}">
						<h1>${ninja.name }</h1>
						<div class="section">
							<div class="title">Summary</div>
							<div class="content">
								<table class="display-table">
									<tr>
										<th>Name</th>
										<td>${ninja.name }</td>
									</tr>
									<tr>
										<th>Graduation</th>
										<td>${ninja.graduation}</td>
									</tr>
									<tr>
										<th>Level</th>
										<td>${ninja.level}</td>
									</tr>
									<tr>
										<th>Progress</th>
										<td>
											<progress id="expbar" max="${ninja.expNeeded }" value="${ninja.expCurrent }"></progress>
											<div class="exp">${ninja.expCurrent } / ${ninja.expNeeded }</div>
											<c:if test="${ninja.expCurrent gt ninja.expNeeded or
											ninja.expCurrent eq ninja.expNeeded}">
											<br>
											<a class="btn btn-green" href="Control?action=levelUp"> Level Up!</a>
											</c:if>
										</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="section">
							<div class="title">Attributes</div>
							<div class="content">
								<table class="display-table">
									<tr>
										<th>Strength</th>
										<td>${ninja.attributes.strength }</td>
									</tr>
									<tr>
										<th>Speed</th>
										<td>${ninja.attributes.speed }</td>
									</tr>
									<tr>
										<th>Resistance</th>
										<td>${ninja.attributes.resistance }</td>
									</tr>
									<tr>
										<th>Endurance</th>
										<td>${ninja.attributes.endurance }</td>
									</tr>
									<tr>
										<th>Intelligence</th>
										<td>${ninja.attributes.intelligence }</td>
									</tr>
									<tr>
										<th>Wisdom</th>
										<td>${ninja.attributes.wisdom }</td>
									</tr>
								</table>
							</div>
						</div>
						<div class="section">
							<div class="title">Affinities</div>
							<div class="content">
								<table class="display-table">
									<tr>
										<th>Physical</th>
										<td>???</td>
										<th>Sword</th>
										<td>???</td>
									</tr>
									<tr>
										<th>Fire</th>
										<td>???</td>
										<th>Water</th>
										<td>???</td>
									</tr>
									<tr>
										<th>Thunder</th>
										<td>???</td>
										<th>Earth</th>
										<td>???</td>
									</tr>
									<tr>
										<th>Wind</th>
										<td>???</td>
										<th>Nature</th>
										<td>???</td>
									</tr>
									<tr>
										<th>Illusion</th>
										<td>???</td>
										<th>Medical</th>
										<td>???</td>
									</tr>
								</table>
							</div>
						</div>
					</c:when>
					
					<c:when test="${not empty action.mission }">
						<h1>Current Mission</h1>
						<p>
							Currently, ${ninja.name } is on the mission: <b>${action.mission.name }</b>
						</p>
						<div id="mission-finish-display">		
						<c:if test="${actionRemainingTime lt 0 or actionRemainingTime eq 0}">
							<a class="btn btn-blue" href="Control?action=finish"> Finish Mission </a>
						</c:if>
						<c:if test="${actionRemainingTime gt 0}">
						It will be finished in: <b><span id="timer-display"></span></b>
						</c:if>
						</div>
					</c:when>
					
					<c:when test="${not empty action.training }">
						<h1>Current Mission</h1>
						<p>
							Currently, ${ninja.name } is on the mission: <b>${action.mission.name }</b>
						</p>
						<div id="mission-finish-display">		
						<c:if test="${actionRemainingTime lt 0 or actionRemainingTime eq 0}">
							<a class="btn btn-blue" href="Control?action=finish"> Finish Mission </a>
						</c:if>
						<c:if test="${actionRemainingTime gt 0}">
						It will be finished in: <b><span id="timer-display"></span></b>
						</c:if>
						</div>
					</c:when>
					
					<c:when test="${param.page  eq 'academy'}">
						<h1>Ninja Academy</h1>
						<div class="section full">
						<c:choose>
						<c:when test="${ninja.academyLevel eq 0 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #1 : Introduction</div>
								<p>
								Hello ${ninja.name }! Welcome to the ${ninja.location.name } Ninja Academy.
								First of all I will be your instructor during your period as a student.
								I will teach you the history of the Bit World, how to fight, primary ninja
								techiniques amongst other stuff. So, let's get started! Click the button 
								below to complete the first lesson.
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 1 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #2 : The Bit World</div>
								<p>
								Okay, now that you're ready let's go on to the history of the Bit World.
								Everything is made of bits. That's right, me, you, fire, water. Everything. 
								I think you got it now. Everything we know started because of the Great
								Spiritual Beasts. They created everything we know, but they have left this
								plane after we tried to dominated them. Yes, we were a-holes back then.
								So, when you're in a battle the red bits represents your life and the blue
								bits represents your energy, allright?
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 2 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #3 : The Ninja Wars I</div>
								<p>
								... and everything is made o bits. Shall I go on? Allright.
								After the Great Spiritual Beasts created us, they also thought us the 
								way of ninja. Oh yeah! They thought us how to use our blue bits (let's call 
								them energy from now on, shall we?) to create seas of fire, or bend the 
								earth beneath us. Of course, they tried to use that power to bend the 
								Spiritual Beasts to their control. They failed and the beasts left us.
								It caused a great discord between the ancient folk, starting a great war.
								Are you listening to me?
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 3 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #4 : The Ninja Wars II</div>
								<p>
								The great ninja war started when the Great Spiritual Beasts left our world.
								To where, nobody knows. Lots of clans had different ideas of what should
								happen next, you know? Who should be the ruler, who's the strongest ninja
								clan. So, obviously they start killing each other with us, ninjas. Well,
								we're more than assassination tools, aren't we? Well, it's not philosophy
								class anyway. Let's go on to the rise of the Shogun.
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 4 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #5 : The Ninja Wars III</div>
								<p>
								After a lot of killing, the Kanmuri clan, with the help of several other
								from the Hayashi village defeated the other major clans and their leader 
								declared himself Shogun of Bit World. He gave the title of Daymio to the
								leader of the other major clans. So the chain of command goes commoner,
								ninja, elite ninja, Daymio and Shogun. For now.
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 5 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #6 : Combat I</div>
								<p>
								So, let's to go the point, combat. Yeah, I know that's what you're here for
								anyway. First of all, attributes. Everyone is different, as a person you
								know. We all have different qualities. Some are strong, other are intelligent
								and so on. Every attribute you have influences the outcome of your actions
								in combat. Speaking of in combat actions...
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 6 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #7 : Combat II</div>
								<p>
								If you found yourself in a combat right now, you shouldn't, but that's not
								the point, you could only use a basic attack. Yes, you can attack! Didn't
								you know? Well, you probably want to learn more attacks and techniques
								right? Trainers can teach you those. After these lessons I'm gonna be your
								first trainer, and teach you the basic ninja techniques. After you become
								a ninja, you can choose other trainers to teach you all kinds of skills!
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 7 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #8 : Combat III</div>
								<p>
								So, in combat, watch out for your red bits level, should I call it health
								level? Yes, but I don't want to. And for your energy level as well. You
								already know why attributes matter and that you can use different techniques
								during a fight. Oh, you can also have weapons equipped and use them! Yes,
								slash them with katanas, or use explosive paper tags. Well, once you can use
								them, we don't want any accidents, do we? For now, stick to the kunai and
								shurikens young one.
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 8 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #9 : The Life of a Ninja I</div>
								<p>
								When you graduate, you'll be able to do ninja missions! Oh yeah, clean
								some houses, help people out. Heh, not what you become a ninja for? Well,
								all kinds of people make all kinds of requests for the Daymio, and as long
								as there is a payment, he will let a ninja do the contract, the mission.
								We need young and inexperienced ninjas like yourself to do these, and as
								you get stronger, you may be able to go into more and more fighty missions.
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 8 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Lesson #10 : The Life of a Ninja II</div>
								<p>
								Aren't you satisfied with missions? Allright, when you get strong enough,
								you're gonna be able to leave our village and explore the whole Bit World.
								That's right, there are no boundaries, you'll be able to explore every
								explorable place out there and probably get killed if you don't know where
								you're going. Because you don't know what kinds of threat the ninja have
								to face outside the village.
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyMission" class="btn btn-green"> Do Lesson! </a>
							</div>
						</div>
						</c:when>
						<c:when test="${ninja.academyLevel eq 9 }">
						<div class="title">Academy Class</div>
						<div class="dialogue">
							<div class="dialogue-window">
								<div class="dialogue-header">Ninja Training</div>
								<p>
								Well, I have said everything I wanted to say. Now it's time for some
								ninja work. I will train you now on the basic ninja techniques. After
								that you'll be able to take the ninja test for your graduation! What
								are you waiting for?
								</p>
							</div>
							<div style="text-align: center;">
								<a href="Control?action=doAcademyTrainer" class="btn btn-green"> Start Training! </a>
							</div>
						</div>
						</c:when>
						</c:choose>
						</div>
						<a class="btn btn-red" href="game?page=index"> &#8678; Back to the Village</a>
					</c:when>
					
					<c:when test="${param.page  eq 'clans'}">
						<h1>${ninja.location.name } Clans</h1>
						<p>This is where you can join a village clan.</p>
						<a class="btn btn-red" href="game?page=index"> &#8678; Back to the Village</a>
					</c:when>
					
					<c:when test="${param.page  eq 'hospital'}">
						<h1>Hospital</h1>
						<p>This is where you recover.</p>
						<a class="btn btn-red" href="game?page=index"> &#8678; Back to the Village</a>
					</c:when>
					
					<c:when test="${param.page  eq 'shop'}">
						<h1>Ninja Shop</h1>
						<p>This is where you buy stuff.</p>
						<a class="btn btn-red" href="game?page=index"> &#8678; Back to the Village</a>
					</c:when>
					
					<c:when test="${param.page  eq 'headquarters'}">
						<h1>Headquarters</h1>
						<p>This is where you do missions.</p>
						<div id="refresh-mission-button">
							<c:if test="${empty lastMissionRefresh }">
								<a class="btn btn-blue" href="Control?action=refreshMissions"> Refresh Missions </a>
							</c:if>
							<c:if test="${not empty lastMissionRefresh}">
								<c:if test="${lastMissionRefresh lt refreshTimer or lastMissionRefresh eq refreshTimer}">
									You will be able to get new missions in: 
									<b><span id="timer-display"></span></b>
								</c:if>
								<c:if test="${lastMissionRefresh gt refreshTimer}">
									<a class="btn btn-blue" href="Control?action=refreshMissions"> Refresh Missions </a>
								</c:if>
							</c:if>
						</div>
						<p>Available Missions:</p>
						<div class="mission-container">
						<c:forEach items="${availableMissions }" var="mission" >
							<a class="rank${mission.rankClass }" href="Control?action=doMission&object=${mission.id}">
								<b>${mission.name } : ${mission.rank }</b><br>
								${mission.description }
							</a>
						</c:forEach>
						<c:if test="${empty availableMissions }">
							You currently have no available missions. You can get new ones by pressing the refresh button
							after waiting a while.
						</c:if>
						</div>
						<a class="btn btn-red" href="game?page=index"> &#8678; Back to the Village</a>
					</c:when>
					
					<c:when test="${param.page  eq 'main'}">
						<h1>The Main Buildling</h1>
						<p>This is the main building of the village.</p>
						<a class="btn btn-red" href="game?page=index"> &#8678; Back to the Village</a>
					</c:when>
					
					<c:otherwise>
						<c:choose>
							<c:when test="${ninja.location.name eq 'Hayashi Village' }">
							<jsp:include page="pages/hayashi.jsp"></jsp:include>
							</c:when>
							<c:when test="${ninja.location.name eq 'Kasumi Village' }">
							<jsp:include page="pages/kasumi.jsp"></jsp:include>
							</c:when>
							<c:when test="${ninja.location.name eq 'Nokoribi Village' }">
							<jsp:include page="pages/nokoribi.jsp"></jsp:include>
							</c:when>
							<c:when test="${ninja.location.name eq 'Noukon Village' }">
							<jsp:include page="pages/noukon.jsp"></jsp:include>
							</c:when>
							<c:when test="${ninja.location.name eq 'Chouwa Village' }">
							<jsp:include page="pages/chouwa.jsp"></jsp:include>
							</c:when>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</c:when>
			
			<c:otherwise>
			<h1>Welcome, ${user.name }</h1>
			You are ready to select a character and start playing!
			</c:otherwise>
			
			</c:choose>
			</div>
	</div>
</body>
</html>