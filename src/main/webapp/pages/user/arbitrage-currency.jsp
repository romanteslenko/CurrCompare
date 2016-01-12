<%@ page import="com.teslenko.currcomp.domain.chains.TradeChain" %>
<%@ page import="com.teslenko.currcomp.domain.rates.RatesManager" %>
<%@ page import="com.teslenko.currcomp.domain.rates.Rate" %>
<%@ page import="java.util.List" %>
<%@ page import="com.teslenko.currcomp.domain.chains.TradeChainManager" %>
<%@ page import="com.teslenko.currcomp.domain.chains.ArbitrageManager" %>
<%@ page import="com.teslenko.currcomp.domain.show.ChainPrinter" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.teslenko.currcomp.domain.show.RatesPrinter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>PROFITEXCHANGE.COM.UA</title>
    <style type="text/css">
        <%@include file="styles/style.css"%>
    </style>
</head>
<body>
<div id="wrapper">
    <div id="header">
        <a href="/index" id="site-name">PROFITEXCHANGE.COM.UA</a>
        <ul id="nav">
            <li class="main_page"><a href="/index">Главная</a></li>
            <li><a href="/rates">Лучшие курсы</a></li>
            <li><a href="#">Арбитраж</a></li>
        </ul>
    </div>
    <div id="masthead">
        <p id="first-line">Мониторинг курсов</p>
        <p id="second-line">электронных и наличных валют</p>
        <p id="third-line">Мы ежеминутно сравниваем данные о курсах обмена электронных<br/>
            денег в более чем ста проверенных обменных пунктах</p>
    </div>
    <div id="content">
        <p id="about">
            В данном разделе представлены варианты цепочек последовательных операций обмена различных
            интерактивных валют, направленных на извлечение прибыли из разницы в курсах у различных пунктов
            обмена в одно и то же время.<br/>
            Вы можете выбрать интересующую вас валюту или ознакомиться со всеми лучшими вариантами арбитражных
            сделок<br/>
        </p>

        <div class="column left" style="width: 350px; margin-left: 20px">
            <h2>Просмотр по валюте</h2>

            <form action="/arbitrage" method="GET">
                <select name="currency">
                    <%
                        RatesManager ratesManager = new RatesManager();
                        List<Rate> rates = ratesManager.grabRatesFromHolder();
                        Set<String> fromCurrencies = ratesManager.getIncomingCurrenciesNames(rates);
                        RatesPrinter printer = new RatesPrinter();
                        for (String currency : fromCurrencies) {
                            out.println(printer.printCurrencyNamesInSelectFrom(currency));
                        }
                    %>
                </select><br/>
                <input class="button" type="submit" value="Подбор"/>
            </form>
        </div>
        <div class="column right" style="width: 350px;">
            <h2>Все лучшие варианты</h2>

            <form action="/arbitrage" method="GET">
                <select disabled>
                    <option value="all">Все валюты</option>
                </select><br/>
                <input class="button" type="submit" value="Поиск"/>
            </form>
        </div>
        <div>
            <table>
                <tr>
                    <td>Результат</td>
                    <td>Направление обмена</td>
                    <td>Курс</td>
                    <td>Пункт обмена</td>
                </tr>
                <%
                    String currency = request.getParameter("currency");
                    List<Rate> bestRates = ratesManager.selectBestRates(rates);
                    TradeChainManager chainManager = new TradeChainManager();
                    List<TradeChain> chains = chainManager.findAllChains(bestRates);
                    ArbitrageManager arbitrageManager = new ArbitrageManager();
                    List<TradeChain> arbitrages = arbitrageManager.findAllArbitrages(chains);
                    List<TradeChain> specialArbitrages = chainManager.selectSpecialChains(currency, currency, arbitrages);
                    ChainPrinter chainPrinter = new ChainPrinter();
                    for (TradeChain chain : specialArbitrages) {
                        out.println(chainPrinter.printArbitrageChain(chain));
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