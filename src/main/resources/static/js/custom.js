function scrollAction() {
  var scrollValue = $(this).scrollTop();
  if (scrollValue > 50) {
      $('#mysiteNav').addClass('navbar-white');
      $('#mysiteNav').removeClass('navbar-default');
      $('.bg-overlay').css('background-color', 'rgba(0,0,0,' +  (0.2 + (scrollValue / 1000)) + ')');
  }
  else {
      $('#mysiteNav').addClass('navbar-default');
      $('#mysiteNav').removeClass('navbar-white');
      $('.bg-overlay').css('background-color', 'rgba(0,0,0,0.2)');
  }

  $('.paralax').each(function(){
    var $bgobj = $(this);
    var yPos = -(scrollValue / 3);
    var coords = 'center '+ yPos + 'px';
    $bgobj.css({
      backgroundPosition: coords
    });
  });

}

$(document).scroll(function() {
  scrollAction();
});

$(document).ready(function(){
	scrollAction();
});
