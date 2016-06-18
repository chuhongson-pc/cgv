function init_Home() {
    "use strict";

	//1. Init revolution slider and add arrows behaviour
				var api = $('.banner').revolution({
                    delay:9000,
                    startwidth:1170,
                    startheight:500,
             
                     onHoverStop:"on",
             
                     hideArrowsOnMobile:"off",

                     hideTimerBar:"on",
                     hideThumbs:'0',
             
                     keyboardNavigation:"on",
             
                     navigationType:"none",
                     navigationArrows:"solo",
             
                     soloArrowLeftHalign:"left",
                     soloArrowLeftValign:"center",
                     soloArrowLeftHOffset:0,
                     soloArrowLeftVOffset:0,
             
                     soloArrowRightHalign:"right",
                     soloArrowRightValign:"center",
                     soloArrowRightHOffset:0,
                     soloArrowRightVOffset:0,
             
             
                     touchenabled:"on",
                     swipe_velocity:"0.7",
                     swipe_max_touches:"1",
                     swipe_min_touches:"1",
                     drag_block_vertical:"false",
             
             
                     fullWidth:"off",
                     forceFullWidth:"off",
                     fullScreen:"off",
             
                  });

                    api.bind("revolution.slide.onchange",function (e,data) {
                         var slides = $('.banner .slide');
                         var currentSlide= data.slideIndex;

                         var nextSlide = slides.eq(currentSlide).attr('data-slide');
                         var prevSlide = slides.eq(currentSlide-2).attr('data-slide');

                         var lastSlide = slides.length;

                         if(currentSlide == lastSlide) {
                             var nextSlide = slides.eq(0).attr('data-slide');
                         }

                         //put onload value for slider navigation
                        $('.tp-leftarrow').html( '<span class="slider__info">' + prevSlide + '</span>');
                        $('.tp-rightarrow').html( '<span class="slider__info">' + nextSlide + '</span>');

                    });

	
	//2. Dropdown for authorize button
    		//user list option
            $('.auth__show').click(function (e){
                e.preventDefault();
                $('.auth__function').toggleClass('open-function')
            })

            $('.btn--singin').click(function (e){
                e.preventDefault();
                $('.auth__function').toggleClass('open-function')
            });

    //3. Mega select with filters (and markers)
    //Mega select interaction
                    $('.mega-select__filter').click(function(e){
                        //prevent the default behaviour of the link
                        e.preventDefault();
                        $('.select__field').val('');
           
                        $('.mega-select__filter').removeClass('filter--active');
                        $(this).addClass('filter--active');
                        
                        //get the data attribute of the clicked link(which is equal to value filter of our content)
                        var filter = $(this).attr('data-filter');

                        //Filter buttons
                        //show all the list items(this is needed to get the hidden ones shown)
                        $(".select__btn a").show();
                        $(".select__btn a").css('display', 'inline-block');
                        
                        /*using the :not attribute and the filter class in it we are selecting
                        only the list items that don't have that class and hide them '*/
                        $('.select__btn a:not(.' + filter + ')').hide();

                        //Filter dropdown
                        //show all the list items(this is needed to get the hidden ones shown)
                        $(".select__group").removeClass("active-dropdown");
                        $(".select__group").show();
                        
                        /*using the :not attribute and the filter class in it we are selecting
                        only the list items that don't have that class and hide them '*/
                        $('.select__group.' + filter).addClass("active-dropdown");
                        $('.select__group:not(.' + filter + ')').hide();

                        //Filter marker
                        //show all the list items(this is needed to get the hidden ones shown)
                        $(".marker-indecator").show();
                        
                        /*using the :not attribute and the filter class in it we are selecting
                        only the list items that don't have that class and hide them '*/
                        $('.marker-indecator:not(.' + filter + ')').hide();                        
                    });

                    $('.filter--active').trigger('click');
                    $('.active-dropdown').css("z-index", '-1');
            
                    $('.select__field').focus(function() {
                        $(this).parent().find('.active-dropdown').css("opacity", 1);
                        $(this).parent().find('.active-dropdown').css("z-index", '50');
                    });

                    $('.select__field').blur(function() {
                        $(this).parent().find('.active-dropdown').css("opacity", 0);
                        $(this).parent().find('.active-dropdown').css("z-index", '-1');
                    });

                    $('.select__variant').click( function (e) {
                        e.preventDefault();
                        $(this).parent().find('.active-dropdown').css("z-index", '50');
                        var value = $(this).attr('data-value');
                        $('.select__field').val(value);
                        $(this).parent().find('.active-dropdown').css("z-index", '-1');
                    });

                    $('body').click( function (e){
                      console.log(e.target);
                    })

    //4. Rating scrore init
    //Rating star
    $('.score').raty({
        width:130, 
        score: 0,
        path: 'images/rate/',
        starOff : 'star-off.svg',
        starOn  : 'star-on.svg' 
    });

    //5. Scroll down navigation function
    //scroll down
    $('.movie-best__check').click(function (ev) {
        ev.preventDefault();
        $('html, body').stop().animate({'scrollTop': $('#target').offset().top-30}, 900, 'swing');
    });
}