package oauth2.dto.response;


class StudentResponse extends UserResponse{
    Integer entrancedYear;
    Integer grade;
    Integer classNum;
    Integer studentNum;
    String githubLink;

    public StudentResponse(String email, String name, String profileUrl) {
        super(email, name, Role.STUDENT, profileUrl);
    }

    public StudentResponse(
            String email, String name,
            String profileUrl, Integer entrancedYear, Integer grade,
            Integer classNum, Integer studentNum, String githubLink) {
        super(email, name, Role.STUDENT, profileUrl);
        this.entrancedYear = entrancedYear;
        this.grade = grade;
        this.classNum = classNum;
        this.studentNum = studentNum;
        this.githubLink = githubLink;
    }
}

