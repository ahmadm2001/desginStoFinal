package com.example.desginstofinal;

public class Order {
    private String Name;
    private String Student;
    private String id;
    private String date;
    private  String endDate;
    private boolean done;
    private  String guid;
    private  double orderid;
    public  Order(){}
    public Order(String name, String student, String id, String date, String endDate, boolean done, String guid, double orderid) {
        Name = name;
        Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
        this.done = done;
        this.guid = guid;
        this.orderid = orderid;
    }
    public Order(String name, String student, String id, String date, String endDate) {
        this.Name = name;
        this.Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
    }
    public Order(String name, String student, String id, String date, String endDate,boolean done) {
        this.Name = name;
        this.Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
        this.done=done;
    }
    public Order(String name, String student, String id, String date, String endDate,boolean done,double orderid) {
        this.Name = name;
        this.Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
        this.done=done;
        this.orderid=orderid;
    }

    public double getOrderid() {
        return orderid;
    }

    public void setOrderid(double orderid) {
        this.orderid = orderid;
    }

    public boolean getDone() {
        return this.done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    public String getName() {
        return Name;
    }

    public String getStudent() {
        return Student;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setName(String name) {
        Name = name;
    }

    public void setStudent(String student) {
        Student = student;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getGuid() {
        return guid;
    }

}
