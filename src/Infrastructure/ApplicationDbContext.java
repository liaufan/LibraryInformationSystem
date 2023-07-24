package Infrastructure;

import Models.*;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;

public class ApplicationDbContext {
    private Connection connection;

    public ApplicationDbContext(){
        String url = "jdbc:mysql://localhost:3306/";
        String dbName = "LibraryDb";
        String username = "admin";
        String password = "mysql123";

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
                sql += "CreatedDate = " + "'" + book.CreatedDate + "'";
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

    public void UpdateTransaction(ArrayList<Transaction> transactions) throws SQLException {
        Statement statement = connection.createStatement();
        for(Transaction transaction: transactions){
            String sql = "";
            if(transaction.Id == 0 ){
                //Insert new row
                sql += "INSERT INTO Transactions (Id, BookId, BorrowerId, BorrowDate, ReturnDate, IsReturned, CreatedDate) VALUES ( DEFAULT, ";
                sql += "'" + transaction.BookId + "', ";
                sql += "'" + transaction.BorrowerId + "', ";
                sql += "'" + transaction.BorrowDate + "', ";
                sql += "'" + transaction.ReturnDate + "', ";
                sql += transaction.IsReturned + ", ";
                sql += "'" + transaction.CreatedDate + "');";
            } else {
                //Update existing row by Id
                sql += "UPDATE Books SET";
                sql += "'" + transaction.BookId + "', ";
                sql += "'" + transaction.BorrowerId + "', ";
                sql += "'" + transaction.BorrowDate + "', ";
                sql += "'" + transaction.ReturnDate + "', ";
                sql += transaction.IsReturned + ", ";
                sql += "'" + transaction.CreatedDate + "');";
            }

            statement.executeUpdate(sql);
        }
    }



    public ArrayList<Transaction> QueryTransaction(String whereClause) throws SQLException {
        ArrayList<Transaction> responses = new ArrayList<>();
        Statement statement = connection.createStatement();
        System.out.println("SELECT * FROM Transactions WHERE " + whereClause + ";");
        ResultSet resultSet = statement.executeQuery("SELECT * FROM Transactions WHERE " + whereClause + ";");

        while(resultSet.next()){
            Transaction transaction = new Transaction();

            transaction.Id = resultSet.getInt("Id");
            transaction.BookId = resultSet.getInt("BookId");
            transaction.BorrowerId = resultSet.getInt("BorrowerId");
            transaction.BorrowDate = resultSet.getDate("BorrowDate");
            transaction.ReturnDate = resultSet.getDate("ReturnDate");
            transaction.IsReturned = resultSet.getBoolean("IsReturned");
            transaction.CreatedDate = resultSet.getDate("CreatedDate");

            responses.add(transaction);
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

            // TODO: pump initialize data for all tables
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
