import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Klasa bazowa dla materiałów w bibliotece (abstrakcyjna)
abstract class Material {
    protected String title;
    protected String author;
    protected String borrowedBy = null;

    // Konstruktor klasy Material
    public Material(String title, String author) {
        this.title = title;
        this.author = author;
    }

    // Abstrakcyjna metoda do wyświetlania informacji o materiale
    public abstract void displayInfo();

    // Sprawdza dostępność materiału
    public boolean isAvailable() {
        return borrowedBy == null;
    }

    // Metoda do wypożyczenia materiału
    public void borrow(String userId) {
        if (isAvailable()) {
            this.borrowedBy = userId;
            System.out.println(title + " została wypożyczona przez użytkownika o ID: " + userId);
        } else {
            System.out.println(title + " jest już wypożyczona.");
        }
    }

    // Metoda do zwrotu materiału
    public void returnMaterial() {
        this.borrowedBy = null;
        System.out.println(title + " została zwrócona.");
    }
}

// Klasa reprezentująca książkę
class Book extends Material {
    private final String genre;

    // Konstruktor klasy Book
    public Book(String title, String author, String genre) {
        super(title, author);
        this.genre = genre;
    }

    // Implementacja metody do wyświetlania informacji o książce
    @Override
    public void displayInfo() {
        System.out.println("Książka: " + title + ", Autor: " + author + ", Gatunek: " + genre + ", Wypożyczona: " + (borrowedBy != null ? "Tak" : "Nie"));
    }
}

// Klasa reprezentująca czasopismo
class Magazine extends Material {
    private final int issueNumber;

    // Konstruktor klasy Magazine
    public Magazine(String title, String author, int issueNumber) {
        super(title, author);
        this.issueNumber = issueNumber;
    }

    // Implementacja metody do wyświetlania informacji o czasopiśmie
    @Override
    public void displayInfo() {
        System.out.println("Czasopismo: " + title + ", Autor: " + author + ", Numer wydania: " + issueNumber + ", Wypożyczona: " + (borrowedBy != null ? "Tak" : "Nie"));
    }
}

// Abstrakcyjna klasa bazowa dla użytkowników
abstract class User {
    protected String name;
    protected String id;

    // Konstruktor klasy User
    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Abstrakcyjna metoda do wyświetlania informacji o użytkowniku
    public abstract void displayInfo();
}

// Klasa reprezentująca czytelnika
class Reader extends User {
    // Konstruktor klasy Reader
    public Reader(String name, String id) {
        super(name, id);
    }

    // Implementacja metody do wyświetlania informacji o czytelniku
    @Override
    public void displayInfo() {
        System.out.println("Czytelnik: " + name + ", ID: " + id);
    }
}

// Klasa do zarządzania użytkownikami
class UserManager {
    private final List<User> users = new ArrayList<>();

    // Dodaje nowego użytkownika do listy
    public void addUser(User user) {
        users.add(user);
        System.out.println("Dodano użytkownika: " + user.name);
    }

    // Usuwa użytkownika z listy
    public void removeUser(String userId) {
        users.removeIf(user -> user.id.equals(userId));
        System.out.println("Usunięto użytkownika o ID: " + userId);
    }

    // Wyświetla listę użytkowników
    public void displayUsers() {
        for (User user : users) {
            user.displayInfo();
        }
    }

    // Sprawdza, czy użytkownik istnieje na liście
    public boolean userExists(String userId) {
        return users.stream().anyMatch(user -> user.id.equals(userId));
    }

    // Wyświetla materiały wypożyczone przez konkretnego użytkownika
    public void displayBorrowedMaterials(String userId, List<Material> materials) {
        boolean found = false;
        for (Material material : materials) {
            if (userId.equals(material.borrowedBy)) {
                material.displayInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("Brak wypożyczonych materiałów dla użytkownika o ID: " + userId);
        }
    }
}

// Główna klasa zarządzająca biblioteką
public class Z12Library {
    private final List<Material> materials;
    private final UserManager userManager;

    // Konstruktor klasy Z12Library
    public Z12Library() {
        materials = new ArrayList<>();
        userManager = new UserManager();
    }

    // Dodaje nowy materiał do biblioteki
    public void addMaterial(Material material) {
        materials.add(material);
        System.out.println("Dodano materiał: " + material.title);
    }

    // Usuwa materiał z biblioteki
    public void removeMaterial(String materialTitle) {
        materials.removeIf(material -> material.title.equals(materialTitle));
        System.out.println("Usunięto materiał: " + materialTitle);
    }

