const st = document.querySelectorAll(".status");
const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const idForItem = document.querySelectorAll(".item_id")
const statusBtn = document.querySelectorAll(".status_btn")
const html = document.querySelector('html')
const closeBtn = document.querySelector(".closeBtn")

let id_array = [];

let xhr = new XMLHttpRequest();

idForItem.forEach(item => {

 id_array.push(item.innerHTML);

})


st.forEach(item => {
    item.onclick = function () {
        id = (item.previousSibling.textContent)

        const idObj = {
            "id_order_status": id
        };

        const idJson = JSON.stringify(idObj);

        const urlToHelper = window.location.protocol + '//' + window.location.host+"/demo1_war_exploded/helperData";

        xhr.open('POST', urlToHelper,true);
        xhr.responseType = 'json';


        xhr.send(idJson);


        xhr.onreadystatechange = function() {
            if(xhr.readyState != 4) return;

            alert("good");
        };

        if (xhr.status != 200) {
            alert(xhr.status + ': ' + xhr.statusText);
        } else {
            alert(xhr.responseText);
        }

        xhr.onprogress = function(event) {
            alert(`Загружено ${event.loaded} из ${event.total}`);
        };

    }
})

function commentClick() {
    alert("asasa")
    getOkno.innerHTML = `
<h3>please choose id order and write comment to it</h3>

<form name="form_comment" action="comment" onsubmit="return validateForm()" method="post" >
<!--        <p><input type="text" name="id_history_order" placeholder="id" class="id_history-order"></p>-->
        <p><textarea id="textArea" name="textComment" cols="30" rows="5" placeholder="add your comment"></textarea></p>
        <p><input class="send_comment" type="submit" value="send"/></p>        
</form>
`
    closeModal();
}

function validateForm() {
    let id = document.querySelector(".id_history-order").value;
    let t = 0;


    id_array.forEach(i => {
        if(+i === +id) {
            t=1;
        }
    })



    if (t == 0) {
        alert("your id not correct");
        return false;
    }

    idForItem.forEach(item => {

       if (id === item.innerHTML) {
           if (item.nextElementSibling.textContent.replace(/\s/g, '') === "CANCELED") {
               alert("you cant add comment to status with srtatus canceled")
           }


       }

    })

}

function orderSend() {
    getOkno.innerHTML = `
<h3>please choose id order for send it to your email</h3>
<h3>you need to disable the protection of your account!!!</h3>

<form name="form_comment" action="sendOrder" onsubmit="return validateForm()" method="post" >
        <p><input type="text" name="id_history_order" placeholder="id"></p>
        <p><input type="text" name="address" placeholder="your gmail address"></p>
        <p><input class="send_comment" type="submit" value="send"/></p>        
</form>
`
    closeModal();

}

statusBtn.forEach( item => {
    item.textContent == "ACCEPTED" ? item.style.background = "#3498DB" : item.style.background = "#b64545";
})

function closeModal() {
    getOkno.style.display = "block";
    zatemnenie.classList.add('zatemnenie');
    getOkno.classList.add('styleOkno');
    html.style.overflow = "hidden";
    closeBtn.style.opacity = 1;
    closeBtn.style.cursor = "pointer";
}
