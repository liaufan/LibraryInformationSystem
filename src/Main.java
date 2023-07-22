import Infrastructure.ApplicationDbContext;
import UI.HomePage;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        ApplicationDbContext _context = new ApplicationDbContext();
        _context.DatabaseSeed();

        new HomePage();
    }
}