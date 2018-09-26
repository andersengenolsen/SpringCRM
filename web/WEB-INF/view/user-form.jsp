<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SpringCRM | Users</title>
    <!-- Latest compiled and minified CSS -->
    <link href="<c:url value="/resources/styles/bootstrap.min.css" />" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
    <link href="<c:url value="/resources/styles/base.css" />" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
</head>
<body>

<div class="container">
    <h3 class="text-center text-white pt-5 text-uppercase">Update / add user</h3>
    <h4 class="text-center text-white pt-3 ">Leave password fields unchanged if only changing username</h4>
    <h5 class="text-center text-white pt-3 ">No worry, they are encrypted</h5>
    <form:form action="update-user" method="post" modelAttribute="user"
               cssClass="text-white font-weight-bold">

        <form:hidden path="id"/>

        <div class="form-group">
            <label for="username">Username</label>
            <form:input path="username" cssClass="form-control"/>
            <form:errors path="username" cssClass="text-danger"/>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <form:input path="password" cssClass="form-control" type="password"/>
            <form:errors path="password" cssClass="text-danger"/>
            <c:if test="${not empty error}">
                <span class="text-danger">${error}</span>
            </c:if>
        </div>

        <div class="form-group">
            <label for="passwordVerif">Repeat password</label>
            <form:input path="passwordVerif" cssClass="form-control" type="password"/>
            <form:errors path="passwordVerif" cssClass="text-danger"/>
        </div>

        <input type="submit" class="btn-lg btn-primary" value="Submit"/>

    </form:form>
</div>
</body>
</html>