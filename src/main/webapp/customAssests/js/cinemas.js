var InUpmodalCinema = document.getElementById("InUpmyModal");

function updateCinema(cinemaId){
	window.location.href = "UpdateCinemaData?cinemaId="+cinemaId;
}
function updateCinemaForm() 
{
	InUpmodalCinema.style.display = "block";
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Cinema...');
	
	$(".btnAdd").hide();
   	$(".btnUpdate").show();
}
function upCCAfterErrorCinema() 
{
	InUpmodalCinema.style.display = "block";
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Cinema...');
	
	$(".btnAdd").hide();
   	$(".btnUpdate").show();
}