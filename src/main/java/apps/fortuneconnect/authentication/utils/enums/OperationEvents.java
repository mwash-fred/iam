package apps.fortuneconnect.authentication.utils.enums;

import lombok.Getter;

@Getter
public enum OperationEvents {
    ADD("create"),
    INQUIRE("inquire"),
    MODIFY("modify"),
    DELETE("delete"),
    VERIFY("verify"),
    PASSWORD_RESET("password-reset");

    private final String value;

    OperationEvents(String value) {
        this.value = value;
    }
}
