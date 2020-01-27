

function x(message) {

	$.ajax({

		type : "get",

		url : "/tx/"+message,

		dataType : "html",

		success : function(response) {

			$('.modal-content').html(response);

			$('#exampleModal').modal('show');

			console.log('test')
		}
	})
}

function editttt(id){
	
	$.ajax({
		  
		type:"get",
		
		url:"/insert22/" + id,
		
		dataType:"html",
		
		success:function(response){
			
			$('.modal-content').html(response);
			
			$('#exampleModal').modal('show');
			
			/*console.log('test')*/
		}
	})
}





