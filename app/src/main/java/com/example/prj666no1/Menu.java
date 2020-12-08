package com.example.prj666no1;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Menu {
    @SerializedName("_id")
    private String menu_id;

    @SerializedName("menuCode")
    private String menu_menuCode;

    @SerializedName("menu")
    private List<SingleDayMenu> menu_allMenu;

    @SerializedName("menuName")
    private String menu_menuName;

    @SerializedName("pictureLink")
    private String pictureLink;


    public Menu(String menu_id, String menu_menuCode, List<SingleDayMenu> menu_allMenu, String menu_menuName, String pictureLink) {
        this.menu_id = menu_id;
        this.menu_menuCode = menu_menuCode;
        this.menu_allMenu = menu_allMenu;
        this.menu_menuName = menu_menuName;
        this.pictureLink = pictureLink;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public String getMenu_menuCode() {
        return menu_menuCode;
    }

    public List<SingleDayMenu> getMenu_allMenu() {
        return menu_allMenu;
    }

    public String getMenu_menuName() {
        return menu_menuName;
    }

    public String getPictureLink() {
        return pictureLink;
    }
}
