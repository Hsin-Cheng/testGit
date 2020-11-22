<%@ page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<jsp:useBean id="bdr" scope="application"
	class="com.bidding.model.BdRedis" />
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="Minimal Shop Theme">
<meta name="keywords"
	content="responsive, retina ready, html5, css3, shop, market, onli store, bootstrap theme" />
<meta name="author" content="KingStudio.ro">
<!-- favicon -->
<link rel="icon"
	href="<%=request.getContextPath()%>/frontend/template/images/favicon.png">
<!-- page title -->
<title>MS - Minimal Shop Theme</title>
<!-- bootstrap css -->
<link
	href="<%=request.getContextPath()%>/frontend/template/css/bootstrap.min.css"
	rel="stylesheet">
<!-- css -->
<link
	href="<%=request.getContextPath()%>/frontend/template/css/style.css"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/frontend/template/css/animate.css"
	rel="stylesheet">
<!-- fonts -->
<link
	href="https://fonts.googleapis.com/css?family=Rubik:400,500,700,900"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Lily+Script+One"
	rel="stylesheet">
<link
	href="<%=request.getContextPath()%>/frontend/template/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href='<%=request.getContextPath()%>/frontend/template/fonts/FontAwesome.otf'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/frontend/template/css/linear-icons.css">
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<style>
.memAvatar {
	width: 8%;
	display: inline-block;
}

.authAvatar {
	width: 30%;
}

img {
	max-width: 80%;
}

.blog.block img {
	margin: auto;
}

.price {
	margin-top: 3%;
}

.authorBlock {
	text-align: center;
}

.post-category h4 {
	display: inline-block;
	margin: -2% 2% 0 5%;
	color: #ad8b60;
}
#winningForm{
	display: none;
}
</style>
</head>

