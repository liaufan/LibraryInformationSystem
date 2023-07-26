package Infrastructure;

import Models.*;

import java.awt.desktop.SystemSleepEvent;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class ApplicationDbContext {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/";
    private String dbName = "LibraryDb";
    private String username = "admin";
    private String password = "mysql123";

    public ApplicationDbContext(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(url+dbName, username, password);
        } catch (Exception e){
            if(e.toString().contains("Unknown database")){
                //Create Database if not already created
                try{
                    connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    statement.executeUpdate("CREATE DATABASE "+dbName);

                    connection.close();
                    connection = DriverManager.getConnection(url+dbName, username, password);
                }catch(Exception ex){
                    System.out.println(ex);
                }
            } else {
                System.out.println(e);
            }
        }
    }

    public void Dispose(){
        try{
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public ResultSet QueryDatabase(String sqlStatement) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeQuery(sqlStatement);
    }

    public int UpdateDatabase(String sqlStatement) throws SQLException {
        Statement statement = connection.createStatement();

        return statement.executeUpdate(sqlStatement);
    }

    public ArrayList<Book> QueryBooks(String whereClause) throws SQLException {
        ArrayList<Book> responses = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Books WHERE " + whereClause + ";");

        while(resultSet.next()){
            Book book = new Book();

            book.Id = resultSet.getInt("Id");
            book.Title = resultSet.getString("Title");
            book.Author = resultSet.getString("Author");
            book.PublicationYear = resultSet.getString("PublicationYear");
            book.IsAvailable = resultSet.getBoolean("IsAvailable");
            book.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(book);
        }

        return responses;
    }

    public void UpdateBooks(ArrayList<Book> books) throws SQLException {
        Statement statement = connection.createStatement();

        for(Book book: books){
            String sql = "";
            if(book.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Books (Id, Title, Author, PublicationYear, IsAvailable, CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + book.Title + "', ";
                sql += "'" + book.Author + "', ";
                sql += "'" + book.PublicationYear + "', ";
                sql += book.IsAvailable + ", ";
                sql += "'" + book.CreatedDate + "');";
            } else {
                //Update existing row by Id
                sql += "UPDATE Books SET ";
                sql += "Title = " + "'" + book.Title + "', ";
                sql += "Author = " + "'" + book.Author + "', ";
                sql += "PublicationYear = " + "'" + book.PublicationYear + "', ";
                sql += "IsAvailable = " + book.IsAvailable + ", ";
                sql += "CreatedDate = " + "'" + book.CreatedDate + "' ";
                sql += "WHERE Id = " + book.Id + ";";
            }
            System.out.println(sql);
            statement.executeUpdate(sql);
        }
    }

    public ArrayList<Borrower> QueryBorrowers(String whereClause) throws SQLException {
        ArrayList<Borrower> responses = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Borrowers WHERE " + whereClause + ";");

        while(resultSet.next()){
            Borrower borrower = new Borrower();

            borrower.Id = resultSet.getInt("Id");
            borrower.Name = resultSet.getString("Name");
            borrower.Email = resultSet.getString("Email");
            borrower.Phone = resultSet.getString("Phone");
            borrower.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(borrower);
        }

        return responses;
    }

    public void UpdateBorrowers(ArrayList<Borrower> borrowers) throws SQLException {
        Statement statement = connection.createStatement();

        for(Borrower borrower: borrowers){
            String sql = "";
            if(borrower.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Borrowers (Id, Name, Email, Phone, CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + borrower.Name + "', ";
                sql += "'" + borrower.Email + "', ";
                sql += "'" + borrower.Phone + "', ";
                sql += "'" + borrower.CreatedDate + "');";
            } else {
                //Update existing row by Id
                sql += "UPDATE Borrowers SET";
                sql += "Name = " + "'" + borrower.Name + "', ";
                sql += "Email = " + "'" + borrower.Email + "', ";
                sql += "Phone = " + "'" + borrower.Phone + "', ";
                sql += "CreatedDate = " + "'" + borrower.CreatedDate + "'";
                sql += "WHERE Id = " + borrower.Id + ";";
            }

            statement.executeUpdate(sql);
        }
    }

    public ArrayList<Reservation> QueryReservations(String whereClause) throws SQLException {
        ArrayList<Reservation> responses = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Reservations WHERE " + whereClause + ";");

        while(resultSet.next()){
            Reservation reservation = new Reservation();

            reservation.Id = resultSet.getInt("Id");
            reservation.BookId = resultSet.getInt("BookId");
            reservation.BookName = resultSet.getString("BookName");
            reservation.BorrowerId = resultSet.getInt("BorrowerId");
            reservation.BorrowerName = resultSet.getString("BorrowerName");
            reservation.BorrowDate = resultSet.getDate("BorrowDate");
            reservation.ReturnDate = resultSet.getDate("ReturnDate");
            reservation.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(reservation);
        }

        return responses;
    }

    public void UpdateReservations(ArrayList<Reservation> reservations) throws SQLException {
        Statement statement = connection.createStatement();

        for(Reservation reservation: reservations){
            String sql = "";
            if(reservation.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Reservations (Id, BookId, BookName, BorrowerId, BorrowerName, BorrowDate, ReturnDate, CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + reservation.BookId + "', ";
                sql += "'" + reservation.BookName + "', ";
                sql += "'" + reservation.BorrowerId + "', ";
                sql += "'" + reservation.BorrowerName + "', ";
                sql += "'" + reservation.BorrowDate + "', ";
                sql += "'" + reservation.ReturnDate + "', ";
                sql += "'" + reservation.CreatedDate + "');";
            } else {
                //Update existing row by Id
                sql += "UPDATE Reservations SET ";
                sql += "BookId = " + "'" + reservation.BookId + "', ";
                sql += "BookName = " + "'" + reservation.BookName + "', ";
                sql += "BorrowerId = " + "'" + reservation.BorrowerId + "', ";
                sql += "BorrowerName = " + "'" + reservation.BorrowerName + "', ";
                sql += "BorrowDate = " + "'" + reservation.BorrowDate + "', ";
                sql += "ReturnDate = " + reservation.ReturnDate + ", ";
                sql += "CreatedDate = " + "'" + reservation.CreatedDate + "' ";
                sql += "WHERE Id = " + reservation.Id + ";";
            }
            System.out.println(sql);
            statement.executeUpdate(sql);
        }
    }

    public void UpdateRating(ArrayList<Rating> ratings) throws SQLException {
        Statement statement = connection.createStatement();
        for(Rating rating: ratings){
            String sql = "";
            System.out.println("rating");
            if(rating.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Ratings (Id, BookId, BorrowerName, BookName, Rating, Reviews, CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + rating.BookId + "', ";
                sql += "'" + rating.BorrowerName + "', ";
                sql += "'" + rating.BookName + "', ";
                sql += "'" + rating.Rating + "', ";
                sql += "'" + rating.Reviews + "', ";
                sql += "'" + rating.CreatedDate + "');";
            }
            System.out.println(sql);

            statement.executeUpdate(sql);
        }
    }

    public ArrayList<Rating> QueryBookRating(String whereClause) throws SQLException {
        ArrayList<Rating> responses = new ArrayList<>();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM Ratings WHERE " + whereClause + ";");

        while(resultSet.next()){
            Rating rating = new Rating();

            rating.Id = resultSet.getInt("Id");
            rating.BookId = resultSet.getInt("BookId");
            rating.BorrowerName = resultSet.getString("BorrowerName");
            rating.BookName = resultSet.getString("BookName");
            rating.Rating = resultSet.getInt("Rating");
            rating.Reviews = resultSet.getString("Reviews");
            rating.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(rating);
        }

        return responses;
    }

    public void UpdateTransaction(ArrayList<Transaction> transactions) throws SQLException {
        Statement statement = connection.createStatement();
        for(Transaction transaction: transactions){
            String sql = "";
            if(transaction.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Transactions (Id, BookId, BorrowerId, BorrowDate, ReturnDate, ExpectedReturnDate, IsReturned, CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + transaction.BookId + "', ";
                sql += "'" + transaction.BorrowerId + "', ";
                sql += "'" + transaction.BorrowDate + "', ";
                sql += "'" + transaction.ReturnDate + "', ";
                sql += "'" + transaction.ExpectedReturnDate + "', ";
                sql += transaction.IsReturned + ", ";
                sql += "'" + transaction.CreatedDate + "');";
            } else {
                //Update existing row by Id
                sql += "UPDATE Transactions SET ";
                sql += "BookId = '" + transaction.BookId + "', ";
                sql += "BorrowerId = '" + transaction.BorrowerId + "', ";
                sql += "BorrowDate = '" + transaction.BorrowDate + "', ";
                sql += "ReturnDate = '" + new Date(System.currentTimeMillis()) + "', ";
                sql += "ExpectedReturnDate = '" + transaction.ExpectedReturnDate + "', ";
                sql += "IsReturned = '" + "1" + "', ";
                sql += "CreatedDate = '" + transaction.CreatedDate + "' ";
                sql += "WHERE Id = " + transaction.Id + ";";
            }
            System.out.println(sql);

            statement.executeUpdate(sql);
        }
    }

    public ArrayList<Transaction> QueryTransaction(String whereClause) throws SQLException {
        ArrayList<Transaction> responses = new ArrayList<>();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE " + whereClause + ";");

        while(resultSet.next()){
            Transaction transaction = new Transaction();

            transaction.Id = resultSet.getInt("Id");
            transaction.BookId = resultSet.getInt("BookId");
            transaction.BorrowerId = resultSet.getInt("BorrowerId");
            transaction.BorrowDate = resultSet.getDate("BorrowDate");
            transaction.ReturnDate = resultSet.getDate("ReturnDate");
            transaction.ExpectedReturnDate = resultSet.getDate("ExpectedReturnDate");
            transaction.IsReturned = resultSet.getBoolean("IsReturned");
            transaction.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(transaction);
        }

        return responses;
    }

    public void UpdateUsers(ArrayList<User> users) throws SQLException {
        Statement statement = connection.createStatement();
        for(User user: users){
            System.out.println("CREATING USER");
            System.out.println(user);
            String sql = "";
            if(user.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Users (Id, Username, Password, Name, PhoneNumber,  CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + user.Username + "', ";
                sql += "'" + user.Password + "', ";
                sql += "'" + user.Name + "', ";
                sql += "'" + user.PhoneNumber + "', ";
                sql += "'" + user.CreatedDate + "');";
            } else {
                //Update existing row by Id
                sql += "UPDATE Users SET ";
                sql += "Username = '" + user.Username + "', ";
                sql += "Password = '" + user.Password + "', ";
                sql += "Name = '" + user.Name + "', ";
                sql += "PhoneNumber = '" + user.PhoneNumber + "', ";
                sql += "CreatedDate = '" + user.CreatedDate + "' ";
                sql += "WHERE Id = " + user.Id + ";";
            }
            System.out.println(sql);

            statement.executeUpdate(sql);
        }
    }

    public ArrayList<User> QueryUsers(String whereClause) throws SQLException {
        ArrayList<User> responses = new ArrayList<>();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM Users WHERE " + whereClause + ";");

        while(resultSet.next()){
            User user = new User();

            user.Id = resultSet.getInt("Id");
            user.Username = resultSet.getString("Username");
            user.Password = resultSet.getString("Password");
            user.Name = resultSet.getString("Name");
            user.PhoneNumber = resultSet.getString("PhoneNumber");
            user.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(user);
        }

        return responses;
    }

    public void DatabaseSeed(){
        // Initialize Database schemas
        try {
            Statement statement = connection.createStatement();

            try {
                String sql = "CREATE TABLE Books (";
                Book obj = new Book();
                for (Field field : obj.getClass().getFields()) {
                    sql += field.getName() + " " + GetDatabaseType(field.getGenericType().toString()) + SetPrimaryKey(field.getName()) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2);
                sql += ");";
                statement.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception ignored) {
            } //Skip if table already created


            try {
                String sql = "CREATE TABLE Borrowers (";
                Borrower obj = new Borrower();
                for (Field field : obj.getClass().getFields()) {
                    sql += field.getName() + " " + GetDatabaseType(field.getGenericType().toString()) + SetPrimaryKey(field.getName()) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2);
                sql += ");";
                statement.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception ignored) {
            } //Skip if table already created

            try {
                String sql = "CREATE TABLE Reservations (";
                Reservation obj = new Reservation();
                for (Field field : obj.getClass().getFields()) {
                    sql += field.getName() + " " + GetDatabaseType(field.getGenericType().toString()) + SetPrimaryKey(field.getName()) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2);
                sql += ");";
                statement.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception ignored) {
            } //Skip if table already created

            try {
                String sql = "CREATE TABLE Transactions (";
                Transaction obj = new Transaction();
                for (Field field : obj.getClass().getFields()) {
                    sql += field.getName() + " " + GetDatabaseType(field.getGenericType().toString()) + SetPrimaryKey(field.getName()) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2);
                sql += ");";
                statement.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception ignored) {
            } //Skip if table already created

            try {
                String sql = "CREATE TABLE Ratings (";
                Rating obj = new Rating();
                for (Field field : obj.getClass().getFields()) {
                    sql += field.getName() + " " + GetDatabaseType(field.getGenericType().toString()) + SetPrimaryKey(field.getName()) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2);
                sql += ");";
                statement.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception ignored) {
            } //Skip if table already created

            try {
                String sql = "CREATE TABLE Users (";
                User obj = new User();
                for (Field field : obj.getClass().getFields()) {
                    sql += field.getName() + " " + GetDatabaseType(field.getGenericType().toString()) + SetPrimaryKey(field.getName()) + ", ";
                }
                sql = sql.substring(0, sql.length() - 2);
                sql += ");";
                statement.executeUpdate(sql);
                System.out.println(sql);
            } catch (Exception ignored) {
            } //Skip if table already created

            // TODO: pump initialize data for all tables
            var books = QueryBooks("true");
            if(books.size() == 0){
                statement.executeUpdate("INSERT INTO Books (Id, Title, Author, PublicationYear, IsAvailable, CreatedDate) VALUES ( DEFAULT, 'The Little Prince', 'Antoine de Saint-Exupery', '1943', false, '2023-07-24')");
                statement.executeUpdate("INSERT INTO Books (Id, Title, Author, PublicationYear, IsAvailable, CreatedDate) VALUES ( DEFAULT, 'Harry Potter and the Philosophers Stone', 'J. K. Rowling', '1997', false, '2023-07-24')");
                statement.executeUpdate("INSERT INTO Books (Id, Title, Author, PublicationYear, IsAvailable, CreatedDate) VALUES ( DEFAULT, 'Dream of the Red Chamber', 'Cao Xueqin', '1791', true, '2023-07-24')");
                statement.executeUpdate("INSERT INTO Books (Id, Title, Author, PublicationYear, IsAvailable, CreatedDate) VALUES ( DEFAULT, 'The Hobbit', 'J. R. R. Tolkien', '1937', true, '2023-07-24')");
                statement.executeUpdate("INSERT INTO Books (Id, Title, Author, PublicationYear, IsAvailable, CreatedDate) VALUES ( DEFAULT, 'The Hunger Games', 'Suzanne Collins', '2008', true, '2023-07-24')");
            }

            var borrowers = QueryBorrowers("true");
            if(borrowers.size() == 0){
                statement.executeUpdate("INSERT INTO Borrowers (Id, Name, Email, Phone, CreatedDate) VALUES ( DEFAULT, 'John', 'john@gmail.com', '0123456789', '2023-07-24')");
                statement.executeUpdate("INSERT INTO Borrowers (Id, Name, Email, Phone, CreatedDate) VALUES ( DEFAULT, 'Cindy', 'cindy@gmail.com', '019882738', '2023-07-24')");
                statement.executeUpdate("INSERT INTO Borrowers (Id, Name, Email, Phone, CreatedDate) VALUES ( DEFAULT, 'Albert', 'albert@gmail.com', '0187239878', '2023-07-24')");
            }

            var reservations = QueryReservations("true");
            if(reservations.size() == 0){
                statement.executeUpdate("INSERT INTO Reservations (Id, BookId, BookName, BorrowerId, BorrowerName, BorrowDate, ReturnDate, CreatedDate) VALUES ( DEFAULT, 1, 'The Little Prince', 1, 'John', '2023-07-20','2023-07-31','2023-07-24')");
                statement.executeUpdate("INSERT INTO Reservations (Id, BookId, BookName, BorrowerId, BorrowerName, BorrowDate, ReturnDate, CreatedDate) VALUES ( DEFAULT, 2, 'Harry Potter and the Philosophers Stone', 2, 'Cindy', '2023-07-30','2023-08-15','2023-07-24')");
            }

            var transactions = QueryTransaction("true");
            if(transactions.size() == 0){
                statement.executeUpdate("INSERT INTO Transactions (Id, BookId, BorrowerId, BorrowDate, ReturnDate, ExpectedReturnDate, IsReturned, CreatedDate) VALUES ( DEFAULT, 1, 2,'2023-07-01', '1901-01-01', '2023-07-24', false, '2023-07-01')");
                statement.executeUpdate("INSERT INTO Transactions (Id, BookId, BorrowerId, BorrowDate, ReturnDate, ExpectedReturnDate, IsReturned, CreatedDate) VALUES ( DEFAULT, 2, 3,'2023-07-01', '1901-01-01', '2023-07-24', false, '2023-07-01')");
            }

            var users = QueryUsers("true");
            if(users.size() == 0){
                statement.executeUpdate("INSERT INTO Users (Id, Username, Password, Name, PhoneNumber, CreatedDate) VALUES ( DEFAULT, 'admin', 'abcd1234', 'Admin User','01132228332','2023-07-24')");
            }
        }catch(SQLException ex){
            System.out.println(ex);
        }
    }

    private String GetDatabaseType(String genericType){
        String[] types = genericType.split("\\.");
        String type = types[types.length-1];

        if( type.equals("String")){
            return "varchar(255)";
        } else {
            return type;
        }
    }

    private String SetPrimaryKey(String name){
        if(name.equals("Id")){
            return " NOT NULL AUTO_INCREMENT, PRIMARY KEY ("+name+")";
        }else{
            return "";
        }
    }
}
