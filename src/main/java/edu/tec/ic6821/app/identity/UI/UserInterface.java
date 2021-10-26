package edu.tec.ic6821.app.identity.UI;

import edu.tec.ic6821.app.identity.note.Note;
import edu.tec.ic6821.app.identity.noteservice.NoteService;
import edu.tec.ic6821.app.identity.noteservice.NoteServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInterface {

    private NoteService noteService; // FALTA INICIALIZARLO CON EL NoteDao
    private boolean salir = false;
    private boolean inicio = false;
    private String primeraFrase = "\n\n--- Bienvenido a nuestra Aplicacion de Notas ---\n";
    private String[] menu = {"\n","------------------- MENU -----------------------",
                             "- Para CREAR una nota digite: 1",
                             "- Para EDITAR una nota digite: 2",
                             "- Para ELIMINAR una nota digite: 3",
                             "- Para VER las notas creadas digite: 4",
                             "- Para ver el MENU digite: 5",
                             "- Para SALIR de la aplicacion digite: 6", "\n"};

    Scanner sn = new Scanner(System.in);
    int op; //Guarda la opcion del usuario

    public UserInterface(){
        while(!salir){
            if(!inicio){
                System.out.println(primeraFrase);
                showMenu();
                inicio = true;
            }
            System.out.println("\nEscriba una de las opciones: ");
            op = sn.nextInt();

            switch (op){
                case 1:
                    while(!createNote()){
                        createNote();
                    }
                    break;
            }
        }
    }

    public void showMenu(){
        for (String s: menu
             ) {System.out.println(s);
        }
    }

    // Cree la función que debería usar el crear nota para que entienda como es el proceso.
    // Pero igual no funciona hasta que se inicialice el NoteService.
    public boolean createNote(){
        String title;
        String content;
        System.out.println("Escriba el titulo de la nota: ");
        title = sn.next();
        System.out.println("Escriba el contenido de la nota: ");
        content = sn.next();
        Optional<Note> noteCreated = noteService.create(title, content, 1L);
        if(!noteCreated.isEmpty()){
            System.out.println("La nota se ha creado correctamente");
            return true;
        }
        System.out.println("ERROR: El titulo de la nota ya está repetido, por favor ingrese otro título distinto");
        return false;
    }



}
