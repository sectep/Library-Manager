package librarymanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private static List<String> lines = new ArrayList<>();
    private static String line;

    // method which adds the book.
    public static void addBook(String ISBN, String name, String author, String genre, String number) {
        if (!idExists(ISBN))
            writeData(ISBN, name, author, genre, number);

    }

    // method, which updated the number of books.
    public static void updateNum(String ISBN, String num) {
        readToArray();
        update(ISBN, num);

        try (BufferedWriter out = new BufferedWriter(new FileWriter("librarymanager\\library.txt"))) {
            for (String l : lines)
                out.write(l + "\n");
        } catch (IOException exc) {
            System.out.println("Exception occurred while file was changing quantities.");
        }
    }

    // a metthod, which reads the book information, based on he ID.
    public static void readData(String ISBN) {
        try (BufferedReader in = new BufferedReader(new FileReader("librarymanager\\library.txt"))) {

            // read until the end.
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Id: ")) {

                    if (line.substring(4).equals(ISBN)) {
                        for (int i = 0; i < 4; i++)
                            System.out.println(in.readLine());

                        return;
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println("An error has occurred, while data was reading");
        }
    }

    // method, which removes the book, based on ISBN
    public static void removeBook(String ISBN) {
        readToArray();
        if (idExists(ISBN)) {
            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("Id: ") && lines.get(i).substring(4).equals(ISBN)) {

                    for (int j = 0; j < 6; j++) {
                        lines.remove(i);
                    }
                    updateFile();
                    System.out.println("Book with ID " + ISBN + " is removed successfully.");
                    return;
                }
            }
        }
        System.out.println("ID doesnt exist!");
    }

    // method, which prints all lines.
    public static void showAll() {
        readToArray();
        System.out.println("===LIBRARY===");
        for (String l : lines) {
            System.out.println(l);
        }
    }

    // method, which writes binary data into 'library.txt'.
    private static void writeData(String ISBN, String name, String author, String genre, String number) {

        try (BufferedWriter out = new BufferedWriter(new FileWriter("librarymanager\\library.txt", true))) {

            // write new data, if ID doesn't exist.
            if (!idExists(ISBN)) {
                out.write("Id: " + ISBN + "\n");
                out.write("Name: " + name + "\n");
                out.write("Author: " + author + "\n");
                out.write("Genre: " + genreChooser(genre) + "\n");
                out.write("Avaible: " + number + "\n");
                out.write("---\n");
                System.out.println("The book has been saved successfully.");
                return;
            }
        } catch (IOException exc) {
            System.out.println("Exception has occurred while method was writing data.");
        }
        System.out.println("ID already exists.");
    }

    // method, which chooses the Genre, based on data.
    private static String genreChooser(String genres) {
        if (genreExists(genres)) {
            return genres;
        }
        return "OTHER";
    }

    // check if the current genre exists in enuminator list.
    private static boolean genreExists(String genres) {
        for (BookType genre : BookType.values()) {
            if (genre.toString().equals(genres)) {
                return true;
            }
        }
        return false;
    }

    // method, which removes the number from array.
    private static void update(String ISBN, String num) {

        // iterate through array list.
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("Id: ") && lines.get(i).substring(4).equals(ISBN)) {
                // change the quantiny in the next loop.
                for (int j = i; j < lines.size(); j++) {
                    if (lines.get(j).startsWith("Avaible: ")) {
                        lines.set(j, "Avaible: " + num);
                        System.out.println("Book's information has been updated.");
                        return;
                    }
                }
            }
        }
    }

    // method, which reads the file and gets it into array.
    private static void readToArray() {
        lines.clear();
        try (BufferedReader in = new BufferedReader(new FileReader("librarymanager\\library.txt"))) {

            while ((line = in.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException exc) {
            System.out.println("Exception has occurred while method was writing data to array.");
        }
    }

    // method, which cheks if current ID exists.
    private static boolean idExists(String ISBN) {
        try (BufferedReader in = new BufferedReader(new FileReader("librarymanager\\library.txt"))) {

            // read until the end.
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Id: ")) {
                    if (line.substring(4).equals(ISBN)) {
                        return true;
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println("An error has occurred, while data was reading");
        }
        return false;
    }

    // method, which updates the file information.
    private static void updateFile() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter("librarymanager\\library.txt"))) {
            for (String l : lines) {
                out.write(l + "\n");
            }
        } catch (IOException exc) {
            System.out.println("The process of rewriting data  has been interrupted.");

        }
    }
}
