package com.lobantm.hw02.DAO;

import com.lobantm.hw02.model.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAOImpl {
    private static Connection conn = ConnectionDB.getConnection();

    private DeveloperDAOImpl() {
    }

    private static PreparedStatement st = null;
    private static PreparedStatement st1 = null;
    private static ResultSet rs = null;
    private static ResultSet rs1 = null;

    public static void create(Developer developer) {
        try {
            st = conn.prepareStatement("INSERT INTO developers (" +
                    "name_dev, age, email, company, salary)" +
                    "VALUE (?, ?, ?, ?, ?)");
            st.setString(1, developer.getNameDeveloper());
            st.setInt(2, developer.getAge());
            st.setString(3, developer.getEmail());
            st.setInt(4, developer.getCompany().getId());
            st.setFloat(5, developer.getSalary());

            st.executeUpdate();

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Developer read(int id) {
        Developer developer = new Developer();
        List<Skill> skills = new ArrayList<Skill>();
        List<String> projects = new ArrayList<String>();
        developer.setId(id);
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM developers d " +
                    "INNER JOIN companies c ON d.company = c.id " +
                    "WHERE d.id = ?");
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                developer.setId(rs.getInt(1));
                developer.setNameDeveloper(rs.getString(2));
                developer.setAge(rs.getInt(3));
                developer.setEmail(rs.getString(4));
                developer.setCompany(new Company(rs.getInt(5), rs.getString(9)));
                developer.setSalary(rs.getFloat(6));

            }
            st.close();
            rs.close();
            //
            st1 = conn.prepareStatement(
                    "SELECT s.* " +
                            "FROM developers d  " +
                            "  INNER JOIN dev_skill ds ON d.id = ds.developer " +
                            "  INNER JOIN skills s ON ds.skill = s.id  " +
                            "WHERE d.id = ?");
            st1.setInt(1, developer.getId());
            rs1 = st1.executeQuery();
            while (rs1.next()) {
                skills.add(new Skill(rs1.getInt(1), rs1.getString(2)));
            }
            st1.close();
            developer.setSkillList(skills);

            //
            st1 = conn.prepareStatement(
                    "SELECT p.*, cu.*, c.* " +
                            "FROM developers d " +
                            "  INNER JOIN proj_dev pd ON pd.developer = d.id " +
                            "  INNER JOIN projects p ON pd.project = p.id " +
                            "  INNER JOIN customers cu on p.customer = cu.id " +
                            "  INNER JOIN companies c ON c.id = p.company " +
                            "WHERE d.id = ?"
            );
            st1.setInt(1, developer.getId());
            rs1 = st1.executeQuery();
            while (rs1.next()) {
                projects.add(rs1.getString(2));
            }
            st1.close();
            developer.setProjectList(projects);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return developer;
    }

    public static void delete(int id) {
        try {
            PreparedStatement st = conn.prepareStatement("DELETE FROM developers WHERE id = ?");
            st.setInt(1, id);
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            System.err.println("developer is foreign key");
            //e.printStackTrace();
        }
    }

    public static void update(Developer developer) {
        try {
            PreparedStatement st = conn.prepareStatement(
                    "UPDATE developers " +
                        "SET name_dev = ?, " +
                            "age = ?, " +
                            "email = ?, " +
                            "company = ?, " +
                            "salary = ? " +
                        " WHERE id = ?");
            st.setString(1, developer.getNameDeveloper());
            st.setInt(2, developer.getAge());
            st.setString(3, developer.getEmail());
            st.setInt(4, developer.getCompany().getId());
            st.setFloat(5, developer.getSalary());
            st.setInt(6, developer.getId());
            st.executeUpdate();
            st.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Developer> findAll() {
        List<Developer> list = new ArrayList<Developer>();
        Developer d = null;

        try {
            //query 1
            st = conn.prepareStatement(
                    "SELECT d.*, c.* " +
                    "FROM developers d "+
                    "INNER JOIN companies c ON d.company = c.id "
                    );
            rs = st.executeQuery();
            while (rs.next()) {
                d = new Developer();
                d.setId(rs.getInt(1));
                //name of developer
                d.setNameDeveloper(rs.getString(2));
                //data of dev
                d.setAge(rs.getInt(3));
                d.setEmail(rs.getString(4));
                d.setSalary(rs.getFloat(6));
                d.setCompany(new Company(rs.getInt(8),rs.getString(9)));

                //query 2 skills
                List<Skill> skills = new ArrayList<Skill>();
                st1 = conn.prepareStatement(
                        "SELECT s.* " +
                                "FROM developers d  " +
                                "  INNER JOIN dev_skill ds ON d.id = ds.developer " +
                                "  INNER JOIN skills s ON ds.skill = s.id  " +
                                "WHERE d.id = ?");
                st1.setInt(1, d.getId());
                rs1 = st1.executeQuery();
                while (rs1.next()) {
                    skills.add(new Skill(rs1.getInt(1), rs1.getString(2)));
                }
                st1.close();
                d.setSkillList(skills);

                //query 3 projects
                List<String> projects = new ArrayList<String>();
                st1 = conn.prepareStatement(
                        "SELECT p.*, cu.*, c.* " +
                                "FROM developers d " +
                                "  INNER JOIN proj_dev pd ON pd.developer = d.id " +
                                "  INNER JOIN projects p ON pd.project = p.id " +
                                "  INNER JOIN customers cu on p.customer = cu.id " +
                                "  INNER JOIN companies c ON c.id = p.company " +
                                "WHERE d.id = ?"
                );
                st1.setInt(1, d.getId());
                rs1 = st1.executeQuery();
                while (rs1.next()) {
                    projects.add(rs1.getString(2));
                }
                st1.close();
                d.setProjectList(projects);

                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
