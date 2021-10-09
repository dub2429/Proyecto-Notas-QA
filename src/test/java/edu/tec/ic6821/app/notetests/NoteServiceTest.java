package edu.tec.ic6821.app.notetests;

import edu.tec.ic6821.app.identity.dao.NoteDao;
import edu.tec.ic6821.app.identity.note.Note;
import edu.tec.ic6821.app.identity.noteservice.NoteService;
import edu.tec.ic6821.app.identity.noteservice.NoteServiceImpl;

import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class NoteServiceTest {

    @TestConfiguration
    static class NoteServiceTestsConfiguration {
        @MockBean
        NoteDao noteDao;


        @Bean
        public NoteService noteService() {
            return new NoteServiceImpl(noteDao);
        }
    }

    @Autowired
    NoteService noteService;

    @Autowired
    NoteDao noteDao;


    @Before
    public void setUp() {

        Mockito.when(noteDao.create(ArgumentMatchers.any(Note.class)))
                .thenAnswer(invocation -> {
                    Note createdNote = invocation.getArgument(0);
                    return new Note(42L,createdNote.getTitle(), createdNote.getContent(), createdNote.getUserId());
                });
    }

    @Test
    public void givenNewNote_whenCreate_thenReturnNewNote() {
        // given
        String title = "title";
        String content = "content";
        Long userId = 42L;

        // when
        Optional<Note> result = noteService.create(title, content, userId);

        // then
        assertThat(result).isNotEmpty();

        Note newNote = result.get();
        assertThat(newNote).hasFieldOrPropertyWithValue("id", Optional.of(42L));
    }

}
