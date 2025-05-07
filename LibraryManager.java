package librarymanager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    List<String> lines = new ArrayList<>();
    String line;

    // method which adds the book.
    public void addBook(String ISBN, String name, String author, String genre, int number) {
        if (!idExists(ISBN))
            writeData(ISBN, name, author, genre, number);

    }

    // method, which writes binary data into 'library.txt'.
    private void writeData(String ISBN, String name, String author, String genre, int number) {

        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("librarymanager\\library.txt", true))) {

            // write new data, if ID doesn't exist.
            if (!idExists(ISBN)) {
                out.writeUTF("Id" + ISBN + "\n");
                out.writeUTF("Name: " + name + "\n");
                out.writeUTF("Author: " + author + "\n");
                out.writeUTF("Genre: " + genreChooser(genre) + "\n");
                out.writeUTF("Avaible: " + number);
                out.writeUTF("\n");
                return;
            }
        } catch (IOException exc) {
            System.out.println("Exception has occurred while method was writing binary data.");
        }
        System.out.println("ISBN already exists.");
    }

    // method, which chooses the Genre, based on data.
    private String genreChooser(String genres) {
        if (genreExists(genres)) {
            return genres;
        }
        return "OTHER";
    }

    // check if the current genre exists in enuminator list.
    private boolean genreExists(String genres) {
        for (BookType genre : BookType.values()) {
            if (genre.toString().equals(genres)) {
                return true;
            }
        }
        return false;
    }

    // a metthod, which reads the book information, based on he ID.
    public void readData(String ISBN) {
        try (DataInputStream in = new DataInputStream(new FileInputStream("librarymanager\\library.txt"))) {

            // read until the end.
            while ((line = in.readUTF()) != null) {
                if (line.equals(ISBN)) {
                    for (int i = 0; i < 4; i++) {
                        System.out.println(line);
                    }
                    return;
                }
            }
        } catch (IOException exc) {
            System.out.println("An error has occurred, while binary data was reading");
        }
    }

    // method, which updated the number of books.
    public void updateNum(String ISBN, String num) {
        update(ISBN, num);
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream("librarymanager\\library.txt"))) {
            for (String l : lines) {
                out.writeUTF(l);
            }
        } catch (IOException exc) {
            System.out.println("Exception occurred while file was changing quantities.");
        }
    }

    // methodm which removes the number from array.
    private void update(String ISBN, String num) {
        readToArray();

        // iterate through array list.
        for (String l : lines) {
            if (l.equals(ISBN) && l != null) {
                for (int i = 0; i < 4; i++) {
                    if (lines.get(i).startsWith("Avaible: ")) {
                        lines.set(i, "Avaible: " + num);
                        break;
                    }
                }
            }
        }
    }

    // method, which reads the file and gets it into array.
    private void readToArray() {
        try (DataInputStream in = new DataInputStream(new FileInputStream("librarymanager\\library.txt"))) {

            while ((line = in.readUTF()) != null) {
                lines.add(line);
            }
        } catch (IOException exc) {
            System.out.println("Exception has occurred while method was writing data to array.");
        }
    }

    // method, which cheks if current ID exists.
    private boolean idExists(String ISBN) {
        try (DataInputStream in = new DataInputStream(new FileInputStream("librarymanager\\library.txt"))) {

            // read until the end.
            while ((line = in.readUTF()) != null) {
                if (line.equals(ISBN))
                    return true;
            }
        } catch (IOException exc) {
            System.out.println("An error has occurred, while binary data was reading");
        }
        return false;
    }
}
