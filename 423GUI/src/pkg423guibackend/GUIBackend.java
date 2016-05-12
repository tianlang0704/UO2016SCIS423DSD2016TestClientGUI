package pkg423guibackend;

import java.util.ArrayList;

import dsd2016.api.*;

public class GUIBackend {
	
	public static int[] verifyEmail(String email) {
		StringBuilder outMsg = new StringBuilder();
		int[] codes = new int[1];
		codes[0] = DSD2016JAVA.verifyEmail(email, outMsg) ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
		return codes;
	}
	
	public static int[] registerUser(String name, String email, String password, String gender, ArrayList<String> inB64Pics, ArrayList<String> outB64BadPics) {
		StringBuilder outMsg = new StringBuilder();
		int[] codes = new int[1];
		codes[0] = DSD2016JAVA.registerNewUser(name, email, password, gender, inB64Pics, outB64BadPics, outMsg) == 1 ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
		return codes;
	}
	
	public static int[] validateUser(String email, String password, String inB64Pic) {
		StringBuilder outMsg = new StringBuilder();
		int[] codes = new int[1];
		codes[0] = DSD2016JAVA.validateUser(email, password, inB64Pic, outMsg) == 1 ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
		return codes;
	}
}
