        // When the user scrolls down 50px from the top of the document, resize the header's font size
        function init() {
            console.log('init');
            var timer = '';
            window.onscroll = function() {
                //     if (timer !== '') {
                //         clearTimeout(timer);
                //         timer = '';
                //     }
                //     timer = setTimeout(function() {
                //         scrollFunction();
                //         scrollBottom();
                //     }, 50);
                // };
                searchBarUnfocus();
                scrollFunction();
                scrollBottom();
            }
            scrollAfterSearch();
        }
        //scroll top animation 
        function scrollFunction() {
            // console.log(document.body.scrollTop > 50 || document.documentElement.scrollTop > 50);
            if (document.body.scrollTop > 80 || document.documentElement.scrollTop > 80) {
                document.getElementById("logoBlock").style.visibility = "hidden";
                document.getElementById("logoBlock").style.color = "rgba(255, 255, 255, 0)";
                document.getElementById("logoBlock").style.fontSize = "0px";
                document.getElementById("logoBlock").style.height = "0px";
                document.getElementById("rightHeader").style.display = "none";
                document.getElementById("leftHeader").style.display = "none";
                document.getElementById("searchBlock").style.boxShadow = "rgb(162 162 162 / 31%) 0px 3px 4px 0px";
                document.getElementById("searchBlock").style.boxShadow = "rgb(162 162 162 / 31%) 0px 3px 4px 0px";
                document.getElementById("searchBar1").style.width = "225px";
                document.getElementById("fixedTop").style.position = "fixed";


            } else {
                document.getElementById("logoBlock").style.visibility = null;
                document.getElementById("logoBlock").style.fontSize = null;
                document.getElementById("logoBlock").style.color = null;
                document.getElementById("logoBlock").style.height = null;
                document.getElementById("leftHeader").style.display = null;
                document.getElementById("rightHeader").style.display = null;
                document.getElementById("searchBlock").style.boxShadow = null;
                document.getElementById("searchBlock").style.transition = null;
                document.getElementById("searchBar1").style.width = null;
                document.getElementById("searchBlock").style.marginTop = null;
                document.getElementById("fixedTop").style = null;

            }
        }
        //scroll loading function

        console.log("window.innerHeight" + window.innerHeight);
        console.log("document.body.offsetHeight" + document.body.offsetHeight);


        function scrollAfterSearch() {

            if ($("#getPicResult").length !== 0) {
                $('html, body').animate({
                    scrollTop: $("#getPicResult").offset().top,
                }, 2000, 'swing');
            } else if ($("#errorMsgs").length !== 0) {
                console.log($("#errorMsgs").offset().top);
                $('html, body').animate({
                    // scrollTop: $("#errorMsgs").offset().top,
                    scrollTop: 650,

                }, 1300, 'swing');
            }
        }


        function scrollBottom() {

            // console.log('window.scrollY:' + window.scrollY);
            // if ((window.innerHeight + window.scrollY) > document.body.offsetHeight) {

            //     console.log('to bottom');
            //     // loadImg();
            //     // loadImg10();
            //     loadMasonry();

            // }

            $(window).scroll(function() {
                if ($(window).scrollTop() + $(window).height() == $(document).height()) {
                    // loadImg();
                    loadMasonry();
                }
            });
        }

        // scroll down reload function=========================
        function loadImg() {
            if ($('#lastDiv')) {
                var grid = document.getElementById("grid");
                for (var i = 1; i < 12; i++) {
                    var div = '<div class="grid-item"><img src="/G1/painter/ShowImage?ptr_no=' + random() + '></div>';
                    grid.append(div);
                }
            }
        }

        // ====================================================
        var rank = 5;

        function loadImg10() {

            $.ajax({

                url: "/G1/painter/TagGetPic",
                method: "post",
                data: { action: "getMostLiked", rank: rank },
                success: function(data) {
                    $(".grid").append(data);
                    rank += 5;
                },
                error: function() {
                    console.log('(index_front_js)LoadImg100 error');
                }

            })
        }
        //==========================================
        $(document).on("change", function(e) {
            loadMasonry();
        });

        //===========================================

        window.onload = () => {
            init();
            loadMasonry();
        }

        function random() {
            // var totalPic = $('#totalPic').html();
            return Math.floor(Math.random() * 7) + 1;
        }

        function loadMasonry() {
            var elem = document.querySelector('.grid');
            var msnry = new Masonry(elem, {
                // options
                itemSelector: '.grid-item',
                // columnWidth: 400,
                gutter: 25,
                fitWidth: true,

            });
            msnry.on('layoutComplete', () => {
                console.log('LayoutComplete');
            })
        }

        function toTop() {
            window.scrollTo(0, 0);
        }

        function searchBarUnfocus() {
            document.getElementById("searchBar1").blur();
        }
        // =================================================
        //=================ajax搜尋輸入模糊查詢返回推薦搜尋結果 ajax =====================
        $('#searchBar1').on("keyup", function() {
            var srtag = $(this).val();
            // alert($(this).val());
            // var url ="/controller/Search";
            $.ajax({
                method: "post",
                url: "/G1/painter/TagGetPic",
                data: { srtag: srtag, action: "searchByTag" },
                success: function(dataReceived) { //dataReceived=> out.println("<li> #"+result+"</li>")
                    // alert(dataReceived);
                    var bubbleJson = JSON.parse(dataReceived);
                    for (let i = 0; i <= 12; i++) {
                        var bubblei = "#bubble" + i;
                        var jsonBubblei = "bubble" + i;
                        if (bubbleJson[jsonBubblei]) {
                            $(bubblei + ">span").html(bubbleJson[jsonBubblei]);
                        } else {
                            $(bubblei + ">span").html("");
                        }
                    }
                    $("#searchList> div").html(dataReceived);
                },
                error: function() {
                    alert("(searchByTag)failed");
                }
            });
        });

        //===========bubble funciton==============================
        $('.bubble').on("click", function() {
            $('#searchBar1').val($(this).children().html());
            $('#searchForm1').submit();
        });