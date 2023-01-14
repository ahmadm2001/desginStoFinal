package com.testapp.forcodes;

public class Item {
    String add_name,add_count,add_details,purl;
    Item()
    {}
        public Item(String add_name, String add_count, String add_details, String purl) {
        this.add_name = add_name;
        this.add_count = add_count;
        this.add_details = add_details;
        this.purl = purl;
    }

    public String getAdd_name() {
        return add_name;
    }

    public void setAdd_name(String add_name) {
        this.add_name = add_name;
    }

    public String getAdd_count() {
        return add_count;
    }

    public void setAdd_count(String add_count) {
        this.add_count = add_count;
    }

    public String getAdd_details() {
        return add_details;
    }

    public void setAdd_details(String add_details) {
        this.add_details = add_details;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }
}
