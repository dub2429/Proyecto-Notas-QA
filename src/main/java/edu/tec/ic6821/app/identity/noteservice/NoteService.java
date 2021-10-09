package edu.tec.ic6821.app.identity.noteservice;




import edu.tec.ic6821.app.identity.note.Note;

import java.util.Optional;

public interface NoteService {
    Optional<Note> create(String title, String content, Long userId);
}

