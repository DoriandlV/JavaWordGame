package com.codesse.codetest.wordgame;


/**
 * This is the shell implementation of the WordGame interface.
 * It is the class that you should focus on when developing your solution to the Challenge.
 */
public class WordGameImpl implements WordGame {

  private final ValidWords validWords;
  private final SymbolsChecker symbolsChecker;
  private final LeaderBoard leaderBoard;


  public WordGameImpl(String allowedSymbols, ValidWords validWords) {
    this.symbolsChecker = new SymbolsChecker(allowedSymbols);
    this.validWords = validWords;
    leaderBoard = new LeaderBoard();
  }

  @Override
  public int submitWord(String playerName, String word) {
    if (validWords.contains(word) &&
        symbolsChecker.canBeMakeFromAllowed(word)){
      final int score = word.length();
      final boolean isAdded = leaderBoard.add(playerName, word, score);
      if (!isAdded){
        return 0;
      }
      return score;
    }
    return 0;
  }

  @Override
  public String getPlayerNameAtPosition(int position) {
    return leaderBoard.getPlayerNameAtPosition(position);
  }

  @Override
  public String getWordEntryAtPosition(int position) {
    return leaderBoard.getWordEntryAtPosition(position);
  }

  @Override
  public Integer getScoreAtPosition(int position) {
    return leaderBoard.getScoreAtPosition(position);
  }

}