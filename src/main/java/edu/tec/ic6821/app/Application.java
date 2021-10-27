package edu.tec.ic6821.app;

import edu.tec.ic6821.app.identity.dao.JdbcNoteDao;
import edu.tec.ic6821.app.identity.dao.NoteDao;
import edu.tec.ic6821.app.identity.note.Note;
import edu.tec.ic6821.app.identity.noteservice.NoteService;
import edu.tec.ic6821.app.identity.noteservice.NoteServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}

