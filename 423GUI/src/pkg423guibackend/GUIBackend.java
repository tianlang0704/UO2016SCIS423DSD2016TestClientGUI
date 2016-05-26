package pkg423guibackend;

import java.util.ArrayList;
import dsd2016.api.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIBackend {
    
    private static final String userInfoLoc = "TestClientData/";
    private static final String userDataName = "userinfo.dat";
	
    public static int[] registerUser(String name, String email, String gender, ArrayList<String> inB64Pics, ArrayList<String> outB64BadPics) throws FileNotFoundException, IOException {
        
        System.out.println("made it to backend");
        System.out.println(name);
        System.out.println(email);
        System.out.println(gender);
        
        
        int errCnt = 0;
        boolean goodName, goodEmail;
        
        //Initial input checking.
        if(!(goodName = name.matches("^[\\p{L} .'-]+$"))) {
            System.out.println("name match");
            errCnt++;
        }
        if(!(goodEmail = email.matches("^.+@.+\\..+$"))) {
            System.out.println("email match");
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
        
        if(codes[0] == GUIConstants.SUCCESS) {
            
            System.out.println("creating dir");
            File dir = new File(userInfoLoc);
            if(!dir.exists()) {
                System.out.println("NEW");
                dir.mkdir();
                dir.createNewFile();
                System.out.println("made dir");
            }
            
            File f = new File(userInfoLoc + userDataName);
            if(!f.exists()) {
                System.out.println("create new file");
                try {
                    f.createNewFile();
                    PrintWriter w = new PrintWriter(f);
                    w.printf("%s,%s,%s,%s", outMsg.toString(), name, email, gender);
                    w.flush();
                    w.close();
                    w = new PrintWriter(userInfoLoc + "/README.txt");
                    w.print("The userinfo.dat file represents \"registered\" user data for the Spring 2016 CIS 423 class' Test Client program. Do not alter.");
                    w.flush();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    PrintWriter w;
                    w = new PrintWriter(new FileOutputStream(f, true));
                    w.printf("\n%s,%s,%s,%s", outMsg.toString(), name, email, gender);
                    w.flush();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            
        }
        
            return codes;
        
    }
	
	public static int[] validateUser(String userID, String inB64Pic, StringBuilder userData) {
		StringBuilder outMsg = new StringBuilder();
		int[] codes = new int[2];
                int result = DSD2016JAVA.validateUser(userID, inB64Pic, outMsg);
                codes[1] = result == DSD2016JAVA.ERRORCODE_LOGIN_FAIL ? GUIConstants.FAILURE
                        : result == DSD2016JAVA.ERRORCODE_LOGIN_IO_ERROR ? GUIConstants.IO_ERROR
                        : result == DSD2016JAVA.ERRORCODE_LOGIN_UNKNOWN ? GUIConstants.UNKNOWN_ERROR
                        : GUIConstants.SUCCESS;
                codes[0] = codes[1] == GUIConstants.SUCCESS ? GUIConstants.SUCCESS : GUIConstants.FAILURE;
                
                if(codes[0] == GUIConstants.SUCCESS) {
                    try {
                    Scanner sc = new Scanner(new File(userInfoLoc + userDataName));
                    String line;
                    while(sc.hasNext()) {
                        line = sc.nextLine();
                        if(line.contains(userID)) {
                            userData.append(line);
                            break;
                        }
                    }
                    
                    sc.close();
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
                
		return codes;
	}

}