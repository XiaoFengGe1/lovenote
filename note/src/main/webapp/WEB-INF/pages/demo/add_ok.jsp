<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title></title>
</head>
<body>

<h2>New Article</h2>
<form:form method="post" commandName="article">
   <table>
    <tr>
        <td><form:label path="title">title</form:label></td>
        <td><form:input path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="content">content</form:label></td>
        <td><form:input path="content" /></td>
    </tr>
    <tr>
        <td><form:label path="status">status</form:label></td>
        <td><form:radiobutton path="status" value="0"/>未发布<form:radiobutton path="status" value="1"/>已发布</td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>

</body>
</html>