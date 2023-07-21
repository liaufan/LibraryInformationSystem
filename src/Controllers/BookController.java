package Controllers;

import Applications.Book.AddBookCommand;

public class BookController {
    public void AddBook(AddBookCommand command){
        command.Handle();
    }
}
