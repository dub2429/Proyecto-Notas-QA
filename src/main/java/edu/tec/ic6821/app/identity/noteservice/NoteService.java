package edu.tec.ic6821.app.identity.noteservice;

import edu.tec.ic6821.app.identity.note.Note;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NoteService {
    Optional<Note> create(String title, String content, Long userId);
    boolean editTitle(String oldTitle, String newTitle);
    boolean editContent(String title, String content);
    List<Note> showAllNotes();
    boolean deleteNote(String title);
}

