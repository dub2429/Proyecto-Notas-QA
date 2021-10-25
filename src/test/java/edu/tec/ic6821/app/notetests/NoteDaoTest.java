package edu.tec.ic6821.app.notetests;

import edu.tec.ic6821.app.identity.dao.NoteDao;
import edu.tec.ic6821.app.identity.dao.UserDao;
import edu.tec.ic6821.app.identity.model.User;
import edu.tec.ic6821.app.identity.note.Note;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.sql.SQLException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoteDaoTest {

    @Autowired
    private NoteDao noteDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void givenNote_whenCreate_thenReturnNoteWithGeneratedId() {
        // given
        User user = new User("someuser", "123queso");
        User persistedUser = userDao.create(user);

        Note note = new Note(1L,"Nombre Nota", "Texto", persistedUser.getId().get());

        // when
        Note persistedNote =  noteDao.create(note);

        // then
        assertThat(persistedNote.getTitle()).isEqualTo(note.getTitle());
        assertThat(persistedNote.getContent()).isEqualTo(note.getContent());
        assertThat(persistedNote.getUserId()).isEqualTo(persistedUser.getId().get());
        assertThat(persistedNote.getId()).isNotNull();
    }

    @Test
    public void createNotes_whenShowNotes_thenReturnListOfNotesCreated() throws SQLException {
        // given
        User user = new User("someuser", "123queso");
        User persistedUser = userDao.create(user);

        Note note = new Note("Nota1", "contenido", persistedUser.getId().get());
        Note note2 = new Note("Nota2", "contenido", persistedUser.getId().get());

        // when
        Note persistedNote =  noteDao.create(note);
        Note persistedNote2 = noteDao.create(note2);
        List<Note> notes = noteDao.showAllNotes();

        // then
        assertThat(notes.get(0).getTitle()).isEqualTo(persistedNote.getTitle());
        assertThat(notes.get(1).getTitle()).isEqualTo(persistedNote2.getTitle());
    }

}

