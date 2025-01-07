#include <stdio.h>
#include <sys/types.h> 
#include <sys/stat.h>
#include <unistd.h> 
#include <stdlib.h>
#include <signal.h>
#include <wait.h>
#include <fcntl.h>
#include <string.h>
#include <errno.h>
#include <limits.h>
#include <time.h>

char signame[32][10];  /* array to hold signal names */
/* function to initialize the array of signal names */
void initialize_signals();

/* Wrapper functions for system calls */
void unix_error(const char *msg); //cannot use fprintf?/printf?
pid_t Fork();
pid_t Wait(int *status);
pid_t Waitpid(pid_t pid, int *status, int options);
int Sigqueue(pid_t pid, int signum, union sigval value);
// sigqueue - sends  the  signal  specified  in  sig  to the process whose PID is given in pid.
int Sigemptyset(sigset_t *set); //return 0 if ok, -1 on error
// sigemptyset - initializes the signal set given by set to empty, with all signals excluded from the set.
int Sigfillset(sigset_t *set); //return 0 if ok, -1 on error
// sigfillset - initializes set to full, including all signals
int Sigaction(int signum, const struct sigaction *new_act, struct sigaction *old_act);
// sigaction - allows the calling process to examine and/or specify the action to be associated with a specific signal
int Sigprocmask(int how, const sigset_t *set, sigset_t *oldset); //return 0 if ok, -1 on error
// sigprocmask - examine and change blocked signals
ssize_t Write(int d, const void *buffer, size_t nbytes);
typedef void* handler_t;
//typedef void (*handler_t)(int); //typedef void* handler_t;
handler_t Signal(int signum, handler_t handler); //done

int child_caught = 0;
/*void child_sig_handler(int sig){
  child_caught++;
}*/

/* Signal Handler */
int total = 0;
void sig_handler(int sig, siginfo_t *value){ //void *ucontext removed since not needed

  sigset_t mask_all, prev_all;
  char printer[200];
  pid_t pid = value -> si_pid; //child process id
  int val = value -> si_value.sival_int;
  //total += val;
  if(sig == 10){
    sprintf(printer, "Child process caught SIGUSR1 with the value %d\n", val); //value.si_value? pid?
    Write(1, printer, strlen(printer)); //1=write to stdout
  }  else {
    sprintf(printer, "Parent process caught %s with the value %d\n", signame[sig], val); //first pid needs to change
    Write(1, printer, strlen(printer)); //1=write to stdout
  }
  //SEND BACK WITH VALUE RECEIVED * 3
  union sigval newValue;
  newValue.sival_int = val * 3;
  int newVal = newValue.sival_int;
  sprintf(printer, "Parent process %d sending SIGUSR1 to the child process %d with the value %d\n", getpid(), pid, newVal);
  Write(1, printer, strlen(printer));
  Sigqueue(pid, SIGUSR1, newValue); //sends signal to childprocess with changed value

  //ESSENTIAL SIGNAL HANDLING TO AVOID INTERRUPTIONS
  Sigfillset(&mask_all); //initialzies set to hold all signals
  Sigprocmask(SIG_SETMASK, &mask_all, &prev_all); //temporary blocks all signals listed in mask_all
  total += val;
  //important so we don't have any interruptions from other signals
  Sigemptyset(&mask_all); //initiallizes set to be empty
  Sigprocmask(SIG_SETMASK, &prev_all, NULL); //unbloks all signals

  return;
}

