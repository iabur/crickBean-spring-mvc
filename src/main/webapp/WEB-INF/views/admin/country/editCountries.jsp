<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
<h1 class="ml-3">Uddate existing country</h1>
<form:form action="${pageContext.request.contextPath}/country/update" modelAttribute="country" enctype="multipart/form-data" class="align-center">
  <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
    <label for="country" class="mr-3">Country: </label>
    <form:input path="countryName"
                type="text"
                class="form-control"
                id="country"
                placeholder="Country Name"
    />
  </div>

  <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
    <label for="file" class="mr-3">Flag: </label>
    <input type="file" name="file" class="form-control-file" id="file" />
  </div>
  <div class="form-group mx-sm-3 mb-2" style="width: 350px;">
    <label for="type" class="mr-3">Active Status: </label>
    <form:select path="active" class="form-control">
      <form:option value="true" label="Active"/>
      <form:option value="false" label="Inactive"/>
    </form:select>
  </div>
  <form:hidden path="countryId" value="${countryId}"/>

  <button type="submit" class="btn btn-primary ml-3">Update</button>
</form:form>
</div>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>