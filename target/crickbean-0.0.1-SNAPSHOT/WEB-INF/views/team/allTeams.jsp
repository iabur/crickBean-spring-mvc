<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/views/common/homeHeader.jsp"/>
<div class="container">
    <form class="form-inline md-form mr-auto mb-4">
        <input
                id="queryText"
                class="form-control mr-sm-2"
                type="text"
                placeholder="Search"
                aria-label="Search"
        />
        <button id="submitText" class="btn aqua-gradient btn-rounded btn-sm my-0" type="submit">
            Search
        </button>
    </form>
    <div id="accordion">

        <h4>1.International Teams</h4>

        <div class="row">
            <c:forEach var="team" items="${teams}">
                <c:if test="${team.teamType == 'International'}">
                    <div class="col-md-4">
                        <!-- Card Light -->
                        <div class="card">
                            <!-- Card image -->
                            <div class="view overlay">
                                <img style="height: 200px"
                                     class="card-img-top"
                                     src="${pageContext.request.contextPath}${team.teamPhoto}"
                                     alt="Card image cap"
                                />
                                <a>
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>

                            <!-- Card content -->
                            <div class="card-body">
                                <!-- Title -->
                                <h4 class="card-title">${team.teamName}</h4>
                                <hr/>
                                <!-- Text -->
                                <p class="card-text">
                                        ${team.teamDescription}

                                </p>
                            </div>
                        </div>
                        <!-- Card Light -->

                    </div>
                </c:if>
            </c:forEach>
        </div>


        <h4>2.Local teams</h4>

        <div class="row">
            <c:forEach var="team" items="${teams}">
                <c:if test="${team.teamType == 'Local'}">
                    <div class="col-md-4">
                        <!-- Card Light -->
                        <div class="card">
                            <!-- Card image -->
                            <div class="view overlay">
                                <img style="height: 200px"
                                     class="card-img-top"
                                     src="${pageContext.request.contextPath}${team.teamPhoto}"
                                     alt="Card image cap"
                                />
                                <a>
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>

                            <!-- Card content -->
                            <div class="card-body">
                                <!-- Title -->
                                <h4 class="card-title">${team.teamName}</h4>
                                <hr/>
                                <!-- Text -->
                                <p class="card-text">
                                        ${team.teamDescription}

                                </p>
                                <!-- Link -->
                            </div>
                        </div>
                        <!-- Card Light -->
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/views/common/homeFooter.jsp"/>
//replace the url with query text for searching
<script>
    $('#submitText').click(function (e) {
        e.preventDefault();
        var countryId = ${countryId};
        if (countryId>=0){
            window.location = "teams?queryText="+$('#queryText').val()+"&countryId="+countryId;
        }
        else {
            window.location = "teams?queryText="+$('#queryText').val()+"&countryId=";
        }
    })
</script>