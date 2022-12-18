let nombreMes = ['Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio',
'Agosto','Septiembre','Octubre','Noviembre','Diciembre'];

let diaEscogido = [];
let mesEscogido =[];
let añoEscogido =[];
let fechasEscogidas=[];
let cantAgregados =0;

document.getElementById("fechasEscogidas").value=fechasEscogidas;
var btn = document.getElementById("openModal");
btn.addEventListener("click", function(evento){
	
	document.getElementById("fechasEscogidas").value=fechasEscogidas;
});
var aceptar = document.getElementById("aceptar");
aceptar.addEventListener("click", function(evento){
	
	document.getElementById("fechasEscogidas").value=fechasEscogidas;
});
let fechaActual = new Date();
let diaActual = fechaActual.getDate();
let numeroMes = fechaActual.getMonth();
let añoActual = fechaActual.getFullYear();

let fecha = document.getElementById("dias");
let mes = document.getElementById("mes");
let año = document.getElementById("año");

let anteriorMes = document.getElementById("anterior");
let siguienteMes = document.getElementById("siguiente");

let contador = 0;

mes.textContent = nombreMes[numeroMes];
año.textContent = añoActual.toString();

anteriorMes.addEventListener('click', ()=>UltimoMes());
siguienteMes.addEventListener('click',()=>SiguienteMes());

let añoPresente = añoActual;
let mesPresente = numeroMes;
let diaPresente = diaActual;

let costoInput = document.getElementById("costo");
let valor = costoInput.getAttribute("value")

let fechaInput = document.getElementById("fechas");
let fechaSolicitada = fechaInput.getAttribute("value")
var array = fechaSolicitada.split(",");

let palabra;
let palabraN;

for(let i = 0;i<array.length;i++){
	palabraN = "";
	for(let j = 0; j<array[i].length;j++){
		palabra = array[i];
		if(palabra[j] != "["){
			if(palabra[j] != "]"){
				if(palabra[j] != " "){
					palabraN = palabraN + palabra[j];
				}
			}
		}
	}
	array[i]=palabraN;
}

let valorFinal = 0;
EscribirMes(numeroMes);
mostrarMensaje();

