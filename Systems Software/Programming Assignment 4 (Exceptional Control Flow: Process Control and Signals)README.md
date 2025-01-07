# Programming Assignment 4 (Exceptional Control Flow: Process Control and Signals) 
 

## Student Learning Outcomes
1) Create processes using the system call `fork()`
2) Send signals from a process to another using the system call `sigqueue()`
3) Install portable signal handlers using `sigaction()`
4) Write signal handlers that follow the guidelines for writing async signal-safe signal handlers

## Assignment Description
Write a C program, `prog4.c`, to create `NUMCHILDREN` processes (4, 8, or 16) that send a random signal with an integer value to the parent process. The parent process receives the signals and send a `SIGUSR1` with an integer value to each sender process.

In the parent process, you will need to install the same signal handler to catch all catchable signals (the exceptions are: `SIGCHLD`, as we want the default behavior for reaping, and `SIGSTOP` and `SIGKILL` for which the default behavior cannot be changed).

In each child process, you will randomly select a signal to send to the parent process with an integer value equal to `(i*20)` where `i` is the child index. Do not send any of the following signals from the child processes: `SIGUSR1`, `SIGSTOP`, `SIGKILL`, and `SIGCHLD`. After sending the signal, the child process will pause until the signal `SIGUSR1` is received from the parent process, then exits with status 0

Then, in the parent process, you will need to reap all the child processes using `wait()` or `waitpid()`. The status of each child process, when reaped, must be printed.

In the signal handler, if the received signal is `SIGUSR1`, print a message indicating that the child process received a `SIGUSR1` signal and the integer value received with the signal. If the received signal is not `SIGUSR1`, the parent process prints a message about the signal and the integer value received. Then, the parent process sends back a `SIGUSR1` signal to the sending process with an integer value equal to the received value multiplied by 3. The parent process accumulates the values received from the children in a global variable `total` printed at the end before returning from the main method.

A skeleton `prog4.c` file is provided that contains the declaration of an array to hold the signal names and the prototypes of the wrapper functions for all the system calls to be used in this assignment.

Your signal handler function must be named `signal_handler` and you must use `sigaction()` to install the handler (`signal()` is not allowed. Use the wrapper function `Signal()` that uses `sigaction()` available on the slides and in the textbook)

You must write an **async-signal-safe** signal handler. Follow the guidelines for writing async-signal-safe handlers covered in class and available in the textbook. 
You must put comments in your signal handler function describing which guidelines you followed to create an async-signal-safe handler; if you feel one or more rules do not need to be followed, explain your rationale. 

The initial repository contains the following files:
- `prog4.c` where you will define the main method, the signal handler function, and the system call wrapper functions
- `Makefile` to compile `prog4.c` with the macro `NUMCHILDREN` defined at compile time.  The targets are `test_4`, `test_8`, and `test_16` for `NUMCHILDREN` = 4, 8, and 16 respectively. The program is executed and the output is saved to the file `output`. The file `output` is checked by the script file `checkOutput.pl`. Run the targets `test_4`, `test_8`, and `test_16` to make sure your code passes all the tests.
- `checkOutput.pl`, a Perl script to compare the file output to the reference output and generate a score out of 90. The expected output for `NUMCHILDREN=8` is provided below for your reference. Your output must be exactly the same, except for the pid numbers and the signal names. Make the file `checkOutput.pl` executable using the command `chmod 700 checkOutput.pl` before running the different makefile targets.

**Resources for the system calls needed for this assignment:**

[sigaction() man page](https://man7.org/linux/man-pages/man2/sigaction.2.html)

[sigqueue() man page](https://man7.org/linux/man-pages/man3/sigqueue.3.html)

[Signal Safety man page](https://man7.org/linux/man-pages/man7/signal-safety.7.html) 

[System Calls man page](https://man7.org/linux/man-pages/man2/syscalls.2.html)


**Sample Output of the autograder (for NUMCHILDREN = 8)**
```
Parent process 1722455 installing signal handlers
Parent process caught SIGPROF with the value 0
Parent process caught SIGURG with the value 20
Parent process 1722455 sending SIGUSR1 to the child process 1722458 with the value 60
Parent process 1722455 sending SIGUSR1 to the child process 1722457 with the value 0
Parent process caught SIGUSR2 with the value 60
Parent process 1722455 sending SIGUSR1 to the child process 1722460 with the value 180
Child process caught SIGUSR1 with the value 60
Child process caught SIGUSR1 with the value 0
Child process caught SIGUSR1 with the value 180
Child process 1722458 sending SIGURG to the parent process with the value 20
Child process 1722457 sending SIGPROF to the parent process with the value 0
Child process 1722460 sending SIGUSR2 to the parent process with the value 60
Parent process caught SIGIO with the value 40
Parent process 1722455 sending SIGUSR1 to the child process 1722459 with the value 120
Parent process caught SIGVTALRM with the value 80
Parent process 1722455 sending SIGUSR1 to the child process 1722461 with the value 240
Child process caught SIGUSR1 with the value 120
Child process caught SIGUSR1 with the value 240
Child process 1722459 sending SIGIO to the parent process with the value 40
Child process 1722461 sending SIGVTALRM to the parent process with the value 80
Parent process caught SIGIO with the value 100
Parent process 1722455 sending SIGUSR1 to the child process 1722462 with the value 300
Child process caught SIGUSR1 with the value 300
Child process 1722462 sending SIGIO to the parent process with the value 100
Parent process caught SIGTTIN with the value 120
Parent process 1722455 sending SIGUSR1 to the child process 1722463 with the value 360
Child process caught SIGUSR1 with the value 360
Child process 1722463 sending SIGTTIN to the parent process with the value 120
Parent process caught SIGTERM with the value 140
Parent process 1722455 sending SIGUSR1 to the child process 1722464 with the value 420
Child process caught SIGUSR1 with the value 420
Child process 1722464 sending SIGTERM to the parent process with the value 140
Child process 1722457 terminated normally with exit status 0
Child process 1722458 terminated normally with exit status 0
Child process 1722459 terminated normally with exit status 0
Child process 1722460 terminated normally with exit status 0
Child process 1722461 terminated normally with exit status 0
Child process 1722462 terminated normally with exit status 0
Child process 1722463 terminated normally with exit status 0
Child process 1722464 terminated normally with exit status 0
Parent process 1722455 received a total of 560
./checkOutput.pl output 8
installing     = 1
child sending  = 8
child caught   = 8
parent sending = 8
parent caught  = 8
normally       = 8
abnormally     = 0
total          = 1
-------------------
Score  = 90/90
```
