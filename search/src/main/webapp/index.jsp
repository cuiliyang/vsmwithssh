<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset="utf8">
		<title>jquery</title>
		<link rel="stylesheet" type="text/css" href="css/style.css" />
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
				<div class="search-result" style="width:23%;">
					<p>课程名称：信息检索</p>
					<p>指导教师：张琢</p>
					<p>小组成员：孙大鹏、刘荣橙</p>
					<p>李健、张丽云、黄煜纯</p>
				</div>
				<div class="search-item-info" style="width:63%;">
				<p>作业一：我们使用庖丁分词，并对分词结果进行统计，计算了TF值，根据多文档的结果，统计了一个词出现的文档数，用于计算IDF值</p>
				<p>TF值：为一个词在文档中出现的次数除以这篇文档中出现次数最后的词的次数</p>
				<p>IDF值：为文档总数除以一个词在文档集中出现的次数的LOG值，权重为二者乘积</p>
				<p>我们使用了两个LinkedHashMap的结构完成了索引的数据结构[term,[doc,value]]和[doc[term,value]]</p>
				<br/>
				<p>作业二：基于我们建的索引，我们完成了VSM模型和查询反馈，将获得的输入使用庖丁进行分词，获得词的次数，根据作业一中的IDF值，</p>
				<p>计算出查询向量，将查询向量中的每一个Term与[term,[doc,value]]进行匹配，最终获得含有查询向量的term的文档的权重，为了消除长文档的影响，我们将权重除以文档长度。</p>
				<p>我们使用问题集进行测试，结果非常好,30个样本集只有4个答案不在首位，在第二位，其余均在首位</p>
				<p>查询扩展：我们使用了标准Rocchio公式，新的查询向量为原有查询向量，加上相关文档的平均向量，减去不相关文档的平均向量。使用新的向量进行查询</p>
				<br/>
				<p>作业三：使用Lucene建立索引IndexReader->IndexWriter->Analyzer->Document->Field</p>
				<p>使用Lucene查询IndexSearcher->Term->Query->TermQuery->Hits</p>
				</div>
			</div>
			<div class="footer">
				课程名称：信息检索   @  指导教师：张琢    @   小组成员：孙大鹏、刘荣橙、李健、张丽云、黄煜纯（按姓氏笔画排序）
			</div>
		</div>
	</body>
</html>