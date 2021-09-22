const st = document.querySelectorAll(".status");
const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const idForItem = document.querySelectorAll(".item_id")
const statusBtn = document.querySelectorAll(".status_btn")

let id_array = [];

idForItem.forEach(item => {

 id_array.push(item.innerHTML);

})


st.forEach(item => {
    item.onclick = function () {
        id = (item.previousSibling.textContent)

        console.log(JSON.stringify({id_order: id}))

        const idObj = {
            "id_order": id
        };

        const idJson = JSON.stringify(idObj);

        const urlToGeneral = "http://localhost:8080/demo1_war_exploded/changeStatus";

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
            }
        })
    }
})

function commentClick() {
    getOkno.innerHTML = `
<h3>please choose id order and write comment to it</h3>

<form name="form_comment" action="comment" onsubmit="return validateForm()" method="post" >
        <p><input type="text" name="id_history_order" placeholder="id"></p>
        <p><textarea id="textArea" name="textComment" cols="30" rows="5" placeholder="add your comment"></textarea></p>
        <p><input class="send_comment" type="submit" value="send"/></p>        
</form>
`

   closeModal();
}

function validateForm() {
    let x = document.forms["form_comment"]["id_history_order"].value;
    let t = 0;

    id_array.forEach(i => {
        if(i == x) {
            t=1;
        }
    })

    if (t == 0) {
        alert("your id not correct");
        return false;
    }
}


function orderSend() {
    getOkno.innerHTML = `
<h3>please choose id order for send it to your email</h3>
<h3>you need to disable the protection of your account!!!</h3>

<form name="form_comment" action="sendOrder" onsubmit="return validateForm()" method="post" >
        <p><input type="text" name="id_history_order" placeholder="id"></p>
        <p><input type="text" name="address" placeholder="your gmail address"></p>
        <p><input type="text" name="password" placeholder="your gmail password"></p>
        
        <p><input class="send_comment" type="submit" value="send"/></p>        
</form>
`

    closeModal();

}

function closeModal() {
    getOkno.style.display = "block";
    zatemnenie.classList.add('zatemnenie');
    getOkno.classList.add('styleOkno');
    html.style.overflow = "hidden";
    closeBtn.style.opacity = 1;
    closeBtn.style.cursor = "pointer";
}



statusBtn.forEach( item => {
    item.textContent == "ACCEPTED" ? item.style.background = "#3498DB" : item.style.background = "#b64545";
})

