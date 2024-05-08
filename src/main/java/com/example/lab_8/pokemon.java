package com.example.lab_8;

public class pokemon {
    String pokeid;
    String name;
    String type1;
    String type2;
    String desc;
    String height;
    String weight;
    String gen1;
    String gen2;
    String gen3;
    String category;

    public pokemon(String id,String name, String t1, String t2, String desc, String height, String weight, String gen1,String gen2, String gen3, String category){
        this.pokeid = id;
        this.name = name;
        this.type1 = t1;
        this.type2 = t2;
        this.desc = desc;
        this.height = height;
        this.weight = weight;
        this.gen1= gen1;
        this.gen2= gen2;
        this.gen3=gen3;
        this.category=category;
    }

    public String getPokeid() {
        return pokeid;
    }

    public void setPokeid(String pokeid) {
        this.pokeid = pokeid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public String getGen3() {
        return gen3;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setGen3(String gen3) {
        this.gen3 = gen3;
    }

    public String getGen1() {
        return gen1;
    }

    public String getGen2() {
        return gen2;
    }

    public void setGen2(String gen2) {
        this.gen2 = gen2;
    }

    public void setGen1(String gen1) {
        this.gen1 = gen1;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
