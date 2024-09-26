package apps.fortuneconnect.authentication.utils.enums;

public enum QueryType {
    ACCOUNT_NO("account-no"),
    ID_NO("id-no"),
    UID("uid"),
    ID("id"),
    CODE("code"),
    EMAIL("email");
    private final String value;

    QueryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
