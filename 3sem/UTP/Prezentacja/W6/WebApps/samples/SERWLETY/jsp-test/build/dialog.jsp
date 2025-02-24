<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="m" %>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=windows-1250">
  <title>Test</title>
</head>
<c:set var="fmsg" value="Wprowadź informację" /> 
<c:set var="preinfo" value="Wprowadziłeś:" /> 
<body>
<h2>Aplikacja testowa<br>${fmsg}</h2>

<form method="get">
<input type="text" name="info" size="50">
<p></p>
<input type="submit" value="Submit">
<input type="reset" value="Reset">
</form>

<c:if test="${fn:length(param.info) > 0}" >
  <m:msg pre="${preinfo}" info="${param.info}"/>
</c:if>
</body>
</html>
