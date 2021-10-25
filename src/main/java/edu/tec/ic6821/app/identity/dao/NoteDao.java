package edu.tec.ic6821.app.identity.dao;

import edu.tec.ic6821.app.identity.note.Note;

import java.util.Optional;

public interface NoteDao {
    Note create(Note note);
    Boolean existsByTitle(String title);
    void deleteNote(String title);
    //Optional<Note> findByTitle(String username);
}