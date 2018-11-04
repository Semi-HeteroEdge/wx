package com.shawn.fakewechat.bean;



public class ContactShowInfo {

    private int headImage;
    private String username;
    private boolean isMute;
    private boolean isRead;
    private int accountType;

    public ContactShowInfo(int headImage, String username) {
        this.headImage = headImage;
        this.username = username;
    }


    public int getHeadImage() {
        return headImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
