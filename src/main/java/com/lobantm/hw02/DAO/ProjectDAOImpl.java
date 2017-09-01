package com.lobantm.hw02.DAO;

import com.lobantm.hw02.model.Company;
import com.lobantm.hw02.model.Customer;
import com.lobantm.hw02.model.Developer;
import com.lobantm.hw02.model.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAOImpl {
    private static Connection conn = ConnectionDB.getConnection();
    private static PreparedStatement st = null;
    private static PreparedStatement st1 = null;
    private static ResultSet rs = null;
    private static ResultSet rs1 = null;

    private ProjectDAOImpl() {
    }

    public static void create(Project project) {
        try {
            st = conn.prepareStatement(
                    "INSERT INTO projects (project, cost, customer, company) " +
                            "VALUE (?, ?, ?, ?)");

            st.setString(1, project.getNameProject());
            st.setFloat(2, project.getCost());
            st.setInt(3, project.getCustomer().getId());
            st.setInt(4, project.getCompany().getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Project read(int id) {
        Project project = new Project();
        List<String> developersList = new ArrayList<String>();
        try {
            st = conn.prepareStatement(
                    "SELECT * " +
                        "FROM projects p " +
                        "INNER JOIN customers cu ON cu.id = p.customer " +
                        "INNER JOIN companies c ON c.id = p.company " +
                        "WHERE id = ?");
            st.setInt(1,id);
            rs = st.executeQuery();

            if (rs.next()){
                project.setId(rs.getInt(1));
                project.setNameProject(rs.getString(2));
                project.setCost(rs.getFloat(3));
                project.setCustomer(new Customer(rs.getInt(7),rs.getString(8)));
                project.setCompany(new Company(rs.getInt(10),rs.getString(11)));

                st1 = conn.prepareStatement(
                        "SELECT d.name_dev " +
                                "FROM developers d " +
                                "INNER JOIN proj_dev pd ON pd.developer = d.id " +
                                "INNER JOIN projects p ON pd.project = p.project " +
                                "WHERE p.id = ?");
                st1.setInt(1, project.getId());
                rs1 = st1.executeQuery();
                while (rs1.next()){
                    developersList.add(rs1.getString(1));
                }
                project.setDeveloperList(developersList);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void delete(int id) {
        try {
            st = conn.prepareStatement("DELETE FROM projects WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.err.println("project is foreign key");
            //e.printStackTrace();
        }
    }

    public static void update(Project project) {
        try {
            st = conn.prepareStatement(
                    "UPDATE projects " +
                            "SET project = ?, " +
                            "cost = ?, " +
                            "customer = ?, " +
                            "company = ? " +
                            "WHERE  id = ? ");
            st.setString(1, project.getNameProject());
            st.setFloat(2, project.getCost());
            st.setInt(3, project.getCustomer().getId());
            st.setInt(4, project.getCompany().getId());
            st.setInt(5, project.getId());

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Project> findAll() {
        List<Project> list = new ArrayList<Project>();
        Project p = null;
        List<Developer> developers = new ArrayList<Developer>();
        //query 1
        try {
            st = conn.prepareStatement(
                    "SELECT p.*, c.*, cu.* FROM projects p " +
                            "INNER JOIN companies c ON p.company = c.id " +
                            "INNER JOIN customers cu ON p.customer = cu.id "
                            );
            rs = st.executeQuery();
            while (rs.next()){
                p = new Project();
                p.setId(rs.getInt(1));
                p.setNameProject(rs.getString(2));
                p.setCost(rs.getFloat(3));
                p.setCompany(new Company(rs.getInt(7),rs.getString(8)));
                p.setCustomer(new Customer(rs.getInt(10),rs.getString(11)));

                //query 2
                List<String> nameDevelopers = new ArrayList<String>();
                st1 = conn.prepareStatement(
                        "SELECT d.name_dev " +
                                "FROM projects p " +
                                "INNER JOIN proj_dev pd ON pd.project = p.id " +
                                "INNER JOIN developers d ON pd.developer = d.id " +
                                "WHERE p.id = ?");
                st1.setInt(1, p.getId());
                rs1 = st1.executeQuery();
                while (rs1.next()){
                    nameDevelopers.add(rs1.getString(1));
                }
                p.setDeveloperList(nameDevelopers);
                list.add(p);
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
