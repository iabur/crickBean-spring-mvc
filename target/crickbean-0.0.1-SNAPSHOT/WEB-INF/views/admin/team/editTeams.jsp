<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
<h1 class="ml-3">Update existing Team</h1>
<form:form modelAttribute="team" enctype="multipart/form-data" action="${pageContext.request.contextPath}/team/update">
    <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
        <label for="team" class="mr-3">Team: </label>
        <form:input path="teamName"
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
        <input name="file" type="file" class="form-control-file" id="file"/>
    </div>
    <form:hidden path="teamId"  value="${teamId}"></form:hidden>
    <button type="submit" class="btn btn-primary ml-3 mt-1">Update</button>
</form:form>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>
