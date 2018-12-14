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
        <%--
        使用的方法是任务队列，每个子任务拥有一个长度为3的队列，用来存放不同节点返回的任务结果，如果一个队列满，且三个结果完全一致
        才会写入存储中，如果有不一致的结果，为子任务开启一个验算队列，再次分发该子任务
        --%>
        <tr>3、任务堆积（消息队列）</tr>
        <%--
        暂不实现，一个节点只允许同一时间进行一次运算
        --%>
        <tr>4、Go语言改造</tr>
        <%--
        暂不实现
        --%>
        <tr>5、投票算法设计-使得区块信息保持最终一致性</tr>
        <%--
        使用随机种子，根据时间来随机，每隔一分钟就有一个节点成为收集者，收集的区块序号按照顺序，如上一次收集了1-100，这次收集
        101-200.收集者采集所有开启了存储的节点相关序号的数据，并做多数一致投票，再广播回网络。
        --%>
        <tr>6、引入缓存，缓存一些计算结果</tr>
        <%--
       暂不实现
       --%>
        <tr>7、效仿https，在安全方面做出一些研究</tr>
        <%--
        主要是数据传输加密方面
        --%>
        <tr>8、任务丢失重传的协议</tr>
        <%--
        主要是分发任务时有任务丢失，会造成有的节点失去联系，做一个确认机制
        --%>
    </table>
	</body>
</html>