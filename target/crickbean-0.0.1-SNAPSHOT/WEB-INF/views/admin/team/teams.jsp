<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
<h1 class="ml-3">Add a new Team</h1>
<form:form action="${pageContext.request.contextPath}/team/add" enctype="multipart/form-data" modelAttribute="team">
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="team" class="mr-3">Team: </label>
        <form:input
                path="teamName"
                type="text"
                class="form-control"
                id="team"
                placeholder="Team Name"
        />
    </div>
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="type" class="mr-3">Team Type: </label>
        <form:select path="teamType" class="form-control">
            <form:option value="International" label="International"/>
            <form:option value="Local" label="Local"/>
        </form:select>
    </div>
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="description" class="mr-3">Description: </label>
        <form:textarea path="teamDescription"
                class="form-control"
                id="exampleFormControlTextarea1"
                rows="3"
        ></form:textarea>
    </div>

    <div class="form-group mx-sm-3 mb-2">
        <label for="file" class="mr-3">File: </label>
        <input type="file" name="file" class="form-control-file" id="file"/>
    </div>

    <button type="submit" class="btn btn-primary ml-3 mt-1">Submit</button>
</form:form>

<table class="table mt-5 table-success table-hover">
    <thead class="thead-dark">
    <tr>
        <th>Team Name</th>
        <th>Team Type</th>
        <th>Active Status</th>
        <th>Team Description</th>
        <th>Logo</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allTeams}" var="team">
    <tr>
        <td>${team.teamName}</td>
        <td>${team.teamType}</td>
        <td><c:if test="${team.active==true}">Active</c:if>
            <c:if test="${team.active==false}">Inactive</c:if>
        </td>
        <td>
           ${team.teamDescription}
        </td>
        <td><img style="width: 200px" src="${pageContext.request.contextPath}${team.teamPhoto}" alt=""/></td>
        <td>
            <a href="${pageContext.request.contextPath}/team/update?teamId=${team.teamId}" class="btn btn-primary btn-circle ml-1" role="button"
            ><i class="far fa-edit text-white"></i
            ></a>
        </td>
        <td>
            <a href="${pageContext.request.contextPath}/team/delete?teamId=${team.teamId}" class="btn btn-danger btn-circle ml-1" role="button"
            ><i class="fas fa-trash text-white"></i
            ></a>
        </td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>
