let courses = [
    {name: "java1", teacher: "teacher1"},
    {name: "java2", teacher: "teacher2"},
    {name: "java3", teacher: "teacher3"},
    {name: "java4", teacher: "teacher4"},
    {name: "java5", teacher: "teacher5"},
    {name: "java6", teacher: "teacher6"},
    {name: "java7", teacher: "teacher7"},
    {name: "java8", teacher: "teacher8"},
    {name: "java9", teacher: "teacher9"},
    {name: "java10", teacher: "teacher10"},
    {name: "java11", teacher: "teacher11"},
    {name: "java12", teacher: "teacher12"},
    {name: "java13", teacher: "teacher13"},
    {name: "java14", teacher: "teacher14"},
    {name: "java15", teacher: "teacher15"},
    {name: "java16", teacher: "teacher16"},
    {name: "java17", teacher: "teacher17"},
    {name: "java18", teacher: "teacher18"}
];

let table = document.querySelector('#table');

let pagination = document.querySelector('#pagination');
let notesOnPage = 5;
let pagesQuantity = Math.ceil(courses.length / notesOnPage);
let items = [];
for (let i = 1; i <= pagesQuantity; i ++) {
    let li = document.createElement('li');
    li.innerHTML = i;
    pagination.appendChild(li);
    items.push(li);
}
let active;
showPage(items[0]);
for (let item of items) {
    item.addEventListener('click', function () {
        showPage(this);
    })
}
function showPage(item){
    if (active){
        active.classList.remove('active');
    }
    active = item;
    item.classList.add('active');
    let pageNum = +item.innerHTML;
    let notes = courses.slice((pageNum - 1) * notesOnPage, pageNum * notesOnPage);

    table.innerHTML = '';
    for (let note of notes){
        let tr = document.createElement('tr');
        table.appendChild(tr);

        let td;

        td = document.createElement('td');
        td.innerHTML = note.name;
        tr.appendChild(td);

        td = document.createElement('td');
        td.innerHTML = note.teacher;
        tr.appendChild(td);

    }
}