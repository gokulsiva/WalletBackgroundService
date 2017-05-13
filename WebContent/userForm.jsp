<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="com.techgig.wallet.bean.UserBean, com.techgig.wallet.service.UserAdmin" %>
    
    
    <%
    
    String id = "";
    String name = "";
    String email = "";
	String postUrl = "CreateUser";
	String buttonValue = "Create User";
	String welcomeTitle = "Create user";
	String indexUrlString = "<< Back to LogIn";
	String indexUrl = "index.jsp";
    
    id = request.getParameter("linkId");
    if(!(id == null)){
    	UserBean user = UserAdmin.getUser(new Integer(id));
    	name = user.getName();
    	email = user.getName();
    	postUrl = "UpdateUser";
    	buttonValue = "Update User";   	
    	welcomeTitle = "Edit User";
    	indexUrlString = "<< Back to questions";
    	if(request.getSession().getAttribute("GamificationStringAccountType").equals("admin")){
    		indexUrl = "admin-question-index.jsp";
    	} else {
    		indexUrl = "question-index.jsp";
    	}
    } else {
    	id = "";
    }
    
    %>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User</title>
<style type="text/css">
textarea {
  overflow-wrap: normal;
  overflow-x: scroll;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}
body {
	background: #DFDCE3;
}
.font-class {
	font-family: cursive;
}
input[type=text]{
 width: 250px;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}
input[type=password]{
 width: 250px;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}
input[type=submit]{
 font-family: cursive;
 font-weight: bold;
 background: #FFE658;
 border:1px solid #777575;
 border-radius:5px;
 color:black;
 margin-left:5px;
 margin-right:10px;
 padding:5px 5px 5px 5px;
}
input[type=submit]:hover {
	background: #CBE32D;
}
a:hover {
	color: red;
}
</style>
<script src="js/jquery.js"></script>
<script type="text/javascript">

$(document).ready(function() {

var $form = $('#questionForm');

$form.on('submit', function(ev){
    ev.preventDefault();
    var password = $("#password").val();
    var confirmPassword = $("#repassword").val();
    if (password != confirmPassword) {
        alert("Passwords do not match.");
        return false;
    }
    var walletPassword = $("#walletPassword").val();
    var confirmWalletPassword = $("#reWalletPassword").val();
    if (walletPassword != confirmWalletPassword) {
        alert("Wallet passwords do not match.");
        return false;
    }
    $("#hidden_div").show();
    
    $.ajax({
        url: $form.attr("action"),
        type:   'POST',
        data: $form.serialize(),
        success: function(msg){
        	$("#hidden_div").text(msg);
        }
    });
});
});

</script>
</head>
<body>

<% if(!(id.equals("") || id.equals(null))){ %>



<% } %>

<div id="hidden_div" align="center" hidden style="color: green;">Submitting data please wait.</div>

<div align="left"><font size="30px"><b><%= welcomeTitle %></b></font></div>
<br><br>
<a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 5px;"><b><%= indexUrlString %></b></a>
<br><br>
<form id="questionForm" action="<%= postUrl %>" method="post">
<input type="hidden" name="id" value="<%= id %>">
<table>
<tr><th></th></tr>
<tr>
<td class="font-class">Enter name </td>
<td>:</td>
<td><input required type="text" name="name" value="<%= name %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter email </td>
<td>:</td>
<td><input required type="text" name="email" value="<%= email %>" required ></td>
</tr>
<tr>
<td class="font-class">Enter password </td>
<td>:</td>
<td><input required type="password" id="password" name="password" value="" required ></td>
</tr>
<tr>
<td class="font-class">Re-enter password </td>
<td>:</td>
<td><input required type="password" id="repassword" name="repassword" value="" required ></td>
</tr>

<tr>
<td class="font-class">Enter wallet password </td>
<td>:</td>
<td><input required type="password" id="walletPassword" name="walletPassword" value="" required ></td>
</tr>
<tr>
<td class="font-class">Re-enter wallet password </td>
<td>:</td>
<td><input required type="password" id="reWalletPassword" name="reWalletPassword" value="" required ></td>
</tr>



<tr>
<td  style="padding-top: 20px;"><input type="submit" value="<%= buttonValue %>" onclick="return Validate()" ></td>
<td  style="padding-top: 20px;"><a href="<%= indexUrl %>" style="text-decoration: none; margin-left: 10px;"><b><%= indexUrlString %></b></a></td>
</tr>
</table>


</form>

</body>
</html>