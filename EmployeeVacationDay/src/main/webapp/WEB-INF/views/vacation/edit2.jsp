<%@taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<spr:form action="${pageContext.request.contextPath}/save2"
	method="post" modelAttribute="vacationMonths">


	<div class="modal-header">
		<!-- 	<h5 class="modal-title" id="exampleModalLabel">Modal title</h5> -->
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>

		<c:if test="${not empty msg}">
			<p style="color: red;">${msg}</p>
		</c:if>
	</div>
	<div class="modal-body">


		<p>Name/Surname/FatherName : ${employee.name} ${employee.surname}
			${employee.fatherName}</p>
		<p>Iscinin total mezuniyyeti : ${canculV.total} gun</p>
		<p>Istifade etdiyi mezuniyyetler :</p>


		<c:forEach var="vacation" items="${vacationMonth}">
			<p>Tarix : ${vacation.startTime} / ${vacation.endTime}</p>
		</c:forEach>

		<p>Qaliq mezuniyyet gunu : ${canculV.final_remainder}</p>

		<spr:input type="date" path="startTime" id="fname"
			class="form-control" required="required" style="width:160px;" />

		<br>
		<spr:input type="date" path="endTime" id="fname" class="form-control"
			required="required" style="width:160px;" />
		<br>


		<spr:input type="hidden" path="vacation" class="form-control"
			placeholder="id" value="${id}" />

		<input type="hidden" id="year" name="year" value="${year}">




	</div>
	<div class="modal-footer">

		<button type="submit" class="btn btn-primary">save</button>

		<button type="button" class="btn btn-secondary" data-dismiss="modal">
			cancel</button>
	</div>
</spr:form>
