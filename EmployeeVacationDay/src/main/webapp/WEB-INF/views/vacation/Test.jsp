<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Insert title here</title>
	<link
			href="<c:url value="/resources/static/css/home/button_style.css"/>"
			rel="stylesheet">

	<link
			href="<c:url value="/resources/static/css/home/jquery.dataTables.min.css"/>"
			rel="stylesheet">

	<link href="<c:url value="/resources/static/css/home/table_style.css"/>"
		  rel="stylesheet">

	<!-- for bithday bootstrap -->

	<link rel="stylesheet"
		  href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">


</head>
<style>
	table.dataTable thead th, table.dataTable thead th {
		padding-left: 3px 3px;
		border: 0px solid #FFFFF;
		margin-left: 2px;
	}

	table.dataTable {
		scrollX: % 100;
	}

	table.dataTable tbody th, table.dataTable tbody td {
		white-space: nowrap;
		scrollX: % 100;
	}

	table.dataTable thead th, table.dataTable thead td {
		padding: 10px 18px;
		border-bottom: 1px solid #FFFF;
	}

	.table>thead>tr>th {
		vertical-align: bottom;
		border-bottom: 0px solid #FFFFF;
	}

	.dataTables_wrapper.no-footer .dataTables_scrollBody {
		border: 0px solid black;
		margin-left: 2px;
		margin-right: 20px;
	}

	th {
		border-top: 1px solid #dddddd;
		border-bottom: 1px solid #dddddd;
		border-right: 1px solid #dddddd;
		border-left: 5px;
	}
</style>
<body>
 






<table style="margin-top:10px;"><tr>
<td>
<select class="form-control" style="width:220px; margin-bottom:8px; margin-left:20px; height:37px;"  id="interface_lang">
  <option value="az" disabled selected>choose</option>  
  <option value="2019-2020" >2019-2020</option>
  <option value="2020-2021">2020-2021</option>
  <option value="2021-2022">2021-2022</option>
  <option value="2022-2023" >2022-2023</option>
</select>
</td>
<td>

 
<a href="javascript:x('${vacation.id}')" id="langTagA"	class="btn btn-primary color" 

style="background-color: #F2F2F2F2;color:black;margin-left:10px; margin-top: -7px;">
search</a>
</td>
</tr>

</table>


 

<div style="margin: 0.2%;">
	<table id="customers" class=" nowrap "
		   style="width: 100%; margin-left: -2.5px;">
		<thead>
		<tr>
			<th
					style="font-size: 14px; text-align: center; background-color: white;">
				Ad soyad ata adi</th>

			<th style="font-size: 14px; text-align: center;">
				Department/şöbə</th>

			<th style="font-size: 14px; text-align: center;">Vəzifə</th>

			<th style="font-size: 14px; text-align: center;">İşə başlama
				tarixi</th>

			<th style="font-size: 14px; text-align: center;">2018 -
				2019-cı il <br> üzrə qalıq</th>

			<th style="font-size: 14px; text-align: center;">ƏƏMG <br>2019-2020 <!-- Əsas əmək
				məzuniyyət <br> günləri  2019 - 2020 -->
			</th>

			<th style="font-size: 14px; text-align: center;">
			SMG
		
			</th>

			<th style="font-size: 14px; text-align: center;">Əmək stajına
				görə <br>əlavə məzuniyyət</th>

			<th style="font-size: 14px; text-align: center;">Total</th>

			<th>2019 - 2020-ci il<br> üzrə qalıq</th>

			<th> mezuniyyet<br>elave et</th>
			
			<th>action</th>


		</tr>
		</thead>
		<tbody>
		 <c:forEach var="vacation" items="${vacations}">
	
		<tr>
			<td>${vacation.employee.name} ${vacation.employee.surname} ${vacation.employee.fatherName}</td>
			<td>Elmi araşdırmalar və inkişaf (R&D) departementi</td>
			<td>Kiçik proqramçı</td>
			
			<td>${vacation.employee.startTime}</td>
			
			<td>${vacation.remainder}</td>
			<td>${vacation.basic_vacation_day}</td>
			<td>${vacation.sosial_vacation_day}</td>
		
			<td>${vacation.stajVacationDay}</td>
			<td>${vacation.total}</td>
			<td>${vacation.final_remainder}</td>
			<td>
			<a href="javascript:editttt('${vacation.id}')"> +</a> 
			</td>
			<td>edit</td>
		</tr>
		</c:forEach> 
		
			
		</tbody>
	</table>
	
	
		<a href="javascript:inserttt()">
		<button class="button button5">
			insert
		</button>
	</a>

	<br>
		<br>

<a href="javascript:confirmmm()" class="btn btn-primary color" 
style="background-color: #F2F2F2F2;color:black;margin-left:1260px; margin-top: -7px;">
tesdiq et</a>


<h3><a href="${pageContext.request.contextPath}/calendar">calendar</a></h3>



</div>

<!-- ---------  -->
<!-- Modal update-->
<!-- ---------  -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" 
aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">    
    </div>
  </div>
</div>


<script src="<c:url value="/resources/static/js/mod.js"/>"></script>

<script src="<c:url value="/resources/static/js/project.js"/>"></script>

<script src="<c:url value="/resources/static/js/jquery-3.3.1.js"/>"></script>

<script src="<c:url value="/resources/static/js/bootstrap.min.js"/>"></script>

<script
		src="<c:url value="/resources/static/js/jquery.dataTables.min.js"/>"></script>

<script>
	$(document).ready(function() {
		$('#customers').DataTable({
			"scrollX" : true,
			"bInfo" : false,
			"bAutoWidth" : false,
			"bLengthChange" : false,
			"pageLength" : 18,
			"ordering" : true,
			"bPaginate" : true,
			"bFilter" : true

		});

	});
</script>

<script>

$("#interface_lang").change(function(){
	$("#langTagA").attr("href","${pageContext.request.contextPath}/"+$("#interface_lang").val());
});


</script>

</body>

</html>

