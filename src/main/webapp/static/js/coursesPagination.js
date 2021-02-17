let pages = document.querySelector('#pagination');
let form = document.createElement('form');
form.setAttribute("action", pagination);
form.setAttribute("method", "POST");
if (pagesQuantity < 6) {
    for (let i = 1; i <= pagesQuantity; i++) {
        addButton(i);
    }
} else {
    if (currentPage > 2) {
        addButton(1);
    }
    if (currentPage > 3) {
        addEllipsis();
    }
    if (currentPage != 1) {
        addButton(currentPage - 1);
    }
    addButton(currentPage);
    if (currentPage != pagesQuantity) {
        addButton(currentPage + 1);
    }
    if (currentPage < pagesQuantity - 2) {
        addEllipsis();
    }
    if (currentPage < pagesQuantity - 1)
    addButton(pagesQuantity);
}

function addButton(buttonValue) {
    let button = document.createElement('button');
    if (currentPage == buttonValue) {
        button.setAttribute("style", "border: 1px solid blue")
    }
    button.setAttribute("name", "numOfPage")
    button.setAttribute("type", "submit")
    button.setAttribute("value", buttonValue);
    button.innerText = buttonValue;
    form.appendChild(button);
}

function addEllipsis() {
    let b = document.createElement('b');
    b.setAttribute("style", "color: blue");
    b.innerText = '...';
    form.appendChild(b);
}

pages.appendChild(form);