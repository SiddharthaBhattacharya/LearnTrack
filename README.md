# LearnTrack - Student & Course Management

LearnTrack is a small console application written in Core Java. It keeps student, course and enrollment details in memory while the program is running. It is intended as a practice project for Java basics and object-oriented programming.

## Features

- Add, view, search, update and deactivate students
- Add, view, search, activate and deactivate courses
- Enroll active students in active courses
- View enrollments for one student or all students
- Mark an enrollment as completed or cancelled
- Check invalid menu input and display a useful message when an ID is not found

The program uses `ArrayList` for in-memory storage. Data is cleared when the application exits; there is no database or file storage.

## Requirements

- JDK 17 or newer (the project was compiled and tested with OpenJDK 21)
- A terminal or Java IDE such as IntelliJ IDEA or Eclipse

## Compile and run

Open a terminal in the project root folder (`LearnTrack`).

### macOS / Linux

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out com.airtribe.learntrack.Main
```

### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$files = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName }
javac -d out $files
java -cp out com.airtribe.learntrack.Main
```

In an IDE, open the `LearnTrack` folder as a Java project and run `com.airtribe.learntrack.Main`.

## Project structure

```text
src/com/airtribe/learntrack/
├── Main.java
├── constants/       # Menu values and application text
├── entity/          # Person, Student, Course, Enrollment
├── enums/           # EnrollmentStatus
├── exception/       # EntityNotFoundException
├── repository/      # ArrayList-based data storage
├── service/         # Student, course and enrollment rules
└── util/            # ID generation and input validation

docs/
├── Setup_Instructions.md
├── JVM_Basics.md
└── Design_Notes.md
```

## A few things to try

1. Add a student and note the student ID.
2. Add a course and note the course ID.
3. Enroll that student in the course.
4. View the student's enrollments and mark one as completed.
5. Try searching for an ID that does not exist and enter text instead of a menu number.

See `docs/` for setup and design notes.
