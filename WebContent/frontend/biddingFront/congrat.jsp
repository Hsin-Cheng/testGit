<!doctype html>
<html>

<head>
    <meta charset='utf-8'>
    <meta name='viewport' content='width=device-width, initial-scale=1'>
    <title>Snippet - BBBootstrap</title>
<link
	href="<%=request.getContextPath()%>/frontend/template/css/bootstrap.min.css"
	rel="stylesheet">
<!-- css -->
<link
	href="<%=request.getContextPath()%>/frontend/template/css/style.css"
	rel="stylesheet">    
	<style> @import url(http://fonts.googleapis.com/css?family=Calibri:400,300,700);

 body {
     background-color: #D32F2F;
     font-family: 'Calibri', sans-serif !important
 }

 .mt-100 {
     margin-top: 100px
 }

 .container {
     margin-top: 200px
 }

 .card {
     position: relative;
     display: flex;
     width: 450px;
     flex-direction: column;
     min-width: 0;
     word-wrap: break-word;
     background-color: #fff;
     background-clip: border-box;
     border: 1px solid #d2d2dc;
     border-radius: 4px;
     -webkit-box-shadow: 0px 0px 5px 0px rgb(249, 249, 250);
     -moz-box-shadow: 0px 0px 5px 0px rgba(212, 182, 212, 1);
     box-shadow: 0px 0px 5px 0px rgb(161, 163, 164)
 }

 .card .card-body {
     padding: 1rem 1rem
 }

 .card-body {
     flex: 1 1 auto;
     padding: 1.25rem
 }

 p {
     font-size: 14px
 }

 h4 {
     margin-top: 18px
 }

 .cross {
     padding: 10px;
     color: #d6312d;
     cursor: pointer
 }

 .continue:focus {
     outline: none
 }

 .continue {
     border-radius: 5px;
     text-transform: capitalize;
     font-size: 13px;
     padding: 8px 19px;
     cursor: pointer;
     color: #fff;
     background-color: #D50000
 }

 .continue:hover {
     background-color: #D32F2F !important
 }</style>
    	<script
		src="<%=request.getContextPath()%>/frontend/template/js/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/template/js/bootstrap.min.js"></script>
	<!-- sticky nav -->
	<script
		src="<%=request.getContextPath()%>/frontend/template/js/jquery.easing.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/frontend/template/js/scrolling-nav-sticky.js"></script>
	<!-- / sticky nav -->
	<!-- hide nav -->
	<script
		src="<%=request.getContextPath()%>/frontend/template/js/hide-nav.js"></script>
	<!-- / hide nav -->
	<!-- preloader -->
	<script
		src="<%=request.getContextPath()%>/frontend/template/js/preloader.js"></script>
	<!-- / preloader -->
	<!-- / javascript -->
	<script
		src="<%=request.getContextPath()%>/frontend/biddingFront/TimJs/biddingPage.js"></script>

</head>

<body oncontextmenu='return false' class='snippet-body'>
    <div class="container d-flex justify-content-center">
        <div class="row">
            <div class="col-md-6"> <button type="button" class="btn btn-lg btn-warning" data-toggle="modal" data-target="#myModal">Open Congratulations card Modal</button> </div>
        </div>
    </div>
    <div class="modal fade" id="myModal" role="dialog">
        <div class="modal-dialog">
            <div class="card">
                <div class="text-right cross"> <i class="fa fa-times"></i> </div>
                <div class="card-body text-center"> <img src="https://img.icons8.com/bubbles/200/000000/trophy.png">
                    <h4>CONGRATULATIONS!</h4>
                    <p>You have been personally selected to take part in our 2017 annual visitors survey!</p> <button class="btn btn-out btn-square continue">CONTINUE</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>