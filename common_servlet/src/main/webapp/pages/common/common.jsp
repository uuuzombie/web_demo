<!DOCTYPE HTML>
<!-- 自定义css -->
<link href="${pageContext.request.contextPath}/css/common/common.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath}/css/common/errorPage.css" rel="stylesheet" type="text/css" />

<!-- zTree_v3 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/common/zTree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/common/zTree_v3/js/jquery.ztree.core-3.5.js"></script>


<!-- js utils -->
<script src="${pageContext.request.contextPath}/js/common/common.js"></script>
<script src="${pageContext.request.contextPath}/js/common/validate.js"></script>
<script src="${pageContext.request.contextPath}/js/common/errorMessage.js"></script>

<!-- spring tags -->
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- web root -->
<c:set var="root" value="${pageContext.request.contextPath}" />

<!-- session -->
<%--<%@ page import="com.sky.demo.common_servlet.util.SessionUtil" %>--%>
<%--<c:set var="session" value="<%=SessionUtil.getSessionInfo(request)%>" />--%>

<!-- 设置js根路径 -->
<script>var root_path='${root}';</script>