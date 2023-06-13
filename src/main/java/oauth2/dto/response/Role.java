package oauth2.dto.response;

public enum Role {
    BLOCK(0, "ROLE_BLOCK"),
    STUDENT(1, "ROLE_STUDENT"),
    CONTACTOR(2, "ROLE_CONTACTOR"),
    TEACHER(3, "ROLE_TEACHER"),
    SYSTEM(4, "ROLE_SYSTEM");

    private final Integer level;
    private final String mean;

    Role(int level, String mean) {
        this.level = level;
        this.mean = mean;
    }
}

