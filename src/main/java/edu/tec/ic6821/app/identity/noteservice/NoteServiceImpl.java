package edu.tec.ic6821.app.identity.noteservice;


import edu.tec.ic6821.app.identity.dao.NoteDao;
import edu.tec.ic6821.app.identity.note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteDao noteDao;

    @Autowired
    public NoteServiceImpl(NoteDao noteDao) {
        this.noteDao = noteDao;
    }


    @Override
    public Optional<Note> create(String title, String content, Long userId) {
        Boolean titleAlreadyExists = noteDao.existsByTitle(title);
        if (titleAlreadyExists) {
            return Optional.empty();
        } else {
            Note note = new Note(title, content, userId);
            return Optional.of(noteDao.create(note));
        }
    }

    @Override
    public boolean editTitle(Note note, String title){
        Boolean titleAlreadyExists = noteDao.existsByTitle(title);
        if (titleAlreadyExists) {
            return false;
        } else {
            noteDao.editTitle(note,title);
            return true;
        }
    }

    @Override
    public void editContent(Note note, String content) {
        noteDao.editContent(note,content);
    }

    @Override
    public void deleteNote(String title){
        noteDao.deleteNote(title);
    }
}
