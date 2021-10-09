package edu.tec.ic6821.app.identity.notecontroller;



import edu.tec.ic6821.app.common.errors.ErrorDto;
import edu.tec.ic6821.app.identity.model.CustomUserDetails;
import edu.tec.ic6821.app.identity.note.Note;
import edu.tec.ic6821.app.identity.notedto.NoteDto;
import edu.tec.ic6821.app.identity.noteservice.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public ResponseEntity<?> RegisterNoteDto(@Valid @RequestBody NoteDto noteDto) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            Optional<Note> optionalNote = noteService.create(
                    noteDto.getTitle(),
                    noteDto.getContent(),
                    userDetails.getUserId());

            return optionalNote.<ResponseEntity<?>>map(
                    (note) -> new ResponseEntity<>(
                            NoteDto.from(note),
                            HttpStatus.CREATED)
            ).orElseGet(
                    () -> new ResponseEntity<>(HttpStatus.BAD_REQUEST)
            );
        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

