function confirmmm() {

	$.ajax({

		type : "get",

		url : "/confirm",

		dataType : "html",

		success : function(response) {

			$('.modal-content').html(response);

			$('#exampleModal').modal('show');

			console.log('test')
		}
	})
}