package com.lobantm.hw02.view;


import com.lobantm.hw02.DAO.*;
import com.lobantm.hw02.model.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private static final int EXIT_ITEM = 0;

    private static final int NEW_ITEM       = 1;
    private static final int READ_ITEM      = 2;
    private static final int UPDATE_ITEM    = 3;
    private static final int DELETE_ITEM    = 4;
    private static final int REPORT_ITEM    = 5;

    private static final int NEW_DEVELOPER  = 11;
    private static final int NEW_PROJECT    = 12;
    private static final int NEW_COMPANY    = 13;
    private static final int NEW_CUSTOMER   = 14;
    private static final int NEW_SKILL      = 15;

    private static final int READ_DEVELOPER  = 21;
    private static final int READ_PROJECT    = 22;
    private static final int READ_COMPANY    = 23;
    private static final int READ_CUSTOMER   = 24;
    private static final int READ_SKILL      = 25;

    private static final int UPDATE_DEVELOPER  = 31;
    private static final int UPDATE_PROJECT    = 32;
    private static final int UPDATE_COMPANY    = 33;
    private static final int UPDATE_CUSTOMER   = 34;
    private static final int UPDATE_SKILL      = 35;

    private static final int DELETE_DEVELOPER  = 41;
    private static final int DELETE_PROJECT    = 42;
    private static final int DELETE_COMPANY   = 43;
    private static final int DELETE_CUSTOMER   = 44;
    private static final int DELETE_SKILL      = 45;

    private static final int SHOW_ALL_DEVELOPERS  = 51;
    private static final int SHOW_ALL_PROJECTS    = 52;
    private static final int SHOW_ALL_COMPANIES   = 53;
    private static final int SHOW_ALL_CUSTOMERS   = 54;
    private static final int SHOW_ALL_SKILLS      = 55;

    private Scanner scan = null;

    public Menu() {
        this.scan = new Scanner(System.in);
    }

    public void main(){
        //главное меню
        int choise = EXIT_ITEM;

        do {
            showMainMenu();
            choise = getInput();

            switch (choise) {
                case EXIT_ITEM: System.exit(0); break;
                case NEW_ITEM: showOneMenu(); break;
                case READ_ITEM: showTwoMenu(); break;
                case UPDATE_ITEM: showThreeMenu(); break;
                case DELETE_ITEM: showFourMenu(); break;
                case REPORT_ITEM: showFiveMenu(); break;

                case NEW_DEVELOPER: addDeveloper(); break;
                case NEW_PROJECT: addProject(); break;
                case NEW_COMPANY: addCompany(); break;
                case NEW_CUSTOMER: addCustomer(); break;
                case NEW_SKILL: addSkill(); break;

                case READ_DEVELOPER: readDeveloper(); break;
                case READ_PROJECT: readProject(); break;
                case READ_COMPANY: readCompany(); break;
                case READ_CUSTOMER: readCustomer(); break;
                case READ_SKILL: readSkill(); break;

                case UPDATE_DEVELOPER: updateDeveloper(); break;
                case UPDATE_PROJECT: updateProject(); break;
                case UPDATE_COMPANY: updateCompany(); break;
                case UPDATE_CUSTOMER: updateCustomer(); break;
                case UPDATE_SKILL: updateSkill(); break;

                case DELETE_DEVELOPER: deleteDeveloper(); break;
                case DELETE_PROJECT: deleteProject(); break;
                case DELETE_COMPANY: deleteCompany(); break;
                case DELETE_CUSTOMER: deleteCustomer(); break;
                case DELETE_SKILL: deleteSkill(); break;

                case SHOW_ALL_DEVELOPERS: showDevelopers(); break;
                case SHOW_ALL_PROJECTS: showProjects(); break;
                case SHOW_ALL_COMPANIES: showCompanies(); break;
                case SHOW_ALL_CUSTOMERS: showCustomers(); break;
                case SHOW_ALL_SKILLS: showSkills(); break;

            }
        } while (choise != EXIT_ITEM);
    }

    //SERVICES
    //SHOW ALL
    private void showSkills() {
        List<Skill> list = SkillDAOImpl.findAll();
        for (Skill skill : list) {
            System.out.println(skill.getNameSkill());
        }
    }

    private void showCustomers() {
        List<Customer> list = CustomerDAOImpl.findAll();
        for(Customer cu : list){
            System.out.println(cu);
        }
    }

    private void showCompanies() {
        List<Company> list = CompanyDAOImpl.findAll();
        for(Company c : list){
            System.out.println(c);
        }
    }

    private void showProjects() {
        List<Project> list = ProjectDAOImpl.findAll();
        for(Project p : list){
            System.out.println(p);
        }
    }

    private void showDevelopers() {
        List<Developer> list = DeveloperDAOImpl.findAll();
        for (Developer d: list){
            System.out.println(d);
        }
    }

    //DELETE
    private void deleteSkill() {
        int number = scanIntWithRetry("enter number of skill");
        SkillDAOImpl.delete(number);
        showSkills();
    }

    private void deleteCustomer() {
        int number = scanIntWithRetry("enter number of customer");
        CustomerDAOImpl.delete(number);
        showCustomers();
    }

    private void deleteCompany() {
        int number = scanIntWithRetry("enter number of company");
        CompanyDAOImpl.delete(number);
        showCompanies();
    }

    private void deleteProject() {
        int number = scanIntWithRetry("enter number of project");
        ProjectDAOImpl.delete(number);
        showProjects();
    }

    private void deleteDeveloper() {
        int number = scanIntWithRetry("enter number of developer");
        DeveloperDAOImpl.delete(number);
        showDevelopers();
    }

    //UPDATE
    private void updateSkill() {
        int number = scanIntWithRetry("enter number of skill");
        System.out.println("enter new skill");
        String skillName = scan.nextLine();
        SkillDAOImpl.update(number, skillName);
        showSkills();
    }

    private void updateCustomer() {
        int number = scanIntWithRetry("enter number of customer");
        System.out.println("enter new customer");
        String customerName = scan.nextLine();
        CustomerDAOImpl.update(number, customerName);
        showCustomers();
    }

    private void updateCompany() {
        int number = scanIntWithRetry("enter number of company");
        System.out.println("enter new company");
        String companyName = scan.nextLine();
        CompanyDAOImpl.update(number, companyName);
        showCompanies();
    }

    private void updateProject() {
        int id = scanIntWithRetry("enter number of project");
        Project p = new Project();
        p.setId(id);
        System.out.println("enter new name of project");
        p.setNameProject(scan.nextLine());

        showCompanies();
        int number = scanIntWithRetry("enter id company of project");
        p.setCompany(CompanyDAOImpl.read(number));
        showCustomers();
        number = scanIntWithRetry("enter id customer of project");
        p.setCustomer(CustomerDAOImpl.read(number));

        ProjectDAOImpl.update(p);
        showProjects();
    }

    private void updateDeveloper() {
        int id = scanIntWithRetry("enter number of developer");
        Developer d = new Developer();
        d.setId(id);
        System.out.println("enter developers name");
        d.setNameDeveloper(scan.nextLine());

        int number = scanIntWithRetry("enter age developer");
        d.setAge(number);
        System.out.println("enter email developer");
        d.setEmail(scan.nextLine());
        showCompanies();
        number = scanIntWithRetry("enter id company of developer");
        d.setCompany(CompanyDAOImpl.read(number));
        float salary = scanIntWithRetry("enter salary of developer");
        d.setSalary(salary);

        DeveloperDAOImpl.update( d);
        showDevelopers();
    }

    //READ
    private void readSkill() {
        int number = scanIntWithRetry("enter number of skill");
        Skill skill = SkillDAOImpl.read(number);
        if ( skill != null)
            System.out.println( skill.getNameSkill() );
        else System.out.println("table is empty");
    }

    private void readCustomer() {
        int number = scanIntWithRetry("enter number of customer");
        Customer customer = CustomerDAOImpl.read(number);
        if ( customer != null)
            System.out.println( customer.getNameCustomer() );
        else System.out.println("table is empty");
    }

    private void readCompany() {
        int number = scanIntWithRetry("enter number of company");
        Company company = CompanyDAOImpl.read(number);
        if ( company != null)
            System.out.println( company.getNameCompany() );
        else System.out.println("table is empty");
    }

    private void readProject() {
        int number = scanIntWithRetry("enter number of project");
        Project project = ProjectDAOImpl.read(number);
        if ( project != null)
            System.out.println( project.getNameProject() );
        else System.out.println("table is empty");
    }

    private void readDeveloper() {
        int number = scanIntWithRetry("enter number of developer");
        Developer d = DeveloperDAOImpl.read(number);
        if (d != null)
            System.out.println(d);
        else System.out.println("table is empty");
    }

    //NEW
    private void addSkill() {
        System.out.println("enter new skill");
        String skillName = scan.nextLine();
        SkillDAOImpl.create(skillName);
        showSkills();
    }

    private void addCustomer() {
        System.out.println("enter new customer");
        String customerName = scan.nextLine();
        CustomerDAOImpl.create(customerName);
        showCustomers();
    }

    private void addCompany() {
        System.out.println("enter new company");
        String companyName = scan.nextLine();
        CompanyDAOImpl.create(companyName);
        showCompanies();
    }

    private void addProject() {
        Project project = new Project();
        System.out.println("enter new project");
        project.setNameProject(scan.nextLine());
        showCompanies();
        int number = scanIntWithRetry("enter id company of project");
        project.setCompany(CompanyDAOImpl.read(number));
        showCustomers();
        number = scanIntWithRetry("enter id customer of project");
        project.setCustomer(CustomerDAOImpl.read(number));


        ProjectDAOImpl.create(project);
        showProjects();
    }

    private void addDeveloper() {
        Developer developer = new Developer();
        System.out.println("enter new developer");
        developer.setNameDeveloper(scan.nextLine());
        int number = scanIntWithRetry("enter age developer");
        developer.setAge(number);
        System.out.println("enter email developer");
        developer.setEmail(scan.nextLine());
        showCompanies();
        number = scanIntWithRetry("enter id company of developer");
        developer.setCompany(CompanyDAOImpl.read(number));
        float salary = scanIntWithRetry("enter salary of developer");
        developer.setSalary(salary);

        DeveloperDAOImpl.create(developer);
        showDevelopers();
    }

    //INPUT CHOISE MENU
    private Float scanFloatWithRetry(String ageMsg) {
        Float sum = null;
        String ageStr;
        do {
            System.out.println(ageMsg);
            ageStr = scan.nextLine();
            try {
                sum = Float.valueOf(ageStr);
            } catch (NumberFormatException nfe) {
                System.out.println("Not an int value, input again");
            }
        } while (sum == null);
        return sum;
    }

    private Double scanDoubleWithRetry(String ageMsg) {
        Double sum = null;
        String ageStr;
        do {
            System.out.println(ageMsg);
            ageStr = scan.nextLine();
            try {
                sum = Double.valueOf(ageStr);
            } catch (NumberFormatException nfe) {
                System.out.println("Not an int value, input again");
            }
        } while (sum == null);
        return sum;
    }

    private Date scanDateWithRetry(String message) {
        final String DATE_FORMAT = "dd.MM.yyyy";
        Date date = null;
        String dateStr;
        do {
            System.out.println(message + "(" + DATE_FORMAT + ")");
            dateStr = scan.nextLine();
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            try {
                date = sdf.parse(dateStr);
            } catch (ParseException pe) {
                System.out.println("Illegal date format, correct format is \'" + DATE_FORMAT + "\', try again");
            }
        } while (date == null);
        return date;
    }

    private int scanIntWithRetry(String ageMsg) {
        Integer age = null;
        String ageStr;
        do {
            System.out.println(ageMsg);
            ageStr = scan.nextLine();
            try {
                age = Integer.valueOf(ageStr);
            } catch (NumberFormatException nfe) {
                System.out.println("Not an int value, input again");
            }
        } while (age == null);

        return age;
    }

    private int getInput() {
        int res = -1;
        String inputStr = scan.nextLine();
        try {
            res = Integer.parseInt(inputStr);
        } catch (NumberFormatException nfe) {
            System.out.println("String " + inputStr + " is not a number");
        }
        return res;
    }

    //SHOW MENU
    public void showMainMenu() {
        System.out.println("-----------------");
        System.out.println("1. New data     2. read data    3. Edit data    4. Delete   5. Reports      0. Exit");
    }

    public void showOneMenu() {
        System.out.println("-----------------");
        System.out.println("11. New developer");
        System.out.println("12. New project");
        System.out.println("13. New company");
        System.out.println("14. New customer");
        System.out.println("15. New skill");
    }

    public void showTwoMenu() {
        System.out.println("-----------------");
        System.out.println("21. Get developer");
        System.out.println("22. Get project");
        System.out.println("23. Get company");
        System.out.println("24. Get customer");
        System.out.println("25. Get skill");
    }

    public void showThreeMenu() {
        System.out.println("------------------");
        System.out.println("31. Update developer");
        System.out.println("32. Update project");
        System.out.println("33. Update company");
        System.out.println("34. Update customer");
        System.out.println("35. Update skill");
    }

    public void showFourMenu() {
        System.out.println("-----------------");
        System.out.println("41. Delete developers");
        System.out.println("42. Delete projects");
        System.out.println("43. Delete companies");
        System.out.println("44. Delete customers");
        System.out.println("45. Delete skills");
    }

    public void showFiveMenu() {
        System.out.println("-----------------");
        System.out.println("51. Show developers");
        System.out.println("52. Show projects");
        System.out.println("53. Show companies");
        System.out.println("54. Show customers");
        System.out.println("55. Show skills");
    }


}
