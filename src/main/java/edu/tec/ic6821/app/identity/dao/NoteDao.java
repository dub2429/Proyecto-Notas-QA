package edu.tec.ic6821.app.identity.dao;

import edu.tec.ic6821.app.identity.note.Note;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface NoteDao {
    Note create(Note note);
    boolean editTitle(Note note, String title);
    void editContent(Note note, String content);
    List<Note> showAllNotes();
    Boolean existsByTitle(String title);
}