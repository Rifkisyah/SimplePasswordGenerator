package com.passwordgenerator.logic;

import java.util.ArrayList;
import java.util.Random;

public class GeneratePassword {
	private String setChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890%$#@!*?-=+";
	private int passwordSize;
	private String finalPassword;
	private ArrayList<String> passwordHistoryList = new ArrayList<String>();
	private int j = 0;

	public void setPasswordSize(int passwordSize) {
		this.passwordSize = passwordSize;
	}

	public void runningApp() {
		do {
			finalPassword = appendCharToFinalPassword();
		} while ( passwordHistoryList.equals(finalPassword));

		appendGeneratedPasswordToListHistory();
	}
	
	private char getSelectedChar() {
		Random random = new Random();
		int index = random.nextInt(setChar.length());
		return setChar.charAt(index);
	}
	
	private String appendCharToFinalPassword() {
		String oldChar = Character.toString(getSelectedChar());
		String newChar;
		for(int i = 0; i < passwordSize; i++) {
			newChar = Character.toString(getSelectedChar());
			oldChar += newChar;
		}
		return oldChar;
	}
	
	private void appendGeneratedPasswordToListHistory() {
		passwordHistoryList.add(finalPassword);
	}
	
	private void checkSamePassword(int j) {
		if(finalPassword.contains(passwordHistoryList.get(j))) {
			System.out.println("info :  there has same password");
			finalPassword = "";
			j++;
		}

	}
	
	public String getFinalPassword () {
		return finalPassword;
	}
	
}
