function editttt(id){
	
	$.ajax({
		  
		type:"get",
		
		url:"/insert2/" + id,
		
		dataType:"html",
		
		success:function(response){
			
			$('.modal-content').html(response);
			
			$('#exampleModal').modal('show');
			
			/*console.log('test')*/
		}
	})
}

function inserttt() {

	$.ajax({

		type : "get",

		url : "/test",

		dataType : "html",

		success : function(response) {

			$('.modal-content').html(response);

			$('#exampleModal').modal('show');

			console.log('test')
		}
	})
}

