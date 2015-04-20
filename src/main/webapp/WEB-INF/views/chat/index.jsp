<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spring Chat</title>
        <c:set var="base" value="${pageContext.servletContext.contextPath}" />
        <link rel="stylesheet" href="resources/css/foundation.css"/>
    </head>
    <body>
        <nav class="top-bar" data-topbar>
            <ul class="title-area">

                <li class="name">
                    <h1>
                        <a href="#">
                            Spring Chat
                        </a>
                    </h1>
                </li>
                <li class="toggle-topbar menu-icon"><a href="#"><span>menu</span></a></li>
            </ul>

            <section class="top-bar-section">

                <ul class="right">
                    <li class="divider"></li>
                    <li class="has-dropdown">
                        <a href="#">Main Item 1</a>
                        <ul class="dropdown">
                            <li><label>Section Name</label></li>
                            <li class="has-dropdown">
                                <a href="#" class="">Has Dropdown, Level 1</a>
                                <ul class="dropdown">
                                    <li><a href="#">Dropdown Options</a></li>
                                    <li><a href="#">Dropdown Options</a></li>
                                    <li><a href="#">Level 2</a></li>
                                    <li><a href="#">Subdropdown Option</a></li>
                                    <li><a href="#">Subdropdown Option</a></li>
                                    <li><a href="#">Subdropdown Option</a></li>
                                </ul>
                            </li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li class="divider"></li>
                            <li><label>Section Name</label></li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li class="divider"></li>
                            <li><a href="#">See all →</a></li>
                        </ul>
                    </li>
                    <li class="divider"></li>
                    <li><a href="#">Main Item 2</a></li>
                    <li class="divider"></li>
                    <li class="has-dropdown">
                        <a href="#">Main Item 3</a>
                        <ul class="dropdown">
                            <li><a href="#">Dropdown Option</a></li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li><a href="#">Dropdown Option</a></li>
                            <li class="divider"></li>
                            <li><a href="#">See all →</a></li>
                        </ul>
                    </li>
                </ul>
            </section>
        </nav>






        <div class="row">


            <div class="large-9 columns">

                <h3>Get in Touch!</h3>
                <p>We'd love to hear from you. You can either reach out to us as a whole and one of our awesome team members will get back to you, or if you have a specific question reach out to one of our staff. We love getting email all day <em>all day</em>.</p>

                <div class="section-container tabs" data-section>
                    <section class="section">
                        <h5 class="title"><a href="#panel1">Contact Our Company</a></h5>
                        <div class="content" data-slug="panel1">
                            <form>
                                <div class="row collapse">
                                    <div class="large-2 columns">
                                        <label class="inline">Your Name</label>
                                    </div>
                                    <div class="large-10 columns">
                                        <input type="text" id="yourName" placeholder="Jane Smith">
                                    </div>
                                </div>
                                <div class="row collapse">
                                    <div class="large-2 columns">
                                        <label class="inline"> Your Email</label>
                                    </div>
                                    <div class="large-10 columns">
                                        <input type="text" id="yourEmail" placeholder="jane@smithco.com">
                                    </div>
                                </div>
                                <label>What's up?</label>
                                <textarea rows="4"></textarea>
                                <button type="submit" class="radius button">Submit</button>
                            </form>
                        </div>
                    </section>
                    <section class="section">
                        <h5 class="title"><a href="#panel2">Specific Person</a></h5>
                        <div class="content" data-slug="panel2">
                            <ul class="large-block-grid-5">
                                <li><a href="/cdn-cgi/l/email-protection#274a464b6754425542494e535e09454409554245"><img src="http://placehold.it/200x200&text=[person]">Mal Reynolds</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#8ef4e1ebcefdebfcebe0e7faf7a0eceda0fcebec"><img src="http://placehold.it/200x200&text=[person]">Zoe Washburne</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#ec868d958289ac9f899e8982859895c28e8fc29e898e"><img src="http://placehold.it/200x200&text=[person]">Jayne Cobb</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#b1d5ded2f1c2d4c3d4dfd8c5c89fd3d29fc3d4d3"><img src="http://placehold.it/200x200&text=[person]">Simon Tam</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#462d2f2a2a3f2933312f322e2b3f2b2f28220635233423282f323f68242568342324"><img src="http://placehold.it/200x200&text=[person]">River Tam</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#28444d494e47465c404d5f41464c685b4d5a4d46415c51064a4b065a4d4a"><img src="http://placehold.it/200x200&text=[person]">Hoban Washburne</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#4e2c2121250e3d2b3c2b20273a37602c2d603c2b2c"><img src="http://placehold.it/200x200&text=[person]">Shepherd Book</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#ea81868f8faa998f988f84839e93c48889c4988f88"><img src="http://placehold.it/200x200&text=[person]">Kaywinnet Lee Fry</a></li>
                                <li><a href="/cdn-cgi/l/email-protection#ec85828d9e8dac8b99858088c28f83819cc28d8080"><img src="http://placehold.it/200x200&text=[person]">Inarra Serra</a></li>
                            </ul>
                        </div>
                    </section>
                </div>
            </div>







            <div class="large-3 columns">
                <h5>Map</h5>

                <p>
                    <a href="" data-reveal-id="mapModal"><img src="http://placehold.it/400x280"></a><br/>
                    <a href="" data-reveal-id="mapModal">View Map</a>
                </p>
                <p>
                    123 Awesome St.<br/>
                    Barsoom, MA 95155
                </p>
            </div>

        </div>






        <footer class="row">
            <div class="large-12 columns">
                <hr/>
                <div class="row">
                    <div class="large-6 columns">
                        <p>© Copyright no one at all. Go to town.</p>
                    </div>
                    <div class="large-6 columns">
                        <ul class="inline-list right">
                            <li><a href="#">Link 1</a></li>
                            <li><a href="#">Link 2</a></li>
                            <li><a href="#">Link 3</a></li>
                            <li><a href="#">Link 4</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>







        <div class="reveal-modal" id="mapModal">
            <h4>Where We Are</h4>
            <p><img src="http://placehold.it/800x600"/></p>


            <a href="#" class="close-reveal-modal">×</a>
        </div>

    </body>
</html>
