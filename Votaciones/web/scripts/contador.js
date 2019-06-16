/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//funcion que genera el contador en reversa para indicar cuanto tiempo dispone para votar.
function startTimer(duration, display) {
    var timer = duration, minutes, seconds;
    setInterval(function () {
        minutes = parseInt(timer / 60, 10);
        seconds = parseInt(timer % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        display.textContent = minutes + ":" + seconds;

        if (--timer < 0) {
            window.location.replace("ServicioLogout");
        }
    }, 1000);
}

window.onload = function () {
    var dosMinutos = 60 * 0.125,//aqui se define el tiempo antes de la redireccion 
            display = document.querySelector('#tiempo');
    startTimer(dosMinutos, display);
};