<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Spring form tags -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!-- Spring Security tag -->
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  AppUser: anders
  Date: 13.09.18
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
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
    <script src="<c:url value="/resources/javascript/base.js" />"></script>

</head>
<body>
<div class="container">
    <h3 class="text-center text-white pt-5">SpringCRM Application</h3>
    <div class="row">
        <div class="col-md-12">
            <!-- Logged in as -->
            <p class="text-light font-weight-bold float-left">Logged in as: <security:authentication
                    property="principal.username"/></p>
            <!-- Link to Admin panel, only visibile for admin users -->
            <security:authorize access="hasRole('admin')">
                <a href="${pageContext.request.contextPath}/admin" class="btn btn-primary btn-info btn-lg float-right">Admin
                    panel</a>
            </security:authorize>
        </div>
        <div class="col-md-12">
            <!-- Displaying user role -->
            <p class="text-light font-weight-bold">User role: <security:authentication
                    property="principal.authorities"/></p>
        </div>
        <div class="col-md-12">
            <div class="table-responsive">
                <table id="customer-list" class="table table-bordered table-striped text-white font-weight-bold">

                    <thead>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Edit</th>
                    <th>Delete</th>
                    </thead>

                    <tbody>

                    <!-- Looping and printing all customers -->
                    <c:forEach var="customer" items="${customers}">

                        <!-- Creating update link -->
                        <c:url var="updateLink" value="/customer/update-customer">
                            <c:param name="customerId" value="${customer.id}"/>
                        </c:url>
                        <!-- Creating delete link -->
                        <c:url var="deleteLink" value="/customer/delete-customer">
                            <c:param name="customerId" value="${customer.id}"/>
                        </c:url>

                        <tr>
                            <td>${customer.firstName}</td>
                            <td>${customer.lastName}</td>
                            <td>${customer.email}</td>
                            <td>
                                <a href="${updateLink}" class="btn btn-sm btn-primary"><i class="far fa-edit"></i></a>
                            </td>
                            <td>
                                <a href="${deleteLink}" class="btn btn-sm btn-danger" id="delete-btn"><i
                                        class="far fa-trash-alt"></i></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>

                </table>
                <a href="add-customer" class="btn btn-primary btn-info btn-lg float-left">New customer</a>
                <!-- Logout button -->
                <form:form action="${pageContext.request.contextPath}/logout" method="POST">
                    <input type="submit" value="Logout"
                           class="btn btn-primary btn-danger btn-lg float-right">
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
