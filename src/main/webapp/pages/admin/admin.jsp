<%@ page import="com.teslenko.currcomp.domain.exchanges.Exchange" %>
<%@ page import="java.util.List" %>
<%@ page import="com.teslenko.currcomp.dao.db.ExchangesJdbcDao" %>
<%@ page import="com.teslenko.currcomp.domain.show.ExchangesPrinter" %>
<%@ page import="java.util.Comparator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <meta charset="UTF-8">
    <title>PROFITEXCHANGE.COM.UA</title>
    <style>
        table {
            border-collapse: collapse;
            border: 2px solid black;
            margin-bottom: 15px;
        }

        td, th {
            border: 1px solid lightgray;
        }

        caption {
            font-size: 20px;
            font-weight: bold;
        }

        .id-column {
            width: 50px;
            text-align: center;
        }

        .xml-column {
            width: 300px;
        }

        .partner-column {
            width: 300px;
        }

    </style>
</head>
<body>
<div id="create-new">
    <table>
        <caption>Добавление новой записи</caption>
        <tr>
            <th>Domain</th>
            <th>Name</th>
            <th>Partner URL</th>
            <th>Rates URL</th>
            <th>Status</th>
            <th>Save data</th>
        </tr>
        <tr>
            <form action="/admin" method="POST">
                <td><input type="text" class="domain-column" name="domain"></td>
                <td><input type="text" class="name-column" name="name"></td>
                <td><input type="text" class="partner-column" name="partner-url"></td>
                <td><input type="text" class="xml-column" name="rates-url"></td>
                <td>
                    <select name="status" class="status-column">
                        <option value="active">active</option>
                        <option value="blocked">blocked</option>
                    </select>
                </td>
                <td><input type="submit" name="button" value="Add"></td>
            </form>
        </tr>
    </table>
</div>
<div id="data">
    <table>
        <caption>Изменение данных пунктов обмена</caption>
        <tr>
            <th>ID</th>
            <th>Domain</th>
            <th>Name</th>
            <th>Partner URL</th>
            <th>Rates URL</th>
            <th>Status</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>

        <%
            ExchangesJdbcDao dao = new ExchangesJdbcDao();
            List<Exchange> exchanges = dao.selectAllExchanges();
            exchanges.sort(new Comparator<Exchange>() {
                @Override
                public int compare(Exchange o1, Exchange o2) {
                    return o1.getId() - o2.getId();
                }
            });
            ExchangesPrinter printer = new ExchangesPrinter();
            for (Exchange exchange : exchanges) {
                out.println(printer.printExchangeData(exchange));
            }
        %>

    </table>
</div>
</body>
</html>