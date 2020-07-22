<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
<div class="container">

  <table class="table mt-5 table-striped">
    <thead class="thead-dark">
      <tr>
        <th>User Name</th>
        <th>Photo</th>
        <th>Role</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${users}" var="user" >
      <tr>
        <td>${user.username}</td>
        <td><img class="rounded-circle" style="width: 100px;height: 100px" src="${pageContext.request.contextPath}${user.logo}" alt=""></td>
        <td><c:forEach items="${user.roles}" var="role">
          ${role.roleName}
        </c:forEach> </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>