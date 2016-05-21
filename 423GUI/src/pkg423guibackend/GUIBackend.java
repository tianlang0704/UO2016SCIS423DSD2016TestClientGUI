package pkg423guibackend;

import java.util.ArrayList;
import dsd2016.api.*;

public class GUIBackend {
	
	public static int[] registerUser(ArrayList<String> inB64Pics, ArrayList<String> outB64BadPics) {
		StringBuilder outMsg = new StringBuilder();
		int[] codes = new int[2];
                int result = DSD2016JAVA.registerNewUser(inB64Pics, outB64BadPics, outMsg);
                codes[1] = result == DSD2016JAVA.ERRORCODE_REGISTER_FAIL ? GUIConstants.FAILURE
                        : result == DSD2016JAVA.ERRORCODE_REGISTER_IO_ERROR ? GUIConstants.IO_ERROR
                        : result == DSD2016JAVA.ERRORCODE_REGISTER_UNKNOWN ? GUIConstants.UNKNOWN_ERROR
                        : GUIConstants.SUCCESS;
		codes[0] = codes[1] == GUIConstants.SUCCESS ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
                
		return codes;
	}
	
	public static int[] validateUser(String userID, String inB64Pic) {
		StringBuilder outMsg = new StringBuilder();
		int[] codes = new int[2];
                int result = DSD2016JAVA.validateUser(userID, inB64Pic, outMsg);
                codes[1] = result == DSD2016JAVA.ERRORCODE_LOGIN_FAIL ? GUIConstants.FAILURE
                        : result == DSD2016JAVA.ERRORCODE_LOGIN_IO_ERROR ? GUIConstants.IO_ERROR
                        : result == DSD2016JAVA.ERRORCODE_LOGIN_UNKNOWN ? GUIConstants.UNKNOWN_ERROR
                        : GUIConstants.SUCCESS;
                codes[0] = codes[1] == GUIConstants.SUCCESS ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
                
		return codes;
	}
}
