<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- jquery packed -->
<script type="text/javascript" src="js/jquery-1.2.3.pack.js"></script>
<!-- tablePagination -->
	<script type="text/javascript" src="js/jquery.tablePagination.0.5.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('.tableww').tablePagination({});
			
			$("#button").click(function(){
				var html = "<br/><textarea  name='docsList' class='input' cols='30' rows='5'> </textarea>"
				var inputs = $(".input");
				alert(inputs.length);
				$(".aaa").append(html);
			});
		});
	</script>
</head>
<body>
搜索首页<br/>
<s:property  value="out"/>
<s:form action="calcu.action" method="post" theme="simple" namespace="/"> 

</br>
*******************************
</br>
<s:submit value="计算" method="calcu"/> 
<table class="aaa" id="aaa">
	<input type="button" id="button" value="hello"><br/>
	<s:textarea name="docsList" cssClass="input" cols="30" rows="5"/> 
</table>
</br>
*******************************

*******************************

<table border="1">
    <tr><td colspan="3">TF</td></tr>
    <tr><td>content</td><td>doc</td><td>value</td></tr>
    
    <s:iterator value="docTermFreq.keySet()" id="term">
	    <s:iterator value="docTermFreq.get(#term).keySet()" id="doc">
	      <tr>
		      <td><s:property value="#doc"/></td>
		      <td><s:property value="#term"/></td>
		      <td><s:property value="docTermFreq.get(#term).get(#doc)"/></td>
	      <tr>
	    </s:iterator>
    </s:iterator>
   </table>
</br>
*******************************
   <table border="1">
    <tr><td colspan="2">IDF</td></tr>
    <tr><td>content</td><td>value</td></tr>
    <s:iterator value="idf.keySet()" id="doc">
     <tr>
      <td><s:property value="#doc"/></td>
      <td><s:property value="idf.get(#doc)"/></td>
     </tr>
    </s:iterator>
   </table>
</br>
*******************************
   <table border="1">
    <tr><td colspan="3">TF*IDF</td></tr>
    <tr><td>content</td><td>doc</td><td>value</td></tr>
    
    <s:iterator value="docTerms.keySet()" id="term">
	    <s:iterator value="docTerms.get(#term).keySet()" id="doc">
	      <tr>
		      <td><s:property value="#doc"/></td>
		      <td><s:property value="#term"/></td>
		      <td><s:property value="docTerms.get(#term).get(#doc)"/></td>
	      <tr>
	    </s:iterator>
    </s:iterator>
   </table>
</br>
*******************************
   <table border="1">
    <tr><td colspan="3">InTF*IDF</td></tr>
    <tr><td>content</td><td>doc</td><td>value</td></tr>
    
    <s:iterator value="terms.keySet()" id="term">
	    <s:iterator value="terms.get(#term).keySet()" id="doc">
	      <tr>
		      <td><s:property value="#doc"/></td>
		      <td><s:property value="#term"/></td>
		      <td><s:property value="terms.get(#term).get(#doc)"/></td>
	      <tr>
	    </s:iterator>
    </s:iterator>
   </table>
</br>

</br>
</s:form> 
</body>
</html>