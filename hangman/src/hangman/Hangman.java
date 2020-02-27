package hangman;

import java.io.BufferedReader;
import java.io.File;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Hangman {

	private String[] dic;
	public String secretword = "try";
	private String dashedWord;
	private int noOfWrongAnswers = 0;
	private int maxwrong;
	// private int counter;
	final int noWordsRead = 100;

	public String[] readFile(String fileName)  {//function to read file to words array
		String[] words = new String [noWordsRead];
		FileReader fileReader;
		try {
			fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			int i;
			for(i=0;i<noWordsRead;i++) {
					try {
						words[i] = bufferedReader.readLine();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			try {
				bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return words;

	}
	public void setDictionary(String[] words) {
		dic= words;

	}

	
	public String selectRandomSecretWord() {

		while (dic[0] != null) {
			Random rnd = new Random();
			secretword = dic[rnd.nextInt(dic.length)];
			while (secretword == null) {
				secretword = dic[rnd.nextInt(dic.length)];
			}
			dashedWord = secretword;
			dashingword();
			return secretword;
		}
		return null;

	}

	// see if the user enters a right letter
	public String guess(Character c) {

		if (this.checkLoss() == 0) {
			return null;
		}
		if (this.checkChar(c) == 0) {
			this.noOfWrongAnswers++;
		}
		return this.dashedWord;

	}

	// set max wrong answers to 5
	public void setMaxWrongGuesses(Integer max) {

		this.maxwrong = max;
	}

	// creat seen dashed word
	public void dashingword() {

		char[] dashingCharArray = this.dashedWord.toCharArray();
		for (int i = 0; i < this.secretword.length(); i++) {
			dashingCharArray[i] = '-';
		}
		this.dashedWord = String.valueOf(dashingCharArray);

	}

	public int checkLoss() {

		if (this.noOfWrongAnswers == this.maxwrong - 1) {
			return 0;
		}
		return 1;

	}

	public int checkChar(char c) {

		int flag = 0;
		for (int i = 0; i < this.secretword.length(); i++) {
			if (this.secretword.charAt(i) == Character.toUpperCase(c)
					|| this.secretword.charAt(i) == Character.toLowerCase(c)) {
				char[] dashcar = this.dashedWord.toCharArray();
				dashcar[i] = Character.toUpperCase(c);
				this.dashedWord = String.valueOf(dashcar);
				flag = 1;
			}
		}
		return flag;

	}
}
