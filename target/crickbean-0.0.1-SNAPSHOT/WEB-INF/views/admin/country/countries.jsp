<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/adminSidebar.jsp"/>
<jsp:include page="/WEB-INF/views/common/adminHeader.jsp"/>
  <h1 class="ml-3">Add a new country</h1>
  <form:form action="${pageContext.request.contextPath}/country/add" modelAttribute="country" enctype="multipart/form-data" class="form-inline align-center">
    <div class="form-group mx-sm-3 mb-2">
      <label for="country" class="mr-3">Country: </label>
      <form:input path="countryName"
        type="text"
        class="form-control"
        id="country"
        placeholder="Country Name"
      />
    </div>

    <h6>Flag:</h6>
    <div class="form-group mx-sm-3 mb-2">
      <input type="file" name="file" class="form-control-file" id="file" />
    </div>

    <button type="submit" class="btn btn-primary mb-2">Submit</button>
  </form:form>
  <table class="table mt-5 table-success table-hover">
    <thead class="thead-dark">
      <tr>
        <th>Country Name</th>
        <th>Country Flag</th>
        <th>Active Status</th>
        <th>Update</th>
        <th>Delete</th>
      </tr>
    </thead>
    <tbody>
    <c:forEach items="${allCountry}" var="country">
      <tr>
        <td>${country.countryName}</td>
        <td><img style="width: 200px" src="${pageContext.request.contextPath}${country.countryLogo}" alt="" /></td>
        <td>${country.active}</td>
        <td>
          <a href="${pageContext.request.contextPath}/country/update?countryId=${country.country_id}" class="btn btn-primary btn-circle ml-1" role="button"
            ><i class="far fa-edit text-white"></i
          ></a>
        </td>
        <td>
          <a href="${pageContext.request.contextPath}/country/delete?countryId= ${country.country_id}" class="btn btn-danger btn-circle ml-1" role="button"
            ><i class="fas fa-trash text-white"></i
          ></a>
        </td>
      </tr>
    </c:forEach>
    </tbody>
  </table>
</div>
<jsp:include page="/WEB-INF/views/common/adminFooter.jsp"/>