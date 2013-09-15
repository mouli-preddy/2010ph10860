Program:
The class that reads lines from the text file.
The text file is sent to this class during initiation.
A public class is present that will iterate through the file to return RISCInstruction class.



Label:
The class that stores locations of all the labels that it encounterd while reading through the file.



Debug:
The class that is responsible for all the console output. An print statement should pas through this class to get printed
on the console.
Contains various functions depending on the severity of debug command needed.
Debug mode is enabled during initiation.



Register:
The static class holding the registers for the emulator.



Stack:
The static class holding the stack pointer along with 4096 32bit memmory addresses.



RISCInstruction:
The most important class of the emulator.
Constructor parses the command and populates various parameters which are later used for executing the command.
exeCmd() passes itself to various classes dynamically, for the command to be executed.



CMDS_*
The classes that are invoked to do certain tasks on all the above classes so as to accomplish a task.
