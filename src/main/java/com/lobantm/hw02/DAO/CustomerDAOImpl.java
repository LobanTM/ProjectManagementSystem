package com.lobantm.hw02.DAO;


import com.lobantm.hw02.model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl {
    private static Connection conn = ConnectionDB.getConnection();
    private static Statement st = null;
    private static ResultSet rs = null;

    public CustomerDAOImpl() {
    }

    public static void create(String customer) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO customers (customer) VALUE (?)");
            st.setString(1, customer);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Customer read(int id) {
        Customer customer = new Customer();
        customer.setId(id);
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM customers WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next())
                customer.setNameCustomer(rs.getString(2));
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    public static void delete(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM customers WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.err.println("customer is foreign key");
            //e.printStackTrace();
        }
    }

    public static void update(int id, String customerName) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE customers SET customer = ? WHERE id = ?");
            st.setString(1, customerName);
            st.setInt(2, id);
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Customer> findAll() {
        List<Customer> list = new ArrayList<Customer>();
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM customers");
            while(rs.next()){
                Customer customerRes = new Customer(rs.getInt(1), rs.getString(2));
                list.add(customerRes);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
