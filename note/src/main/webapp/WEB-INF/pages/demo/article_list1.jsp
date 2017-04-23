<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="/js/dataTables/jquery.dataTables.css"> -->
	
	<link href="http://cdn.bootcss.com/twitter-bootstrap/2.3.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://www.zhuhaihengxin.com/libs/datatables/1.10.0/css/jquery.dataTables.css" rel="stylesheet">
<head>
	<title></title>
</head>
<body>
	
	<h2>List Article</h2>
	<table id="table_id" class="display" cellspacing="0" width="100%">
	    <thead>
	        <tr>
	            <th>Column 1</th>
	            <th>Column 2</th>
	            <th>Column 3</th>
	            <th>Column 3</th>
	            <th>Operation</th>
	        </tr>
	    </thead>
	    <tbody>
	    </tbody>
	</table>
	
	<script src="http://www.zhuhaihengxin.com/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="http://www.zhuhaihengxin.com/libs/bootstrap/3.0.3/js/bootstrap.min.js"></script>
	<script type="text/javascript" charset="utf8" src="http://www.zhuhaihengxin.com/libs/datatables/1.10.4/js/jquery.dataTables.min.js"></script>
	


    <script type="text/javascript">
    $(document).ready( function () {
        $('#table_id').DataTable({
            "paging": true,
            "pageLength": 10,
            "searching": true,
            "ordering": true,
            "processing": true,
            "serverSide": true,
            "info":false,
            "ajax" : "article_list_dt?date="+new Date().getTime(),
            "columns": [
              {"data": "id"},
              {"data": "created"},
              {"data": "summary"}
            ],
            "columnDefs": [
              {
                "targets": [3],
                "data": "id",
                "render": function(data, type, full) {
                  return "<a href='edit?id=" + data + "'>Edit</a>" + " <a href='delete?id=" + data + "'>Delete</a>" + " <a href='view?id=" + data + "'>View</a>";
                }
              }
            ] 
          });
    } );
    </script>
</body>
</html>