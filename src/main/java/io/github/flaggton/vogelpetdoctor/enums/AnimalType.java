package io.github.flaggton.vogelpetdoctor.enums;

public enum AnimalType {
    DOG("Dog"),
    BIRD("Bird"),
    CAT("Cat"),
    HORSE("Horse"),
    OTHER("Other");

    private final String uiText;

    AnimalType(String uiText){
        this.uiText = uiText;
    }

    public String getUiText() {
        return uiText;
    }
}
