package edu.luther.cs252.group1.oldmodel.vm252utilities;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class VM252Utilities
{
    //
    // Public Class Constants
    //
        // Instructions having eight-bit encodings
        public static final int INPUT_OPCODE = 60;
        public static final int OUTPUT_OPCODE = 61;
        public static final int NO_OP_OPCODE = 62;
        public static final int STOP_OPCODE = 63;

        // Instructions having sixteen-bit encodings
        public static final int LOAD_OPCODE = 0;
        public static final int STORE_OPCODE = 1;
        public static final int ADD_OPCODE = 2;
        public static final int SUBTRACT_OPCODE = 3;
        public static final int JUMP_OPCODE = 4;
        public static final int JUMP_ON_ZERO_OPCODE = 5;
        public static final int JUMP_ON_POSITIVE_OPCODE = 6;
        public static final int SET_OPCODE = 14;

        public static final int numberOfMemoryBytes = 8192;


    //
    // Public Class Methods
    //

    //
    // Public Class Method byte [] readObjectCodeFromObjectFile(String objectFileName)
    //
    // Purpose:
    //     Reads the object‑code bytes only from the VM252 object‑code file having a
    //         given name
    //
    // Formals:
    //     objectFileName (in) - the name of the VM252 object-code file to be read
    //
    // Pre-conditions:
    //     none
    //
    // Post-conditions:
    //     none
    //
    // Returns:
    //     an array of byte’s holding bytes of the object-code for the object-code file
    //         having the name in objectFileName, if a file having the name in
    //         objectFileName exists and in a valid VM252 object-code file
    //     null, otherwise
    //
    // Worst-case asymptotic runtime:
    //     O(1 + (the file-length of the file having the name in objectFileName))
    //

        public static byte [] readObjectCodeFromObjectFile(String objectFileName)
        {

            byte [] objectCode = null;

            try {

                //
                // Let objectFile = a FileInputStream corresponding to the file whose name
                //     is in objectCodeFile
                //

                    final FileInputStream objectFile
                        = new FileInputStream(new File(objectFileName));

                byte [] sourceFileInformation = null;
                byte [] executableSourceLineMap = null;
                byte [] symbolicAddressInformation = null;
                byte [] byteContentMap = null;

                int byte3;
                int byte2;
                int byte1;
                int byte0;

                //
                // Read the content of objectFile into objectCode, sourceFileInformation,
                //     executableSourceLineMap, symbolicAddressInformation, and
                //     byteContentMap, collectively
                //

                    //
                    // Let objectCodeSize = the # of bytes of object code
                    //

                        byte3 = objectFile.read();
                        byte2 = objectFile.read();
                        byte1 = objectFile.read();
                        byte0 = objectFile.read();

                        if (byte0 == -1 || byte1 == -1 || byte2 == -1 || byte3 == -1)
                            throw new IOException();

                        final int objectCodeSize
                            = (byte3 & 0xff) << 24 | (byte2 & 0xff) << 16
                                | (byte1 & 0xff) << 8 | byte0 & 0xff;

                    //
                    // Let sourceFileInformationSize = the # of bytes of source file
                    //     information
                    //

                        byte3 = objectFile.read();
                        byte2 = objectFile.read();
                        byte1 = objectFile.read();
                        byte0 = objectFile.read();

                        if (byte0 == -1 || byte1 == -1 || byte2 == -1 || byte3 == -1)
                            throw new IOException();

                        final int sourceFileInformationSize
                            = (byte3 & 0xff) << 24 | (byte2 & 0xff) << 16
                                | (byte1 & 0xff) << 8 | byte0 & 0xff;

                    //
                    // Let executableSourceLineMapSize
                    //     = the # of bytes of the executable source-line map
                    //

                        byte3 = objectFile.read();
                        byte2 = objectFile.read();
                        byte1 = objectFile.read();
                        byte0 = objectFile.read();

                        if (byte0 == -1 || byte1 == -1 || byte2 == -1 || byte3 == -1)
                            throw new IOException();

                        final int executableSourceLineMapSize
                            = (byte3 & 0xff) << 24 | (byte2 & 0xff) << 16
                                | (byte1 & 0xff) << 8 | byte0 & 0xff;

                    //
                    // Let symbolicAddressInformationSize = the # of bytes of
                    //     symbolic-address information
                    //

                        byte3 = objectFile.read();
                        byte2 = objectFile.read();
                        byte1 = objectFile.read();
                        byte0 = objectFile.read();

                        if (byte0 == -1 || byte1 == -1 || byte2 == -1 || byte3 == -1)
                            throw new IOException();

                        final int symbolicAddressInformationSize
                            = (byte3 & 0xff) << 24 | (byte2 & 0xff) << 16
                                | (byte1 & 0xff) << 8 | byte0 & 0xff;

                    //
                    // Let symbolicAddressInformationSize
                    //     = the # of bytes of symbolic-address information
                    //

                        byte3 = objectFile.read();
                        byte2 = objectFile.read();
                        byte1 = objectFile.read();
                        byte0 = objectFile.read();

                        if (byte0 == -1 || byte1 == -1 || byte2 == -1 || byte3 == -1)
                            throw new IOException();

                        final int byteContentMapSize
                            = (byte3 & 0xff) << 24 | (byte2 & 0xff) << 16
                                | (byte1 & 0xff) << 8 | byte0 & 0xff;

                    if (byteContentMapSize != 0 && objectCodeSize != byteContentMapSize)
                        throw new IOException();

                    //
                    // Let objectCode[ 0 ... objectCodeSize-1 ] = the bytes of object code
                    //

                        objectCode = new byte[ objectCodeSize ];

                        int objectCodeReadStatus = objectFile.read(objectCode);

                        if (objectCodeReadStatus == -1)
                            throw new IOException();

                    //
                    // Let sourceFileInformation[ 0 ... sourceFileInformationSize-1 ]
                    //     = the bytes of source-file information
                    //

                        sourceFileInformation = new byte[ sourceFileInformationSize ];

                        int sourceFileNameReadStatus
                            = objectFile.read(sourceFileInformation);

                        if (sourceFileNameReadStatus == -1)
                            throw new IOException();

                    //
                    // Let executableSourceLineMap[ 0 ... executableSourceLineMapSize-1 ]
                    //     = the bytes of the executable source-line map
                    //

                        executableSourceLineMap
                            = new byte[ executableSourceLineMapSize ];

                        int executableSourceLineMapReadStatus
                            = objectFile.read(executableSourceLineMap);

                        if (executableSourceLineMapReadStatus == -1)
                            throw new IOException();

                    //
                    // Let symbolicAddressInformation[ 0
                    //         ... symbolicAddressInformationSize-1 ]
                    //     = the bytes of symbolic-address information map
                    //

                        symbolicAddressInformation
                            = new byte[ symbolicAddressInformationSize ];

                        int symbolicAddressInformationReadStatus
                            = objectFile.read(symbolicAddressInformation);

                        if (symbolicAddressInformationReadStatus == -1)
                            throw new IOException();

                    //
                    // Let byteContentMap[ 0 ... byteContentMapSize-1 ]
                    //     = the bytes of the byte-content map
                    //

                        byteContentMap = new byte[ byteContentMapSize ];

                        int byteContentMapReadStatus = objectFile.read(byteContentMap);

                        if (byteContentMapReadStatus == -1)
                            throw new IOException();

                    objectFile.close();

                }
                catch (FileNotFoundException exception) {

                    ; // do nothing

                    }
                catch (IOException exception) {

                    ; // do nothing

                    }

            return objectCode;

            }

        //
        // Public Class Method byte [] encodedInstructionBytes(int opcode, int operand)
        // Credits: Using method version supplied for phase01 sample solution
        //
        // Purpose:
        //     Creates the binary encoding of a VM252 instruction
        //
        // Formals:
        //     opcode (in) - the integer operation code for the instruction to be encoded
        //     operand (in) - the integer operand for the instruction to be encoded;
        //         will be ignored for certain opcodes
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     a byte-array of 1 or two elements, where element 0 contains the
        //         operation code and (for opcodes that require an operand) most-significant
        //         bits of the operand, and element 1 (for opcodes that require an operand)
        //         contains the least-significant bits of the operand
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public static byte [] encodedInstructionBytes(int opcode, int operand)
        {

            byte [] instruction;

            switch (opcode) {

                //
                // Cases for instructions encoded with a 3-bit opcode and a 13-bit unsigned
                // operand
                //

                case LOAD_OPCODE :
                case STORE_OPCODE :
                case ADD_OPCODE :
                case SUBTRACT_OPCODE :
                case JUMP_OPCODE :
                case JUMP_ON_ZERO_OPCODE :
                case JUMP_ON_POSITIVE_OPCODE : {

                    instruction = new byte[ 2 ];

                    //
                    // Let instruction[ 0 ] = the 3-bit opcode and the 5 most-significant
                    //     bits of the operand
                    // Let instruction[ 1 ] = the 8 least-significant bits of the operand
                    //

                    instruction[ 0 ] = ((byte) (opcode << 5 | operand >> 8 & 0x1f));
                    instruction[ 1 ] = ((byte) (operand & 0xff));

                    break;

                }

                //
                // Cases for instructions encoded with a 4-bit opcode and a 12-bit signed
                // operand
                //

                case SET_OPCODE : {

                    instruction = new byte[ 2 ];

                    //
                    // Let instruction[ 0 ] = the 4-bit opcode and the 5 most-significant
                    //     bits of the operand
                    // Let instruction[ 1 ] = the 8 least-significant bits of the operand
                    //

                    instruction[ 0 ] = ((byte) (opcode << 4 | operand >> 8 & 0xf));
                    instruction[ 1 ] = ((byte) (operand & 0xff));

                    break;

                }

                //
                // Cases for instructions encoded with a 6-bit opcode and no operand
                //

                case INPUT_OPCODE :
                case OUTPUT_OPCODE :
                case NO_OP_OPCODE :
                case STOP_OPCODE : {

                    instruction = new byte[ 1 ];

                    //
                    // Let instruction[ 0 ] = the 6-bit opcode and 2 0 bits
                    //

                    instruction[ 0 ] = ((byte) (opcode << 2 & 0xff));

                    break;

                }

                //
                // Case for invalid opcode
                //

                default :

                    instruction = null;

            }

            return instruction;

        }

        //
        // Public Class Method int [] decodedInstructionComponents(byte [] instructionBytes)
        // Credits: Using method version supplied for phase01 sample solution
        //
        // Purpose:
        //     Decodes the binary encoding of a VM252 instruction
        //
        // Formals:
        //     instructionBytes (in) - the 1- or 2-byte binary encoding
        //         of a VM252 instruction
        //
        // Pre-conditions:
        //     none
        //
        // Post-conditions:
        //     none
        //
        // Returns:
        //     an int-array of 1 or two elements, where element 0 contains the
        //         operation code, and element 1 (for opcodes that require an operand)
        //         contains the operand
        //
        // Worst-case asymptotic runtime:
        //     O(1)
        //
        public static int [] decodedInstructionComponents(byte [] instructionBytes)
        {

            //
            // If instruction bytes is not a valid binary encoding of a VM252 instruction,
            //     return a null array
            //

            if (instructionBytes == null || instructionBytes.length > 2)

                return null;

                //
                // Otherwise, return an array holding the opcode and (for some opcodes) operand
                //    from the encoded instruction
                //

            else {

                int [] instructionComponents = null;

                switch (instructionBytes[ 0 ] >> 5 & 0x7) {

                    //
                    // If the 3 most-significant bits of the byte holding the encoded
                    // opcode denote an instruction having a 3-bit opcode and a 13-bit
                    // unsigned integer operand, return an array holding those two values
                    //

                    case LOAD_OPCODE :
                    case STORE_OPCODE :
                    case ADD_OPCODE :
                    case SUBTRACT_OPCODE :
                    case JUMP_OPCODE :
                    case JUMP_ON_ZERO_OPCODE :
                    case JUMP_ON_POSITIVE_OPCODE : {

                        if (instructionBytes.length == 2) {

                            instructionComponents = new int [ 2 ];

                            //
                            // Let instructionComponents[ 0 ]
                            //     = the 3 most-significant bits of
                            //         instructionBytes[ 0 ]
                            // Let instructionComponents[ 1 ]
                            //     = the 5 least-significant bits of
                            //         instructionBytes[ 0 ] concatenated with
                            //         instructionBytes[ 1 ]


                            instructionComponents[ 0 ]
                                    = instructionBytes[ 0 ] >> 5 & 0x7;
                            instructionComponents[ 1 ]
                                    = instructionBytes[ 0 ] << 8 & 0x1f00
                                    | instructionBytes[ 1 ] & 0xff;

                        }

                        break;

                    }

                    default :

                        switch (instructionBytes[ 0 ] >> 4 & 0xf) {

                            //
                            // If the 4 most-significant bits of the byte holding the
                            // encoded opcode denote an instruction having a 4-bit opcode
                            // and a 12-bit signed integer operand, return an array
                            // holding those two values
                            //

                            case SET_OPCODE : {

                                if (instructionBytes.length == 2) {

                                    instructionComponents = new int [ 2 ];

                                    //
                                    // Let instructionComponents[ 0 ]
                                    //     = the 4 most-significant bits of
                                    //         instructionBytes[ 0 ]
                                    // Let instructionComponents[ 1 ]
                                    //     = the 4 least-significant bits of
                                    //         instructionBytes[ 0 ] concatenated
                                    //         with instructionBytes[ 1 ]


                                    instructionComponents[ 0 ]
                                            = instructionBytes[ 0 ] >> 4 & 0xf;
                                    instructionComponents[ 1 ]
                                            = instructionBytes[ 0 ] << 28 >> 20
                                            | instructionBytes[ 1 ] & 0xff;

                                }

                                break;

                            }

                            //
                            // Otherwise, the 6 most-significant bits of the byte holding
                            // the encoded necessarily opcode denote an instruction
                            // having a 6-bit opcode and no operand, so return an array
                            // holding the opcode only
                            //

                            default : {

                                instructionComponents = new int [ 1 ];

                                instructionComponents[ 0 ]
                                        = instructionBytes[ 0 ] >> 2 & 0x3f;

                            }

                        }

                }

                return instructionComponents;

            }

        }
}
