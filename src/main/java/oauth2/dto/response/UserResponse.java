package oauth2.dto.response;

public abstract class UserResponse{
    String email;
    String name;
    Role role;
    String profileUrl;

    public UserResponse(String email, String name, Role role, String profileUrl) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.profileUrl = profileUrl;
    }

}
