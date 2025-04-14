# Homework Assignment: Exception Handling and File IO
## This Java application simulates a scheduling system for events such as appointments and meetings. It emphasizes clean object-oriented design, custom exceptions, file handling, user interaction, and basic search/sort functionalities.
### Key Features include:
- Java Class Hierarchy: Implements a well-structured class hierarchy (Event, Appointment, Meeting, Date, and Time) based on UML specifications.
- Custom Exception Handling:
  - InvalidDateTimeException is thrown when invalid date/time values are detected.
  - Proper use of the throws and try-catch mechanisms across constructors and setters.
  - Regular Expressions: Validates and parses date/time strings in one-arg constructors using regex.
- File Input:
  - readEvents(Event[] list, String filename): Reads events from events.txt into an array and handles FileNotFoundException and InvalidDateTimeException.
- User Interaction via Menu: Supports the following actions through console prompts:
    1. View all events
    2. Search event by description
    3. Search events by date (with validation)
    4. Sort events by date and time
    5. Exit the program
- Search and Sort:
  - findEvent() and findEvents() allow filtering by description or date.
  - sortEvents() sorts events chronologically using custom criteria.

### What It Demonstrates
- Object-oriented programming and class design
- Exception handling with custom-defined exceptions
- Regular expression parsing
- Reading and writing from text files
- Interactive CLI with input validation
- Sorting and searching in arrays

