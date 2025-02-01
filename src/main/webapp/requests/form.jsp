<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${param.action == 'update' ? 'Update Request' : 'Add New Request'}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container my-4">
    <h2 class="text-center">${param.action == 'update' ? 'Update Request' : 'Add New Request'}</h2>
    <form action="/requests" method="post">
        <input type="hidden" name="action" value="${param.action}">
        <c:if test="${param.action == 'update'}">
            <input type="hidden" name="id" value="${param.id}">
        </c:if>

        <button type="submit"
                class="btn btn-primary">${param.action == 'update' ? 'Update Request' : 'Add New Request'}</button>
        <a href="/subscriptions?action=list" class="btn btn-secondary">Cancel</a>

    </form>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
