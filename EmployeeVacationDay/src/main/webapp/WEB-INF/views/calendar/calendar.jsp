<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">


  <link rel="stylesheet" href="http://cdn.jsdelivr.net/webjars/fullcalendar/2.2.5/fullcalendar.css" />
 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment.min.js"></script>
 
  <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.5/fullcalendar.min.js"></script>
  
  <link rel="stylesheet" href="https://code.jquery.com/ui/jquery-ui-git.css" />
	
  <script src="https://code.jquery.com/ui/jquery-ui-git.js"></script> 
   
  <script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.5/core/ru.js"></script> 
  
  <script src="//cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.2.7/lang-all.js"></script>

  
    <script>
  
    
    $(function() {
        var dialog, form,
     
         	tips = $( ".validateTips" ); 
    		
    		
      

        
        
        function validateDateRange(start, end) {

        	if(!start.isValid()) {
        		alert("Start date is invalid");
        		return false; 
        	}
        	
        	if(!end.isValid()) {
        		alert("End date is invalid");
        		return false; 
        	}
        	
        	if(start.isAfter(end)) {
    	 		alert("End date must be after start date");	
    	 		return false; 
    	 	}
        	
        	if(start.isSame(end)) {
        		alert("End date must be after start date");
        		return false;
        	}
        	return true;
        }   
        
        
        
        
        
       	function removeEvent() {  
       		
       		var eventStart = moment(modstartdateandtime.value);
     		var eventEnd = moment(modenddateandtime.value);
       		
        	var eventData;
        	
    		if (moduid.value) {
    			eventData = {
    				id: moduid.value,
    				title: modtitle.value,
    				start: eventStart,
    				finish: eventEnd, 
    				description: moddescription.value
    			};
    			
        		editDialog.dialog( "close" );
        	
    	    	$.ajax({
    			    type: "DELETE",
    			    url: "/event",
    			    data: JSON.stringify(eventData),
    			    contentType: "application/json; charset=utf-8",
    			    dataType: "json",
    			    success: function(data){
    			    	$('#calendar').fullCalendar( 'refetchEvents' );
    			    },
    			    failure: function(errMsg) {
    			        alert(errMsg);
    			    }
    			});
    		}
    	    return true;
        }
    	
       	
       	
       	
       	
       	
       	
       	
       	
        function addEvent(start, end) {
          	var valid = true;
     			
     			var eventStart = moment(startdateandtime.value);
     			var eventEnd = moment(enddateandtime.value);
       
    		valid = valid && newtitle.value;
    	 	valid = valid && startdateandtime.value;
    	 	valid = valid && validateDateRange(eventStart, eventEnd);
    	 	    
    		if ( valid ) {
    	    	var eventData;
    			if (newtitle.value) {
    				eventData = {
    					title: newtitle.value,
    					description: description.value,
    					start: eventStart,
    					finish: eventEnd, 
    				};
    				//$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
    			}
    	    	
    	       
    	            
    			
    			$('#calendar').fullCalendar('unselect');
    			dialog.dialog( "close" );
    			
    			 $.ajax({
    			    type: "POST",
    			    url: "/event",
    			    data: JSON.stringify(eventData),
    			    contentType: "application/json; charset=utf-8",
    			    dataType: "json",
    			    success: function(data){
    			    	$('#calendar').fullCalendar('renderEvent', data, true); // stick? = true
    			    },
    			    failure: function(errMsg) {
    			        alert(errMsg);
    			    }
    			});
          }
          return valid;
        }  
        
       	
       	
        function editEvent(event, elements) {
        	var eventStart = moment(event.start).format("YYYY-MM-DDTHH:mm:ss"); //moment(event.start);
     			var eventEnd = moment(event.finish).format("YYYY-MM-DDTHH:mm:ss");
     			
     	//	alert (eventStart + "   " + eventEnd + "   " + event.finish);
         
     		modtitle.value = event.title;
    	    moddescription.value = event.description; 
    	    modstartdateandtime.value = eventStart;			
    	    modenddateandtime.value = eventEnd;
    	    moduid.value = event.id;
    	    editDialog.dialog( "open" );
        }	
         	
       	
        
    	
        function saveEvent() {
        	var valid = true;	
    	 	var eventStart = moment(modstartdateandtime.value);
     		var eventEnd = moment(modenddateandtime.value);
       
     		valid = valid && modtitle.value;
    	 	valid = valid && startdateandtime.value;
    	 	valid = valid && validateDateRange(eventStart, eventEnd);
   		
    		if ( valid ) {
    	    	var eventData;
    			if (modtitle.value) {
    				eventData = {
    					id: moduid.value,
    					title: modtitle.value,
    					start: eventStart,
    					finish:  eventEnd, 
    					description: moddescription.value
    				};
    		//		alert(eventData.title.value + " " + eventData.start.value + " " + eventData.finish.value)
    		//		$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
    			}    
        
    			$('#calendar').fullCalendar('unselect');
    	    	
    	    	editDialog.dialog( "close" );
    	    	
    	    	$.ajax({
    			    type: "PATCH",
    			    url: "/event",
    			    data: JSON.stringify(eventData),
    			    contentType: "application/json; charset=utf-8",
    			    dataType: "json",
    			    success: function(data){
    			    	$('#calendar').fullCalendar( 'refetchEvents' );
    			    },
    			    failure: function(errMsg) {
    			        alert(errMsg);
    			    }
    			});
        	}
    	 	return valid;
        }
        
        
        
        
        
       	
       	
       	
       	
        dialog = $( "#dialog-form" ).dialog({
            autoOpen: false,
            height: 350,
            width: 350,
            modal: true,
            buttons: {
             "Create": addEvent,
              Cancel: function() {
                dialog.dialog( "close" );
              }
            },
            close: function() {
              form[ 0 ].reset();
            }
          });
       	
       	
       	
       	
       	
       	
        editDialog = $( "#edit-dialog-form" ).dialog({
  	      autoOpen: false,
  	      height: 350,
  	      width: 350,
  	      modal: true,
  	      buttons: {
  	       
  	        Delete: removeEvent,
  	        Cancel: function() {
  	          editDialog.dialog( "close" );
  	        }
  	      },
  	      close: function() {
  	        form[ 0 ].reset();
  	      }
  	    });
        
        
        
        
        
        form = dialog.find( "form" ).on( "submit", function( event ) {
            event.preventDefault();  
            addEvent();
           
          }); 
        
        
        
        
        
        
        
        $( "#create-event" ).button().on( "click", function() {
          dialog.dialog( "open" );
          
        });  
        
        
        
        
        
        
       	
       	
       	
       	
    	
        $(document).ready(function() {
        	
        	$('#calendar').fullCalendar({
              
    			header: {
    				left: 'prev,next today',
    				center: 'title',
    				right: 'month,agendaWeek,agendaDay'
    			},
    			 
    			defaultDate: moment().format("YYYY-MM-DD"),
    			editable: true,
    			eventLimit: true, // allow "more" link when too many events
    			events: {
    		        url: '/allevents',
    		        type: 'GET',
    		        error: function() {
    		            alert('there was an error while fetching events!');
    		        },
    		        //color: 'blue',   // a non-ajax option
    		        //textColor: 'white' // a non-ajax option
    		    },
    			selectable: true,
    			selectHelper: true,
    			select: function(start, end) {
    				startdateandtime.value = moment(start).format("YYYY-MM-DDTHH:mm:ss");
    				enddateandtime.value = moment(end).format("YYYY-MM-DDTHH:mm:ss");
    				dialog.dialog( "open" );
    			},
    			eventClick: function(event, element) {
    				editEvent(event, element);
    				
    		       //$('#calendar').fullCalendar('updateEvent', event);
    		    },
    		    eventMouseover: function(event, jsEvent, view) {

    		   	},
    		    eventMouseout: function(event, jsEvent, view) {
    		    },
    			loading: function(bool) {
    				$('#loading').toggle(bool);
    			}
    		});
    	});
    	  	
       	
    
    	
 
    	
    	
    	
    }); 
    
    
  </script>
 
 
