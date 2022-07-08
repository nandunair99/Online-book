/**
* (Cast-Crew)
*/
var InUpmodal = document.getElementById("InUpmyModal");
var InUpspan = document.getElementsByClassName("InUpclose")[0];
const buttonRight = document.getElementById('rightBtn');
const buttonLeft = document.getElementById('leftBtn'); 
 
// open insert form div modal (Cast-Crew)
function addNewCC() 
{
	InUpmodal.style.display = "block";
	$(".wrapper").addClass("blur");   
}
// open update from div modal (Cast-Crew)
function upCC(id,name,img) 
{
	InUpmodal.style.display = "block";
	$(".ccid").val(id);
	$(".ccname").val(name);
	$(".txtDisImg").val(img);
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Cast Crew...');
	$(".btnCC").hide();
   	$(".btnUpdateCC").show();
   	$(".upimgView").show();
   	$(".upimgView").attr('src','data:image/png;base64,'+ img);
}
function upCCAfterError() 
{
	InUpmodal.style.display = "block";
	$(".wrapper").addClass("blur");   
	$(".InUpmsg").html('Update Cast Crew...');
	$(".btnCC").hide();
   	$(".btnUpdateCC").show();
   	$(".upimgView").show();
}


// When the user clicks on <span> (x), close the modal (Cast-Crew)
 function InUpclosePopup(page)
 {
 	modal.style.display = "none";	
 	$(".wrapper").removeClass("blur");
 	window.location.replace(page);
 }
 //scroll cardview
 buttonRight.onclick = function () {
 	document.getElementById('cardRow').scrollLeft +=170;
 };
  buttonLeft.onclick = function () {
 	document.getElementById('cardRow').scrollLeft -=170;
 };
 