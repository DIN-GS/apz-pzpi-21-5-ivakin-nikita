package nikita.ivakin.apzpzpi215ivakinnikitatask2.enums;

public enum POST {
    BRIGADE_COMMANDER("Командир бригади"),
    REGIMENT_COMMANDER("Командир полку"),
    BATTALION_COMMANDER("Командир батальйону"),
    COMPANY_COMMANDER("Командир роти"),
    PLAT_COMMANDER("Командир взводу");

    private final String role;

    POST(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
