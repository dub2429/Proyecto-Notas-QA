package edu.tec.ic6821.app.identity.notedto;

public class RegisterNoteDto {

    private String title;
    private String content;

    public RegisterNoteDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
