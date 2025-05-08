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
                out.write("\n");
                return;
            }
        } catch (IOException exc) {
            System.out.println("Exception has occurred while method was writing binary data.");
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

    // a metthod, which reads the book information, based on he ID.
    public static void readData(String ISBN) {
        try (BufferedReader in = new BufferedReader(new FileReader("librarymanager\\library.txt"))) {

            // read until the end.
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Id: ")) {
                    System.out.println("\n" + line);
                    for (int i = 0; i < 4; i++) {
                        System.out.println(in.readLine());
                    }
                }
            }
        } catch (IOException exc) {
            System.out.println("An error has occurred, while binary data was reading");
        }
    }

    // method, which updated the number of books.
    public static void updateNum(String ISBN, String num) {
        update(ISBN, num);
        try (BufferedWriter out = new BufferedWriter(new FileWriter("librarymanager\\library.txt"))) {
            for (String l : lines) {
                out.write(l);
            }
        } catch (IOException exc) {
            System.out.println("Exception occurred while file was changing quantities.");
        }
    }

    // methodm which removes the number from array.
    private static void update(String ISBN, String num) {

        // iterate through array list.
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("Id: ") && lines.get(i).substring(4).equals(ISBN)) {
                for (int j = 0; j < 5; j++) {
                    if (lines.get(i).startsWith("Avaible: ")) {
                        lines.set(i, "Avaible: " + num);
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
                if (line.substring(3).equals(ISBN))
                    return true;
            }
        } catch (IOException exc) {
            System.out.println("An error has occurred, while binary data was reading");
        }
        return false;
    }

    // method, which removes the book, based on ISBN
    public static void removeBook(String ISBN) {
        if (idExists(ISBN)) {

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith("ID: ") && lines.get(i).substring(4).equals(ISBN)) {
                    for (int j = 0; j < 6; j++) {
                        lines.remove(i);
                        updateFile();
                    }
                    return;
                }
            }
        }
        System.out.println("ID doesnt exist!");
    }

    // method, which updates the file information.
    private static void updateFile() {
        readToArray();
        try (BufferedWriter out = new BufferedWriter(new FileWriter("librarymanager\\library.txt"))) {
            for (String l : lines) {
                out.write(l);
            }
        } catch (IOException exc) {
            System.out.println("The process of rewriting data  has been interrupted.");

        }
    }

    // method, which prints all lines.
    public static void showAll() {
        for (String l : lines) {
            if (l == null) {
                System.out.println("The file is empty.");
                return;
            }
            System.out.println(l);
        }
    }
}