function EscribirMes(mes) {
	console.log(contador);
	mostrarMensaje();
	for (let i = ComienzoSemana(); i > 0; i--) {
		fecha.innerHTML += ` <div class="diaDeshabilitado">
            ${getTotalDias(numeroMes - 1) - (i - 1)}
        </div>`;
	}
	for (let i = 1; i <= getTotalDias(mes); i++) {
		if (añoPresente < añoActual) {
			if (ExisteFecha(i, mes + 1, añoActual)) {
				fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
			} else if (ExisteFechaSolicitada(i, mes + 1, añoActual)) {
				fecha.innerHTML += `<div class="dia" id="dia-${i}H" style="color: rgb(33,150,243);"><input name="dia" type="checkbox" value="${añoActual}-${mes + 1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off" checked>
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
			}
			else {
				fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input name="dia" type="checkbox" value="${añoActual}-${mes + 1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off">
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
			}
		} else if (añoPresente === añoActual) {
			if (mesPresente < numeroMes) {
				if (ExisteFecha(i, mes + 1, añoActual)) {
					fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
				}
				else if (ExisteFechaSolicitada(i, mes + 1, añoActual)) {
					fecha.innerHTML += `<div class="dia" id="dia-${i}H" style="color: rgb(33,150,243);"><input name="dia" type="checkbox" value="${añoActual}-${mes + 1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off" checked>
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;

				} else {
					fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input type="checkbox" value="${añoActual}-${mes + 1}-${i}" class="diaCheck" name="dia" id="dia-${i}" autocomplete="off">
									<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
				}
			} else if (mesPresente === numeroMes) {
				if (i >= diaActual) {
					if (ExisteFecha(i, mes + 1, añoActual)) {
						fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
					}
					else if (ExisteFechaSolicitada(i, mes + 1, añoActual)) {
						fecha.innerHTML += `<div class="dia" id="dia-${i}H" style="color: rgb(33,150,243);"><input name="dia" type="checkbox" value="${añoActual}-${mes + 1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off" checked>
										<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
					}
					else {
						fecha.innerHTML += `<div class="dia" id="dia-${i}H"><input name="dia" type="checkbox" value="${añoActual}-${mes + 1}-${i}" class="diaCheck" id="dia-${i}" autocomplete="off">
											<label onclick="CambiarColor(this)" name="${i}" class="diaTexto" for="dia-${i}">${i}</label><br></div>`;
					}
				} else {
					fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
				}
			} else {
				fecha.innerHTML += `<div class="diaDeshabilitado">${i}</div>`;
			}
		} else {
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
	if(checkBox.checked){
		casilla.style.color = "#000";
		if(ExisteFechaSolicitada(numeroDia,numeroMes+1,añoActual)){
			EliminarFecha(BuscarFecha(numeroDia,numeroMes+1,añoActual));
			CrearLista();
		}
		cantAgregados--;
		contador--;
	}else{
		casilla.style.color = "#2196f3";
		if(!ExisteFechaSolicitada(numeroDia,numeroMes+1,añoActual)){
			AgregarFecha(numeroDia,numeroMes+1,añoActual);
			CrearLista();
		}
		cantAgregados++;
		contador++;
	}
	getValor();
	mostrarMensaje();
}
function getValor() {
	valorFinal = valor;
	valorFinal = valorFinal*contador;
}
function mostrarMensaje(){
	if(contador == 0){
		var mensaje =`No hay fechas elegidas.`
	}else{
		var mensaje =`¿Está seguro de proceder con la solicitud de arriendo? El costo total de arriendo es de $${valorFinal}.`
	}
	var etiqueta= document.getElementById("mensaje").innerHTML = mensaje;
}
function ExisteFecha(diaBuscado,mesBuscado,añoBuscado){
    let diaLista;
    let mesLista;
    let añoLista;
    let fechaLista;
	for(let i=0;i<array.length;i++){
		fechaLista = array[i].split("-");
		añoLista = fechaLista[0];
		mesLista = fechaLista[1];
		diaLista = fechaLista[2];
		if(añoLista == añoBuscado && mesLista == mesBuscado && diaLista == diaBuscado){
			return true;
		}
	}
	return false;
}

function BuscarFecha(dia,mes,año){
	for(let i = 0; i<diaEscogido.length;i++){
		if(diaEscogido[i] == dia && mesEscogido[i] == mes && añoEscogido[i] == año){
			return i;
		}
	}
	return 0
}
function ExisteFechaSolicitada(dia,mes,año){
	for(let i = 0; i<diaEscogido.length;i++){		
		if(diaEscogido[i] == dia && mesEscogido[i] == mes && añoEscogido[i] == año){
			
			return true;
		}
	}
	return false
}
function AgregarFecha(dia,mes,año){
	diaEscogido.push(dia);
	mesEscogido.push(mes);
	añoEscogido.push(año);
}
function EliminarFecha(pos){
	
	let diaAux = [];
	let mesAux = [];
	let añoAux = [];
	
	for(let i = 0;i<diaEscogido.length;i++){
		if(i != pos){
			diaAux.push(diaEscogido[i]);
			mesAux.push(mesEscogido[i]);
			añoAux.push(añoEscogido[i]);	
		}
	}
	diaEscogido = diaAux;
	mesEscogido = mesAux;
	añoEscogido = añoAux;
}
function CrearLista(){
	let fechaE;
	let fechasEscogidasAux=[];
	for(let i = 0;i<diaEscogido.length;i++){
		if(diaEscogido[i] <10){
			if(mesEscogido[i]<10){
				fechaE = añoEscogido[i]+"-0"+mesEscogido[i]+"-0"+diaEscogido[i];
			}else{
				fechaE = añoEscogido[i]+"-"+mesEscogido[i]+"-0"+diaEscogido[i];
			}
		}else{
			if(mesEscogido[i]<10){
				fechaE = añoEscogido[i]+"-0"+mesEscogido[i]+"-"+diaEscogido[i];
			}else{
				fechaE = añoEscogido[i]+"-"+mesEscogido[i]+"-"+diaEscogido[i];
			}
		}
		fechasEscogidasAux.push(fechaE)
	}
	fechasEscogidas = fechasEscogidasAux;
	var lista = document.getElementById("fechasEscogidas").value=fechasEscogidas;
}
