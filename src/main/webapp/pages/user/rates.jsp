<%@ page import="com.teslenko.currcomp.domain.rates.RatesManager" %>
<%@ page import="com.teslenko.currcomp.domain.rates.Rate" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.teslenko.currcomp.domain.show.RatesPrinter" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>PROFITEXCHANGE.COM.UA</title>
    <style>
        <%@include file="styles/style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header"><a href="/index" id="site-name">PROFITEXCHANGE.COM.UA</a>
        <ul id="nav">
            <li class="current_page_item"><a href="/index">Главная</a></li>
            <li><a href="#">Лучшие курсы</a></li>
            <li><a href="/arbitrage">Арбитраж</a></li>
        </ul>
    </div>
    <div id="masthead">
        <p id="first-line">Мониторинг курсов</p>

        <p id="second-line">электронных и наличных валют</p>

        <p id="third-line">Мы ежеминутно сравниваем данные о курсах обмена электронных<br/>денег в более чем ста
            проверенных обменных пунктах</p>
    </div>
    <div id="content">
        <p id="about">
            В данном разделе вы можете сравнить курсы обмена электронных валют в различных обменных пунктах.<br/>
            Опция "многократный обмен" осуществляет выбоку цепочек обмена, которые могут привести к получению большей
            суммы валюты.<br/>
            При расчете результирующего курса не учитывается возможная комиссия обменника.
        </p>

        <div class="column left">
            <h2>Непосредственный обмен</h2>

            <p>Выберите валюты для обмена</p>

            <form action="/rates" method="GET">
                Отдаем:<br/>
                <select name="start-currency">

                    <%
                        RatesManager ratesManager = new RatesManager();
                        List<Rate> rates = ratesManager.grabRatesFromHolder();
                        Set<String> fromCurrencies = ratesManager.getIncomingCurrenciesNames(rates);
                        RatesPrinter ratesPrinter = new RatesPrinter();
                        for (String currency : fromCurrencies) {
                        out.println(ratesPrinter.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>

                </select><br/>
                Получаем:<br/>
                <select name="target-currency">

                    <%
                        Set<String> toCurrencies = ratesManager
                                .getOutgoingCurrenciesNames(ratesManager.grabRatesFromHolder());
                        for (String currency : toCurrencies) {
                            out.println(ratesPrinter.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>

                </select><br>
                <input type="hidden" name="search-type" value="direct">
                <input class="button" type="submit" value="Подбор"/><br/>
            </form>
        </div>
        <div class="column middle">
            <h2>Многократный обмен</h2>

            <p>Выберите валюты для обмена</p>

            <form action="/rates" method="GET">
                Отдаем:<br/>
                <select name="start-currency">

                    <%
                        for (String currency : fromCurrencies) {
                        out.println(ratesPrinter.printCurrencyNamesInSelectFrom(currency));
                    }
                    %>

                </select><br/>
                Получаем:<br/>
                <select name="target-currency">

                    <%
                        for (String currency : toCurrencies) {
                            out.println(ratesPrinter.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>

                </select><br>
                <input type="hidden" name="search-type" value="chains">
                <input class="button" type="submit" value="Подбор"/><br/>
            </form>
        </div>
        <div class="column right">
            <h2>Все лучшие варианты</h2>
            <p>Выборка лучших вариантов по всем возможным направлениям обмена</p>
            <form action="/rates" method="GET">
                <input class="button" type="submit" value="Просмотр"/>
            </form>
        </div>
        <br/>

        <table>
            <tr>
                <td>Входящая валюта</td>
                <td>Исходящая валюта</td>
                <td>Курс обмена</td>
                <td>Пункт обмена</td>
            </tr>

            <%
                List<Rate> bestRates = ratesManager.selectBestRates(rates);
                RatesManager.sortByCurrencyName(bestRates);
                for (Rate rate : bestRates) {
                    out.println(ratesPrinter.printRateInTableRow(rate));
                }
            %>

        </table>
    </div>
</div>
<div id="footer">
    <div id="footer-wrap">
        <p id="credit">&copy; Роман Тесленко, ХНУРЭ, 2015</p>

        <div style="clear:both;"></div>
    </div>
</div>
</body>
</html>