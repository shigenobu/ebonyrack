var es = document.querySelectorAll("area");
for (let i = 0; i < es.length; i++) {
  es[i].addEventListener('click', (e) => {
    var id = "dialog-" + e.target.id;
    var d = document.getElementById(id);
    d.addEventListener('click', (ee) => ee.stopPropagation());
    d.addEventListener('mouseover', (ee) => ee.stopPropagation());
    d.addEventListener('mousemove', (ee) => ee.stopPropagation());
    d.addEventListener('mousedown', (ee) => ee.stopPropagation());

    var cid = "close-" + e.target.id;
    var cd = document.getElementById(cid);
    cd.addEventListener('click', () => {
      if (d.open) {
        d.close();
      }
    })
    d.showModal();
  });
}