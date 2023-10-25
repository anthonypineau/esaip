function myFunction() {
    document.getElementById("dropdown-content").classList.toggle("show");
}

window.onclick = function(e) {
    if (!e.target.matches('.dropbutton')) {
        var myDropdown = document.getElementById("dropdown-content");
        if (myDropdown.classList.contains('show')) {
            myDropdown.classList.remove('show');
        }
    }
}