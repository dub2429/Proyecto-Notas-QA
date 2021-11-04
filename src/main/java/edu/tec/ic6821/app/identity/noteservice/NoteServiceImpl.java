package edu.tec.ic6821.app.identity.noteservice;


import edu.tec.ic6821.app.identity.dao.NoteDao;
import edu.tec.ic6821.app.identity.note.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
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
    public boolean editTitle(String oldTitle, String newTitle){
        boolean oldTitleExists = noteDao.existsByTitle(oldTitle);
        if(oldTitleExists){
            boolean newTitleAlreadyExists = noteDao.existsByTitle(newTitle);
            if (newTitleAlreadyExists) {
                return false;
            } else {
                noteDao.editTitle(oldTitle, newTitle);
                return true;
            }
        }else{
            return false;
        }
    }

    @Override
    public boolean editContent(String title, String content) {
        boolean titleAlreadyExists = noteDao.existsByTitle(title);
        if(!titleAlreadyExists){
            return false;
        }
        noteDao.editContent(title,content);
        return true;
    }

    @Override
    public List<Note> showAllNotes() {
        return noteDao.showAllNotes();
    }

    @Override
    public boolean deleteNote(String title){
        boolean titleExists = noteDao.existsByTitle(title);
        if(!titleExists){
            return false;
        }
        noteDao.deleteNote(title);
        return true;
    }
}
