package br.com.guiabolso.centraldeerros.enums;

public enum LevelEnum {

    ERROR("error"), WARNING("warning"), DEBUG("debug");

    private String levelEnum;

    LevelEnum(String levelEnum) {
        this.levelEnum = levelEnum;
    }

    public String getLevelEnum() {
        return levelEnum;
    }
}
