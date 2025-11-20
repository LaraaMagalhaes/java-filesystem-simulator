# File System Simulator (SimuFS)

**Course:** Operating Systems
**Language:** Java
**Team Members:** Anna Lara Magalhães and Luis Eduardo Vale

## Abstract
This project proposes the development of a simulator to understand the structure and functioning of a file system, focusing on data persistence and integrity via Journaling.

---

## Part 1: Introduction to File Systems with Journaling

### File System Description
A File System is the operating system component responsible for managing how data is stored and retrieved on a storage device. Without it, data would be just a disorganized set of bytes. It organizes this data into files and directories, maintaining metadata such as names, sizes, and dates.

### The Journaling Concept
Journaling is a technique used to ensure data integrity in case of failures (such as power outages or system crashes).
* **Mechanism:** Before making the actual change to the disk data (creating a folder, moving a file), the system writes the intention of the operation into a separate area called the "Journal" (Log).
* **Types:** In this simulator, we implement logic similar to **Write-Ahead Logging**, where the operation is logged as `PENDING` before it happens and `COMMIT` after it is successfully completed. If the system restarts and there are pending operations, it knows a failure occurred.

---

## Part 2: Simulator Architecture

### Data Structure
The simulator uses a **Tree** structure to represent the file system in memory:
* **Root Node:** The `/` directory is the base of the tree.
* **Internal Nodes (Directory):** Contain a list of children.
* **Leaf Nodes (File):** Contain the final data (text content).

Persistence is achieved through **Object Serialization (Java Serialization)**, saving the entire object tree into the `filesystem.dat` file.

### Journaling Implementation
The `Journal` class manages a text file (`storage/journal.log`).
1.  **Registration:** System captures the intent.
2.  **Execution:** System alters the tree structure in memory.
3.  **Logging:** System writes the operation (e.g., `CREATE`, `DELETE`) to the log file.
---

## Part 3: Java Implementation

The project uses a Simplified MVC Pattern:

### 1. Package `com.fs.model`
* **`Directory.java`:** Represents a folder. 
* **`File.java`**: Represents a file with attributes like name and content.

### 2. Package `com.fs.system`
* **`FileSystemSimulator.java`:** The "brain" of the system.
    * Manages navigation (`cd`).
    * Executes commands (`mkdir`, `create`, `rm`, `cp`, `mv`).
    * Handles persistence (Save/Load `filesystem.dat`).
* **`Journal.java`:** Handles writing logs to `journal.log`.

### 3. Package `com.fs.cli`
* **`Main.java`**: Command Line Interface (Shell). Parses user input and executes simulator methods.

---

## Part 4: Installation and Usage

### Prerequisites
* Java JDK 11 or higher installed.
* IDE (VS Code, IntelliJ, or Eclipse) or Terminal.

### How to Run
1.  Clone this repository.
2.  Open the project in your IDE.
3.  Run the main class: `src/main/java/com/fs/cli/Main.java`.

### Available Commands

| Command | Description | Usage Example |
| :--- | :--- | :--- |
| `mkdir` | Creates a directory | `mkdir docs` |
| `cd` | Navigates between folders | `cd docs` |
| `ls` | Lists content | `ls` |
| `create` | Creates a file | `create notes.txt content...` |
| `rm` | Removes file or folder | `rm notes.txt` |
| `cp` | Copies a file | `cp notes.txt backup.txt` |
| `mv` | Renames file or folder | `mv old.txt new.txt` |