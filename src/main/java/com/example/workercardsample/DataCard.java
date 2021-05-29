package com.example.workercardsample;

import java.util.ArrayList;

public class DataCard {
    private String cardName;
    private String email;
    private String profile;
    private ArrayList<DataCheckList> checkLists;

    public DataCard(String cardName, String email, String profile, ArrayList<DataCheckList> checkLists) {
        this.cardName = cardName;
        this.email = email;
        this.profile = profile;
        this.checkLists = checkLists;
    }

    public DataCard(String cardName, String email, String profile) {
        this.cardName = cardName;
        this.email = email;
        this.profile = profile;
    }

    public DataCard() {

    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public ArrayList<DataCheckList> getCheckLists() {
        return checkLists;
    }

    public void setCheckLists(ArrayList<DataCheckList> checkLists) {
        this.checkLists = checkLists;
    }
}
