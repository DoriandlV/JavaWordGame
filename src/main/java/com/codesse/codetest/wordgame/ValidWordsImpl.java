package com.codesse.codetest.wordgame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Vector;

/**
 * Copyright (C) 2015 Codesse. All rights reserved.
 * ••••••••••••••••••••••••••••••••••••••••••••••••
 */
public class ValidWordsImpl implements ValidWords {

	Vector v = new Vector();

	public ValidWordsImpl() {
		try {
			InputStreamReader reader = new InputStreamReader(
					this.getClass().getResourceAsStream("/wordlist.txt"), "utf-8");
			BufferedReader in = new BufferedReader(reader);
			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				v.add(inputLine);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean contains(String word) {
		return v.contains(word);
	}

	@Override
	public int size() {
		return v.size();
	}
}
