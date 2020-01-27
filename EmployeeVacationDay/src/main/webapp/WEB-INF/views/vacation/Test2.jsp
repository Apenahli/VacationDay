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



<div style="margin: 0.2%;">


    <h1> This is test111</h1>
    <h1> This is test222</h1>
    <h1> This is test333</h1>
    <br>

</div>

<!-- ---------  -->
<!-- Modal update-->
<!-- ---------  -->

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
     aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content"></div>
    </div>
</div>


<script src="<c:url value="/resources/static/js/modal.js"/>"></script>

<script src="<c:url value="/resources/static/js/jquery-3.3.1.js"/>"></script>

<script src="<c:url value="/resources/static/js/bootstrap.min.js"/>"></script>
<%--
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script> --%>

<script
        src="<c:url value="/resources/static/js/jquery.dataTables.min.js"/>"></script>

<script>
    $(document).ready(function() {
        $('#customers').DataTable({
            "scrollX" : true,
            "bInfo" : false,
            "bAutoWidth" : false,
            "bLengthChange" : false,
            "pageLength" : 10,
            "ordering" : true,
            "bPaginate" : true,
            "bFilter" : true

        });

    });
</script>

</body>
</html>