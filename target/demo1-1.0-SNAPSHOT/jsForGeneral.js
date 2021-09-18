const names = document.querySelectorAll(".names");
let id;

const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const html = document.querySelector('html')
const closeBtn = document.querySelector(".closeBtn")




names.forEach(item => {
    item.onclick = function () {
        id = (item.previousSibling.textContent)

        console.log(JSON.stringify({id_order : id}))

        const idObj = {
            "id_order": id
        };

        const idJson = JSON.stringify(idObj);

        const url = "http://localhost:8080/demo1_war_exploded/general";

        $.ajax({
            url: url,
            method: "post",
            data: idJson,
            contentType: "application/json",
            error: function(message) {
                console.log(message);
            },
            success: function(data) {
                console.log(data);
            }
        });

        getOkno.style.display = "block";
        zatemnenie.classList.add('zatemnenie');
        getOkno.classList.add('styleOkno');
        html.style.overflow = "hidden";
        closeBtn.style.opacity = 1;
        closeBtn.style.cursor = "pointer";

    }
})

closeBtn.onclick = function () {
    //window.location = window.location.href
    zatemnenie.classList.remove('zatemnenie')
    getOkno.classList.remove('styleOkno')
    html.style.overflow = ""
    closeBtn.style.opacity = 0
    closeBtn.style.cursor = "default"
    getOkno.innerHTML = ``
}
