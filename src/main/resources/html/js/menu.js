const menu = document.querySelector('a.menu');
const nav = document.querySelector('nav');

menu.addEventListener('click', () => {
  nav.classList.toggle('menu-open')
});