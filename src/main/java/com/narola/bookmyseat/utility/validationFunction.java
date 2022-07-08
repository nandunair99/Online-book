package com.narola.bookmyseat.utility;

import java.util.Random;
import java.util.regex.Pattern;

public class validationFunction {
	/***
	 * To check the String is contain only alphabet or space
	 * 
	 * @param str String parameter of the function to check
	 * @return Return true if the string contains only alphabet or space else return
	 *         false
	 */
	public static boolean isAlphaSpace(String str) {
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isLetter(ch) || ch == ' ') {
				continue;
			}
			return false;
		}
		return true;
	}

	/***
	 * To check the String is null or Empty
	 * 
	 * @param str String parameter of the function to check
	 * @return Return true if the string is empty or null else return false
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null || str.trim().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isAlphaSpaceBackslash(String str)
	{
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isLetter(ch) || ch == ' ') {
				continue;
			}else {
				if(ch == '/'){
					continue;	
				}
			}
			return false;
		}
		return true;
	}
	
	public static boolean isAlphaSpaceNumeric(String str)
	{
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (Character.isLetter(ch) || ch == ' ' || Character.isDigit(ch)) {
				continue;
			}
			return false;
		}
		return true;
	}
	public static boolean isNumeric(String str)
	{
		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			System.out.println("before:"+ch);
			if(Character.isDigit(ch)) {
				System.out.println(ch);
				continue;
			}
			return false;
		}
		return true;
	}
	public static boolean isValidEmail(String emailID) {
		 String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                 "[a-zA-Z0-9_+&*-]+)*@" +
                 "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                 "A-Z]{2,7}$";
		 Pattern pat = Pattern.compile(emailRegex);
		 return pat.matcher(emailID).matches();
	}

	public static boolean isValidPhNo(String phno) {
		return true;
	}
	public static char[] generatePassword(int length) {
	      String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	      String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
	      String specialCharacters = "!@#$";
	      String numbers = "1234567890";
	      String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
	      Random random = new Random();
	      char[] password = new char[length];

	      password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
	      password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
	      password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
	      password[3] = numbers.charAt(random.nextInt(numbers.length()));
	   
	      for(int i = 4; i< length ; i++) {
	         password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
	      }
	      return password;
	   }
}
