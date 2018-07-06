<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script>
<script>

	google.load('visualization', '1', {
		'packages' : [ 'corechart' ]
	});

	google.setOnLoadCallback(drawChart2);
	
 	// 차트 그리기 함수
	function drawChart2() {
		//json 데이터 받아로기
		// dataType : "json" 결과값이 json 형식
		// async :false  비동기식 옵션을 끔(동기식)
		// ajax 는 비동기식이다. 즉 기본값이 비동기식 true 이다
		// 즉 차트가 그려지기 전에는 다른 작업은 하지 못한다.
		//responseText  : 서버의 응답 텍스트
			var jsonData = $.ajax({
			
			url : "/kmove/cart_money_list.do",
			
			dataType : "json",
			
			async : false
			
			}).responseText;
		
		//alert(jsonData);
		//json 데이터를 데이터 테이블로 변환
		var data = new google.visualization.DataTable(jsonData);
		
		// 차트 그리기 (PieChart, LineChart, ColumnChart)
		var chart = new google.visualization.PieChart(document
		
			.getElementById('chart_div'));
			
			//draw(데이터, 옵션)
			chart.draw(data, {
				width : 400,
				height : 240
		});

 }


</script>
<h1>
	Google Chart!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

<div id="chart_div">
 
 </div>
</body>
</html>