//guidelines were followed - tried to make is as async-safe signal handlers safe as possible
// main function
int main() {
  //pid_t pid = getpid(); //parent process
  char printer[200];
  initialize_signals();

  sprintf(printer, "Parent process %d installing signal handlers\n", getpid());
  Write(1, printer, strlen(printer)); //1=write to stdout

  for(int i = 1; i < 31; i++){ //installing signal handler to all catchable signals
    if(i != 9 && i != 17 && i != 19){ //9 - sigkill, 17 - sigchld, 19 - sigstop
      Signal(i, &sig_handler);
    }
  }
  //FORK X NUMBER OF TIMES in loop if fork == 0 send signal to parent process - sigqueue i*20 in loop
  //NUMCHILDREN SET IN MAKEFILE

  pid_t pids[NUMCHILDREN];
  //CHILD PROCESS
  for(int i = 0; i < NUMCHILDREN; i++){
    srand(time(NULL)+i);
    if((pids[i] = Fork()) == 0){ //if fork returns 0
      int j = 0;
      do {
        j = random() % 31;
      } while(j == 9 || j == 10 || j == 17 || j == 19);
      union sigval value;
      value.sival_int = i * 20;
      sprintf(printer, "Child process %d sending %s to the parent process with the value %d\n", getpid(), signame[j], value.sival_int);
      Write(1, printer, strlen(printer));
      Sigqueue(getppid(), j, value); //int Sigqueue(pid_t pid, int signum, union sigval value);
      pause(); //pauses until signal is received back from parents
      /*
      sigset_t mask_all, prev_all;
      Sigfillset(&mask_all); //initialzies set to hold all signals
      Sigprocmask(SIG_SETMASK, &mask_all, &prev_all); //temporary blocks all signals listed in mask_all
      child_caught++;
      //important so we don't have any interruptions from other signals
      Sigemptyset(&mask_all); //initiallizes set to be empty
      Sigprocmask(SIG_SETMASK, &prev_all, NULL);
      if(child_caught > 4){
        break;
      }*/

      exit(0);
      //pid refers to parent process id - signum is process value j - value is value being sent *20
    }
  }


  //slide 68 in part 1
  //REAP CHILD PROCESS
  int child_status;
  pid_t wpid;
  for(int i = 0; i < NUMCHILDREN; i++){
    //if((wpid = Waitpid(pid, &child_status, 0)) > 0){
    if((wpid = Waitpid(pids[i], &child_status, 0)) > 0){
      //waitpid returns process id if child terminates so must be greater than 0
      sprintf(printer, "Child process %d terminated normally with the exit status %d\n", wpid, WEXITSTATUS(child_status));
      Write(1, printer, strlen(printer));
    } //maybe add else to be through?
  }
  sprintf(printer, "Parent process %d received a total of %d\n", getpid(), total);
  Write(1, printer, strlen(printer));

  //never call sig_handler
  return 0;
}
void initialize_signals(){
  strcpy(signame[0],  "UNUSED");     // Unused
  strcpy(signame[1],  "SIGHUP");     // Hangup (POSIX)
  strcpy(signame[2],  "SIGINT");     // Interrupt (ANSI)
  strcpy(signame[3],  "SIGQUIT");    // Quit (POSIX)
  strcpy(signame[4],  "SIGILL");     // Illegal instruction (ANSI)
  strcpy(signame[5],  "SIGTRAP");    // Trace trap (POSIX)
  strcpy(signame[6],  "SIGIOT");     // IO trap (4.2 BSD); SIGABRT - Abort (ANSI)
  strcpy(signame[7],  "SIGBUS");     // BUS error
  strcpy(signame[8],  "SIGFPE");     // Floating-point exception (ANSI)
  strcpy(signame[9],  "SIGKILL");    // Kill, unblockable (POSIX)
  strcpy(signame[10], "SIGUSR1");    // User-defined signal 1 (POSIX)
  strcpy(signame[11], "SIGSEGV");    // Segmentation violation (ANSI)
  strcpy(signame[12], "SIGUSR2");    // User-defined signal 2 (POSIX)
  strcpy(signame[13], "SIGPIPE");    // Broken pipe (POSIX)
  strcpy(signame[14], "SIGALRM");    // Alarm clock (POSIX)
  strcpy(signame[15], "SIGTERM");    // Termination (ANSI)
  strcpy(signame[16], "SIGSTKFLT");  // Stack fault
  strcpy(signame[17], "SIGCHLD");    // Child status changed (POSIX); SIGCLD (System V)
  strcpy(signame[18], "SIGCONT");    // Continue (POSIX)
  strcpy(signame[19], "SIGSTOP");    // Stop, unblockable (POSIX)
  strcpy(signame[20], "SIGTSTP");    // Keyboard stop (POSIX)
  strcpy(signame[21], "SIGTTIN");    // Background read from tty (POSIX)
  strcpy(signame[22], "SIGTTOU");    // Background write to tty (POSIX)
  strcpy(signame[23], "SIGURG");     // Urgent condition on socket (4.2 BSD)
  strcpy(signame[24], "SIGCPU");     // CPU limit exceeded (4.2 BSD)
  strcpy(signame[25], "SIGXFSZ");    // File size limit exceeded (4.2 BSD)
  strcpy(signame[26], "SIGVTALRM");  // Virtual alarm clock (4.2 BSD)
  strcpy(signame[27], "SIGPROF");    // Profiling alarm clock (4.2 BSD)
  strcpy(signame[28], "SIGWINCH");   // Window size change (4.3 BSD, Sun)
  strcpy(signame[29], "SIGIO");      // I/O possible (4.2 BSD); Pollable event (System V)
  strcpy(signame[30], "SIGPWR");     // Power failure restart (System V)
  strcpy(signame[31], "SIGSYS");     // Bad system call
}

