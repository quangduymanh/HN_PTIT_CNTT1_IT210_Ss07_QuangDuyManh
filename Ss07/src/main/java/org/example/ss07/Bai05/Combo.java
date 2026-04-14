package org.example.ss07.Bai05;

import java.util.List;

public class Combo {
    private String name;
    private List<String> itemList;
    private String banner;

    public Combo() {}

    public Combo(String name, List<String> itemList, String banner) {
        this.name = name;
        this.itemList = itemList;
        this.banner = banner;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<String> getItemList() { return itemList; }
    public void setItemList(List<String> itemList) { this.itemList = itemList; }

    public String getBanner() { return banner; }
    public void setBanner(String banner) { this.banner = banner; }
}