<%--
  Created by IntelliJ IDEA.
  User: radek
  Date: 26/02/2021
  Time: 13:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>
<%@ include file="/app/dashboard-side-menu.jsp" %>

<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">SZCZEGÓŁY PLANU</h3>
            </div>
            <div class="col d-flex justify-content-end mb-2 noPadding">
                <a href="#" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4">Powrót</a>
            </div>
        </div>

        <div class="schedules-content">
            <div class="schedules-content-header">
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Nazwa planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">${planObj.name}</p>
                    </div>
                </div>
                <div class="form-group row">
                                <span class="col-sm-2 label-size col-form-label">
                                    Opis planu
                                </span>
                    <div class="col-sm-10">
                        <p class="schedules-text">
                            ${planObj.description}
                        </p>
                    </div>
                </div>
            </div>

            <table class="table">
                <c:set var="day" value=""/>
                <c:forEach items="${detailsList}" var="detailedPlan">
                    <c:if test="${day != detailedPlan.dayName}">
                        <thead>
                        <tr class="d-flex">

                            <th class="col-2">${day = detailedPlan.dayName}</th>
                            <th class="col-7"></th>
                            <th class="col-1"></th>
                            <th class="col-2"></th>
                        </tr>
                        </thead>
                    </c:if>
                    <tbody class="text-color-lighter">
                    <tr class="d-flex">
                        <td class="col-2">${detailedPlan.mealName}</td>
                        <td class="col-7">${detailedPlan.recipeName}</td>
                        <td class="col-1 center">
                            <a href='<c:url value="deletemeal?mealId=${detailedPlan.id}&planId=${detailedPlan.planId}"/>'
                               class="btn btn-danger rounded-0 text-light m-1">Usuń</a>
                        </td>
                        <td class="col-2 center">
                            <a href='<c:url value="/app/recipe/details?name=${detailedPlan.recipeName}"/>'
                               class="btn btn-info rounded-0 text-light m-1">Szczegóły</a>
                        </td>
                    </tr>
                    </tbody>
                </c:forEach>
            </table>


        </div>
    </div>
</div>
</div>
</section>


<%@ include file="/footer.jsp" %>