<body>
	<div style=""></div>
	<input id="memId" type="hidden" value="${memId}">
	<input id="bdNo" type="hidden" value="${bdNo}">
	<!-- preloader -->
	<div id="preloader">
		<div class="spinner spinner-round"></div>
	</div>
	<!-- / preloader -->
	<div id="top"></div>
	<!-- header -->
	<header>
		<!-- nav -->
		<nav class="navbar navbar-default nav-sec navbar-fixed-top">
			<div class="container">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="index.html"><img
						src="<%=request.getContextPath()%>/frontend/template/images/logo.png"
						alt="logo"></a>
				</div>
				<!-- / navbar-header -->
				<div class="secondary-nav">
					<a href="login-register.html" class="my-account space-right"><i
						class="fa fa-user"></i></a> <a href="shopping-cart.html"
						class="shopping-cart"><i class="fa fa-shopping-cart"></i> <span
						class="cart-badge">2</span></a>
				</div>
				<div class="navbar-collapse collapse text-center">
					<ul class="nav navbar-nav">
						<li><a href="index.html"><span>HOME</span></a></li>
						<li><a href="about.html"><span>ABOUT</span></a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><span>BLOG</span> <span
								class="dropdown-icon"></span></a>
							<ul class="dropdown-menu animated zoomIn fast">
								<li><a href="blog.html"><span>BLOG FULLWIDTH</span></a></li>
								<li><a href="blog-masonry.html"><span>BLOG
											MASONRY</span></a></li>
								<li class="active"><a href="blog-sidebar.html"><span>BLOG
											SIDEBAR</span></a></li>
								<li><a href="single-post-full.html"><span>POST
											FULLWIDTH</span></a></li>
								<li><a href="single-post.html"><span>POST
											SIDEBAR</span></a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><span>SHOP</span> <span
								class="dropdown-icon"></span></a>
							<ul class="dropdown-menu animated zoomIn fast">
								<li><a href="shop.html"><span>FULL WIDTH</span></a></li>
								<li><a href="shop-right.html"><span>RIGHT
											SIDEBAR</span></a></li>
								<li><a href="shop-left.html"><span>LEFT SIDEBAR</span></a></li>
								<li><a href="shop-masonry.html"><span>MASONRY</span></a></li>
								<li><a href="single-product.html"><span>SINGLE
											PRODUCT</span></a></li>
								<li><a href="single-product2.html"><span>SINGLE
											PRODUCT 2</span></a></li>
								<li><a href="single-product3.html"><span>SINGLE
											PRODUCT 3</span></a></li>
							</ul></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown" role="button" aria-haspopup="true"
							aria-expanded="false"><span>PAGES</span> <span
								class="dropdown-icon"></span></a>
							<ul class="dropdown-menu animated zoomIn fast">
								<li><a href="faq.html"><span>FAQ</span></a></li>
								<li><a href="shopping-cart.html"><span>SHOPPING
											CART</span></a></li>
								<li><a href="login-register.html"><span>LOGIN /
											REGISTER</span></a></li>
								<li><a href="my-account.html"><span>MY ACCOUNT</span></a></li>
								<li><a href="checkout.html"><span>CHECKOUT</span></a></li>
								<li><a href="404.html"><span>404 PAGE</span></a></li>
								<li><a href="components.html"><span>COMPONENTS</span></a></li>
							</ul></li>
						<li><a href="contact.html"><span>CONTACT</span></a></li>
					</ul>
				</div>
				<!--/ nav-collapse -->
			</div>
			<!-- / container -->
		</nav>
		<!-- / nav -->
		<!-- header-banner -->
		<div id="header-banner">
			<div class="banner-content single-page text-center">
				<div class="banner-border">
					<div class="banner-info">
						<h1>Bidding Page</h1>
						<p>Welcome! enjoy the bid!</p>
					</div>
					<!-- / banner-info -->
				</div>
				<!-- / banner-border -->
			</div>
			<!-- / banner-content -->
		</div>
		<!-- / header-banner -->
	</header>
	<!-- / header -->
	<!-- content -->
	<div id="page-content" class="container">
		<!-- blog content + sidebar -->
		<section id="blog">
			<div class="row">
				<!-- blog content area -->
				<div class="col-sm-8 col-md-9">
					<div class="blog block post-content-area">
						<div class="post-info-box">
							<img
								src="<%=request.getContextPath()%>/frontend/imgData/img (17).jpg"
								style="max-height: auto; width: 100%" alt="">
							<div class="space-50">&nbsp;</div>
							<p class="lead clockLead">Time remaining:</p>
							<div id="clock">
								<%@include file="/frontend/biddingFront/clock.jsp"%>
							</div>

							<div style="text-align: center;" id="winningForm">
								<h2>
									<a>Congratulations!</a>
								</h2>
								<!-- Trigger the modal with a button -->
								<button type="button" class="btn btn-info btn-lg"
									data-toggle="modal" data-target="#myModal" onclick="getPrice()">Proceed ></button>

								<!-- Modal -->
								<div class="modal fade" id="myModal" role="dialog">
									<div class="modal-dialog" style="width: auto;">

										<!-- Modal content-->
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal">&times;</button>
												<h4 class="modal-title">CHECKOUT</h4>
											</div>
											<div class="modal-body">
												<p>Congratulations for winning the bid!</p>
												<div class="container">
													<div class="row checkout-screen">
														<div class="col-sm-8 checkout-form">
															<form action="<%=request.getContextPath()%>/biddingPage/BdPageServlet" id="checkoutForm">
															<input type="hidden" name="action" value="checkout">
															<input type="hidden" name="bdNo" value="${bdNo}">
															<input type="hidden" name="memId" value="${memId}">
															<input type="hidden" name="bdProdNo" value="3001">
																<h4 class="space-left">CHECKOUT</h4>
																<p class="space-left have-account space-bottom">
																	please fill the form for payment  <a href="login-register.html"><span>terms</span></a>
																</p>
																<div class="row">
																	<div class="col-sm-6">
																		<input type="text" class="form-control"
																			name="bdFirstName" placeholder="*FIRST NAME"
																			required=""> <input type="text"
																			class="form-control" name="bdLastName"
																			placeholder="*LAST NAME" required=""> <input
																			type="email" class="form-control" name="bdEmail"
																			placeholder="*EMAIL" required="">

																	</div>
																	<div class="col-sm-6">
																		<input type="tel" class="form-control" name="bdPhone"
																			placeholder="*PHONE" required=""> <input
																			type="text" class="form-control" name="company"
																			placeholder="COMPANY"> <input type="text"
																			class="form-control" name="bdAddr"
																			placeholder="*ADDRESS" required="">
																	</div>
																</div>
																<!-- / row -->

																<div class="row">
																
																	<div class="col-sm-6">
																		<select class="form-control" name="city">
																			<optgroup label="city:">
																				<option value="s1">Miami</option>
																				<option value="s2">Melbourne</option>
																				<option value="s3">London</option>
																				<option value="s4">Barcelona</option>
																			</optgroup>
																		</select> <input type="text" class="form-control" name="bdZip"
																			placeholder="ZIP CODE" required="">
																	</div>
																</div>
																<!-- / row -->

																<div class="checkout-form-footer space-left space-right">
																	<textarea class="form-control" name="message"
																		placeholder="MESSAGE"></textarea>
																	<a href="" class="btn btn-primary-filled btn-rounded checkout"><i
																		class="lnr lnr-cart"></i><span>Checkout</span></a>
																		<button type="submit" id="checkout" style="display:none;"></button>
																</div>
																<!-- / checkout-form-footer -->
														</div>
														<!-- / checkout-form -->
														</form>
														<div class="col-sm-4 checkout-total">
															<h4>
																CART TOTAL: <span id="checkoutPrice">$0</span>
															</h4>
															<p>*The price includes shipping and taxes.</p>
															<div class="cart-total-footer">
																<a href=""
																	class="btn btn-default-filled btn-rounded"><i
																	class="lnr lnr-arrow-left"></i><span>Back to
																		Page</span></a> <a href="shop-right.html"
																	class="btn btn-primary-filled btn-rounded"><i
																	class="lnr lnr-store"></i><span>Go to Shop</span></a>
															</div>
															<!-- / cart-total-footer -->
														</div>
														<!-- / checkout-total -->
													<c:forEach var="message" items="${errorMsgs}">
														<span>${message}</span>
													</c:forEach>

													</div>
													<!-- / row -->
												</div>
											</div>
											<div class="modal-footer" style="text-align: center;">
												<button type="button" class="btn btn-default"
													data-dismiss="modal">cancel</button>
											</div>
										</div>

									</div>
								</div>

							</div>


							<p class="blog-post-footer"></p>
							<!-- / blog-block -->

							<h2 class="my-4">Bidders</h2>
							<div class="post-category" id="top1">
								<a href="#"><h4>Top1</h4> <img
									src="<%=request.getContextPath()%>/frontend/template/img/alien.svg"
									alt="Image" class="mr-2 memAvatar" style="margin-right: 1.5%;">
									<span>Jean Smith</span> <span class="pull-right price"
									id="price1">$0</span> </a>
							</div>
							<!-- / post-category -->
							<div class="post-category" id="top2">
								<a href="#"><h4>Top2</h4> <img
									src="<%=request.getContextPath()%>/frontend/template/img/ghost.svg"
									alt="Image" class="mr-2 memAvatar" style="margin-right: 1.5%;">
									<span class="mr-2">Chris Wilson</span> <span
									class="pull-right price" id="price2">$0</span> </a>
							</div>
							<!-- / post-category -->
							<div class="post-category" id="top3">
								<a href="#">
									<h4>Top3</h4> <img
									src="<%=request.getContextPath()%>/frontend/template/img/knight.svg"
									alt="Image" class="mr-12 memAvatar" style="margin-right: 1.5%;">
									<span>Kyle Anderson</span> <span class="pull-right price"
									id="price3">$0</span>
								</a>
							</div>


							<!-- / blog-block -->
							<div class="space-50">&nbsp;</div>
							<h3>Bidding ArtWork</h3>
							<div class="space-50">&nbsp;</div>
							<p class="post-meta">
								Posted by <a href="#">Admin</a> in <a href="#">Blog</a> on <a
									href="#">Nov. 17, 2016</a>
							</p>
							<p>Pellentesque pretium at justo iaculis vehicula. Aenean
								vestibulum purus a nulla sollicitudin molestie. Maecenas
								bibendum erat in erat maximus, vel imperdiet leo mattis. Integer
								vitae pellentesque massa. Fusce ac suscipit neque. Etiam justo
								risus, tristique id feugiat a, venenatis sed justo. Mauris
								aliquam venenatis nulla, et tincidunt enim fermentum ut. Aliquam
								mattis molestie est id condimentum.</p>
							<p>Nam porta lectus nec magna ullamcorper egestas eget at ex.
								In sit amet dolor vitae felis condimentum rutrum eget ut neque.
								Curabitur sagittis quam sodales, consequat urna sit amet, porta
								mi.</p>
							<p class="blog-post-footer">
								<a href="single-post.html"
									class="btn btn-primary-filled btn-rounded"><span>Read
										More</span><i class="fa fa-long-arrow-right"></i></a> <span
									class="post-icons pull-right"> <a href="#x"><i
										class="lnr lnr-thumbs-up"></i></a> <a href="#x"><i
										class="lnr lnr-bubble"></i></a>
								</span>
							</p>
							<!-- / blog-post-footer -->
						</div>
						<!-- / post-info-box -->
					</div>

					<div class="blog block post-content-area">
						<div id="blog-carousel" class="carousel slide"
							data-ride="carousel">
							<!-- wrapper for slides -->
							<div class="carousel-inner" role="listbox">
								<div class="item active">
									<img
										src="<%=request.getContextPath()%>/frontend/template/images/blog-post.jpg"
										alt="">
								</div>
								<div class="item">
									<img
										src="<%=request.getContextPath()%>/frontend/template/images/blog-post.jpg"
										alt="">
								</div>
								<div class="item">
									<img
										src="<%=request.getContextPath()%>/frontend/template/images/blog-post.jpg"
										alt="">
								</div>
							</div>
							<!-- / wrapper for slides -->
							<!-- controls -->
							<a class="left carousel-control" href="#blog-carousel"
								role="button" data-slide="prev"> <span
								class="lnr lnr-chevron-left" aria-hidden="true"></span>
							</a> <a class="right carousel-control" href="#blog-carousel"
								role="button" data-slide="next"> <span
								class="lnr lnr-chevron-right" aria-hidden="true"></span>
							</a>
							<!-- / controls -->
						</div>
						<!-- / blog-carousel -->
						<div class="post-info-box">
							<h3>SLIDER POST</h3>
							<p class="post-meta">
								Posted by <a href="#">Admin</a> in <a href="#">Design</a> on <a
									href="#">Nov. 16, 2016</a>
							</p>
							<p>Pellentesque pretium at justo iaculis vehicula. Aenean
								vestibulum purus a nulla sollicitudin molestie. Maecenas
								bibendum erat in erat maximus, vel imperdiet leo mattis. Integer
								vitae pellentesque massa. Fusce ac suscipit neque. Etiam justo
								risus, tristique id feugiat a, venenatis sed justo. Mauris
								aliquam venenatis nulla, et tincidunt enim fermentum ut. Aliquam
								mattis molestie est id condimentum.</p>
							<p>Nam porta lectus nec magna ullamcorper egestas eget at ex.
								In sit amet dolor vitae felis condimentum rutrum eget ut neque.
								Curabitur sagittis quam sodales, consequat urna sit amet, porta
								mi.</p>
							<p class="blog-post-footer">
								<a href="single-post.html"
									class="btn btn-primary-filled btn-rounded"><span>Read
										More</span><i class="fa fa-long-arrow-right"></i></a> <span
									class="post-icons pull-right"> <a href="#x"><i
										class="lnr lnr-thumbs-up"></i></a> <a href="#x"><i
										class="lnr lnr-bubble"></i></a>
								</span>
							</p>
							<!-- / blog-post-footer -->
						</div>
						<!-- / post-info-box -->
					</div>
					<!-- / blog-block -->
					<div class="space-50">&nbsp;</div>
				</div>
				<!-- / col-sm-8 col-md-9 -->
				<!-- / blog content area -->
				<!-- blog sidebar area -->
				<div class="col-sm-4 col-md-3 blog-sidebar">
					<div class="blog block categories-sidebar-widget">
						<div class="side-box mb-4">
							<p>
								Price: $<strong id="currentPrice" class="text-black">0</strong><br>
								Number of Bids: <strong id="numberOfBids" class="text-black">0</strong>
							</p>
							<form
								action="<%=request.getContextPath()%>/bidding/BiddingServlet">
								<div class="mb-4">
									<div class="pb-3" style="border-bottom: 1px solid #efefef;">
										<input id="submitBidPrice" name="bid" type="text"
											class="form-control mb-2" placeholder="$0.00">
									</div>
									<button type="button" id="submitBid"
										class="btn btn-secondary btn-block mt-4">Submit a Bid</button>
									<div style="text-align: center; margin: 5% 0">
										<span class="d-block text-center my-2">or</span>
									</div>
									<button id="plus100" class="btn btn-primary btn-block">+$100</button>
								</div>
								<p class="mb-0" style="text-align: center; margin-top: 10%;">
									<a href="">Sign In</a> / <a href="">Register</a>
								</p>
							</form>
						</div>
					</div>
					<!-- / categories-sidebar-widget -->
					<div class="space-50">&nbsp;</div>
					<div class="space-50">&nbsp;</div>
					<div class="blog block about-sidebar-widget authorBlock">
						<p class="blog-post-footer"></p>
						<img
							src="<%=request.getContextPath()%>/frontend/template/img/troglodyte.svg"
							alt="Image" class="img-fluid w-50 rounded-circle mb-4 authAvatar">
						<h4>AUTHOR</h4>
						<h3 class="h5 text-black">Collen Winston</h3>
						<p>Aenean vestibulum purus a nulla sollicitudin molestie.
							Maecenas bibendum erat in erat maximus.</p>
					</div>
					<!-- about-sidebar-widget -->
					<div class="space-50">&nbsp;</div>
					<p class="blog-post-footer"></p>
					<div class="blog block tags-sidebar-widget authorBlock">
						<div class="widget-title">
							<h4>TAGS CLOUD</h4>
						</div>
						<p>
							<a href="#" class="btn btn-xs btn-primary-filled">Blog</a> <a
								href="#" class="btn btn-xs btn-primary-filled">Travel</a> <a
								href="#" class="btn btn-xs btn-primary-filled">Design</a> <a
								href="#" class="btn btn-xs btn-primary-filled">Videography</a> <a
								href="#" class="btn btn-xs btn-primary-filled">Tutorials</a>
						</p>
					</div>
					<!-- about-sidebar-widget -->
				</div>
				<!-- / col-sm-4 col-md-3 -->
				<!-- / blog sidebar area -->
			</div>
			<!-- / row -->
		</section>
		<!-- / blog content + sidebar -->
		<!-- pagination -->
		<div class="pagination">
			<a href="#x" class="btn btn-direction btn-default-filled"><i
				class="fa fa-long-arrow-left"></i><span>Previous Page</span></a> <a
				href="#x" class="btn btn-direction btn-default-filled pull-right"><span>Next
					Page</span><i class="fa fa-long-arrow-right"></i></a>
		</div>
		<!-- / pagination -->
	</div>
	<!-- / container -->
	<!-- / content -->
	<!-- scroll to top -->
	<a href="#top" class="scroll-to-top page-scroll is-hidden"
		data-nav-status="toggle"><i class="fa fa-angle-up"></i></a>
	<!-- / scroll to top -->


	<!-- footer -->
	<footer class="light-footer">
		<div class="widget-area">
			<div class="container">
				<div class="row">
					<div class="col-md-4 widget">
						<div class="about-widget">
							<div class="widget-title-image">
								<img
									src="<%=request.getContextPath()%>/frontend/template/images/logo2.png"
									alt="">
							</div>
							<p>Vivamus consequat lacus quam, nec egestas quam egestas sit
								amet. Suspendisse et risus gravida tellus aliquam ullamcorper.
								Pellentesque elit dolor, ornare ut lorem nec, convallis nibh
								accumsan lacus morbi leo lipsum.</p>
						</div>
						<!-- / about-widget -->
					</div>
					<!-- / widget -->
					<!-- / first widget -->
					<div class="col-md-2 widget">
						<div class="widget-title">
							<h4>BRANDS</h4>
						</div>
						<div class="link-widget">
							<div class="info">
								<a href="#x">Brand 1</a>
							</div>
							<div class="info">
								<a href="#x">Brand 2</a>
							</div>
							<div class="info">
								<a href="#x">Brand 3</a>
							</div>
							<div class="info">
								<a href="#x">Brand 4</a>
							</div>
						</div>
					</div>
					<!-- / widget -->
					<!-- / second widget -->
					<div class="col-md-2 widget">
						<div class="widget-title">
							<h4>SUPPORT</h4>
						</div>
						<div class="link-widget">
							<div class="info">
								<a href="#x">Terms & Conditions</a>
							</div>
							<div class="info">
								<a href="#x">Shipping & Return</a>
							</div>
							<div class="info">
								<a href="faq.html">F.A.Q</a>
							</div>
							<div class="info">
								<a href="contact.html">Contact</a>
							</div>
						</div>
					</div>
					<!-- / widget -->
					<!-- / third widget -->
					<div class="col-md-4 widget">
						<div class="widget-title">
							<h4>CONTACT</h4>
						</div>
						<div class="contact-widget">
							<div class="info">
								<p>
									<i class="lnr lnr-map-marker"></i><span>Miami, S Miami
										Ave, SW 20th, Store No.1</span>
								</p>
							</div>
							<div class="info">
								<a href="tel:+0123456789"><i class="lnr lnr-phone-handset"></i><span>+0123
										456 789</span></a>
							</div>
							<div class="info">
								<a href="mailto:hello@yoursite.com"><i
									class="lnr lnr-envelope"></i><span>office@yoursite.com</span></a>
							</div>
							<div class="info">
								<i class="lnr lnr-thumbs-up"></i> <span class="social text-left">
									<a class="no-margin" href="#"><i class="fa fa-facebook"></i></a>
									<a href="#"><i class="fa fa-twitter"></i></a> <a href="#"><i
										class="fa fa-google-plus"></i></a> <a href="#"><i
										class="fa fa-linkedin"></i></a> <a href="#"><i
										class="fa fa-pinterest"></i></a>
								</span>
							</div>
						</div>
						<!-- / contact-widget -->
					</div>
					<!-- / widget -->
					<!-- / fourth widget -->
				</div>
				<!-- / row -->
			</div>
			<!-- / container -->
		</div>
		<!-- / widget-area -->
		<div class="footer-info">
			<div class="container">
				<div class="pull-left copyright">
					<p>
						<strong>© MS - MINIMAL SHOP THEME</strong>
					</p>
				</div>
				<span class="pull-right"> <img
					src="<%=request.getContextPath()%>/frontend/template/images/visa.png"
					alt=""> <img
					src="<%=request.getContextPath()%>/frontend/template/images/mastercard.png"
					alt=""> <img
					src="<%=request.getContextPath()%>/frontend/template/images/discover.png"
					alt=""> <img
					src="<%=request.getContextPath()%>/frontend/template/images/paypal.png"
					alt="">
				</span>
			</div>
			<!-- / container -->
		</div>
		<!-- / footer-info -->

	</footer>
	<!-- / footer -->
	<!-- javascript -->
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
		<script>
	

		</script>
</body>


</html>