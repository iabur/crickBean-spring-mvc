<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>crickbean</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/main/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Open+Sans:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Bitter:400,700">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,700">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/ionicons.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Bold-BS4-CSS-Image-Slider.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Basic.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Footer-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header-Blue.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Header-Dark.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/image-card-materialize-1.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/image-card-materialize.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Navigation-with-Search.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
</head>

<body>
<div>
    <div class="header-dark"><!--Navbar -->
        <nav class="mb-1 navbar navbar-expand-lg navbar-dark secondary-color lighten-1">
            <a class="navbar-brand" href="#">CrickBean</a>
            <button
                    class="navbar-toggler"
                    type="button"
                    data-toggle="collapse"
                    data-target="#navbarSupportedContent-555"
                    aria-controls="navbarSupportedContent-555"
                    aria-expanded="false"
                    aria-label="Toggle navigation"
            >
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent-555">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/country/show-all?queryText=&countryId="
                        >Countries
                            <span class="sr-only"></span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/team/teams?queryText=&countryId=">Teams</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/member/members?queryText=&teamId=">Team Members</a>
                    </li>
                </ul>
                <ul class="navbar-nav ml-auto nav-flex-icons">
                    <li class="nav-item avatar dropdown">
                        <a
                                class="nav-link dropdown-toggle"
                                id="navbarDropdownMenuLink-55"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false"
                        >
                            <img
                                    style="width: 40px;"
                                    src="https://mdbootstrap.com/img/Photos/Avatars/avatar-2.jpg"
                                    class="rounded-circle z-depth-0"
                                    alt="avatar image"
                            />
                        </a>
                        <div
                                class="dropdown-menu dropdown-menu-lg-right dropdown-secondary"
                                aria-labelledby="navbarDropdownMenuLink-555"
                        >
                            <a class="dropdown-item" href="#">Log Out</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <!--/.Navbar -->
    </div>
</div>
<div>