package edu.tec.ic6821.app.notetests;

import edu.tec.ic6821.app.identity.config.JwtAuthEntryPoint;
import edu.tec.ic6821.app.identity.config.JwtProvider;
import edu.tec.ic6821.app.identity.model.CustomUserDetails;
import edu.tec.ic6821.app.identity.note.Note;
import edu.tec.ic6821.app.identity.notecontroller.NoteController;
import edu.tec.ic6821.app.identity.noteservice.NoteService;

import edu.tec.ic6821.app.identity.service.CustomUserDetailsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteController.class)
public class NoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private NoteService noteService;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @MockBean
    private JwtProvider jwtProvider;

    @Test
    public void givenValidCredentials_whenCreateNote_SendNote() throws Exception {
        // given
        String title = "Title";
        String content= "thiscontent";
        Long userId = 42L;
        String username = "someuser";
        String password = "123queso";
        Note newNote = new Note(title, content, userId);
        given(noteService.create(title, content ,userId)).willReturn(Optional.of(newNote));


        when(jwtProvider.validateJwtToken(anyString())).thenReturn(true);
        when(jwtProvider.getUsernameFromJwtToken(anyString())).thenReturn(username);
        when(customUserDetailsService.loadUserByUsername(anyString())).thenReturn(
                new CustomUserDetails(42L, org.springframework.security.core.userdetails.User
                        .withUsername(username)
                        .password(password)
                        .authorities(Collections.emptyList())
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build())
        );

        String body = String.format("{\"title\": \"%s\", \"content\": \"%s\"}", title, content);

        // when ... then
        mvc.perform(post("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer sometoken")
                .content(body))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenValidCredentials_whenSignUp_SendNote() throws Exception {
        // given
        String title = "Title";
        String content = "content";
        Long userId = 42L;
        String username = "someuser";
        String password = "123queso";
        Note newNote = new Note(title, content, userId);
        given(noteService.create(title, content, userId)).willReturn(Optional.of(newNote));


        when(jwtProvider.validateJwtToken(anyString())).thenReturn(true);
        when(jwtProvider.getUsernameFromJwtToken(anyString())).thenReturn(username);
        when(customUserDetailsService.loadUserByUsername(anyString())).thenReturn(
                new CustomUserDetails(42L, org.springframework.security.core.userdetails.User
                        .withUsername(username)
                        .password(password)
                        .authorities(Collections.emptyList())
                        .accountExpired(false)
                        .accountLocked(false)
                        .credentialsExpired(false)
                        .disabled(false)
                        .build())
        );

        String body = String.format("{\"title\": \"%s\", \"content\": \"%s\"}", title, content);

        // when ... then
        mvc.perform(post("/api/note")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer sometoken")
                .content(body))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(title)))
                .andExpect(jsonPath("$.content", is(content)));
    }

}