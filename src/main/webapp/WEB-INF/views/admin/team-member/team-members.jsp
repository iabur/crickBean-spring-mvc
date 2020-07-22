<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
<sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_TEAM_MANAGER')">
<h1 class="ml-3">Add a new Member</h1>
<form:form action="${pageContext.request.contextPath}/member/add" enctype="multipart/form-data" modelAttribute="member">
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="member" class="mr-3">Member Name: </label>
        <form:input path="memberName"
                    type="text"
                    class="form-control"
                    id="member"
                    placeholder="Member Name"
        />
    </div>
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="username" class="mr-3">Member UserName: </label>
        <form:input path="memberUserName"
                    type="text"
                    class="form-control"
                    id="username"
                    placeholder="Member userName"
        />
    </div>
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="password" class="mr-3">Password: </label>
        <form:input path="memberPassword"
                    type="password"
                    class="form-control"
                    id="password"
                    placeholder="Password"
        />
    </div>
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="type" class="mr-3">Member Type: </label>
        <form:select path="memberType" class="form-control">
            <form:option value="Player" label="Player"/>
            <form:option value="Stuff" label="Stuff"/>
        </form:select>
    </div>

    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="description" class="mr-3">Description: </label>
        <form:textarea path="memberDescription"
                       class="form-control"
                       id="exampleFormControlTextarea1"
                       rows="3"
        ></form:textarea>
    </div>

    <div class="form-group mx-sm-3 mb-2">
        <label for="file" class="mr-3">File: </label>
        <input name="file" type="file" class="form-control-file" id="file"/>
    </div>

    <button type="submit" class="btn btn-primary ml-3 mt-1">Submit</button>
</form:form>
</sec:authorize>
<table class="table mt-5 table-success table-hover">
    <thead class="thead-dark">
    <tr>
        <th>Member Name</th>
        <th>Member Type</th>
        <th>Active Status</th>
        <th>Team</th>
        <th>Member Description</th>
        <th>Photo</th>
        <th>Update</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${allMembers}" var="member">
        <tr>
            <td>${member.memberName}</td>
            <td>${member.memberType}</td>
            <td><c:if test="${member.active==true}">
                Active
            </c:if>
                <c:if test="${member.active==false}">
                    Inactive
                </c:if>
            </td>
            <td>${member.team.teamName}</td>
            <td>
                    ${member.memberDescription}
            </td>
            <td><img style="width: 200px;" src="${pageContext.request.contextPath}${member.memberPhoto}" alt=""/></td>
            <td>
                <a href="${pageContext.request.contextPath}/member/update?memberId=${member.memberId}"
                   class="btn btn-primary btn-circle ml-1" role="button"
                ><i class="far fa-edit text-white"></i
                ></a>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/member/delete?memberId=${member.memberId}"
                   class="btn btn-danger btn-circle ml-1" role="button"
                ><i class="fas fa-trash text-white"></i
                ></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>