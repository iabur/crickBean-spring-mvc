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
            <button id="searchButton" class="btn aqua-gradient btn-rounded btn-sm my-0" type="submit">
                Search
            </button>
        </form>

        <div class="row">
            <c:forEach var="country" items="${country}">
                <div class="col-md-4">
                    <!-- Card Light -->
                    <div class="card">
                        <!-- Card image -->
                        <div class="view overlay">
                            <img style="height: 200px"
                                    class="card-img-top"
                                    src="${pageContext.request.contextPath}${country.countryLogo}"
                                    alt="Card image cap"
                            />
                            <a>
                                <div class="mask rgba-white-slight"></div>
                            </a>
                        </div>

                        <!-- Card content -->
                        <div class="card-body">
                            <!-- Title -->
                            <h4 class="card-title"><a style="text-decoration: none" href="${pageContext.request.contextPath}/team/teams?countryId=${country.country_id}&queryText=">${country.countryName}</a></h4>
                            <hr/>
                            <!-- Text -->
                            <p class="card-text">
                                Some quick example text to build on the card title and make up the
                                bulk of the card's content.
                            </p>
                        </div>
                    </div>
                    <!-- Card Light -->
                </div>
            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="/WEB-INF/views/common/homeFooter.jsp"/>

//replace the url with query text for searching
<script>
    $('#searchButton').click(function (e) {
        e.preventDefault();
        window.location = "show-all?queryText="+$('#queryText').val();
    })
</script>