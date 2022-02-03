elementListSort = document.querySelectorAll("a.order");
let newUrlSort;
let baseUrlSort = window.location.href;
for (let button of elementListSort) {
    $(button).on('click', function () {
        if ( !baseUrlSort.includes('musician')){
            if (button.id==='createdUp'){
                newUrlSort= '?sort=created&order=asc&page=1&size='+ document.getElementById('hiddenSort').value;
            }
            else if(button.id==='priceUp') {
                newUrlSort= '?sort=price&order=asc&page=1&size='+ document.getElementById('hiddenSort').value;
            }
            else if(button.id==='priceDown') {
                newUrlSort= '?sort=price&order=desc&page=1&size='+ document.getElementById('hiddenSort').value;
            }
        }
        else if (baseUrlSort.includes("musician")){
            let index = baseUrlSort.lastIndexOf('&');
            let substr = baseUrlSort.substring(index,baseUrlSort.length);
            if (button.id==='createdUp'){
                newUrlSort= '?sort=created&order=asc&page=1&size='+ document.getElementById('hiddenSort').value+substr;
            }
            else if(button.id==='priceUp') {
                newUrlSort= '?sort=price&order=asc&page=1&size='+ document.getElementById('hiddenSort').value+substr;
            }
            else if(button.id==='priceDown') {
                newUrlSort= '?sort=price&order=desc&page=1&size='+ document.getElementById('hiddenSort').value+substr;
            }
        }
        history.replaceState(null, null, newUrlSort);
        location.reload();
    })
}

function showOnPage(size){
    var pos1 = baseUrlSort.indexOf('size=');
    var pos2 = baseUrlSort.indexOf('&',pos1+1);
    newUrlSort=baseUrlSort.replace(baseUrlSort.substring(pos1,pos2),'size='+size);
    history.replaceState(null, null, newUrlSort);
    location.reload();
}

