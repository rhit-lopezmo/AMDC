package backendFeatures;

import java.security.InvalidParameterException;
import java.util.Random;

public class BackendFeatures {
	
	/***
	 * 
	 * @param codeLength (length of generated code; must be 6 or greater)
	 * @param memName (name of member)
	 * @param memID (ID of member)
	 * @return	randomly generated code for member
	 */
	public static String randomCodeGen(int codeLength, String memName, String memID) {
		if(codeLength < 6) {
			throw new InvalidParameterException("Please provide a code length that is 6 or greater.");
		}
		
		if(memName.trim().isEmpty()) {
			throw new InvalidParameterException("Member name cannot be blank.");
		}
		// code's first two chars are the member name's first 2 letters
		return handleCodeGen(codeLength - 2, memName, Integer.parseInt(memID));
	}
	
	private static String handleCodeGen(int codeLength, String memName, int memID) {
		StringBuilder code = new StringBuilder();
		code.append(memName.substring(0, 2));
		Random rand = new Random(System.currentTimeMillis());
		int randNum = rand.nextInt();
		randNum *= memID;
		if(randNum < 0) {
			randNum *= -1;
		}
		code.append(String.valueOf(randNum).substring(0, codeLength));
		return code.toString();
	}
}
