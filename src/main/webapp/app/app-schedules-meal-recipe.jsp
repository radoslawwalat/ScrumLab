<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp"%>
<%@ include file="/app/dashboard-side-menu.jsp"%>

<div class="m-4 p-3 width-medium">
    <form method="post" action="${pageContext.request.contextPath}/app/recipe/plan/add">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">

        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">DODAJ PRZEPIS DO PLANU</h3>
            </div>

            <div class="col d-flex justify-content-end mb-2 noPadding">
                <input type="submit" class="btn btn-success rounded-0 pt-0 pb-0 pr-4 pl-4" value="Zapisz">
            </div>
        </div>

        <div class="schedules-content">

                <div class="form-group row">
                    <label for="choosePlan" class="col-sm-2 label-size col-form-label">
                        Wybierz plan
                    </label>
                    <div class="col-sm-3">
                        <select name="plan" class="form-control" id="choosePlan">
                            <c:if test="${not empty uPlans}">
                                <c:forEach items="${uPlans}" var="plans">
                            <option value="${plans.id}">${plans.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="name" class="col-sm-2 label-size col-form-label">
                        Nazwa posiłku
                    </label>
                    <div class="col-sm-10">
                        <input name="mealname" type="text" class="form-control" value="" id="name" placeholder="Nazwa posiłku">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="number" class="col-sm-2 label-size col-form-label">
                        Numer posiłku
                    </label>
                    <div class="col-sm-2">
                        <input name="mealorder" type="text" class="form-control" value="" id="number" placeholder="Numer posiłki">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="recipie" class="col-sm-2 label-size col-form-label">
                        Przepis
                    </label>
                    <div class="col-sm-4">
                        <select name="recipe" class="form-control" id="recipie">
                            <c:if test="${not empty uRecipes}">
                                <c:forEach items="${uRecipes}" var="recipes">
                                    <option value="${recipes.id}">${recipes.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="day" class="col-sm-2 label-size col-form-label">
                        Dzień
                    </label>
                    <div class="col-sm-2">
                        <select name="day" class="form-control" id="day">
                            <c:if test="${not empty dayName}">
                                <jsp:useBean id="dayName" scope="request" type="java.util.List"/>
                                <c:forEach items="${dayName}" var="day">
                                    <option value="${day.id}">${day.name}</option>
                                </c:forEach>
                            </c:if>
                        </select>
                    </div>
                </div>

        </div>
    </div>
    </form>
</div>
</div>
</section>

<%@ include file="/footer.jsp"%>