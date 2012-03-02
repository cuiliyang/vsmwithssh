<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset="utf8">
		<title>jquery</title>
		<link rel="stylesheet" href="css/dataTable.css" media="screen">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
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
					$(".aaa").append(html);
				});
			});
		</script>
	</head>
	<body>
		<div class="holder">
			<div class="header clearfix">
				<ul class="nav">
					<li class="first"><a href="index">项目介绍</a></li>
					<li><a href="analyzer">分词</a></li>
					<li><a href="calcu">计算TF*IDF</a></li>
					<li><a href="search">建立VSM模型</a></li>
					<li class="last"><a href="lucene">Lucene实战</a></li>
				</ul>
				<h1>搜索引擎</h1>
				<s:form action="search.action" method="post" theme="simple" namespace="/"> 
					<div>
						<label for="primarySearch">Search <span class="jq-jquery">Content</span></label>
						<s:textfield name="queryString" cssClass="searchText" id="jq-primarySearch" title="Search jQuery" accesskey="f"/>
						<s:submit id="jq-searchGoButton" cssClass="btn"   value="" name="go" method="search"></s:submit>
						<s:submit id="jq-searchGoButton" cssClass="ext-btn"   value="拓展搜索" name="go" method="relevanceSearch"></s:submit>
					</div>
				</s:form>
			</div>
			<div class="content clearfix">
				<div class="search-result" style="width:33%;">
					<s:form action="calcu.action" method="post" theme="simple" namespace="/"> 
						 
						<table class="aaa" id="aaa">
							<input type="button" id="button" value="增加文档"><br/>
							<s:textarea name="docsList" cssClass="input" cols="30" rows="5"/> 
						</table>
				</div>
				<div class="search-middle-info" style="width:10%;"><s:submit value="计算" method="calcu"/> --->
				</div></s:form>
				<div class="search-item-info" style="width:33%;">
					<p align="center">结  果</p><p align="right">耗时<s:property value="time"/>ms</p>
					
					<s:if test="terms.keySet().size()>0">
					<table border="1" class="tableww" >
					    <tr><td colspan="3" align="center">TF</td></tr>
					    <tr>
					    <td align="center">content</td>
					    <td align="center">doc</td>
					    <td align="center">value</td></tr>
					    
					    <s:iterator value="docTermFreq.keySet()" id="term">
						    <s:iterator value="docTermFreq.get(#term).keySet()" id="doc">
						      <tr>
							      <td align="center"><s:property value="#doc"/></td>
							      <td align="center"><s:property value="#term"/></td>
							      <td align="center"><s:property value="docTermFreq.get(#term).get(#doc)"/></td>
						      <tr>
						    </s:iterator>
					    </s:iterator>
					   </table>
					</br>
					<p align="center">******************************************</p>
					  </br>
					   <table border="1" class="tableww">
					    <tr><td align="center" colspan="2">IDF</td></tr>
					    <tr><td align="center">content</td>
					    <td align="center">value</td></tr>
					    <s:iterator value="idf.keySet()" id="doc">
					     <tr>
					      <td align="center"><s:property value="#doc"/></td>
					      <td align="center"><s:property value="idf.get(#doc)"/></td>
					     </tr>
					    </s:iterator>
					   </table>
					</br>
					<p align="center">******************************************</p>
					</br>
						<table border="1" class="tableww">
					    <tr><td colspan="3" align="center">TF*IDF</td></tr>
					    <tr><td align="center">content</td>
					    <td align="center">doc</td>
					    <td align="center">value</td></tr>
					    
					    <s:iterator value="docTerms.keySet()" id="term">
						    <s:iterator value="docTerms.get(#term).keySet()" id="doc">
						      <tr>
							      <td align="center"><s:property value="#doc"/></td>
							      <td align="center"><s:property value="#term"/></td>
							      <td align="center"><s:property value="docTerms.get(#term).get(#doc)"/></td>
						      <tr>
						    </s:iterator>
					    </s:iterator>
					   </table>
					</br>
					<p align="center">******************************************</p>
					</br>
					 <table border="1" class="tableww">
					    <tr><td align="center" colspan="3">InTF*IDF</td></tr>
					    <tr><td align="center">content</td>
					    <td align="center">doc</td>
					    <td align="center">value</td></tr>
					    
					    <s:iterator value="terms.keySet()" id="term">
						    <s:iterator value="terms.get(#term).keySet()" id="doc">
						      <tr>
							      <td align="center"><s:property value="#doc"/></td>
							      <td align="center"><s:property value="#term"/></td>
							      <td align="center"><s:property value="terms.get(#term).get(#doc)"/></td>
						      <tr>
						    </s:iterator>
					    </s:iterator>
					   </table>	</s:if>
				</div>
			</div>
			<div class="footer">
				课程名称：信息检索   @  指导教师：张琢    @   小组成员：孙大鹏、刘荣橙、李健、张丽云、黄煜纯（按姓氏笔画排序）
			</div>
		</div>
	</body>
</html>