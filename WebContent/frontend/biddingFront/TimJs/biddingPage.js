        var memId = $("#memId").val();
        var bdNo = $("#bdNo").val();
        var bidOver = true;

        function isLogin() {
            if (memId.trim().length === 0) {
                alert("memId is emptyString: please login first!");
                return false;
            } else {
                return true;
            }
        }

        (function newBid() {

            $('#submitBid').on("click", function(e) {
                if (!isLogin() || bidOver) {
                    return;
                }
                console.log('Login Checked');
                console.log(memId);
                // e.preventDefault();
                let currentPrice = $('#currentPrice').html();
                let bid = $('#submitBidPrice').val();
                let reg = /^\d+$/;

                if (!bid.match(reg)) {
                    alert("Please enter number");
                    return;
                }

                currentPrice = parseInt(currentPrice);
                bid = parseInt(bid);

                if (bid > currentPrice && bid > 10000) {
                    let ischecked = confirm("confirmation check, Your Price is:" + bid);
                    if (!ischecked) {
                        return;
                    }
                }

                if (bid > currentPrice) {
                    console.log("memId" + memId);
                    $.ajax({
                        method: "post",
                        url: "/G1/biddingPage/BdPageServlet",
                        data: { action: "newBid", bid: bid, memId: memId, bdNo: bdNo },
                        success: function(dataReturn) {
                            $('#currentPrice').text(bid);
                        },
                        error: function() {
                            alert("ajax failed");
                        }
                    });
                } else {
                    alert("please enter higher bid!");
                }
            });

            //================================

            $('#plus100').keydown(function(e) {
                if (e.keyCode == 13) {
                    e.preventDefault();
                    return false;
                } else {
                    alert("err");
                }
            });

            $('#plus100').on("click", function(e) {
                e.preventDefault();

                if (!isLogin() || bidOver) {
                    return;
                }

                let currentPrice = parseInt($('#currentPrice').html());
                let bid = currentPrice + 100;
                if (bid > currentPrice && bid > 15000) {
                    let ischecked = confirm("confirmation check,Your Price:" + bid);
                    if (!ischecked) {
                        return;
                    }
                }
                $.ajax({
                    method: "post",
                    url: "/G1/biddingPage/BdPageServlet",
                    data: { action: "newBid", bid: bid, memId: memId, bdNo, bdNo },
                    success: function(dataReturn) {
                        $('#currentPrice').text(dataReturn);
                    },
                    error: function() {
                        alert("ajax failed");
                    }
                })
            });

        }());


        // ================================================================

        (function() {

            let checkBidEnd = setInterval(function() {

                if (bidOver) {

                    document.getElementById("submitBid").disabled = true;
                    document.getElementById("submitBidPrice").disabled = true;
                    document.getElementById("plus100").disabled = true;
                    updateBid();
                    console.log('Bid End!');

                    $('#winningForm').css('display', 'block');

                    clearInterval(checkBidEnd);
                } else {

                    if (bdNo.trim().length === 0) {
                        alert("No bdNo");
                        return;
                    }
                    updateBid();
                }
            }, 1000)

        }());

        // =============================================
        function updateBid() {
            $.ajax({
                method: "post",
                url: "/G1/biddingPage/BdPageServlet",
                data: { action: "topBidders", bdNo: bdNo },
                success: function(dataReturn) {

                    var bidData = JSON.parse(dataReturn);

                    if (typeof(bidData.top1) !== "undefined") {
                        $('#top1 span').html(bidData.top1);
                        $('#top2 span').html(bidData.top2);
                        $('#top3 span').html(bidData.top3);
                        $('#currentPrice').html(bidData.price1);
                        $('#price1').html('$' + bidData.price1);
                        $('#price2').html('$' + bidData.price2);
                        $('#price3').html('$' + bidData.price3);
                        $('#numberOfBids').html(bidData.numberOfBids);
                        // ============================
                    }
                },
                error: function() {
                    console.log("(biddingPage.js) updateBid failed");
                },
            });
        }
        //========================================以下寫在贏家表單=======

        $(".checkout").on("click", function(e) {
            e.preventDefault();
            // formData= new FormData($('#checkoutForm'));
            //  $.ajax({
            //      type:"post",
            //      url:"<%=request.getContextPath()%>"+"/frontend/biddingFront/biddingPage.js",
            //      data: { formData },
            //      success:function(){
            //          alert('success!');
            //      },
            //      error:function(){
            //          alert('failed!');
            //      }
            //  })

            $('#checkout').click();
        });

        function getPrice() {
            $('#checkoutPrice').html($('#price1').html());
        };