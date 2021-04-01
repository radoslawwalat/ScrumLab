<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>
<%@ include file="/app/dashboard-side-menu.jsp" %>
<div class="m-4 p-4 width-medium">
    <div class="dashboard-header m-4">
        <div class="dashboard-menu">
            <div class="menu-item border-dashed">
                <a href='<c:url value="recipe/add"/>'>
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href='<c:url value="plan/add"/>'>
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj plan</span>
                </a>
            </div>
            <div class="menu-item border-dashed">
                <a href='<c:url value="recipe/plan/add"/>'>
                    <i class="far fa-plus-square icon-plus-square"></i>
                    <span class="title">dodaj przepis do planu</span>
                </a>
            </div>
        </div>

        <div class="dashboard-alerts">
            <div class="alert-item alert-info">
                <i class="fas icon-circle fa-info-circle"></i>
                <span class="font-weight-bold">Liczba przepisów: <c:out value="${uRecipeCount}" default="0"/> </span>
            </div>
            <div class="alert-item alert-light">
                <i class="far icon-calendar fa-calendar-alt"></i>
                <span class="font-weight-bold">Liczba planów: <c:out value="${uPlanCount}" default="0"/></span>
            </div>
        </div>
    </div>
    <div class="m-4 p-4 border-dashed">
        <h2 class="dashboard-content-title">
            <span>Ostatnio dodany plan:</span> <c:out value="${uPlanLast[0].planName}"
                                                      default="Nie dodano żadnego planu"/>
        </h2>

        <table class="table">
            <c:set var="day" value=""/>
            <c:forEach items="${uPlanLast}" var="plan" >
                    <c:if test="${day != plan.dayName}">
<%--                        <p style="display:none"></p>--%>
                <thead>
                <tr class="d-flex">
                    <th class="col-2">${day = plan.dayName}</th>
                    <th class="col-8"></th>
                    <th class="col-2"></th>
                </tr>
                </thead>
                    </c:if>
                <tbody>
                <tr class="d-flex">
                    <td class="col-2">${plan.mealName}</td>
                    <td class="col-8">${plan.recipeName}</td>
                    <td class="col-2">
                        <a type="button" class="btn btn-primary rounded-0" href='<c:url value="recipe/details?name=${plan.recipeName}"/>'>Szczegóły</a>
                    </td>
                </tr>
                </tbody>

            </c:forEach>
        </table>
    </div>
</div>
</div>
</section>
<%@ include file="/footer.jsp" %>

