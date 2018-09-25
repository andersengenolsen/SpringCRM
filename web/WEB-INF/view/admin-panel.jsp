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
<div class="col-md-12">
    <div class="table-responsive">
        <table id="customer-list" class="table table-bordered table-striped text-white font-weight-bold">

            <thead>
            <th>Username</th>
            <th>Roles</th>
            <th>Edit</th>
            <th>Delete</th>
            </thead>

            <tbody>

            <!-- Looping and printing all customers -->
            <c:forEach var="user" items="${users}">

                <!-- Creating update link -->
                <c:url var="updateLink" value="admin/user/update-user">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>
                <!-- Creating delete link -->
                <c:url var="deleteLink" value="admin/user/delete-user">
                    <c:param name="userId" value="${user.id}"/>
                </c:url>

                <tr>
                    <td>${user.username}</td>
                    <td>
                        <c:forEach var="role" items="${user.roles}">
                            <span> ${role.name} </span>
                        </c:forEach>
                    </td>
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
</body>
</html>
