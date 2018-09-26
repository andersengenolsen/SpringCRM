<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  AppUser: anders
  Date: 04.09.18
  Time: 15:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>SpringCRM</title>
    <link href="<c:url value="/resources/styles/bootstrap.min.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="<c:url value="/resources/styles/base.css"/>" rel="stylesheet" type="text/css">
</head>

<body>
<div class="container">
    <h3 class="text-center pt-5 text-white">SpringCRM</h3>

    <div id="login-row" class="row justify-content-center align-items-center">
        <div id="login-column" class="col-md-6">
            <div class="login-box col-md-12">
                <!-- Error message for invalid credentials -->
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger text-center">
                        <span>Sorry! You entered invalid username/password.</span>
                    </div>
                </c:if>
                <!-- Confirmation for logging out -->
                <c:if test="${param.logout != null}">
                    <div class="alert alert-info text-center">
                        <span>You have been logged out!</span>
                    </div>
                </c:if>
                <!-- Login form -->
                <form:form action="${pageContext.request.contextPath}/authenticateUser" method="post">
                    <div class="form-group">
                        <label for="username" class="text-white">USERNAME</label><br>
                        <input type="text" name="username" id="username" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="password" class="text-white">PASSWORD</label><br>
                        <input type="password" name="password" id="password" class="form-control">
                    </div>
                    <div class="form-group">
                        <label for="remember-me" class="text-white"><span>Remember me</span> <span><input
                                id="remember-me" name="remember-me" type="checkbox"></span></label><br>
                        <input type="submit" name="submit" class="btn btn-info btn-md" value="SUBMIT">
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
