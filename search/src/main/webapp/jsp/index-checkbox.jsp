<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset="utf8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="css/dataTable.css" media="screen">
	<style type="text/css">
	body,th,td {
		font-family: Arial, Verdana, sans-serif;
		font-size: 0.9em;
	}
	a:link, a:visited  {
		color: #59B337;
	}

	a:hover, a:active, a:focus {
		color: #000000;
	}
	table.dataTable tr.marked {
		background-color: #FFD900;
	}
	</style>
	<!-- jquery packed -->
	<script type="text/javascript" src="js/jquery-1.2.3.pack.js"></script>
	<!-- tableRowCheckboxToggle -->
	<script type="text/javascript" src="js/tableRowCheckboxToggle.js"></script>
</head>
<body>
搜索首页<br/>
<s:property  value="out"/>
<s:form action="search.action" method="post" theme="simple" namespace="/"> 

<s:textfield name="queryString"/>
<s:submit value="搜索" method="search"/> 
<s:submit value="re搜索" method="relevanceSearch"/> 
</br>
*******************************
</br>
<s:iterator value="queryLists" status="stat"> 
<s:property  value="#stat.index"/>
<s:textfield 
name="queryLists[%{#stat.index}].term" 
value="%{queryLists[#stat.index].term}"/> 
<s:textfield label="Name" 
name="queryLists[%{#stat.index}].value" 
value="%{queryLists[#stat.index].value}"/> 
<br/> 
</s:iterator> 
</br>
*******************************
   <table border="0" cellspacing="0" cellpadding="0" class="dataTable">
    <thead><tr><th class="dataTableHeader">相关</th>
    <th class="dataTableHeader">不相关</th>
    <th class="dataTableHeader">ID</th>
    <th class="dataTableHeader">content</th>
    <th class="dataTableHeader">value</th></thead>
    <tbody>
    <s:iterator value="result.keySet()" id="doc">
     <tr class="even_row">
     <td><s:checkbox value="false" fieldValue="%{#doc}" name="relationDocs"/></td>
     <td><s:checkbox value="false" fieldValue="%{#doc}" name="unRelationDocs" cssClass="unReClass"/></td>
      <td><s:property value="#doc"/></td>
      <td><s:property value="docs.get(#doc)"/></td>
      <td><s:property value="oldresult.get(#doc)"/>
      ***<s:property value="result.get(#doc)"/></td>
     </tr>
    </s:iterator>
  </tbody></table><div id="excludeCheckboxesWithClass"></div>
</br>

</s:form> 
</body>
</html>