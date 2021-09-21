const st = document.querySelectorAll(".status");
const btnAccept = document.querySelectorAll(".btn_accept");
const btnClose = document.querySelectorAll(".btn_close");
const getOkno = document.querySelector('.okno')
const zatemnenie = document.querySelector('.zatem')
const idForItem = document.querySelectorAll(".item_id")

let id_array = [];

idForItem.forEach(item => {

 id_array.push(item.innerHTML);

})



st.forEach(item => {
    item.onclick = function () {
        id = (item.previousSibling.textContent)
        console.log(id)

        item.firstElementChild.classList.add("show")

        btnClose.forEach(close => {
            close.onclick = function () {
                item.textContent = 'CLOSED'
                item.style.background = "#935eff"
                item.firstElementChild.classList.remove("show")
            }
        })

        btnAccept.forEach(accept => {
            accept.onclick = function () {
                item.innerText = "ACCEPTED"
                item.style.background = "#3498DB"
                item.firstElementChild.classList.remove("show")
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

    getOkno.style.display = "block";
    zatemnenie.classList.add('zatemnenie');
    getOkno.classList.add('styleOkno');
    html.style.overflow = "hidden";
    closeBtn.style.opacity = 1;
    closeBtn.style.cursor = "pointer";
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