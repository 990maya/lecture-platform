<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
	 var wsUri = "ws://127.0.0.1:8080/lecture/websocket";

	 function initWebsocket() {
         websocket = new WebSocket(wsUri);
         
         websocket.onopen = function(evt) {
             console.log(evt);
         };
         websocket.onmessage = function(evt) {
         	var data = evt.data
         	console.log(data);
         };
         websocket.onerror = function(evt) {
         	console.log(evt);
         };
     }
    
     initWebsocket(); 
	
	</script>
</body>
</html>