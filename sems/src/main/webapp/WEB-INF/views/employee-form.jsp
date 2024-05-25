<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<!DOCTYPE html>
<html>

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
  content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
  href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
  integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
  crossorigin="anonymous">

<title>Save Employee</title>
</head>

<body>

  <div class="container">

    <h3>New Employee Creation</h3>
    <hr>

    <p class="h4 mb-4">Employee Details</p>

    <c:url var="saveUrl" value="/employees/save" />

    <form action="${saveUrl}" method="POST">

      <!-- Add hidden form field to handle update -->
      <input type="hidden" name="id" value="${employee.id}" />

      <div class="form-inline">
        <input type="text" name="firstName" value="${employee.firstName}"
          class="form-control mb-4 col-4" placeholder="First Name" />
      </div>

      <div class="form-inline">

        <input type="text" name="lastName" value="${employee.lastName}"
          class="form-control mb-4 col-4" placeholder="Last Name" />
      </div>

      <div class="form-inline">

        <input type="text" name="emailId" value="${employee.emailId}"
          class="form-control mb-4 col-4" placeholder="Email Id" />

      </div>

      <button type="submit" class="btn btn-info col-2">Save</button>

    </form>

    <hr>
    <c:url var="listUrl" value="/employees/list" />

    <a href="${listUrl}">Back to Employees List</a>

  </div>
</body>

</html>          