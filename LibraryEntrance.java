package librarymanager;

import java.util.Scanner;

public class LibraryEntrance {
    private Scanner console = new Scanner(System.in);
    String ISBN, title, author, genre, quantity;
    int number, option;

    // method, which launchs the app.
    public void launch() {
        display();
        askOption();

    }

    // method, which asks for chosen option.
    private void askOption() {
        System.out.print("Choose: ");

        if (console.hasNextInt()) {
            option = console.nextInt();
            console.nextLine();
            doOption(option);
            return;
        }
    }

    // method, which initializes the option, based on the choice.
    private void doOption(int what) {
        switch (what) {
            case 1:
                ISBN = askId();
                title = askTitle();
                author = askAuthor();
                genre = askGenre();
                quantity = askQuantity();
                LibraryManager.addBook(ISBN, title, author, genre, quantity);
                break;
            case 2:
                ISBN = askId();
                LibraryManager.readData(ISBN);
                break;
            case 3:
                ISBN = askId();
                quantity = askQuantity();
                LibraryManager.updateNum(ISBN, quantity);
                break;
            case 4:
                ISBN = askId();
                LibraryManager.removeBook(ISBN);
                break;
            case 5:
                LibraryManager.showAll();
                break;
            case 6:
                System.out.println("Program is terminated.");
                break;
            default:
                System.out.println("We only have options from 1 to 6.");
                break;
        }

    }

    // method, which displays the menu.
    private void display() {
        System.out.println("1. Add book.");
        System.out.println("2. Find book.");
        System.out.println("3. Update book's quantity.");
        System.out.println("4. Remove book.");
        System.out.println("5. Show storage.");
        System.out.println("6. Exit.");
    }

    // method, which asks for book's ID.
    private String askId() {
        System.out.print("Write id of the book: ");
        return console.nextLine();
    }

    // method, wich asks for book's name.
    private String askTitle() {
        System.out.print("Write the title of the book: ");
        return console.nextLine();
    }

    // method, which asks for book's author.
    private String askAuthor() {
        System.out.print("Write the author of the book: ");
        return console.nextLine();
    }

    // method, which asks for the genre.
    private String askGenre() {
        System.out.print("Write the genre of the book: ");
        genre = console.nextLine();
        return genre.toUpperCase();
    }

    // method, which asks for the book quantity.
    private String askQuantity() {
        System.out.print("Write the quantity of books avaible: ");
        if (console.hasNextInt()) {
            number = console.nextInt();
            console.nextLine();
        } else {
            System.out.println("Invalid data type!");
        }
        return String.valueOf(number);
    }

}
