# Homework Assignment: Generics and Recursion
## This Java project demonstrates the use of generics, recursion, and comparators through the implementation of an Organizer class that maintains a sorted list of elements such as notes and contacts. 
### Key features include:
- Generic Class Design: The Organizer<T> class uses Java’s ArrayList and supports storing any type with natural or custom ordering.
- Recursive Binary Search: Efficiently finds elements using a generic recursive binary search algorithm.
- Flexible Sorting: Supports dynamic sorting via Java’s Comparator interface. Includes:
  - ComparatorByTitle to sort Note objects by title
  - ComparatorByEmail to sort Contact objects by email
- Comparable Interface:
- Note objects are sorted by date
- Contact objects are sorted by name
- Dynamic Comparator Switching: Users can update the sorting logic at runtime with setComparator(), which re-sorts the list accordingly.
- File Input: readNotes() and readContacts() methods populate the organizer with data from notes.txt and contacts.txt.

### Features Tested:
- Adding and removing notes/contacts while preserving sort order
- Searching for entries using binary search
- Re-sorting lists using different comparators
- Output matches expected formatted results for both notes and contacts

### Complexity:
All methods in the Organizer class are annotated with their respective time complexities using Big-O notation.
