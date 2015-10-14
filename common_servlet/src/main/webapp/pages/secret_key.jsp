<%--
  Created by IntelliJ IDEA.
  User: rg
  Date: 8/16/15
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <script>
        function subForm(action) {
            document.getElementById('action').value = action;
            return true;
        }
    </script>
</head>
<body>
<form id="secret_form" action="/secret" method="post">
    <textarea name="content" id="content" onmouseover="this.focus();this.select();"
              style="width:80%; height:100px;"><%=(request.getAttribute("content") != null ? request.getAttribute("content") : "")%>
    </textarea>
    <input id="action" name="action" type="hidden" value="decode"/>
    <br/>
    <input type="submit" value="编码" style=" width:120px;" onclick="return subForm('encode');">
    &nbsp;&nbsp;&nbsp;&nbsp;
    <input type="submit" value="解码" style=" width:120px;" onclick="return subForm('decode');">
</form>
</body>
</html>
