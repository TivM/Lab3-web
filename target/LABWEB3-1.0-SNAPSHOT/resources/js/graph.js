function checkHit(){
    let coordinateX = event.pageX - 301;
    let coordinateY = event.pageY - 152;
    let xHidden = document.querySelector("#form\\:hiddenX").value = coordinateX;
    let yHidden = document.querySelector("#form\\:hiddenY").value = coordinateY;

    // var svg = document.getElementsByTagName("svg")[0];
    // svg.addEventListener('click', e => {
    //     const point = e.target.closest('.container-point')});


    document.querySelector("#form\\:submit").click();





    // document.querySelector("#form\\:hiddenX");
    // document.querySelector("#form\\:hiddenY");

    // alert(resultB);
    // alert(yHidden);
    // alert(document.forms[0].elements[14]);


}


