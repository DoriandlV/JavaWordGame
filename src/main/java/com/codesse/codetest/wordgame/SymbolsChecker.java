package com.codesse.codetest.wordgame;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class SymbolsChecker {

    private final Map<Character, Integer> charToCountInWord;

    public SymbolsChecker(String allowedSymbols) {
        charToCountInWord = toCharToCountInWord(allowedSymbols);
    }

    private Map<Character, Integer> toCharToCountInWord(String allowedSymbols) {
        final char[] chars = allowedSymbols.toCharArray();
        Map<Character, Integer> charToCount = new HashMap<>();
        for (char aChar : chars) {
            charToCount.merge(aChar, 1, Integer::sum);
        }
        return charToCount;
    }

    public boolean canBeMakeFromAllowed(String word){
        for (char c : word.toCharArray()) {
            final int charCountInWord = StringUtils.countMatches(word, c);
            if (charToCountInWord.getOrDefault(c, 0) < charCountInWord){
                return false;
            }
        }
        return true;
    }
}

