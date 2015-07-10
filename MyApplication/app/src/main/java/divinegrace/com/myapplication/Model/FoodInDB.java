package divinegrace.com.myapplication.Model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by DGBendicion on 7/10/15.
 */
public class FoodInDB extends RealmObject {
    @PrimaryKey
    private int id;

    private String name;

    private String portionName;

    private String dietaryFiber;

    private String trans;

    private String saturated;

    private String totalCarbs;

    private String sodium;

    private String polyunsaturated;

    private String calories;

    private String sugar;

    private String totalFats;

    private String monosaturated;

    private String cholesterol;

    private String protien;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPortionName() {
        return portionName;
    }

    public void setPortionName(String portionName) {
        this.portionName = portionName;
    }

    public String getDietaryFiber() {
        return dietaryFiber;
    }

    public void setDietaryFiber(String dietaryFiber) {
        this.dietaryFiber = dietaryFiber;
    }

    public String getTrans() {
        return trans;
    }

    public void setTrans(String trans) {
        this.trans = trans;
    }

    public String getSaturated() {
        return saturated;
    }

    public void setSaturated(String saturated) {
        this.saturated = saturated;
    }

    public String getTotalCarbs() {
        return totalCarbs;
    }

    public void setTotalCarbs(String totalCarbs) {
        this.totalCarbs = totalCarbs;
    }

    public String getSodium() {
        return sodium;
    }

    public void setSodium(String sodium) {
        this.sodium = sodium;
    }

    public String getPolyunsaturated() {
        return polyunsaturated;
    }

    public void setPolyunsaturated(String polyunsaturated) {
        this.polyunsaturated = polyunsaturated;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getSugar() {
        return sugar;
    }

    public void setSugar(String sugar) {
        this.sugar = sugar;
    }

    public String getTotalFats() {
        return totalFats;
    }

    public void setTotalFats(String totalFats) {
        this.totalFats = totalFats;
    }

    public String getMonosaturated() {
        return monosaturated;
    }

    public void setMonosaturated(String monosaturated) {
        this.monosaturated = monosaturated;
    }

    public String getCholesterol() {
        return cholesterol;
    }

    public void setCholesterol(String cholesterol) {
        this.cholesterol = cholesterol;
    }

    public String getProtien() {
        return protien;
    }

    public void setProtien(String protien) {
        this.protien = protien;
    }

    //field names
    public static String idFieldName = "id";
    public static String nameFieldName = "name";
    public static String portionNameFieldName = "portionName";
    public static String dietaryFiberFieldName = "dietaryFiber";
    public static String transFieldName = "trans";
    public static String saturatedFieldName = "saturated";
    public static String totalCarbsFieldName = "totalCarbs";
    public static String sodiumFieldName = "sodium";
    public static String polyunsaturatedFieldName = "polyunsaturated";
    public static String caloriesFieldName = "calories";
    public static String sugarFieldName = "sugar";
    public static String totalFatsFieldName = "totalFats";
    public static String monosaturatedFieldName = "monosaturated";
    public static String cholesterolFieldName = "cholesterol";
    public static String protienFieldName = "protien";

}
