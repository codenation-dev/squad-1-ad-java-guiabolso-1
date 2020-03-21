package br.com.guiabolso.centraldeerros.enums;

public enum LevelEnum {

    ERROR("ERROR"), WARNING("WARNING"), DEBUG("DEBUG");

    private String levelEnum;

    LevelEnum(String levelEnum) {
        this.levelEnum = levelEnum;
    }

    public String getLevelEnum() {
        return levelEnum;
    }
    
    public static LevelEnum find(String value) {
    	for (LevelEnum level : LevelEnum.values()) {
    		if(value.compareTo(level.levelEnum) == 0)
    			return level;
        }
    	return null;
    }
    
    
}
