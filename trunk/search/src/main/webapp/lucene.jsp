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
		<!-- tableRowCheckboxToggle -->
		<script type="text/javascript" src="js/tableRowCheckboxToggle.js"></script>
		<!-- tablePagination -->
		<script type="text/javascript" src="js/jquery.tablePagination.0.5.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
				$('.tableww').tablePagination({});
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
				<s:form action="lucene.action" method="post" theme="simple" namespace="/"> 
					<div>
						<label for="primarySearch">Search <span class="jq-jquery">Lucene</span></label>
						<s:textfield name="queryString" cssClass="searchText" id="jq-primarySearch" title="Search jQuery" accesskey="f"/>
						<s:submit id="jq-searchGoButton" cssClass="btn"   value="" name="go" method="lucene"></s:submit>
					</div>
				</s:form>
			</div>
			<div class="content clearfix">
				<div class="search-result" style="width:90%;">
						<p align="center">《搜索结果》</p><br/>
					<table width="200" border="1" class="tableww">
						<thead>
							<tr>
								<td>单词</td>
								<td>权重</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="result.keySet()" id="doc">
						     <tr>
						      <td align="center"><s:property value="#doc"/></td>
						      <td align="center"><s:property value="result.get(#doc)"/></td>
						     </tr>
						    </s:iterator>
						</tbody>
					</table>
				</div>
				<%--<div class="search-item-info" style="width:0%;">
				</div>
			--%></div>
			<div class="footer">
				课程名称：信息检索   @  指导教师：张卓    @   小组成员：孙大鹏、刘荣橙、李健、张丽云、黄煜纯（按姓氏笔画排序）
			</div>
		</div>
	</body>
</html>