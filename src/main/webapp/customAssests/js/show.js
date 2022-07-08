var modal = document.getElementById("myModal");
var InUpmodal = document.getElementById("InUpmyModal");
var InUpspan = document.getElementsByClassName("InUpclose")[0];
var modalcc = document.getElementById("myModalCastCrew");
var modal2cc = document.getElementById("modal2CastCrew");

function addNewShow() 
{
	InUpmodal.style.display = "block";
	$(".wrapper").addClass("blur");
	  
}
 function InUpclosePopup(page)
 {
 	InUpmyModal.style.display = "none";	
 	$(".wrapper").removeClass("blur");
 	window.location.replace(page);
 }
 function closePopup(page)
 {
 	modal.style.display = "none";	
 	$(".wrapper").removeClass("blur"); 
 	window.location.replace(page);
 }
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
 function updateShow(showId){
 	window.location.href = "UpdateShowData?showID="+showId;
 }
 function updateShowForm() 
{
	InUpmodal.style.display = "block";
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Show...');
	$(".btnAdd").hide();
   	$(".btnUpdate").show();
}