    // Wypożycza materiał użytkownikowi, jeśli jest dostępny
    public void borrowMaterial(String materialTitle, String userId) {
        for (Material material : materials) {
            if (material.title.equals(materialTitle) && material.isAvailable() && userManager.userExists(userId)) {
                material.borrow(userId);
                return;
            }
        }
        System.out.println("Materiał niedostępny lub użytkownik nie istnieje: " + materialTitle);
    }

    // Zwraca wypożyczony materiał do biblioteki
    public void returnMaterial(String materialTitle) {
        for (Material material : materials) {
            if (material.title.equals(materialTitle) && !material.isAvailable()) {
                material.returnMaterial();
                return;
            }
        }
        System.out.println("Materiał nie został wypożyczony: " + materialTitle);
    }

    // Wyświetla listę wszystkich materiałów w bibliotece
    public void displayMaterials() {
        for (Material material : materials) {
            material.displayInfo();
        }
    }

    // Metoda do zarządzania biblioteką poprzez konsolę
    public void manageLibrary() {
        Scanner scanner = new Scanner(System.in);
        String option = "";

        while (!option.equals("exit")) {
            System.out.println("\n--- Menu Biblioteki ---");
            System.out.println("1. Dodaj książkę");
            System.out.println("2. Usuń książkę");
            System.out.println("3. Lista książek");
            System.out.println("4. Dodaj użytkownika");
            System.out.println("5. Usuń użytkownika");
            System.out.println("6. Lista użytkowników");
            System.out.println("7. Wypożycz książkę");
            System.out.println("8. Zwróć książkę");
            System.out.println("9. Lista wypożyczonych książek użytkownika");
            System.out.println("exit - Wyjdź z programu");
            System.out.print("Wybierz opcję: ");

            option = scanner.nextLine();

            switch (option) {
                case "1":
                    addMaterialThroughConsole(scanner);
                    break;
                case "2":
                    System.out.print("Podaj tytuł materiału do usunięcia: ");
                    String titleToRemove = scanner.nextLine();
                    removeMaterial(titleToRemove);
                    break;
                case "3":
                    displayMaterials();
                    break;
                case "4":
                    addUserThroughConsole(scanner);
                    break;
                case "5":
                    System.out.print("Podaj ID użytkownika do usunięcia: ");
                    String userIdToRemove = scanner.nextLine();
                    userManager.removeUser(userIdToRemove);
                    break;
                case "6":
                    userManager.displayUsers();
                    break;
                case "7":
                    borrowMaterialThroughConsole(scanner);
                    break;
                case "8":
                    System.out.print("Podaj tytuł materiału do zwrotu: ");
                    String titleToReturn = scanner.nextLine();
                    returnMaterial(titleToReturn);
                    break;
                case "9":
                    displayBorrowedMaterialsByUser(scanner);
                    break;
            }
        }

        scanner.close();
    }

    private void addMaterialThroughConsole(Scanner scanner) {
        System.out.print("Podaj tytuł: ");
        String title = scanner.nextLine();
        System.out.print("Podaj autora: ");
        String author = scanner.nextLine();
        System.out.print("Podaj typ (książka/czasopismo): ");
        String type = scanner.nextLine();

        if (type.equalsIgnoreCase("książka")) {
            System.out.print("Podaj gatunek: ");
            String genre = scanner.nextLine();
            addMaterial(new Book(title, author, genre));
        } else if (type.equalsIgnoreCase("czasopismo")) {
            System.out.print("Podaj numer wydania: ");
            int issueNumber = Integer.parseInt(scanner.nextLine());
            addMaterial(new Magazine(title, author, issueNumber));
        } else {
            System.out.println("Nieznany typ materiału!");
        }
    }

    private void addUserThroughConsole(Scanner scanner) {
        System.out.print("Podaj imię i nazwisko: ");
        String name = scanner.nextLine();
        System.out.print("Podaj ID: ");
        String id = scanner.nextLine();
        userManager.addUser(new Reader(name, id));
    }

    private void borrowMaterialThroughConsole(Scanner scanner) {
        System.out.print("Podaj tytuł książki: ");
        String title = scanner.nextLine();
        System.out.print("Podaj ID użytkownika: ");
        String userId = scanner.nextLine();
        borrowMaterial(title, userId);
    }

    private void displayBorrowedMaterialsByUser(Scanner scanner) {
        System.out.print("Podaj ID użytkownika: ");
        String userId = scanner.nextLine();
        userManager.displayBorrowedMaterials(userId, materials);
    }

    public static void main(String[] args) {
        Z12Library library = new Z12Library();
        library.manageLibrary();
    }
}
