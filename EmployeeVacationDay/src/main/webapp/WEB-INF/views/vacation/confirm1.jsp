<%@taglib uri="http://www.springframework.org/tags/form" prefix="spr"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<spr:form action="${pageContext.request.contextPath}/insertConfirm"
	method="post" modelAttribute="vacation">


	<div class="modal-header">
		<h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
		<button type="button" class="close" data-dismiss="modal"
			aria-label="Close">
			<span aria-hidden="true">&times;</span>
		</button>
	</div>
	<div class="modal-body">


		<h1>Test1</h1>




	</div>
	<div class="modal-footer">

		<button type="submit" class="btn btn-primary">beli</button>

		<button type="button" class="btn btn-secondary" data-dismiss="modal">
			imtina et</button>
	</div>
</spr:form>

