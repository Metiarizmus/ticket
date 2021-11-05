const orders = document.querySelectorAll(".order");
let id;

const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const html = document.querySelector('html')
const closeBtn = document.querySelector(".closeBtn")
const doc = new jsPDF()
const textArea = document.getElementsByClassName(".example");
const genPDF = document.querySelector('.pdf');
let xhr = new XMLHttpRequest();


orders.forEach(item => {
    item.onclick = function () {
        id = (item.previousSibling.textContent)

        const idObj = {
            "id_order": id
        };

        genPDF.style.visibility="visible";


        const idJson = JSON.stringify(idObj);

        const urlToHelper = window.location.protocol + '//' + window.location.host+"/demo1_war_exploded/general";

        xhr.open('POST', urlToHelper, true);
        xhr.responseType = 'json';

        xhr.send(idJson);

        let f = 0;

        xhr.onreadystatechange = function() {

            if (xhr.readyState != 4) return;

            let responseObj = xhr.response;

            if (`${responseObj.status}` != "UNAVAILABLE") {
                getOkno.innerHTML = `
<h3>Do you really want to order this ticket?</h3>
<p>route: ${responseObj.route} <br>
price: ${responseObj.price} <br>
dateTicket: ${responseObj.dateTicket} <br>
status: ${responseObj.status}</p>

<form action="order" method="post">
        <p><input class="button-order" onclick="alert('your order is accepted')" type="submit" value="order"/></p>
</form>
`
            }else {
                alert("sorry you cant order with status a UNAVAILABLE")
                f=1;
            }




            textArea.innerText=`
route: ${responseObj.route}
price: ${responseObj.price}
dateTicket: ${responseObj.dateTicket}
status: ${responseObj.status}
`
            doc.text(`route: ${responseObj.route}`, 10, 10)
            doc.text(`price: ${responseObj.price}`, 10, 20)
            doc.text(`date: ${responseObj.dateTicket}`, 10, 30)
            doc.text(`status: ${responseObj.status}`, 10, 40)

            // -------------------------------------------------------------------------------------
            if (xhr.status != 200) {
                alert(xhr.status + ': ' + xhr.statusText);
            } else {

            }

            xhr.onprogress = function(event) {
                alert(`Загружено ${event.loaded} из ${event.total}`);
            };

            if(f===0){
                getOkno.style.display = "block";
                zatemnenie.classList.add('zatemnenie');
                getOkno.classList.add('styleOkno');
                html.style.overflow = "hidden";
                closeBtn.style.opacity = 1;
                closeBtn.style.cursor = "pointer";
            }

    }


}})

closeBtn.addEventListener("click", function () {
    closePopup();
})


function closePopup() {
    zatemnenie.classList.remove('zatemnenie')
    getOkno.classList.remove('styleOkno')
    html.style.overflow = ""
    closeBtn.style.opacity = 0
    closeBtn.style.cursor = "default"
    getOkno.innerHTML = ``;
    genPDF.style.visibility="hidden";
    location.reload();
}

genPDF.onclick = function (){
    doc.save('a4.pdf')
}



