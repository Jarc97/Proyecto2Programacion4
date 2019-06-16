/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function validarEspaciosRegistroPartido(){
    siglas = document.form1.getElementById("in");
    nombre = document.tabla.nombre;
    obser = document.tabla.observaciones;
    bandera = document.tabla.bandera;
    sig = document.getElementById("esiglas");
    
    if (siglas.value !== "" || nombre.value !== "" || obser.value !== "") {
        return true;
    }    
    else {
        sig.innerHTML = "Rellene todos los espacios";
        return false;
    }
}