void unix_error(const char *msg){
  fprintf(stderr, "%s: %s\n", msg, strerror(errno));
  exit(0);
}

pid_t Fork(){
  pid_t forks;
  if((forks = fork()) == -1){ //note -1 is error
    unix_error("Fork Error");
  }
  return forks;
}

pid_t Wait(int *status){
  pid_t pid;
  if((pid = wait(status)) < 0){ //-1 is error
    unix_error("Wait Error");
  }
  return pid;
}

pid_t Waitpid(pid_t pid, int *status, int options){
  pid_t pids;
  if((pids = waitpid(pid, status, options)) < 0){ //-1 is error
    unix_error("Waitpid Error");
  }
  return pids;
}

int Sigqueue(pid_t pid, int signum, union sigval value){ //0 = success, -1 = error
  int sig;
  if((sig = sigqueue(pid, signum, value)) < 0){
    unix_error("Sigqueue Error");
  }
  return 0;
}

int Sigemptyset(sigset_t *set){ //return 0 on success and -1 on error.
  int sig;
  if((sig = sigemptyset(set)) < 0){
    unix_error("Sigemptyset Error");
  }
  return sig;
}

int Sigfillset(sigset_t *set){ //return 0 on success and -1 on error.
  int sig;
  if((sig = sigfillset(set)) < 0){
    unix_error("Sigfillset Error");
  }
  return sig;
}

int Sigaction(int signum, const struct sigaction *new_act, struct sigaction *old_act){ //success = 0, error = -1
  int sig;
  if((sig = sigaction(signum, new_act, old_act)) < 0){
    unix_error("Sigaction Error");
  }
  return sig;
}

int Sigprocmask(int how, const sigset_t *set, sigset_t *oldset){ //return 0 if ok, -1 on error
  int sig;
  if((sig = sigprocmask(how, set, oldset)) < 0){
    unix_error("Sigprocmask Error");
  }
  return sig;
}

ssize_t Write(int d, const void *buffer, size_t nbytes){
  ssize_t wrote;
  if((wrote = write(d, buffer, nbytes)) < 0){
    unix_error("Write Error");
    //_exit(errno); //async-signal-safe function
  }
  return wrote;
}

//portable signal handling
handler_t Signal(int signum, handler_t handler){ //removed *Signal and *handler
  struct sigaction action, old_action;
  action.sa_handler = handler;
  // blocks sigs of type being handled
  Sigemptyset(&action.sa_mask);
  //restart syscalls if possible
  action.sa_flags = SA_RESTART|SA_SIGINFO;
  //action.sa_flags = SA_RESTART|SA_SIGINFO to send info
  if(Sigaction(signum, &action, &old_action) < 0){
    unix_error("Signal Error");
  }
  return (old_action.sa_handler); //handler is set
}
