<%@taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<spr:form action="${pageContext.request.contextPath}/insert"
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
		
		
		<p> 2018-2019 qaliq</p> 
		<spr:input path="remainder" type="text" id="remainder" name="remainder" class="form-control"/><br>
		<spr:select path="basic_vacation_day" id="basic_vacation_day" class="form-control" >
            <spr:option value="21">21</spr:option>
             <spr:option value="30">30</spr:option>
        </spr:select><br>	
		
		<spr:select path="sosial_vacation_day" id="sosial_vacation_day" class="form-control" >
            <spr:option value="0">0</spr:option>
             <spr:option value="2">2</spr:option>
               <spr:option value="5">5</spr:option>
        </spr:select><br>




	</div>
	<div class="modal-footer">

		<button type="submit" class="btn btn-primary">save</button>

		<button type="button" class="btn btn-secondary" data-dismiss="modal">
			cancel</button>
	</div>
</spr:form>

