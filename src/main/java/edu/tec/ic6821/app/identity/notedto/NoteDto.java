package edu.tec.ic6821.app.identity.notedto;

import edu.tec.ic6821.app.identity.note.Note;

public class NoteDto {

    private final String title;
    private final String content;
    private final Long userId;


    public static NoteDto from(Note note){
        return new NoteDto(note.getTitle(), note.getContent(), note.getUserId());
    }

    private NoteDto(String title, String content, Long userId) {
        this.title = title;
        this.content = content;
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

}

