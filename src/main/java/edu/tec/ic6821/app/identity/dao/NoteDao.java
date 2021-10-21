package edu.tec.ic6821.app.identity.dao;

import edu.tec.ic6821.app.identity.note.Note;

import java.util.Optional;

public interface NoteDao {
    Note create(Note note);
    Boolean existsByTitle(String title);
    void deleteNote(long noteId);
    //Optional<Note> findByTitle(String username);
}