<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title></title>
	</head>
	<body>
	当前空闲的节点IP有：【47.95.194.16】【39.107.83.2】【39.106.194.129】<br/>
	当前可以计算的问题有：<br/>
    <a href="${pageContext.request.contextPath }/CalculateStartServlet?qid=1">1.计算1到100万中质数的个数。</a><br/>
    <a href="${pageContext.request.contextPath }/CalculateStartServlet?qid=2">2.计算1到100万中能同时被2,3,5整除的数的个数。</a><br/>
    <table>
        <tr>1、动态IP维护，动态踢出IP和加入IP</tr>
        <tr>2、节点计算错误容错处理</tr>
        <tr>3、任务堆积（消息队列）</tr>
        <tr>4、Go语言改造</tr>
        <tr>5、投票算法设计-使得区块信息保持最终一致性</tr>
        <tr>6、引入缓存，缓存一些计算结果</tr>
        <tr>7、效仿https，在安全方面做出一些研究</tr>
    </table>
	</body>
</html>