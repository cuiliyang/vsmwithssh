<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
分词<br/>
<s:property  value="out"/>
<s:form action="analyzer" method="post" theme="simple" namespace="/"> 

<s:textfield name="content"/>
<s:submit value="分词" method="analyzer"/> 
</br>
*******************************
</br>
<table width="200" border="1" class="tableww">
	<thead>
	<tr>
		<th>单词</th>
		<th>次数</th>
	</tr>
	</thead>
	<tbody>
	<s:iterator value="result.keySet()" id="doc">
     <tr>
      <td><s:property value="#doc"/></td>
      <td><s:property value="result.get(#doc)"/></td>
     </tr>
    </s:iterator>
	</tbody></table>
</br>

</s:form> 
</body>
</html>