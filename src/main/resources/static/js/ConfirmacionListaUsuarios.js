function ActualizarModal(boton) {
	var btn = document.getElementById(boton.id);
	var modal = document.getElementById(boton.name);
	var span = document.getElementById(boton.id+"close");

	var cancelar = document.getElementById(boton.id+"cancelar");
	// When the user clicks on the button, open the modal
	
	modal.style.display = "block";
	EntregarTexto(btn, boton.id);

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
// Get the <span> element that closes the modal

function EntregarTexto(btn, rut){
	let codigo = rut + "mensaje";
	console.log(codigo);
	if(btn.className == "botonHabilitar"){
		var mensaje =`¿Desea habilitar a ${btn.name} del sistema?.`
		var etiqueta= document.getElementById(codigo).innerHTML = mensaje;
	}else{
		var mensaje =`¿Desea deshabilitar a ${btn.name} del sistema?.`
		var etiqueta= document.getElementById(codigo).innerHTML = mensaje;
	}
}