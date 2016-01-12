package com.teslenko.currcomp.dao.db;

import com.teslenko.currcomp.domain.exchanges.Exchange;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.teslenko.currcomp.dao.db.JbdcDriverInitializer.initDriver;

/**
 * Provides functionality to work with Exchanges table in database.
 */
public class ExchangesJdbcDao {
    private static final String DB_DRIVER_CLASS = "org.postgresql.Driver";
    private static final String DB_URI = "jdbc:postgresql://127.0.0.1:5432/test";
    private static final String DB_USER_NAME = "postgres";
    private static final String DB_PASSWORD = "admin";

    static {
        initDriver(DB_DRIVER_CLASS);
    }

    /**
     * Allows to get information about exchanges
     * from database and create a list of exchanges
     * @return list of Exchange objects
     */
    public List<Exchange> selectAllExchanges() {
        List<Exchange> exchanges = new ArrayList<>();
        String selectSQL = "SELECT * FROM exchanges";
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Exchange exchange = new Exchange();
                setExchangeFields(resultSet, exchange);
                exchanges.add(exchange);
            }
        } catch (SQLException e1) {
            System.out.println("Some trouble with database happened");
            e1.printStackTrace();
        }
        return exchanges;
    }

    /**
     * Allows to get information about exchanges that are
     * not blocked by some reason from database and create
     * a list of exchanges
     * @return list of Exchange objects where status field is active
     */
    public List<Exchange> selectActiveExchanges() {
        List<Exchange> exchanges = new ArrayList<>();
        String selectSQL = "SELECT * FROM exchanges WHERE status = 'active'";
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(selectSQL);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Exchange exchange = new Exchange();
                setExchangeFields(resultSet, exchange);
                exchanges.add(exchange);
            }
        } catch (SQLException e1) {
            System.out.println("Some trouble with database happened");
            e1.printStackTrace();
        }
        return exchanges;
    }

    public void insertRecord(String domain, String name, String partnerURL, String ratesURL, String status) {
        String insertSQL = "INSERT INTO exchanges (ex_domain, ex_name, partner_url, rates_url, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(insertSQL)) {
            statement.setString(1, domain);
            statement.setString(2, name);
            statement.setString(3, partnerURL);
            statement.setString(4, ratesURL);
            statement.setString(5, status);
            statement.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("Some trouble with database happened");
            e1.printStackTrace();
        }
    }

    public void deleteRecord(int id) {
        String deleteSQL = "DELETE FROM exchanges WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("Some trouble with database happened");
            e1.printStackTrace();
        }
    }

    public void updateRecord(String domain, String name, String partnerURL, String ratesURL, String status, int id) {
        String updateSQL = "UPDATE exchanges SET ex_domain = ?, ex_name = ?, partner_url = ?, rates_url = ?, status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setString(1, domain);
            statement.setString(2, name);
            statement.setString(3, partnerURL);
            statement.setString(4, ratesURL);
            statement.setString(5, status);
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("Some trouble with database happened");
            e1.printStackTrace();
        }
    }

    public void updateStatus(String status, int id) {
        String updateSQL = "UPDATE exchanges SET status = ? WHERE id = ?";
        try (Connection connection = DriverManager.getConnection(DB_URI, DB_USER_NAME, DB_PASSWORD);
             PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setString(1, status);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e1) {
            System.out.println("Some trouble with database happened");
            e1.printStackTrace();
        }
    }

    private void setExchangeFields(ResultSet resultSet, Exchange exchange) throws SQLException {
        exchange.setDomain(resultSet.getString("ex_domain"));
        exchange.setName(resultSet.getString("ex_name"));
        exchange.setPartnerURL(resultSet.getString("partner_url"));
        exchange.setRatesURL(resultSet.getString("rates_url"));
        exchange.setStatus(resultSet.getString("status"));
        exchange.setId(resultSet.getInt("id"));
    }
}
