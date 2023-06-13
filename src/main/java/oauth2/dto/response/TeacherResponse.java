package oauth2.dto.response;

class TeacherResponse extends UserResponse {
    public TeacherResponse(String email, String name, String profileUrl) {
        super(email, name, Role.TEACHER, profileUrl);
    }
}
