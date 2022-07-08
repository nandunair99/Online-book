
var InUpmodalMovie = document.getElementById("InUpmyModal");
var modalcc = document.getElementById("myModalCastCrew");
var modal2cc = document.getElementById("modal2CastCrew");
var modelTitle = document.getElementById("msgCastCrew");
var btn = document.getElementById("addType");

function addCastCrew(){
 	modalcc.style.display = "block";
	modal2cc.style.display = "flex";

 }
 function addSeatType(temp){
 	alert(temp)
 	if(temp==0){
 		modalcc.style.display = "block";
		modal2cc.style.display = "flex";
	}else{
		modalcc.style.display = "block";
		modal2cc.style.display = "flex";
		modelTitle.innerHTML ="Change Seat Type";
		btn.innerText="Change";
		
		
	}
 }
  function closePopupAddCastCrew()
 {
 	modalcc.style.display = "none";
 }
 function removeCastCrew(id)
 {
 	document.getElementById(id).remove();
 }
 function updateMovie(movieId){
 	window.location.href = "MovieUpdateData?movieId="+movieId;
 }
 function updateMovieForm() 
{
	InUpmodalMovie.style.display = "block";
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Movie...');
	
	$(".btnAddMovie").hide();
   	$(".btnUpdateMovie").show();
   	$(".upimgView").show();
   	$(".divStatus").show();
	$(".UpdateviewImgP").show();
   	$(".UpdateviewImgB").show();
}
function upCCAfterErrorMovie() 
{
	InUpmodalMovie.style.display = "block";
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Movie...');
	
	$(".btnAddMovie").hide();
   	$(".btnUpdateMovie").show();
   	$(".upimgView").show();
   	$(".divStatus").show();
	$(".UpdateviewImgP").show();
   	$(".UpdateviewImgB").show();
   	
}
function viewImg(id)
{
	var img = document.getElementsByClassName(id)[0];
	img.style.display = "block";
	setTimeout(function(){ $("."+id).fadeOut();}, 2000);
}
