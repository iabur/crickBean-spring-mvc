<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <button id="submit" class="btn aqua-gradient btn-rounded btn-sm my-0" type="submit">
            Search
        </button>
    </form>
    <div id="accordion">
        <h4>1.Players</h4>
        <div class="row">
            <c:forEach var="member" items="${members}">
            <C:if test="${member.memberType=='Player'}">
            <div class="col-md-4">
                <!-- Card Wider -->
                <div class="card card-cascade wider">
                    <!-- Card image -->
                    <div class="view view-cascade overlay">
                        <a href="#"><img
                                class="card-img-top"

                                src="${pageContext.request.contextPath}${member.memberPhoto}"
                                alt="Card image cap"
                        /></a>

                        <a href="#!">
                            <div class="mask rgba-white-slight"></div>
                        </a>
                    </div>

                    <!-- Card content -->
                    <div class="card-body card-body-cascade text-center pb-0">
                        <!-- Title -->
                        <h4 class="card-title"><strong> </strong></h4>
                        <!-- Subtitle -->
                        <h5 class="blue-text pb-2"><strong>${member.memberName}</strong></h5>
                        <!-- Text -->
                        <p class="card-text">
                           ${member.memberDescription}
                        </p>

                        <!-- Linkedin -->
                        <a class="px-2 fa-lg li-ic"><i class="fab fa-linkedin-in"></i></a>
                        <!-- Twitter -->
                        <a class="px-2 fa-lg tw-ic"><i class="fab fa-twitter"></i></a>
                        <!-- Dribbble -->
                        <a class="px-2 fa-lg fb-ic"><i class="fab fa-facebook-f"></i></a>

                        <!-- Card footer -->
                        <div class="card-footer text-muted text-center mt-4">
                            25 Years old
                        </div>
                    </div>
                </div>


            </div>
            </C:if>
            </c:forEach>
        </div>
        <h4>2.Team Stuff</h4>
        <div class="row">
            <c:forEach var="member" items="${members}">
                <C:if test="${member.memberType=='Stuff'}">
                    <div class="col-md-4">
                        <!-- Card Wider -->
                        <div class="card card-cascade wider">
                            <!-- Card image -->
                            <div class="view view-cascade overlay">
                                <a href="#"><img
                                        class="card-img-top"
                                        src="${pageContext.request.contextPath}${member.memberPhoto}"
                                        alt="Card image cap"
                                /></a>

                                <a href="#!">
                                    <div class="mask rgba-white-slight"></div>
                                </a>
                            </div>

                            <!-- Card content -->
                            <div class="card-body card-body-cascade text-center pb-0">
                                <!-- Title -->
                                <h4 class="card-title"><strong></strong></h4>
                                <!-- Subtitle -->
                                <h5 class="blue-text pb-2"><strong>${member.memberName}</strong></h5>
                                <!-- Text -->
                                <p class="card-text">
                                        ${member.memberDescription}
                                </p>

                                <!-- Linkedin -->
                                <a class="px-2 fa-lg li-ic"><i class="fab fa-linkedin-in"></i></a>
                                <!-- Twitter -->
                                <a class="px-2 fa-lg tw-ic"><i class="fab fa-twitter"></i></a>
                                <!-- Dribbble -->
                                <a class="px-2 fa-lg fb-ic"><i class="fab fa-facebook-f"></i></a>

                                <!-- Card footer -->
                                <div class="card-footer text-muted text-center mt-4">
                                    Team manager
                                </div>
                            </div>
                        </div>


                    </div>
                </C:if>
            </c:forEach>
        </div>
    </div>
</div>


<jsp:include page="/WEB-INF/views/common/homeFooter.jsp"/>
<script>
$('#submit').click(function (e) {
        e.preventDefault();
        var teamId = ${teamId}
        if (teamId>=0){
            window.location = "members?queryText="+$('#queryText').val()+"&teamId="+teamId;
        }
        else {
            window.location = "members?queryText="+$('#queryText').val()+"&teamId=";
        }
    })
</script>
