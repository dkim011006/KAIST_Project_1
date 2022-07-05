package com.example.project_1;

public class AddressData {
    private int iconDrawable;
    private String contentStr;
    private String titleStr;

    public AddressData(int iconDrawable, String contentStr, String titleStr) {
        this.iconDrawable = iconDrawable;
        this.contentStr = contentStr;
        this.titleStr = titleStr;
    }

    public void setTitle(String title){
        titleStr = title;
    }
    public void setIcon(int icon){
        iconDrawable = icon;
    }
    public void setContent(String content){
        contentStr = content;
    }

    public int getIcon(){
        return this.iconDrawable;
    }
    public String getContent(){
        return this.contentStr;
    }
    public String getTitle(){
        return this.titleStr;
    }
}
