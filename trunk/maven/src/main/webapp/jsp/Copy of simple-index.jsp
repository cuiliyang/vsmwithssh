<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
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
   <table border="1">
    <tr><td colspan="5">通过Key遍历map中的value(value值是Book类型的对象)</td></tr>
    <tr><td>相关</td><td>不相关</td><td>ID</td><td>content</td><td>value</td></tr>
    <s:iterator value="result.keySet()" id="doc">
     <tr>
     <td><s:checkbox value="false" fieldValue="%{#doc}" name="relationDocs"/></td>
     <td><s:checkbox value="false" fieldValue="%{#doc}" name="unRelationDocs"/></td>
      <td><s:property value="#doc"/></td>
      <td><s:property value="docs.get(#doc)"/></td>
      <td><s:property value="oldresult.get(#doc)"/>
      ***<s:property value="result.get(#doc)"/></td>
     </tr>
    </s:iterator>
   </table>
</br>

</s:form> 
</body>
</html>