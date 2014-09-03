<div id="chooser-content" class="chooser-content">
				<div id="content-I">
				<p>Name:</p>
				<input name="bit-ninja-name" type="text" maxlength="45" size="45"><br>
				</div>
				
				
				<div  id="content-II" style="display: none;">
				<p>Starting Village:
				<select name="bit-ninja-village" onchange="updateVillageDescription();" id="village">
					<c:forEach items="${villages }" var="village">
					<option value="${village.id }">${village.name }</option>
					</c:forEach>
				</select>
				</p>
				<div id="village-description" class="description" style="width: 589px; height: 100px;">
					<img alt="" src="../imgs/icons/1.png" style="float: left; margin-right: 5px;" width="100px" height="100px">
					${villages[0].description }
				</div>
				<br>
				<p>Background:
				<select name="bit-ninja-background" onchange="updateBackground();" id="background">
					<c:forEach items="${backgrounds }" var="background">
					<option value="${background.id }">${background.name }</option>
					</c:forEach>
				</select>
				</p>
				<div id="background-description" class="description" style="width: 589px; height: 60px;">
					${backgrounds[0].description }
				</div>
				</div>
				
				
				<div  id="content-III" style="display: none;">
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
				<hr>
				<p>Attributes:</p>
				<table class="attributes display-only" border="1">
					<tr>
						<th>Strength</th>
						<th>Speed</th>
						<th>Resistance</th>
						<th>Endurance</th>
						<th>Intelligence</th>
						<th>Wisdom</th>
					</tr>
					<tr>
						<td><input id="Strength" type="text" name="bit-ninja-strength" readonly="readonly" value="0"></td>
						<td><input id="Speed" type="text" name="bit-ninja-speed" readonly="readonly" value="0"></td>
						<td><input id="Resistance" type="text" name="bit-ninja-resistance" readonly="readonly" value="0"></td>
						<td><input id="Endurance" type="text" name="bit-ninja-endurance" readonly="readonly" value="2"></td>
						<td><input id="Intelligence" type="text" name="bit-ninja-intelligence" readonly="readonly" value="2"></td>
						<td><input id="Wisdom" type="text" name="bit-ninja-wisdom" readonly="readonly" value="2"></td>
					</tr>
				</table>
				</div>
				
				
				<div id="content-IV" style="display: none;">
				<p>Play in Hardcore mode?</p>
				<input type="radio" name="bit-ninja-hardcore" value="false" checked="checked"> No<br>
				<input type="radio" name="bit-ninja-hardcore" value="true"> Yes<br>
				<div class="description" style="width: 589px; height: 45px;">
					In hardcore mode, when your ninja loses a battle, it has a chance to be permanently killed
					based on the amount of damage it took. However, only hardcore characters can go to the 
					Legendary Levels (above level 100).
				</div>
				</div>
				<br>
			</div>