package com.example.prj666no1;

import com.google.gson.annotations.SerializedName;

public class SingleDayMenu {
    @SerializedName("Days")
    private String menu_day;
    @SerializedName("Breakfast")
    private String menu_breakfast;
    @SerializedName("Lunch")
    private String menu_lunch;
    @SerializedName("Dinner")
    private String menu_dinner;

    public String getMenu_day() {
        return menu_day;
    }

    public String getMenu_breakfast() {
        return menu_breakfast;
    }

    public String getMenu_lunch() {
        return menu_lunch;
    }

    public String getMenu_dinner() {
        return menu_dinner;
    }

    public SingleDayMenu(String menu_day, String menu_breakfast, String menu_lunch, String menu_dinner) {
        this.menu_day = menu_day;
        this.menu_breakfast = menu_breakfast;
        this.menu_lunch = menu_lunch;
        this.menu_dinner = menu_dinner;
    }
}
