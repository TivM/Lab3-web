var xButtons = document.querySelectorAll(".x_value input");
xButtons.forEach(click);
function click(element) {
    element.onclick = function () {
        // x = this.value;
        xButtons.forEach(function (element) {
            element.style.backgroundColor = "";
        });
        element.style.backgroundColor = "green"
    }
}
// document.querySelector("#form > div > div > div.x_value > input:nth-child(4)")
// document.querySelector("#form > div > div > div.x_value > input:nth-child(5)")

// var btn = document.querySelectorAll(".x_value input");
// btn.addEventListener("click", function() {
//     this.classList.add("active");
// });

// function changeColor(element){
//     element.style.backgroundColor="green"
// }