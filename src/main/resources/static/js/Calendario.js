let nombreMes = ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio',
'Agosto','Septiembre','Octubre','Noviembre','Diciembre'];

let fechaActual = new Date();
let diaActual = fechaActual.getDate();
let numeroMes = fechaActual.getMonth();
let añoActual = fechaActual.getFullYear();

let fecha = document.getElementById("dias");
let mes = document.getElementById("mes");
let año = document.getElementById("año");

let anteriorMes = document.getElementById("anterior");
let siguienteMes = document.getElementById("siguiente");

mes.textContent = nombreMes[numeroMes];
año.textContent = añoActual.toString();

anteriorMes.addEventListener('click', ()=>UltimoMes());
siguienteMes.addEventListener('click',()=>SiguienteMes());

let añoPresente = añoActual;
let mesPresente = numeroMes;
let diaPresente = diaActual;
EscribirMes(numeroMes);

function EscribirMes(mes){
	for(let i = ComienzoSemana(); i>0;i--){
        fecha.innerHTML += ` <div class="diaDeshabilitado">
            ${getTotalDias(numeroMes-1)-(i-1)}
        </div>`;
    }
	
	for(let i = 1; i<=getTotalDias(mes);i++){
		if(añoPresente<añoActual){
<<<<<<< Updated upstream
			fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input name="dia" type="checkbox" value="${i}-${mes+1}-${añoActual}" class="diaCheck" id="dia-${i}" autocomplete="off">
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
		}else if(añoPresente === añoActual){
			if(mesPresente<numeroMes){
				fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input type="checkbox" value="${i}-${mes+1}-${añoActual}" class="diaCheck" name="dia" id="dia-${i}" autocomplete="off">
									<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
			}else if(mesPresente === numeroMes){
				if(i>=diaActual) {
            		fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input name="dia" type="checkbox" value="${i}-${mes+1}-${añoActual}" class="diaCheck" id="dia-${i}" autocomplete="off">
=======
			fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input name="dia" type="checkbox" value="${añoActual}-${mes+1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off">
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
		}else if(añoPresente === añoActual){
			if(mesPresente<numeroMes){
				fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input type="checkbox" value="${añoActual}-${mes+1}-${i}" class="diaCheck" name="dia" id="dia-${i}" autocomplete="off">
									<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
			}else if(mesPresente === numeroMes){
				if(i>=diaActual) {
            		fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input name="dia" type="checkbox" value="${añoActual}-${mes+1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off">
>>>>>>> Stashed changes
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
        		}else{
            		fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
        		}
			}else{
				fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
			}
		}else{
			fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
		}
	}
}
function getTotalDias(mes){
	if(mes === -1) mes = 11;
	
	if (mes == 0 || mes == 2 || mes == 4 || mes == 6 || mes == 7 || mes == 9 || mes == 11) {
        return  31;

    } else if (mes == 3 || mes == 5 || mes == 8 || mes == 10) {
        return 30;
	} else{
		return EsBisiesto() ? 29:28;
	}
}
function EsBisiesto(){
	return((añoActual % 100 !== 0)&&
	(añoActual % 4 === 0)||(añoActual % 400 ===0));
}
function ComienzoSemana(){
	let comienzo = new Date(añoActual,numeroMes, 1);
	return(comienzo.getDay()-1 === -1) ? 6 : comienzo.getDay()-1;
	
}
function UltimoMes(){
	if(numeroMes !== 0){
		numeroMes--;
	}else{
		numeroMes = 11;
		añoActual--;
	}
	setNuevaFecha();

}
function SiguienteMes(){
	if(numeroMes !== 11){
		numeroMes++;
	}else{
		numeroMes = 0;
		añoActual++;
	}
	setNuevaFecha();
}
function setNuevaFecha(){
	fechaActual.setFullYear(añoActual,numeroMes,diaActual);
	mes.textContent = nombreMes[numeroMes];
	año.textContent = añoActual.toString();
	
	fecha.textContent = '';
    EscribirMes(numeroMes);
}
function CambiarColor(elemento){
	var numeroDia= elemento.getAttribute('name');
	var checkBox = document.getElementById(`dia-${numeroDia}`);
	var casilla = document.getElementById(`dia-${numeroDia}H`)
	console.log(numeroDia)
	console.log(checkBox)
	console.log(casilla)
	if(checkBox.checked){
		casilla.style.color = "#000";
	}else{
		casilla.style.color = "#2196f3";
	}
}
