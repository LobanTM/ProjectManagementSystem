package com.lobantm.hw02.DAO;

import com.lobantm.hw02.model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl {
    private static Connection conn = ConnectionDB.getConnection();
    private static Statement st = null;
    private static ResultSet rs = null;

    public CompanyDAOImpl() {
    }

    public static void create(String company) {
        try {
            PreparedStatement st = conn.prepareStatement("INSERT INTO companies (company) VALUE (?)");
            st.setString(1, company);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Company read(int id) {
        Company company = new Company();
        company.setId(id);
        try {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM companies WHERE id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next())
                company.setNameCompany(rs.getString(2));
            st.close();
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return company;
    }

    public static void delete(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM companies WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.err.println("company is foreign key");
            //e.printStackTrace();
        }
    }

    public static void update(int id, String companyName) {
        try {
            PreparedStatement st = conn.prepareStatement("UPDATE companies SET company = ? WHERE id = ?");
            st.setString(1, companyName);
            st.setInt(2, id);
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Company> findAll() {
        List<Company> list = new ArrayList<Company>();

        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT * FROM companies");
            while(rs.next()){
                Company companyRes = new Company(rs.getInt(1), rs.getString(2));
                list.add(companyRes);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
