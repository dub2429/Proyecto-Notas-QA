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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/note")
public class NoteController {

    private final NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/create")
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


    @DeleteMapping("/delete/{title}")
    public ResponseEntity<?> DeleteNoteDto(@PathVariable String title) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            noteService.deleteNote(title);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/title/{oldTitle}/{newTitle}")
    public ResponseEntity<?> EditNoteTitleDto(@PathVariable String oldTitle, @PathVariable String newTitle) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            boolean editTitle = noteService.editTitle(oldTitle, newTitle);

            if (editTitle) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/content/{title}/{content}")
    public ResponseEntity<?> EditNoteContentDto(@PathVariable String title,@PathVariable String content) {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            noteService.editContent(title,content);
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/show")
    public ResponseEntity<?> showNotes() {
        try {
            CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            List<Note> listOfNotes = noteService.showAllNotes();

            return new ResponseEntity<>(listOfNotes, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(ErrorDto.from(e),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

