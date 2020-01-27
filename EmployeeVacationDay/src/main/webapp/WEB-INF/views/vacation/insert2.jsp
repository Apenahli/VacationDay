<%@taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<spr:form action="${pageContext.request.contextPath}/insert33"
	method="post" modelAttribute="vacation">


	<div class="modal-header">
		<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<div class="modal-body">

		<spr:select path="employee.id" class="form-control">

			<c:forEach var="employee" items="${employees}">

				<spr:option value="${employee.id}">
					<c:out
						value="${employee.name} ${employee.surname}  ${employee.fatherName}" />
					<%-- 	<input type="hidden" id="empstartTime" name="empstartTime" value="${employee.startTime}"> --%>
				</spr:option>

			</c:forEach>
		</spr:select>




		<br>


		<spr:select path="basic_vacation_day" id="basic_vacation_day"
			class="form-control">

			<spr:option value="21">21</spr:option>
			<spr:option value="30">30</spr:option>

		</spr:select>

		<br>

		<spr:select path="sosial_vacation_day" id="sosial_vacation_day"
			class="form-control">
			<spr:option value="0">0</spr:option>
			<spr:option value="2">2</spr:option>
			<spr:option value="5">5</spr:option>
		</spr:select>

		<br>

${year}
 <spr:hidden path="year"  value="${year}" /> 

<%-- 		<spr:select path="year" id="year" class="form-control">
			<spr:option value="2019-2020">2019-2020</spr:option>
			<spr:option value="2020-2021">2020-2021</spr:option>
			<option value="2021-2022">2021-2022</option>
			<option value="2022-2023">2022-2023</option>
		</spr:select> --%>


	</div>
	<div class="modal-footer">

		<button type="submit" class="btn btn-primary">save</button>

		<button type="button" class="btn btn-secondary" data-dismiss="modal">
			cancel</button>
	</div>
</spr:form>

