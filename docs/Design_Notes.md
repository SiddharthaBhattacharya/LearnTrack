# Design Notes

## Why use `ArrayList` instead of an array?

The number of students, courses and enrollments is not known before the program starts. An array has a fixed length, while an `ArrayList` can grow when new objects are added. It also provides useful methods such as `add`, `remove` and `size`, so it fits this small in-memory project.

## Where are static members used?

`IdGenerator` has static counters and static methods. There is no need to create an `IdGenerator` object just to get the next ID, and all services use the same counters during one run of the application. `MenuOptions` and `AppConstants` use `public static final` fields for values shared by the application.

These IDs are only kept in memory. They start again from 1 each time the program is restarted, which is acceptable for this exercise because the data is not saved between runs.

## Where is inheritance used?

`Student` extends `Person`. Both classes have common details such as ID, first name, last name and email, so those fields and their getters/setters are kept in `Person`. `Student` adds the batch and active fields that only apply to a student.

`Student` also overrides `getDisplayName()`. It calls `super.getDisplayName()` and adds the student's batch to the result. This shows inheritance, the use of `super`, and method overriding without adding a complicated class hierarchy.

## How is the code separated?

- `entity` contains the objects and their fields.
- `repository` keeps objects in `ArrayList` collections and looks them up by ID.
- `service` contains the main rules, such as refusing an enrollment for an inactive student or course.
- `ui` is not a separate package in this version: `Main.java` handles the menus, reads input and calls services.
- `util` contains ID generation and input helpers.
- `exception` contains the custom exception used when an ID cannot be found.

## Basic validation and exceptions

`InputValidator` catches `NumberFormatException` when a user enters something other than a whole number. The services throw `EntityNotFoundException` when the requested student, course or enrollment does not exist. The console catches those exceptions and prints a message instead of stopping the program.

## Current limitations

All records stay in memory and are lost when the program exits. This version does not use a database, file persistence, streams, concurrency or a user login system. Those can be added in a later version after the Core Java basics are comfortable.
