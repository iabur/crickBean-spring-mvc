<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>Dashboard - Brand</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/admin/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fonts/fontawesome-all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/-Login-form-Page-BS4-.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Pretty-Registration-Form.css">
</head>

<body id="page-top">
<div id="wrapper">
    <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
        <div class="container-fluid d-flex flex-column p-0">
            <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="${pageContext.request.contextPath}/">
                <div class="sidebar-brand-icon rotate-n-15"><i class="fas fa-laugh-wink"></i></div>
                <div class="sidebar-brand-text mx-3"><span>CrickBean</span></div>
            </a>
            <hr class="sidebar-divider my-0">
            <ul class="nav navbar-nav text-light" id="accordionSidebar">
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_ICC_EMPLOYEE','ROLE_COUNTRY_MANAGER','ROLE_TEAM_MANAGER','ROLE_PLAYER','ROLE_STUFF')">
                <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/users"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_ICC_EMPLOYEE','ROLE_COUNTRY_MANAGER')">
                <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/country/add"><span>Countries</span></a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_COUNTRY_MANAGER','ROLE_TEAM_MANAGER')">
                <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/team/add"><span>Teams</span></a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_TEAM_MANAGER','ROLE_PLAYER','ROLE_STUFF')">
                <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/member/add"><span>Team Members</span></a></li>
                </sec:authorize>
                <sec:authorize access="hasAnyAuthority('ROLE_ADMIN','ROLE_ICC_EMPLOYEE','ROLE_COUNTRY_MANAGER','ROLE_TEAM_MANAGER','ROLE_PLAYER','ROLE_STUFF')">
                <li class="nav-item" role="presentation"><a class="nav-link" href="${pageContext.request.contextPath}/users"><span>Users</span></a></li>
                </sec:authorize>
                <li class="nav-item" role="presentation"></li>
            </ul>
            <div class="text-center d-none d-md-inline"><button class="btn rounded-circle border-0" id="sidebarToggle" type="button"></button></div>
        </div>
    </nav>