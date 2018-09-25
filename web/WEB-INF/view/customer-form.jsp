<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>SpringCRM | Customer List</title>
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
    <h3 class="text-center text-white pt-5 text-uppercase">Add new customer</h3>
    <form:form action="add-customer" method="post" modelAttribute="customer" cssClass="text-white font-weight-bold">

        <form:hidden path="id"/>

        <div class="form-group">
            <label for="firstName">First name</label>
            <form:input path="firstName" cssClass="form-control"/>
            <form:errors path="firstName" cssClass="text-danger"/>
        </div>

        <div class="form-group">
            <label for="lastName">Last name</label>
            <form:input path="lastName" id="lastName" cssClass="form-control"/>
            <form:errors path="lastName" cssClass="text-danger"/>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <form:input path="email" id="email" cssClass="form-control"/>
            <form:errors path="email" cssClass="text-danger"/>
        </div>

        <input type="submit" class="btn-lg btn-primary" value="Submit"/>

    </form:form>
</div>
</body>
</html>
