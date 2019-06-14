/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function horaActual(){
var fecha = new Date();
var dia = fecha.getDate();
var mes;
var listaMeses = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Novimbre", "Diciembre"];
mes = listaMeses[fecha.getMonth()];
anio = fecha.getFullYear();

hora = fecha.getHours();
if (hora>12) {
meri=' pm';
hora=hora-12; }
else
meri=' am';
minuto = fecha.getMinutes();
if (minuto<10) minuto='0'+minuto;


resultado = dia + " de " + mes +" del "+ anio +" a las "+ hora + ":" + minuto + meri;
document.getElementById('hora').innerHTML = resultado;
}