<style>








.fc-event-time{
   display : none;
}

.fc-time{
   display : none;
}

	body {
		margin: 0;
		padding: 0;
		font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
		font-size: 14px;
	}

	#script-warning {
		display: none;
		background: #eee;
		border-bottom: 1px solid #ddd;
		padding: 0 10px;
		line-height: 40px;
		text-align: center;
		font-weight: bold;
		font-size: 12px;
		color: red;
	}

	#loading {
		display: none;
		position: absolute;
		top: 10px;
		right: 10px;
	}

	#calendar {
		max-width: 900px;
		margin: 40px auto;
		padding: 0 10px;
	}

</style>

<style>

	#header {
	    background-color:white;
	    color:black;
	    text-align:center;
	    padding:5px;
	}
	#nav {
	    line-height:30px;
	    background-color:#eeeeee;
	    height:300px;
	    width:100px;
	    float:left;
	    padding:5px;	      
	}
	#section {
	    width:350px;
	    float:left;
	    padding:10px;	 	 
	}
	#footer {
	    background-color:black;
	    color:white;
	    clear:both;
	    text-align:center;
	   padding:5px;	 	 
	}
	
</style>

<!--   <link rel="stylesheet" href="modalstyle.css"/> -->
  <style>
    body { font-size: 87.5%; }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:100%; padding: .4em; }
    
    description { margin-bottom:12px; width:100%; padding: .4em; }
    
    fieldset { padding:0; border:0; margin-top:25px; height: 200px;}
    h1 { font-size: 4.2em; margin: .6em 0; }
    div#events-contain { width: 350px; margin: 20px 0; }
    div#events-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#events-contain table td, div#events-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
  </style>
  
</head>




<body>

	<div id="header">
	<!-- 	<h1>Calendar</h1> -->
	</div>

	<div id='script-warning'>
	</div>

	<div id='loading'>loading...</div>
	
	<div class="calendar">
	<div id="calendar"></div>
	</div>
	
	

	<div id="dialog-form" title="Create new Event">
	  <form id="event-form">
	    <fieldset>
	      <label for="title">title</label>
	      <input type="text" name="newtitle" id="newtitle" value="New Event" class="text ui-widget-content ui-corner-all"/>
	      
	      
	      <input type="hidden" name="description" id="description" value="" class="text ui-widget-content ui-corner-all "/>
	      
	  	  
	      <label for="startdateandtime">starts:</label>
	      <input type="datetime-local" id="startdateandtime"/>
	     
	      <input type="hidden" id="enddateandtime"/>
	      <input type="hidden" name="uid" id="uid"/>
	      
	      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"/> 
	    </fieldset>
	  </form>
	</div>




	<div id="edit-dialog-form" title="Modify an Event">
	  <form id="edit-event-form">
	    <fieldset>
	     
	      <label for="title">title</label>
	      <input type="text" name="modtitle" id="modtitle" value="New Event" class="text ui-widget-content ui-corner-all"/>
	      
	     
	      <input type="hidden" name="moddescription" id="moddescription" value="" class="text ui-widget-content ui-corner-all "/>
	      
	      <input type="datetime-local" id="modstartdateandtime"/>
	    
	      <input type="hidden" id="modenddateandtime"/>   
	      <input type="hidden" name="moduid" id="moduid"/>
	      
	      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px"/> 
	    </fieldset>
	    
	  </form>
	</div>
	
	
	
	
	
</body>
</html>
