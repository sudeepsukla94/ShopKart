<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search !!!</title>

<script>
function SearchFun(){
		
		
 	var data = document.forms[0].SProd.value;
	var url = "/Meera.ShopKart/SearchProdServlet?p="+data;
	var obj = new XMLHttpRequest();
	System.out.println("Inside SearchFun");
	obj.onreadystatechange=function(){
		if(obj.readyState == 4){
			document.getElementById("d1").innerHTML=obj.responseText;
			
		}
	}
	
	obj.open("get",url,true);
	obj.send();
	}
 	
</script>
</head>
<body>



	<form action="SearchProdServlet" method = "post">
		Search Name <input type = "text" name = "SProd"  >
		Submit <input type = "submit" name ="Submit">
	</form>
	<div id = "d1"></div> 


</body>
</html>