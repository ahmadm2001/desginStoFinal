package com.example.desginstofinal;

public class Order {
    private String Name;
    private String Student;
    private String id;
    private String date;
    private  String endDate;
    private boolean isDone=false;
    private  String guid;
    private  double orderid;
    public  Order(){}
    public Order(String name, String student, String id, String date, String endDate, boolean isDone, String guid, double orderid) {
        Name = name;
        Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
        this.isDone = isDone;
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
    public Order(String name, String student, String id, String date, String endDate,boolean isDone) {
        this.Name = name;
        this.Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
        this.isDone=isDone;
    }
    public Order(String name, String student, String id, String date, String endDate,boolean isDone,double orderid) {
        this.Name = name;
        this.Student = student;
        this.id = id;
        this.date = date;
        this.endDate = endDate;
        this.isDone=isDone;
        this.orderid=orderid;
    }

    public double getOrderid() {
        return orderid;
    }

    public void setOrderid(double orderid) {
        this.orderid = orderid;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
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
