
var InUpmodal = document.getElementById("InUpmyModal");
var modalcc = document.getElementById("myModalCastCrew");
var modal2cc = document.getElementById("modal2CastCrew");
var modelTitle = document.getElementById("msgCastCrew");
var btn = document.getElementById("addType");
function addNewCC() 
{
	InUpmodal.style.display = "block";
	$(".wrapper").addClass("blur");  
	
	var currentContentHeight = $('.InUpmodal-contentScreen').outerHeight(); 
	$(".InUpmodal-contentScreen").css("margin-top",-(currentContentHeight/2));
}
 function closePopupInup(page)
 {
 	InUpmodal.style.display = "none";	
 	$(".wrapper").removeClass("blur"); 
 	window.location.replace(page);
 }
var modalcc = document.getElementById("myModalCastCrew");
var modal2cc = document.getElementById("modal2CastCrew");

function addCastCrew(){
 	modalcc.style.display = "block";
	modal2cc.style.display = "flex";
	
	var currentContentHeight = $('.modal-contentScreen').outerHeight(); 
	$(".modal-contentScreen").css("margin-top",-(currentContentHeight/2));

 }
  function closePopupAddSeat()
 {
 	modalcc.style.display = "none";
 }
 function removeSeatType(id)
 {
 	document.getElementById(id).remove();
 }
 
function viewScreen(id){
	window.location.href = "ViewScreen?screenId="+id;
}

function checkAllSeat(el) {

	var chkid = $(el).val();
	if (el.checked == true)
	{
		if($("input."+chkid+":checkbox").prop("disabled")!= true){
			$("input."+chkid+":checkbox").prop("checked", true);
		}
	}
	if(el.checked == false)
	{
		$("input."+chkid+":checkbox").prop("checked", false);
	}
}
function addSeatType(temp,el){
 	if(temp==0){
 		modalcc.style.display = "block";
		modal2cc.style.display = "flex";
		$('.msgCastCrew').text('Add Seat Type');
		$("#addType").html('Add');
		$('#addType').removeClass('btn-primary');
    	$('#addType').addClass('btn-success');
		
	}else{
		 modalcc.style.display = "block";
		 modal2cc.style.display = "flex";
		 $('.msgCastCrew').text('Change Seat Type');
		 $("#addType").html('Change');
		 $('#addType').removeClass('btn-success');
    	 $('#addType').addClass('btn-primary');
    	 $('#uptxtSeatTypeId').val($(el).find(":input[name='txtSeatTypeId']").val());
    	 $('#txtSeatType').val($(el).find(":input[name='txtSeatType']").val());
    	 $('#txtNoOfRow').val($(el).find(":input[name='txtnoOfRow']").val());
    	 
	}
	
	var currentContentHeight = $('.modal-contentScreen').outerHeight(); 
	$(".modal-contentScreen").css("margin-top",-(currentContentHeight/2));
 }
 

 