package pkg423guibackend;

import java.util.ArrayList;
import dsd2016.api.*;

public class GUIBackend {
	
    public static int[] registerUser(String name, String email, String gender, ArrayList<String> inB64Pics, ArrayList<String> outB64BadPics) {
        int errCnt = 0;
        boolean goodName, goodEmail, goodGender;
        
        //Initial input checking.
        if(!(goodName = name.matches("^[a-zA-Z]+$"))) {
            errCnt++;
        }
        if(!(goodEmail = email.matches("^.+@.+\\..+$"))) {
            errCnt++;
        }
        if(!(goodGender = gender.matches("[a-zA-Z]"))) {
            errCnt++;
        }
        
        if(errCnt > 0) {
            int[] codes = new int[errCnt+1];
            codes[0] = GUIConstants.FAILURE;
            
            int index = 1;
            if(!goodName) {
                codes[index++] = GUIConstants.INVALID_NAME;
            }
            if(!goodEmail) {
                codes[index++] = GUIConstants.INVALID_EMAIL;
            }
            if(!goodGender) {
                codes[index++] = GUIConstants.INVALID_GENDER;
            }
            
            return codes;
        }
        
        StringBuilder outMsg = new StringBuilder();
        int[] codes = new int[2];
        int result = DSD2016JAVA.registerNewUser(inB64Pics, outB64BadPics, outMsg);
        codes[1] = result == DSD2016JAVA.ERRORCODE_REGISTER_FAIL ? GUIConstants.FAILURE
                : result == DSD2016JAVA.ERRORCODE_REGISTER_IO_ERROR ? GUIConstants.IO_ERROR
                : result == DSD2016JAVA.ERRORCODE_REGISTER_UNKNOWN ? GUIConstants.UNKNOWN_ERROR
                : GUIConstants.SUCCESS;
        codes[0] = codes[1] == GUIConstants.SUCCESS ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
        
        if(outB64BadPics.size() > 0) {
            for(int i = 0; i < outB64BadPics.size(); i++) {
                //remove error codes from pic strings
                outB64BadPics.set(i, outB64BadPics.get(i).substring(0, outB64BadPics.get(i).indexOf(',')));
                
                //remove bad pics from list of taken pics
                if(inB64Pics.contains(outB64BadPics.get(i))) {
                    inB64Pics.remove(outB64BadPics.get(i));
                }
            }
        }

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
