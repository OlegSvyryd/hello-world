<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <!-- CSS -->
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/Welcome.css' />" >
    <link rel="stylesheet" type="text/css" href="<c:url value='../../CSS/animate.css' />" >

    <!-- Scripts -->
    <script src="<c:url value="../../JS/jquery.min.js" />"></script>

    <!-- Background image -->
    <style>
        html{
            background-color: #263238;
        }
        body{
            background: url('../../Images/background-welcome.jpeg') no-repeat 0 0;
            margin-left: -1px;
        }
    </style>

    <title>StoreBase | Welcome</title>
</head>
<body>

<header>
    <div>
        <spun class = 'logo'>StoreBase</spun>
        <spun class = 'content'>
            <ul>
                <li onclick="document.location.href='registration'">Приєднатись</li>
                  |
                <li onclick="document.location.href='login'">Увійти</li>
            </ul>
        </spun>
    </div>
</header>

<article>
    <spun class = "header-caption">StoreBase</spun>
    <spun class = "header-text">Система обліку та замовлень для торгівельних підприємств з можливістю продажу в мережі.</spun>
    <div><button id = "scroll-down" class="learn-more">Більше</button></div>

    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>

    <section class = "sect2">
        <img src="../../Images/store-icon.png" alt="Роздрібна торгівля" class="hero">
        <div class="column">
            <h2>Роздрібна торгівля</h2>
            <p>Ведення обліку товарів у магазинах, які спеціалізуються на роздрібній торгівлі.</p>
        </div>
    </section>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br>

    <section class = "sect1">
        <img src="../../Images/company-icon.png" alt="Супровід компаній" class="hero">
        <div class="column">
            <h2>Супровід компаній</h2>
            <p>Створення власної компанії, ведення обліку товарів та тісна співпраця з магазинами.</p>
        </div>
    </section>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br>

    <section class = "sect3">
        <img src="../../Images/web-store.png" alt="Web-магазин" class="hero">
        <div class="column">
            <h2>Web - магазин</h2>
            <p>Створення власного інтернет магазину та ведення обліку товарів в ньому.</p>
        </div>
    </section>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br><br><br><br><br><br><br>    <br><br><br><br><br><br><br><br><br>
    <br><br><br>
</article>

<footer>
    <div class="social-icons">
        <a href="https://www.facebook.com/oleg.svyryd" target="_blank">
            <img src="../../Images/icons/social-fb.png" alt="https://www.facebook.com/oleg.svyryd"/>
        </a>
        <a href="https://plus.google.com/u/0/101934570862476397709/posts" target="_blank">
            <img src="../../Images/icons/social-g.png" alt="https://plus.google.com/u/0/101934570862476397709/posts">
        </a>
        <a href="http://vk.com/axaxxaxaaxx" target="_blank">
            <img src="../../Images/icons/social-vk.png" alt="http://vk.com/axaxxaxaaxx"/>
        </a>
    </div>
    <div>Oleg Svyryd 2015</div>
</footer>

<script type="text/javascript">
    $(window).on('beforeunload', function(){
        $(window).scrollTop(0);
    });

    $(document).ready(function(){
        $('#scroll-down').click(function(){
            $("header div").animate({height:"0.3em"}, 1700);
            $('body').animate({scrollTop: 750}, 1750);
        });

        var h = $(window).height()-50;
        $(window).scroll(function(){
            if ( ($(this).scrollTop()+h) >= $(".sect2").offset().top) {
                $(".sect2 .column").css({visibility:"visible"});
                $(".sect2 .column").eq(0).addClass('animated bounceInLeft');
                $(".sect2 .column").eq(1).addClass('animated bounceInRight');
            }
            if ( ($(this).scrollTop()+h) >= $(".sect1").offset().top) {
                $(".sect1 .column").css({visibility:"visible"});
                $(".sect1 .column").eq(0).addClass('animated bounceInRight');
                $(".sect1 .column").eq(1).addClass('animated bounceInLeft');
            }
            if ( ($(this).scrollTop()+h) >= $(".sect3").offset().top) {
                $(".sect3 .column").css({visibility:"visible"});
                $(".sect3 .column").eq(0).addClass('animated bounceInLeft');
                $(".sect3 .column").eq(1).addClass('animated bounceInRight');
            }
        })
    });
</script>

</body>
</html>