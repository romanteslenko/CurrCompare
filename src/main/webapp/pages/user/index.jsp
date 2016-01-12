<%@ page import="com.teslenko.currcomp.domain.rates.RatesManager" %>
<%@ page import="com.teslenko.currcomp.domain.rates.Rate" %>
<%@ page import="java.util.List" %>
<%@ page import="com.teslenko.currcomp.domain.show.RatesPrinter" %>
<%@ page import="com.teslenko.currcomp.dao.ram.CashRatesHolder" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.Random" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>PROFITEXCHANGE.COM.UA</title>
    <style>
        <%@ include file='styles/style.css' %>
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header"><a href="#" id="site-name">PROFITEXCHANGE.COM.UA</a>
        <ul id="nav">
            <li class="current_page_item"><a href="#">Главная</a></li>
            <li><a href="/rates">Лучшие курсы</a></li>
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
            В режиме реального времени к вашим услугам всегда актуальная и проверенная информация о курсах
            электронных валют в "рунете".<br/>
            Вы можете использовать наш сервис для поиска оптимального курса обмена
            интересующих вас валют, а также для заработка на арбитражных сделках. Помимо этого мы предоставляем
            информацию о курсах наличного рынка валют Украины.</p>

        <div class="column left">
            <h2>Арбитражные сделки<br/>с онлайн деньгами</h2>

            <p>Выбирите валюту, на которой предполагаете заработать</p>

            <form action="/arbitrage" method="GET">
                <select name="currency">
                    <%
                        RatesManager ratesManager = new RatesManager();
                        Set<String> fromCurrencies = ratesManager
                                .getIncomingCurrenciesNames(ratesManager.grabRatesFromHolder());
                        RatesPrinter printer = new RatesPrinter();
                        for (String currency : fromCurrencies) {
                            out.println(printer.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>
                </select><br/>
                <input class="button" type="submit" value="Поиск"/><br/>
            </form>
        </div>
        <div class="column middle">
            <h2>Поиск оптимального<br/>курса обмена валют</h2>

            <p>Выберите валюты для обмена</p>

            <form action="/rates" method="GET">
                Отдаем:<br/>
                <select name="start-currency">
                    <%
                        for (String currency : fromCurrencies) {
                            out.println(printer.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>
                </select><br/>
                Получаем:<br/>
                <select name="target-currency">
                    <%
                        Set<String> toCurrencies = ratesManager
                                .getOutgoingCurrenciesNames(ratesManager.grabRatesFromHolder());
                        for (String currency : toCurrencies) {
                            out.println(printer.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>
                </select><br>
                <input type="hidden" name="search-type" value="direct"/>
                <input class="button" type="submit" value="Поиск"/><br/>
            </form>
        </div>
        <div class="column right">
            <h2>Наличные курсы<br/>в городах Украины</h2>
            <table id="cash">
                <tr>
                    <td>Валюта</td>
                    <td>Покупка</td>
                    <td>Продажа</td>
                </tr>
                <tr>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(0).getCurrencyName() %>
                    </td>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(0).getAskQuote() %>
                    </td>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(0).getBidQuote() %>
                    </td>
                </tr>
                <tr>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(1).getCurrencyName() %>
                    </td>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(1).getAskQuote() %>
                    </td>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(1).getBidQuote() %>
                    </td>
                </tr>
                <tr>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(2).getCurrencyName() %>
                    </td>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(2).getAskQuote() %>
                    </td>
                    <td><%= CashRatesHolder.getInstance().getCashRates().get(2).getBidQuote() %>
                    </td>
                </tr>
            </table>
            <p><a href="http://minfin.com.ua/currency/auction/usd/buy/all/">Подробнее>></a></p>
        </div>
        <br/>
        <br/>
        <div>
        <p style="text-shadow: #071728 1px 1px 3px; margin-top: 15px; margin-bottom: 10px">
            Рекомендуемые предложения интерактивных пунктов обмена валют:
        </p>
        <table>
            <tr>
                <td>Входящая валюта</td>
                <td>Исходящая валюта</td>
                <td>Курс обмена</td>
                <td>Пункт обмена</td>
            </tr>
            <%
                List<Rate> bestRates = ratesManager.selectBestRates(ratesManager.grabRatesFromHolder());
                Random random = new Random();
                for (int i = 0; i < 15; i++) {
                    out.println(printer.printRateInTableRow(bestRates.get(random.nextInt(bestRates.size()))));
                }
            %>
        </table>
        </div>
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