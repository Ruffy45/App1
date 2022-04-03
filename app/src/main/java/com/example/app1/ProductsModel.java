package com.example.app1;

public class ProductsModel {

    String name,Section,Reason;

    public ProductsModel() {
    }

    public ProductsModel(String name, String section, String reason) {
        this.name = name;
        this.Section = section;
        this.Reason = reason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        this.Section = section;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        this.Reason = reason;
    }
}

