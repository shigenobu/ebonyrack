function scrollwindow(elem) {
  var e = document.getElementById(elem);
  var coords = e.coords.split(",");

  var left = coords[0];
  var top = coords[1];
  var width = parseInt(coords[2]) - parseInt(coords[0])
  var height = parseInt(coords[3]) - parseInt(coords[1])
  var main = document.getElementsByTagName("main")[0];
  main.scrollTo({
    left: left,
    top: top,
    behavior: "smooth",
  });

  const nav = document.querySelector('nav');
  nav.classList.toggle('menu-open')
}