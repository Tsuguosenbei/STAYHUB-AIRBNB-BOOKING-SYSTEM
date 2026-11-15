## 🚀 How to Run the Application

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Eclipse IDE (recommended) or a terminal
- Git (optional, for cloning the repository)

### Option 1: Run Using Eclipse IDE

1. Download or clone the STAYHUB GitHub repository.
2. Open Eclipse IDE.
3. Select **File > Import > General > Existing Projects into Workspace**.
4. Select the downloaded STAYHUB project folder.
5. Click **Finish**.
6. Expand the `src` folder.
7. Right-click `Main.java`.
8. Select **Run As > Java Application**.
9. Follow the instructions displayed in the console.

### Option 2: Run Using the Terminal

Clone the repository:

```bash
git clone https://github.com/Tsuguosenbei/STAYHUB-AIRBNB-BOOKING-SYSTEM.git
cd STAYHUB-AIRBNB-BOOKING-SYSTEM
```

Compile the Java source files:

```bash
javac -d bin src/*.java
```

Run the application:

```bash
java -cp bin Main
```

### Main Menu

The application provides six menu options:

1. Browse Available Properties
2. Book a Property
3. Cancel a Reservation
4. Host Portal
5. View Active Reservations
6. Exit

### Important Notes

- The application uses a console-based interface.
- Property and reservation data are stored in memory.
- Data created during a session are not saved after the application closes.
- The Host Portal uses a demonstration PIN configured in the source code.
