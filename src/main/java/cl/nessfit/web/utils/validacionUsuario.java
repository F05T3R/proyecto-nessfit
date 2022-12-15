package cl.nessfit.web.utils;
import cl.nessfit.web.service.CUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import cl.nessfit.web.model.Usuario;


@Component
public class validacionUsuario implements Validator {
	@Autowired
    CUsuarioService usuarioService;

    @Override
    public boolean supports(Class<?> clazz) {
    return Usuario.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
	    Usuario usuario = (Usuario) target;
	    
	    Usuario existe = usuarioService.buscarPorEmail(usuario.getEmail());
	    if (existe != null) {
	    	errors.rejectValue("email", null, "El RUT y/o correo electrónico ya existen en el sistema. Intente iniciar sesión");
	    }
	    
		if (usuario.getEmail() == "") {
			errors.rejectValue("email", null, "Complete este campo ");
		}
	    
	    // lógica para validar
	    
	    if(String.valueOf(usuario.getTelefono()).length() < 11 || String.valueOf(usuario.getTelefono()).length() > 16) {
	    	errors.rejectValue("telefono", null, "El teléfono móvil ingresado no es válido.");
	    }
	    if(usuario.getNombre().length() < 3 || usuario.getNombre().isBlank() ) {
	    	errors.rejectValue("nombre", null, "Los nombres o apellidos deben tener más de 2 caracteres");
	    }
	    if(usuario.getApellido().length() < 3 || usuario.getApellido().isBlank()) {
	    	errors.rejectValue("apellido", null, "Los nombres o apellidos deben tener más de 2 caracteres");
	    }
	    if(usuario.getRut().isBlank()) {
	    	errors.rejectValue("rut", null, "Complete este campo.");
            return;
	    }
	    if(usuario.getEmail().isBlank()) {
	    	errors.rejectValue("email", null, "Complete este campo.");
            return;
	    }
	    String rutAux = usuario.getRut();
	    //rutAux = rutAux.replace(".", "").replace("-", "").replace(" ", "").replace(",", ""); 
	    String rut = rutAux.substring(0,rutAux.length()-1);
	    
	    String [] rutLista = rut.split("");
	    int contador = 0;
	    String [] numeros = {"1","2","3","4","5","6","7","8","9","0"};
	    
	    for(int i = 0; i< rutLista.length; i++) {
	    	
	    } 
	    for(int i = 0; i< rutLista.length; i++) {
	        contador= 0;
	        for(int j = 0; j< numeros.length; j++) {

	            if((rutLista[i].equals(numeros[j]))){
	                break;
	            }
	            else {
	                contador++;
	            }
	        }
	        if(contador == 10) {
	        	errors.rejectValue("rut", null, "RUT inválido");
	            return;
	        }
	    }
	    
	    
	   
	    
	    rutLista = invertirLista(rutLista);
	    
	    int digitoVerificador = calcularDigito(rutLista);
	   
	    
	    
	    
	    String [] rutListaAux = rutAux.substring(0,rutAux.length()).split("");
	    
	    if(digitoVerificador == 11) {
	    	if((rutListaAux[rutListaAux.length-1]).equals("0")) {
	    		System.out.print(rutAux);
	    		usuario.setRut(rutAux);
		    	return;
		    }
	    	else {
	    		errors.rejectValue("rut", null, "RUT inválido");
	    	}
	    }
	    
	    else if(digitoVerificador == 10) {
	    	if((rutListaAux[rutListaAux.length-1]).equals("K")) {
	    		
	    		usuario.setRut(rutAux.toUpperCase());
		    	return;
		    }
	    	else {
	    		errors.rejectValue("rut", null, "RUT inválido (Si termina en K debe ser en Mayuscula)");
	    	}
	    }
	    
	    else {
	    	
	    	if(rutListaAux[rutListaAux.length-1].equals("k") || rutListaAux[rutListaAux.length-1].equals("K")) {
	    		errors.rejectValue("rut", null, "RUT inválido");
	    	}
	    	
	    	
	    	else if ((rutListaAux[rutListaAux.length-1]).equals(String.valueOf(digitoVerificador))) {
	    		System.out.print(rutAux);
	    		usuario.setRut(rutAux.toUpperCase());
		    	return;
	    	 }
	    	
	    	else {
	    		errors.rejectValue("rut", null, "RUT inválido");
	    	}
	    }
	    System.out.println(rutAux);
	    usuario.setRut(rutAux);
	    
	    

    }
    
    public static String[] invertirLista(String[] rut){
        String[] invertir_int = new String[rut.length];
        int maximo = rut.length;
  
        for (int i = 0; i < rut.length; i++) {
            //Object j = rut[maximo - 1];
            invertir_int[maximo - 1] = rut[i];
            maximo--;
        }
        
        return invertir_int;
    }
    
    public static int calcularDigito(String[] rut){
        int a = 2;
        int suma = 0;
        int aux = 0;
        for(int i = 0; i<rut.length; i++){
            aux = Integer.parseInt((String) rut[i]);
            suma += (aux*a);
            if (a == 7) {
                 a = 1;
                 aux = 0;
            }
            a++;
            aux = 0;
        }
        int resto = suma % 11;
        
        return 11-resto;
    } 
}


