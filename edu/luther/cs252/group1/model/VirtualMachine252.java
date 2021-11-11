package edu.luther.cs252.group1.model;

import edu.luther.cs252.group1.model.vm252utilities.VM252Utilities;
import edu.luther.cs252.group1.observation.BasicObservable;

import java.util.Arrays;
import java.util.Scanner;

public class VirtualMachine252 extends BasicObservable {

    //region Constructors

    // TODO: preface
    public VirtualMachine252() {

        // Initialize each element of the memory to 0
        Arrays.fill(this.memory, (byte) 0x0);

    }

    // TODO: preface
    public VirtualMachine252(String objectFileName) {

        byte[] program = VM252Utilities.readObjectCodeFromObjectFile(objectFileName);
        if (program != null) {

            // Initialize each element of the memory to 0
            Arrays.fill(this.memory, (byte) 0x0);

            // Copy the program bytes into memory
            System.arraycopy(program, 0, memory, 0, program.length);
        }
    }
    //endregion

    //region Public Class Constants
    //endregion

    //region Private Class Constants
    //endregion

    //region Public Instance Fields
    //endregion

    //region Private Instance Fields

    //
    // Simulated Hardware
    //
    private short accumulator = 0;
    private short programCounter = 0;
    private final byte[] memory = new byte[VM252Utilities.numberOfMemoryBytes];

    private boolean suppressPcIncrement;
    private boolean lastInstructionCausedHalt;

    private final Scanner input = new Scanner(System.in);

    //
    // Delay in execution of instructions when running the program
    //
    private int executionDelay = 0;

    //endregion

    //region Public Class Methods
    //endregion

    //region Private Class Methods
    //endregion

    //region Public Instance Accessors

    // TODO: preface
    public short getProgramCounter() {
        return programCounter;
    }

    // TODO: preface
    public short getAccumulator() {
        return accumulator;
    }

    // TODO: preface
    public int getExecutionDelay() {
        return executionDelay;
    }

    // TODO: preface
    public boolean isLastInstructionCausedHalt() {
        return lastInstructionCausedHalt;
    }

    //endregion


    //region Public Instance Mutators

    // TODO: preface
    public void setProgramCounter(short programCounter) {
        this.programCounter = programCounter;
        announceChange();
    }

    // TODO: preface
    public void setAccumulator(short accumulator) {
        this.accumulator = accumulator;
        announceChange();
    }

    // TODO: preface
    public void runNextInstruction() {

        //
        // Let opcode = the operation code portion of the instruction stored
        //     at address programCounter
        // Let operand = the operand portion (if any) of the instruction
        //     stored at address programCounter
        //

        byte[] encodedInstruction
                = VM252Utilities.fetchBytePair(memory, programCounter);

        int[] decodedInstruction
                = VM252Utilities.decodedInstructionComponents(encodedInstruction);
        int opcode = decodedInstruction[0];

        short operand
                = decodedInstruction.length == 2
                ? ((short) (decodedInstruction[1]))
                : 0;

        suppressPcIncrement = false;
        lastInstructionCausedHalt = false;

        //
        // Simulate execution of a VM252 instruction represented by opcode
        //     (and for instructions that have an operand, operand), altering
        //     accumulator, programCounter, and memory, as required
        // Let suppressPcIncrement = true iff any kind of jump instruction was
        //     executed, a STOP instruction was executed, or a failed INPUT
        //     instruction was executed
        //

        switch (opcode) {

            case VM252Utilities.LOAD_OPCODE -> accumulator = VM252Utilities.fetchIntegerValue(memory, operand);

            case VM252Utilities.SET_OPCODE -> accumulator = operand;

            case VM252Utilities.STORE_OPCODE -> VM252Utilities.storeIntegerValue(memory, operand, accumulator);

            case VM252Utilities.ADD_OPCODE -> accumulator += VM252Utilities.fetchIntegerValue(memory, operand);

            case VM252Utilities.SUBTRACT_OPCODE -> accumulator -= VM252Utilities.fetchIntegerValue(memory, operand);

            case VM252Utilities.JUMP_OPCODE -> {

                programCounter = operand;
                suppressPcIncrement = true;

            }

            case VM252Utilities.JUMP_ON_ZERO_OPCODE -> {

                if (accumulator == 0) {
                    programCounter = operand;
                    suppressPcIncrement = true;
                }

            }

            case VM252Utilities.JUMP_ON_POSITIVE_OPCODE -> {

                if (accumulator > 0) {
                    programCounter = operand;
                    suppressPcIncrement = true;
                }

            }

            case VM252Utilities.INPUT_OPCODE -> {

                //
                // Let lastInstructionCausedHalt = true iff no integer
                //     is available from the standard input stream
                //     (discarding non-integer inputs, if necessary)
                //

                for (System.out.print("INPUT: "), System.out.flush();
                     input.hasNext() && !input.hasNextInt();
                     System.out.print("INPUT: "),
                             System.out.flush()) {
                    //
                    // Loop invariant:
                    //     On the current INPUT attempt, all
                    //     tokens in System.out prior to the
                    //     next available one have been
                    //     non-integer tokens
                    //
                    input.next();
                    System.out.println(
                            "INPUT: Bad integer value; try again"
                    );
                    System.out.flush();
                }

                lastInstructionCausedHalt = !input.hasNext();

                //
                // Issue an error message if no input was available
                //

                if (lastInstructionCausedHalt) {

                    System.out.println(
                            "EOF reading input;  machine halts"
                    );
                    System.out.flush();

                }

                //
                // Otherwise, let accumulator = the next integer read
                //     from the standard input stream
                //

                else

                    accumulator = ((short) input.nextInt());

            }

            case VM252Utilities.OUTPUT_OPCODE -> {

                System.out.println("OUTPUT: " + accumulator);
                System.out.flush();

            }

            case VM252Utilities.NO_OP_OPCODE -> {

                // do nothing

            }

            case VM252Utilities.STOP_OPCODE -> lastInstructionCausedHalt = true;

        }

        //
        // Increment the program counter to contain the address of the next
        //     instruction to execute, unless the program counter was already
        //     adjusted or the program is not continuing
        //

        if (!lastInstructionCausedHalt && !suppressPcIncrement)

            programCounter
                    = ((short)
                    ((programCounter + VM252Utilities.instructionSize(opcode))
                            % VM252Utilities.numberOfMemoryBytes)
            );

        announceChange();
    }

    //endregion

    //region Private Instance Methods
    //endregion


    public static void main(String[] args) {
        VirtualMachine252 vm252 = new VirtualMachine252(
                "/home/adam/Documents/School/CS-252/Project/Phase0/phase00.vm252obj"
        );
        do {
            System.out.print("PC: ");
            System.out.print(vm252.getProgramCounter());
            System.out.print(" | ACC: ");
            System.out.println(vm252.getAccumulator());
            vm252.runNextInstruction();
        } while (!vm252.isLastInstructionCausedHalt());
    }

}
