function scrollwindow(elem) {
  var e = document.getElementById(elem);
  var left = e.getAttribute("x");
  var top = e.getAttribute("y");
  var main = document.getElementsByTagName("main")[0];
  main.scrollTo({
    left: left,
    top: top,
    behavior: "smooth",
  });
  highlight(e);

  const nav = document.querySelector('nav');
  nav.classList.toggle('menu-open')
}