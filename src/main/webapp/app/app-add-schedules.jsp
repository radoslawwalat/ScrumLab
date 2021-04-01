<%--
  Created by IntelliJ IDEA.
  User: radek
  Date: 26/02/2021
  Time: 11:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/header.jsp"%>
<%@ include file="/app/dashboard-side-menu.jsp"%>

<div class="m-4 p-3 width-medium">
    <div class="dashboard-content border-dashed p-3 m-4 view-height">
        <!-- fix action, method -->
        <!-- add name attribute for all inputs -->
        <form method="post" action="add">
            <div class="row border-bottom border-3 p-1 m-1">
                <div class="col noPadding">
                    <h3 class="color-header text-uppercase">NOWY PLAN</h3>
                </div>
                <div class="col d-flex justify-content-end mb-2 noPadding">
                    <button type="submit" class="btn btn-color rounded-0 pt-0 pb-0 pr-4 pl-4">Zapisz</button>
                </div>
            </div>

            <div class="schedules-content">

                <div class="form-group row">
                    <label for="planName" class="col-sm-2 label-size col-form-label">
                        Nazwa planu
                    </label>
                    <div class="col-sm-10">
                        <input class="form-control" id="planName" name="planName" placeholder="Nazwa planu">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="planDescription" class="col-sm-2 label-size col-form-label">
                        Opis planu
                    </label>
                    <div class="col-sm-10">
                                <textarea class="form-control" rows="5" id="planDescription" name="planDescription"
                                          placeholder="Opis plany"></textarea>
                    </div>
                </div>

            </div>
        </form>
    </div>
</div>
</div>
</section>



<%@ include file="/footer.jsp"%>
