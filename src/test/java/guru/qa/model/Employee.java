package guru.qa.model;

import java.util.List;

public class Employee {
    public String name;
    public String secondName;
    public int age;
    public boolean isEmployee;
    public Organization organization;

    public static class Organization {
        public int id;
        public String title;
        public String createdDate;
    }
}