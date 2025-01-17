<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>List of Users</h2>

<table class="table">
    <tr>
        <th>#</th>
        <th>User</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="status">
        <tr>
            <td>${status.index + 1}</td> <!-- Add index to show the row number -->
            <td>${user.name} ${user.id}</td> <!-- Display user name and id -->
        </tr>
    </c:forEach>
</table>
