package co.edu.javeriana.eas.patterns.users.enums;

public enum EProfile {

    ADMINISTRATOR(1),
    CLIENT(2),
    PROVIDER(3);

    private int profile;

    EProfile(int profile) {
        this.profile = profile;
    }

    public int getProfile() {
        return profile;
    }

}