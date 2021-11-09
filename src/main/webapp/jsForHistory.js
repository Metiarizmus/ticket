let id;
let id_comment;
let id_mail;
const st = document.querySelectorAll(".status");
const comment = document.querySelectorAll(".comment")
const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const idForItem = document.querySelectorAll(".item_id")
const statusBtn = document.querySelectorAll(".status_btn")
const html = document.querySelector('html')
const closeBtn = document.querySelector(".closeBtn")
const tableHistory = document.querySelectorAll(".table_history")
const mail_btn = document.querySelectorAll(".mail")
const btn_send_comment = document.querySelector(".send_comment")
const btn_send_mail = document.querySelector(".send_mail")

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

        const idJson_status = JSON.stringify(idObj);

        const urlToChangeStatus = window.location.protocol + '//' + window.location.host+"/demo1_war_exploded/changeStatus";

        sendJson(urlToChangeStatus,idJson_status);

    }
})


comment.forEach((item, index) => {
    item.onclick = function () {
        id_comment = tableHistory[index].firstElementChild.innerHTML;
        console.log(id_comment);

        getOkno.innerHTML = `
<h3>please write comment</h3>

<form id="form_comment_id" name="form_comment" action="comment" oninput="validateForm()" method="post" >
        <p><textarea id="textArea" name="textComment" cols="30" rows="5" placeholder="add your comment"></textarea></p>
</form>
`
        btn_send_comment.style.display="block";

        openModal();

    }
})


function send_com() {
    var Vars = {var1: id_comment};
    var varsData = $.param(Vars);

    var formData = $('#form_comment_id').serialize();

    var data = varsData + '&' + formData;

    const urlToComment = window.location.protocol + '//' + window.location.host+"/demo1_war_exploded/comment";


    $.ajax({
        type: 'POST',
        url: urlToComment,
        data: data,
        success: function(res){
            alert ("commend add")

        }
    }).done(function () {
        window.location.reload(true);
    });

}

function validateForm() {
    let id = id_comment;


    idForItem.forEach(item => {

       if (id === item.innerHTML) {
           if (item.nextElementSibling.textContent.replace(/\s/g, '') === "CANCELED") {
               alert("you cant add comment to status with status canceled")
               return false;
           }


       }

    })

}

mail_btn.forEach((item, index) => {
        item.onclick = function (){
            id_mail = tableHistory[index].firstElementChild.innerHTML;
            console.log(id_mail)
            getOkno.innerHTML = `
<h3>please write your mail</h3>

<form id="form_mail_id" name="form_mail" action="sendOrder"  method="post" >
        <p><input type="text" name="address" placeholder="your gmail address"></p>
</form>
`
            btn_send_mail.style.display="block";
            openModal();

        }
    })

function send_mail() {
    var Vars = {var1: id_mail};
    var varsData = $.param(Vars);

    var formData = $('#form_mail_id').serialize();

    var data = varsData + '&' + formData;

    const urlToSendMail = window.location.protocol + '//' + window.location.host+"/demo1_war_exploded/sendOrder";


    $.ajax({
        type: 'POST',
        url: urlToSendMail,
        data: data,
        success: function(res){
            alert ("order send") }
    })

    location.reload();
}


statusBtn.forEach( item => {
    item.textContent == "ACCEPTED" ? item.style.background = "#3498DB" : item.style.background = "#b64545";
})

function openModal() {
    getOkno.style.display = "block";
    zatemnenie.classList.add('zatemnenie');
    getOkno.classList.add('styleOkno');
    html.style.overflow = "hidden";
    closeBtn.style.opacity = 1;
    closeBtn.style.cursor = "pointer";
}

function closePopup() {
    btn_send_comment.style.display="none";
    btn_send_mail.style.display="none";
    zatemnenie.classList.remove('zatemnenie')
    getOkno.classList.remove('styleOkno')
    html.style.overflow = ""
    closeBtn.style.opacity = 0
    closeBtn.style.cursor = "default"
    getOkno.innerHTML = ``;
    genPDF.style.visibility="hidden";
    location.reload();
}

closeBtn.addEventListener("click", function () {
    closePopup();
})

function sendJson(url, json_str) {

    xhr.open('POST', url,true);
    xhr.responseType = 'json';


    xhr.send(json_str);

    xhr.onreadystatechange = function() {
        if(xhr.readyState != 4) return;

        alert("good");
    };

    if (xhr.status != 200) {
        window.location.reload(true);
    } else {
        alert(xhr.responseText);
    }




}
