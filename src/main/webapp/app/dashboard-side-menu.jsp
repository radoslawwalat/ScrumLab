<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<section class="dashboard-section">
    <div class="row dashboard-nowrap">
        <ul class="nav flex-column long-bg">
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/app/dashboard"/>'>
                        <span>Pulpit</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/app/recipe/list"/>'>
                    <span>Przepisy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/app/plan/list"/>'>
                    <span>Plany</span>
                    <i class="fas fa-angle-right"></i>
                </a>

            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/app/app-edit-user-data"/>'>
                    <span>Edytuj dane</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href='<c:url value="/app/pp-edit-password"/>'>
                    <span>Zmień hasło</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href='<c:url value="/app/super-admin-users"/>'>
                    <span>Użytkownicy</span>
                    <i class="fas fa-angle-right"></i>
                </a>
            </li>
        </ul>