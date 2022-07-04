package com.example.project_1;

public class ListViewItem {
    private int iconDrawable;
    private String contentStr;
    private String titleStr;
    private String explain;
    private String age;

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

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
