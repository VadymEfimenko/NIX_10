elementList = document.querySelectorAll("#flexCheckDefault");
let newUrl;
var baseUrl = window.location.href;
if (!baseUrl.includes('page')) {
    baseUrl = baseUrl + document.getElementById('hidden').value;
}
var url = window.location.href;
for (let i = 0; i < elementList.length; i += 1) {
    if (sessionStorage.getItem(i + '') === '1') {
        elementList[i].checked = true;
    }

    $(elementList[i]).on('click', function () {
        if ($(elementList[i]).is(':checked')) {
            if (baseUrl.includes(elementList[i].value)) {
                location.reload();
                sessionStorage.setItem(i + '', '1');
                return
            }
            if (baseUrl.includes('brand=')) {
                newUrl = baseUrl + ',' + elementList[i].value;
                history.replaceState(null, null, newUrl);
            } else {
                newUrl = baseUrl + '&brand=' + elementList[i].value;
                history.replaceState(null, null, newUrl);
            }
            sessionStorage.setItem(i + '', '1');
        } else {
            if (baseUrl.includes(',' + elementList[i].value)) {
                newUrl = baseUrl.replace(',' + elementList[i].value, '');
                history.replaceState(null, null, newUrl);
            } else if (baseUrl.includes(elementList[i].value + ',')) {
                newUrl = baseUrl.replace(elementList[i].value + ',', '');
                history.replaceState(null, null, newUrl);
            } else {
                history.replaceState(null, null, baseUrl.substring(0,baseUrl.lastIndexOf('&')));
            }
            sessionStorage.setItem(i + '', '0');
        }
        location.reload();
    })
}

