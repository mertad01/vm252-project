package edu.luther.cs252.group1.model;

import edu.luther.cs252.group1.model.vm252utilities.VM252Utilities;
import edu.luther.cs252.group1.observation.BasicObservable;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static edu.luther.cs252.group1.model.vm252utilities.VM252Utilities.*;
import static edu.luther.cs252.group1.model.vm252utilities.VM252Utilities.decodedInstructionComponents;

public class VirtualMachine252 extends BasicObservable {

    //
    // Main method for testing
    //
    public static void main(String[] args) {
        VirtualMachine252 vm252 = new VirtualMachine252(
                "/home/adam/Documents/School/CS-252/Project/Phase0/phase00.vm252obj"
        );
        vm252.updateInstructionComponents();
        System.out.println(vm252.getAccumulator());
        System.out.println(vm252.getProgramCounter());
        vm252.nextInstruction();
        System.out.println(vm252.getAccumulator());
        System.out.println(vm252.getProgramCounter());
        vm252.nextInstruction();
        System.out.println(vm252.getAccumulator());
        System.out.println(vm252.getProgramCounter());
//        if (vm252.getInstructionComponents() != null)
//            while (vm252.getInstructionComponents()[0] != 63)
//                vm252.nextInstruction();
    }

    //
    // Private Instance Fields
    //
        private short accumulator = 0; // the accumulator
        private short programCounter = 0; // the program counter
        private String nextInstruction; // TODO
        private byte[] memory; // program memory
        private boolean[] breakpoints = new boolean[8092];
        private final Scanner in = new Scanner(System.in); // input
        private final PrintStream out = System.out; // output
        private int[] instructionComponents;

    //
    // Constructors
    //

        public VirtualMachine252() {
            // Create and fill the memory with 0
            this.memory = new byte[8192];
            Arrays.fill(this.memory, (byte) 0x0);
            announceChange();
        }

        public VirtualMachine252(String objectFileName) {
            this.memory = VM252Utilities.readObjectCodeFromObjectFile(objectFileName);
            announceChange();
        }

    //
    // Private Accessors
    //
        // Public Instance Method int[] getInstructionComponents()
        //
        // Purpose:
        //     Access the next instruction's internal representation as an array of one or two elements
        //
        // Formals:
        //     none
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     Array of integers, size of either one or two, which represents the next instruction
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        private int[] getInstructionComponents() {
            return instructionComponents;
        }

    //
    // Private Mutators
    //
        // Public Instance Method void updateInstructionComponents()
        //
        // Purpose:
        //     Update the internal instruction byte representation of the next instruction
        //
        // Formals:
        //     none
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     none
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        private void updateInstructionComponents() {
            byte[] instructionBytes = new byte[1];
            instructionBytes[0] = memory[programCounter];

            // If instructionBytes is a valid opcode then it will hold it
            instructionComponents = decodedInstructionComponents(instructionBytes);
            // If components is null then we need to check for a 2-byte opcode/operand encoding
            if (instructionComponents == null) {
                instructionBytes = new byte[2];
                instructionBytes[0] = memory[programCounter];
                instructionBytes[1] = memory[programCounter + 1];
                instructionComponents = decodedInstructionComponents(instructionBytes);
            }
            announceChange();
        }

    //
    // Public Accessors
    //
        // Public Instance Method short getAccumulator()
        //
        // Purpose:
        //     Access the vm252's current accumulator value
        //
        // Formals:
        //     none
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     Accumulator of the vm252 as a short
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public short getAccumulator() {
            return accumulator;
        }

        // Public Instance Method short getProgramCounter()
        //
        // Purpose:
        //     Access the vm252's current programCounter value
        //
        // Formals:
        //     none
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     Program counter of the vm252 as a short
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public short getProgramCounter() {
            return programCounter;
        }

        // Public Instance Method String getNextInstruction()
        //
        // Purpose:
        //     Retrieve the next instruction string
        //
        // Formals:
        //     none
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     String containing the next instruction in line to be run
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public String getNextInstruction() {
            return nextInstruction;
        }


        // Public Instance Method byte[] getMemory()
        //
        // Purpose:
        //     Access the vm252 program memory
        //
        // Formals:
        //     none
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     Current program memory as an array
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        // TODO: Extend to support both "mb" and "ob" commands
        // mb ⇒ Display all of machine memory as bytes in hex
        // ob ⇒ Display the portion of machine memory holding object code as bytes in hex
        // Possibly should do this in our view/controller instead of the model
        public byte[] getMemory() {
            return memory;
        }

    //
    // Public Mutators
    //

        // Public Instance Method void setFile(String objectFileName)
        //
        // Purpose:
        //     Load a new file into memory and reset the programCounter/accumulator
        //
        // Formals:
        //     objectFileName (in) - name of a valid vm252 object file
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     none
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public void setFile(String objectFileName) {
            this.memory = VM252Utilities.readObjectCodeFromObjectFile(objectFileName);
            this.programCounter = 0;
            this.accumulator = 0;
            announceChange();
        }

        // Public Instance Method void setAccumulator(short accumulator)
        //
        // Purpose:
        //     Alter the contents of the accumulator
        //
        // Formals:
        //     accumulator (in) - new accumulator value
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     none
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public void setAccumulator(short accumulator) {
            this.accumulator = accumulator;
            announceChange();
        }

        // Public Instance Method void setProgramCounter(short programCounter)
        //
        // Purpose:
        //     Alter the contents of the program counter
        //
        // Formals:
        //     programCounter (in) - new program counter value
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     none
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public void setProgramCounter(short programCounter) {
            this.programCounter = programCounter;
            announceChange();
        }

