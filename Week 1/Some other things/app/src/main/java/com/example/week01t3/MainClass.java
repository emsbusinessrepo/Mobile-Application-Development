package com.example.week01t3;

public class MainClass {
    public static void main(String[] args) {
        ITUnit MobileAppUnit;

        MobileAppUnit = new ITUnit("Mobile Application Development", "FIT2081", "Android Studio");

        System.out.println("This unit is: " + MobileAppUnit.getName() + " , the code for this Unit is: " + MobileAppUnit.getID() + " and the software used is: " + MobileAppUnit.getSoftware());
        MobileAppUnit.showName();

    }
}
