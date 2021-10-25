package edu.tec.ic6821.app.identity.dao;

import edu.tec.ic6821.app.identity.note.Note;

import java.util.Optional;

public interface NoteDao {
    Note create(Note note);
    void editTitle(Note note, String title);
    void editContent(Note note, String content);
    Boolean existsByTitle(String title);
    //Optional<Note> findByTitle(String username);
}