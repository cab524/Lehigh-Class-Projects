# Project 4: Signal Handling and Interprocess Communication
This project explores the core principles of process management and signal communication in C, showcasing the creation of multiple child processes that interact dynamically with a parent process through custom signal handling.

In this project:
- Multiple child processes (NUMCHILDREN) are dynamically created to send random signals with integer values to the parent process.
- The parent process uses sigaction() to handle signals safely, responds to child processes with SIGUSR1, and accumulates values received from them.
- Async-signal-safe programming principles are followed to ensure robust and efficient signal handling.
- A provided Makefile and autograder validate functionality across different configurations (NUMCHILDREN = 4, 8, 16).
This project demonstrates practical implementations of process synchronization, signal handling, and system calls in C.

