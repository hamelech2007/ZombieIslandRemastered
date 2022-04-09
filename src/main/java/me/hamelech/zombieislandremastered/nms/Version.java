package me.hamelech.zombieislandremastered.nms;

import lombok.Getter;

public enum Version {
    v1_18_R2("1.18.2"),
    v1_18_R1("1.18.1"),
    v1_17_R1("1.17.2"),
    v1_16_R3("1.16.5"),
    v1_16_R2("1.16.3"),
    v1_16_R1("1.16.1"),
    v1_15_R1("1.15.2"),
    v1_14_R1("1.14.4"),
    v1_13_R2("1.13.2"),
    v1_13_R1("1.13"),
    v1_12_R1("1.12.2"),
    v1_11_R1("1.11.2"),
    v1_10_R1("1.10.2"),
    v1_9_R2("1.9.4"),
    v1_9_R1("1.9.2"),
    v1_8_R3("1.8.8"),
    v1_8_R2("1.8.9"),
    v1_8_R1("1.8");

    @Getter
    private final String gameVersion;

    Version (String gameVersion){
        this.gameVersion = gameVersion;
    }

    public boolean isAbove(Version version){
        //TODO
        return this == Version.v1_18_R1 || this == Version.v1_17_R1 || this == Version.v1_18_R2;
    }

    public static Version fromString(String s){
        for(Version version : Version.values()){
            if(version.toString().equals(s)) return version;
        }
        return null;
    }
}
