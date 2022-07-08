/**
 * 
 */
 
 var modal = document.getElementById("myModal");
 var btn = document.getElementById("myBtn");
 var span = document.getElementsByClassName("close")[0];
 var p = document.getElementsByClassName("msg")[0];
 var yesBtn = document.getElementById("yesBtn");
 var modal2 = document.getElementById("modal2");
 var url;
 function closePopup(page)
 {
 	modal.style.display = "none";	
 	$(".wrapper").removeClass("blur"); 
 	window.location.replace(page);
 }
  function closePopupScreen(page,id)
 {
 	modal.style.display = "none";	
 	$(".wrapper").removeClass("blur"); 
 	window.location.replace(page + "?screenId="+id);
 }
 
 /* When the user clicks on <span> (x), close the modal
		span.onclick = function() {
		  modal.style.display = "none";
		  window.location.replace("Place");
		}*/

/* // When the user clicks anywhere outside of the modal, close it
		window.onclick = function(event) {
		  if (event.target == modal) {
		    modal.style.display = "none";
		    window.location.replace("Place");
		  }
		}
 */
yesBtn.onclick = function()
{
	modal.style.display = "none";
	window.location = url;
}

// for delete confirm
function confirmGo(msg,purl)
{
	modal.style.display = "block";
	modal2.style.display = "flex";
	$(".wrapper").addClass("blur");    		
	p.innerHTML = msg;
	url = purl;
}

//searching State
function searchState() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchState");
  filter = input.value.toUpperCase();
  table = document.getElementById("tableState");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}

//searching City
function searchCity() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("searchCity");
  filter = input.value.toUpperCase();
  table = document.getElementById("tableCity");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}


