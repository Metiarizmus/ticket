const names = document.querySelectorAll(".names");
let id;

const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const html = document.querySelector('html')
const closeBtn = document.querySelector(".closeBtn")


names.forEach(item => {
    item.onclick = function () {
        id = (item.previousSibling.textContent)

        const idObj = {
            "id_order": id
        };

        const idJson = JSON.stringify(idObj);

        const urlToGeneral = "http://localhost:8080/demo1_war_exploded/general";

        $.ajax({
            url: urlToGeneral,
            method: "post",
            data: idJson,
            contentType: "application/json",
            error: function (message) {
                console.log(message);
            },
            success: function (data) {
                console.log(data);

                getOkno.innerHTML = `
<h3>Do you really want to order this ticket?</h3>
<p>route: ${data.route} <br>
price: ${data.price} <br>
dateTicket: ${data.dateTicket} <br>
status: ${data.status}</p>
<form action="order" method="post">
        <p><input class="button-order" onclick="alert('your order is accepted')" type="submit" value="yes"/></p>
        
</form>
`
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
    window.location = window.location.href
    zatemnenie.classList.remove('zatemnenie')
    getOkno.classList.remove('styleOkno')
    html.style.overflow = ""
    closeBtn.style.opacity = 0
    closeBtn.style.cursor = "default"
    getOkno.innerHTML = ``
}


