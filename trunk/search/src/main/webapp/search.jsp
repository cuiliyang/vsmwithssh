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
				$('.dataTable').tablePagination({});
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
			</div>
			<div class="content clearfix">
				<div class="search-result" style="width:25%;">
					  <p align="center">《分词权重表》</p><br/>
					<s:if test="queryLists.size()>0">
					<table width="200" border="1" class="tableww">
						<thead>
							<tr>
								<td>序号</td>
								<td>单词</td>
								<td>权重</td>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="queryLists" status="stat"> 
							<tr>
								<td><s:property  value="#stat.index+1"/></td>
								<td>
									<s:property  value="%{queryLists[#stat.index].term}"/>
									<s:hidden name="queryLists[%{#stat.index}].term" value="%{queryLists[#stat.index].term}"/>
								</td>
								<td>
									<s:property  value="%{queryLists[#stat.index].value}"/>
									<s:hidden name="queryLists[%{#stat.index}].value" value="%{queryLists[#stat.index].value}"/> 
								</td>
							</tr>
							</s:iterator> 
						</tbody>
					</table>
					</s:if>
					<s:else><p align="center" style="color:#FF0000">无有效词条...Orz...</p></s:else>
				</div>
				<div class="search-item-info" style="width:60%;">
					 <p align="center">结  果</p>
					 <p align="right">耗时<s:property value="time"/>ms</p>
					  <table border="0" cellspacing="0" cellpadding="0" class="dataTable">
						    <thead>
						    	<tr>
						    		<th class="dataTableHeader">相关</th>
						    		<th class="dataTableHeader">不相关</th>
						    		<th class="dataTableHeader">文档</th>
						    		<th class="dataTableHeader">文档内容</th>
						    		<th class="dataTableHeader">权重</th>
						    	</tr>
						    </thead>
						    <tbody>
							    <s:iterator value="result.keySet()" id="doc">
							     <tr class="odd_row">
							      <td><s:checkbox value="false" fieldValue="%{#doc}" name="relationDocs"/></td>
							      <td><s:checkbox value="false" fieldValue="%{#doc}" name="unRelationDocs" cssClass="unReClass"/></td>
							      <td><s:property value="#doc"/></td>
							      <td>
								      <div style= "overflow:hidden;height:20px;line-height:20px; ">
								        <s:property value="docs.get(#doc)"/>
								      </div> </td>
							      <td>
							      		<s:if test="oldresult.keySet().size()>0">
								      <s:property value="oldresult.get(#doc)"/>
								      <br>--><br></s:if>
								      <s:property value="result.get(#doc)"/>
							      </td>
							     </tr>
						    </s:iterator>
						  </tbody></table>
					</s:form> 
				</div>
			</div>
			<div class="footer">
				课程名称：信息检索   @  指导教师：张琢    @   小组成员：孙大鹏、刘荣橙、李健、张丽云、黄煜纯（按姓氏笔画排序）
			</div>
		</div>
	</body>
</html>