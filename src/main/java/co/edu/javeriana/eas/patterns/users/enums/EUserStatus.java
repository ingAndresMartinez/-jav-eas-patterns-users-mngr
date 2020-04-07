package co.edu.javeriana.eas.patterns.users.enums;

public enum EUserStatus {

    INACTIVE(0),
    ACTIVE(1);

    private int status;

    EUserStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}