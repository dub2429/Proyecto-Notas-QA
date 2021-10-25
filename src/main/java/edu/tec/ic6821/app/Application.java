package edu.tec.ic6821.app;

import edu.tec.ic6821.app.identity.dao.JdbcNoteDao;
import edu.tec.ic6821.app.identity.note.Note;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		//SpringApplication.run(Application.class, args);

		JdbcNoteDao nota_prueba = new JdbcNoteDao();
		Note note = new Note("Planetas", "Los planetas",1);
		System.out.println("Se ha creado la nota");
		nota_prueba.create(note);
		System.out.println("Se ha borrado la nota");
		nota_prueba.deleteNote("Planetas"); //Eliminar la nota

	}

}

