function Confirmar(){
	// Get the modal
modal = document.getElementById("myModal");
// Get the button that opens the modal
var btn = document.getElementById("openModal");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

var cancelar = document.getElementsByClassName("cancelar")[0];

// When the user clicks on the button, open the modal
  modal.style.display = "block";

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

cancelar.onclick = function() {
  modal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
}
function Cancelar(){
	// Get the modal
 modal = document.getElementById("Modal");

// Get the button that opens the modal
var btn = document.getElementById("abrirModal");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("cerrar")[0];

var cancelar = document.getElementsByClassName("cancelar2")[0];
// When the user clicks on the button, open the modal
  modal.style.display = "block";

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}

cancelar.onclick = function() {
  modal.style.display = "none";
}
// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
}