# Library-Manager

A simple console-based **Library Management System** written in Java.  
This project allows users to manage a local file-based database of books using plain text files. It simulates real-world operations such as:

- ✅ Adding new books  
- 🔍 Finding a book by ISBN  
- 🔄 Updating a book’s quantity  
- ❌ Removing a book by ISBN  
- 📃 Viewing the entire book collection  

---

## 🗂️ Book Format

Each book is stored in `library.txt` in the following format:

```
Id: 978-3-16-148410-0
Name: Clean Code
Author: Robert C. Martin
Genre: PROGRAMMING
Avaible: 5
---
```

Books are separated by `"---"` to mark the end of an entry.

---

## 🧠 Technologies Used

- Java Core  
- BufferedReader & BufferedWriter  
- Enumerations (`enum`)  
- Console-based user input with `Scanner`  
- File I/O for persistence  

---

## ▶️ How to Run

1. Clone the repository or download the files:
   ```bash
   git clone https://github.com/YOUR_USERNAME/LibraryManager.git
   ```

2. Make sure the project structure looks like this:
   ```
   LibraryManager/
   └── librarymanager/
       ├── BookType.java
       ├── LibraryEntrance.java
       ├── LibraryManager.java
       ├── Main.java
       └── library.txt
   ```

3. Compile the source code:
   ```bash
   javac librarymanager/*.java
   ```

4. Run the application:
   ```bash
   java librarymanager.Main
   ```

---

## 💡 Example Usage

```
===LIBRARY MANAGMENT
1. Add book.
2. Find book.
3. Update book's quantity.
4. Remove book.
5. Show storage.
6. Exit.
Choose: 1

Write id: 978-3-16-148410-0
Write the title: Clean Code
Write the author: Robert C. Martin
Write the genre: programming
Write the quantity: 10
The book has been saved successfully.
```

---

## 📌 Notes

- Genre is validated against the enum:
  `FANTACY, BIOGRAPHY, SCIENCE, PROGRAMMING, ALGORITHMS, SCRIPTING`.
  If invalid, the genre will default to `OTHER`.

- The file `library.txt` must be located inside the `librarymanager/` directory.

---

## 🚀 Contributions & Future Ideas

You can improve this project by:

- Adding encryption for secure storage  
- Creating a GUI with JavaFX  
- Migrating to a database (like SQLite)  
- Implementing search/filter by name or author  

---

## 👨‍💻 Author

Made with 💻 and ☕ by [Your Name Here].  
Feel free to fork, star ⭐, or improve it!