        // FIXME: handle hex properly yet
        // Public Instance Method void setMemoryByte(int memoryAddress, byte hexByte)
        //
        // Purpose:
        //     Alter the memory at memoryAddress to the unsigned hex value hexByte (amb MA HB command).
        //
        // Formals:
        //     memoryAddress (in) - memory address
        //     hexByte (in) - new unsigned hex value
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     none
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public void setMemoryByte(int memoryAddress, byte hexByte) {
    //            memory[memoryAddress] = hexByte;
            announceChange();
            throw new UnsupportedOperationException("Not implemented properly");
        }

        // TODO: Create breakpoints
        // Public Instance Method void toggleBreakpoint(int memoryAddress)
        //
        // Purpose:
        //     Toggle on or off a breakpoint at a specific address in the vm252 memory
        //
        // Formals:
        //     memoryAddress (in) - memory address
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     none
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public void toggleBreakpoint(int memoryAddress) {
            breakpoints[memoryAddress] = !breakpoints[memoryAddress];
            announceChange();
        }


    // Public Instance Method void nextInstruction()
    //
    // Purpose:
    //     Execute the next instruction in the vm252 memory
    //
    // Formals:
    //     none
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     none
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    public void nextInstruction() {
        updateInstructionComponents();
        if (getInstructionComponents() != null) {
                int opcode;
                int operand;

                switch (getInstructionComponents().length) {
                    // Components only holds opcode
                    case 1 -> {
                        opcode = getInstructionComponents()[0];

                        // Handle opcode behaviors
                        switch (opcode) {
                            case INPUT_OPCODE -> {
                                System.out.print("INPUT: ");

                                // Check if input is a short
                                if (!in.hasNextShort()) {
                                    // Attempt to move past incorrectly inputted value
                                    // FIXME: don't use try:catch for this
                                    try {
                                        in.next();
                                        System.out.println("Bad integer value; try again");
                                    }
                                    // Catch CTRL+D (works since in.next() won't have anything to find)
                                    catch (NoSuchElementException exception) {
                                        System.out.println("EOF reading input;  machine halts");
                                        // Stop the program
                                    }
                                }
                                else {
                                    accumulator = in.nextShort();
                                    programCounter += 1;
                                }
                            }
                            case OUTPUT_OPCODE -> {
                                out.print("OUTPUT: ");
                                out.println(accumulator);
                                programCounter += 1;
                            }
                            case NO_OP_OPCODE -> programCounter += 1;
                            case STOP_OPCODE -> {
//                                running = false; // End program FIXME: do something maybe???
                            }
                        }
                    }
                    // "Components" holds opcode and operand
                    case 2 -> {
                        opcode = getInstructionComponents()[0];
                        operand = getInstructionComponents()[1];

                        // Handle opcode behaviors
                        switch (opcode) {
                            case LOAD_OPCODE -> {
                                accumulator = ((short) ((memory[operand] << 8) | (memory[operand+1] & 0b011111111)));
                                programCounter += 2;
                            }
                            case STORE_OPCODE -> {
                                memory[operand] = (byte) (accumulator >>> 8);
                                memory[operand+1] = (byte) accumulator;
                                programCounter += 2;
                            }
                            case ADD_OPCODE -> {
                                accumulator += ((short) ((memory[operand] << 8) | (memory[operand+1] & 0b011111111)));
                                programCounter += 2;
                            }
                            case SUBTRACT_OPCODE -> {
                                accumulator -= ((short) ((memory[operand] << 8) | (memory[operand+1] & 0b011111111)));
                                programCounter += 2;
                            }
                            case JUMP_OPCODE -> programCounter = (short) operand;
                            case JUMP_ON_ZERO_OPCODE -> {
                                if (accumulator == 0)
                                    programCounter = (short) operand;
                                else
                                    programCounter += 2; // PC += 2
                            }
                            case JUMP_ON_POSITIVE_OPCODE -> {
                                if (accumulator > 0)
                                    programCounter = (short) operand;
                                else
                                    programCounter += 2; // PC += 2
                            }
                            case SET_OPCODE -> {
                                accumulator = (short) operand;
                                programCounter += 2; // PC += 2
                            }
                        }
                    }
                    // If we somehow (may not be possible) get a getInstructionComponents()
                    // list which is wrong increase the program counter
                    default -> programCounter += 1;
                }
            }
            // If a valid opcode is not found, increase the PC and continue
            else
                programCounter += 1;
//        throw new UnsupportedOperationException("Not implemented, yet");
            announceChange();
        }

    // Public Instance Method void runMachine()
    //
    // Purpose:
    //     Run through the vm252 instructions with a specific delay between executing instructions
    //
    // Formals:
    //     none
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     none
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    public void runMachine() {
        announceChange();
        throw new UnsupportedOperationException("Not implemented, yet");
    }

    // Public Instance Method void reinitializeProgramCounter()
    //
    // Purpose:
    //     Act as the "z" command to reset the program counter back to 0
    //
    // Formals:
    //     none
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     none
    //
    // Worst-case asymptotic runtime:
    //     O(1)
    //
    public void reinitializeProgramCounter() {
        setProgramCounter((short) 0);
        announceChange();
    }

    // TODO: Create a help message (or add tooltips to every component)
    // Depending on how we decide to fulfill the "help" command we might not need this method
    public static void help() {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

}
