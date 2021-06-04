import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class BinaryIO.
 */
public class BinaryIO {
	
	/** The bin str. */
	private String binStr;
	
	/** The bin output. */
	private BufferedOutputStream binOutput;
	
	/** The bin input. */
	private BufferedInputStream binInput;

	/**
	 * Instantiates a new binary IO.
	 */
	public BinaryIO() {
		binStr = "";
	}
	
	/**
	 * Gets the binary String.
	 *
	 * @return the binStr
	 */
	public String getBinStr() {
		return binStr;
	}
	
	/**
	 * Gets the binary output Stream - for testing purposes ONLY
	 *
	 * @return the binOutput
	 */
	BufferedOutputStream getBinOutput() {
		return binOutput;
	}

	/**
	 * Gets the binary input stream - for testing purposes ONLY
	 *
	 * @return the binInput
	 */
	BufferedInputStream getBinInput() {
		return binInput;
	}
	
	/**
	 * Converts a string of 1's and 0's to one or more bytes. 
	 * 1) Appends the string encoding of the current character to the current string. 
	 * 2) while the string length is >= 8
	 *    convert the first 8 characters to the equivalent byte value
	 *    write the byte to a file
	 *    remove the first 8 characters
	 *    repeat as necessary
	 *
	 * @param inBinStr the incoming binary string for the current character
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void convStrToBin(String inBinStr) throws IOException {
		// TODO: Write this method
		binStr+=inBinStr;
		try {
			while(binStr.length() >= 8) {
				byte b = convStrToByte(binStr.substring(0,8));
				binOutput.write(b);
				binStr = binStr.substring(8);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
//		System.out.println("current string: " + binStr);
	}
	Byte convStrToByte(String binStr) {
		byte b = 0;
		for(int i =0; i < 8; i++) {
			b*=2;
			if(binStr.charAt(i) == '1') {
				b++;
			}
		}
		return b;
	}
	/**
	 * Convert a byte value into a string of 1's and 0's (MSB to LSB).
	 *
	 * @param aByte the a byte
	 * @return the string
	 */
	 String convBinToStr(byte aByte) {
		String[] bin2nib = {"0000","0001","0010","0011","0100","0101","0110","0111",
		            "1000","1001","1010","1011","1100","1101","1110","1111",};
		int nhi = (aByte >> 4) & 0x0F;
		int nlo = (aByte & 0xF);
		return bin2nib[nhi]+bin2nib[nlo];
	}
	
	/**
	 * Write EOF. 
	 * 1) Append the EOF character to the stream of 1's and 0;s
	 * 2) While the (length of the stream)%8 != 0, append a "0"
	 * 3) Convert the string to 1 or more bytes (until consumed)
	 * 4) flush and close the file;
	 * 5) make sure to clear binStr (in case converting another file later)
	 *
	 * @param EOF_binStr the EO F bin str
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	void writeEOF(String EOF_binStr) throws IOException {
		// TODO: write this method
		binStr+= EOF_binStr +  "0000";
		while(binStr.length()%8 != 0) {
			binStr+="0";
		}
		convStrToBin("");
		try {
			binOutput.flush();
			binOutput.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		binStr = "";
	}
	

	
	/**
	 * Open binary output file.
	 *
	 * @param binFile the bin file
	 * @return the buffered output stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	 BufferedOutputStream openOutputFile(File binFile) throws IOException {
		binOutput = new BufferedOutputStream(new FileOutputStream(binFile));
		return binOutput;
	}
	
	/**
	 * Open binary input file.
	 *
	 * @param binFile the bin file
	 * @return the buffered input stream
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	 BufferedInputStream openInputFile(File binFile) throws IOException {
		binInput = new BufferedInputStream(new FileInputStream(binFile));
		return binInput;
	}

}

