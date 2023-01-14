package com.example.desginstofinal;

public class products {
    private String name, Description,id;
    private boolean flag = false;
   private  String startDate;
   private  String endDate;
    public products() {
    }

    public products(String Name, String Description,String id, boolean flag) {
        this.name = Name;
        this.Description = Description;
        this.flag = flag;
        this.id=id;
    }
    public products(String Name, String Description,String id,String startDate,String endDate, boolean flag) {
        this.name = Name;
        this.Description = Description;
        this.flag = flag;
        this.id=id;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public products(String Name, String Description,String startDate,String endDate, boolean flag) {
        this.name = Name;
        this.Description = Description;
        this.flag = flag;
        this.startDate=startDate;
        this.endDate=endDate;
    }
    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description=description;
    }

    public void setStartDate(String startDate){
        this.startDate=startDate;
    }
    public String getStartDate(){
        return  this.startDate;
    }

    public void setEndDate(String endDate){
        this.endDate=endDate;
    }
    public  String getEndDate(){
        return  this.endDate;
    }

    public boolean getflag() {
        return flag;
    }

    public void setflag(boolean Flag) {
        flag = Flag;
    }

    @Override
    public  boolean equals(Object obj){
        if(!(obj instanceof  products))return  false;
        products pp=(products) obj;
        return  pp.getName().equals(this.getName()) && pp.getDescription().equals(this.getDescription())?true:false ;
    }

}

