<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
<h1 class="ml-3">Update Existing Member</h1>
<form:form action="${pageContext.request.contextPath}/member/update" enctype="multipart/form-data" modelAttribute="member">
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
        <label for="active" class="mr-3">Member Type: </label>
        <form:select path="active" class="form-control">
            <form:option value="true" label="Active"/>
            <form:option value="false" label="Inactive"/>
        </form:select>
    </div>

    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="teams" class="mr-3">Select Team: </label>
        <form:select path="teamId" class="form-control">
            <form:options items="${teams}" itemValue="teamId" itemLabel="teamName"/>
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
    <form:hidden path="memberId" value = "${memberId}" />
    <button type="submit" class="btn btn-primary ml-3 mt-1">Submit</button>
</form:form>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>