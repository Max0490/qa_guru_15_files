package guru.qa.model;

import java.util.List;

public class Employee {
    public static String name;
    public String secondName;
    public int age;
    public static boolean isEmployee;
    public static Organization organization;

    public static class Organization {
        public int id;
        public String title;
        public String createdDate;
    }
}