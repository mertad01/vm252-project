package edu.luther.cs252.group1;

import edu.luther.cs252.group1.vm252utilities.VM252Utilities;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

import static edu.luther.cs252.group1.vm252utilities.VM252Utilities.*;
import static edu.luther.cs252.group1.vm252utilities.VM252Utilities.decodedInstructionComponents;

public class VirtualMachine252 {

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
        }

        public VirtualMachine252(String objectFileName) {
            this.memory = VM252Utilities.readObjectCodeFromObjectFile(objectFileName);
        }

    //
    // Private Accessors
    //
        private int[] getInstructionComponents() {
            return instructionComponents;
        }

    //
    // Private Mutators
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
        }

    //
    // Public Accessors
    //
        public short getAccumulator() {
            return accumulator;
        }

        public short getProgramCounter() {
            return programCounter;
        }

        public String getNextInstruction() {
            return nextInstruction;
        }

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
        public void setFile(String objectFileName) {
            this.memory = VM252Utilities.readObjectCodeFromObjectFile(objectFileName);
            this.programCounter = 0;
            this.accumulator = 0;
        }

        public void setAccumulator(short accumulator) {
            this.accumulator = accumulator;
        }

        public void setProgramCounter(short programCounter) {
            this.programCounter = programCounter;
        }

        // FIXME: Does not handle hex properly yet
        public void setMemoryByte(int memoryAddress, byte hexByte) {
//            memory[memoryAddress] = hexByte;
            throw new UnsupportedOperationException("Not implemented properly");
        }

        // TODO: Create breakpoints
        public void toggleBreakpoint(int memoryAddress) {
            breakpoints[memoryAddress] = !breakpoints[memoryAddress];
        }


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
                                    try {
                                        in.next();
                                        System.out.println("Bad integer value; try again");
                                    }
                                    // Catch CTRL+D (works since in.next() won't have anything to find)
                                    catch (NoSuchElementException exception) {
                                        System.out.println("EOF reading input;  machine halts");
                                        // Stop the program
                                        break; // FIXME: temporary, don't use break later
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
        }

    public void runMachine() {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

    public void reinitializeProgramCounter() {
        setProgramCounter((short) 0);
    }

    // Probably not necessary to have this method, Java garbage collection should do this for us
    public void quit() {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

    // TODO: Create a help message.
    // Depending on how we decide to fulfill the "help" command we might now need this method
    public static void help() {
        throw new UnsupportedOperationException("Not implemented, yet");
    }

}
