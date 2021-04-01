<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp" %>
<%@ include file="/app/dashboard-side-menu.jsp" %>

<div class="m-4 p-3 width-medium ">
    <div class="dashboard-content border-dashed p-3 m-4">
        <div class="row border-bottom border-3 p-1 m-1">
            <div class="col noPadding">
                <h3 class="color-header text-uppercase">czy na pewno chcesz usunąć przepis z planu?</h3>
            </div>
        </div>


        <table class="table">
            <thead>
            <tr class="d-flex">

                <th class="col-2">
                    <a href='<c:url value="delete?name=${recipeName}"/>'
                       class="btn btn-danger rounded-0 text-light m-1">OK</a>
                </th>
                <th class="col-7"></th>
                <th class="col-1"></th>
                <th class="col-2"></th>
            </tr>
            </thead>
            <thead>
            <tr class="d-flex">

                <th class="col-2">
                    <a href='<c:url value="details?id=${planId}"/>'
                       class="btn btn-success rounded-0 text-light m-1">Anuluj</a>
                </th>
                <th class="col-7"></th>
                <th class="col-1"></th>
                <th class="col-2"></th>
            </tr>
            </thead>
            </tbody>
        </table>


    </div>
</div>
</div>
</div>
</section>

<%@ include file="/footer.jsp" %>
