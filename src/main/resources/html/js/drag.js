function mousedragscrollable(element) {
  let target;
  const elms = document.querySelectorAll(element);
  for (let i = 0; i < elms.length; i++) {
    elms[i].addEventListener('mousedown', function (evt) {
      evt.preventDefault();
      target = elms[i];
      target.dataset.down = 'true';
      target.dataset.move = 'false';
      target.dataset.x = evt.clientX;
      target.dataset.y = evt.clientY;
      target.dataset.scrollleft = target.scrollLeft;
      target.dataset.scrolltop = target.scrollTop;
      evt.stopPropagation();
    });
    elms[i].addEventListener('click', function (evt) {
      if (elms[i].detaset != null && elms[i].detaset.move
          == 'true') {
        evt.stopPropagation();
      }
    });
  }
  document.addEventListener('mousemove', function (evt) {
    if (target != null && target.dataset.down == 'true') {
      evt.preventDefault();
      let move_x = parseInt(target.dataset.x) - evt.clientX;
      let move_y = parseInt(target.dataset.y) - evt.clientY;
      if (move_x !== 0 || move_y !== 0) {
        target.dataset.move = 'true';
      } else {
        return;
      }
      target.scrollLeft = parseInt(target.dataset.scrollleft) + move_x;
      target.scrollTop = parseInt(target.dataset.scrolltop) + move_y;
      evt.stopPropagation();
    }
  });
  document.addEventListener('mouseup', function (evt) {
    if (target != null && target.dataset.down == 'true') {
      target.dataset.down = 'false';
      evt.stopPropagation();
    }
  });
}

window.addEventListener('DOMContentLoaded', function () {
  mousedragscrollable('main');
});