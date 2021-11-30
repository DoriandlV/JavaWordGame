package com.codesse.codetest.wordgame;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LeaderBoard {
    private final List<LeaderBoardItem> positions;

    public LeaderBoard() {
        positions = IntStream.rangeClosed(0, 9).mapToObj(i -> (LeaderBoardItem) null)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public synchronized boolean add(String playerName, String word, int score) {
        if (isWordPresent(word)){
            return false;
        }
        final int positionToScore = findPositionToScore(score);
        if (positionToScore != -1){
          positions.add(positionToScore, new LeaderBoardItem(playerName, word, score));
          positions.remove(10);
        }
        return true;
    }

    private int findPositionToScore(int score){
        for (int i = 0; i < 10; i++) {
            final LeaderBoardItem item = positions.get(i);
            if (Objects.isNull(item)){
                return i;
            }
            if (score > item.getScore()){
                return i;
            }
        }
        return -1;
    }

    private boolean isWordPresent(String word) {
      for (LeaderBoardItem position : positions) {
        if (Objects.nonNull(position) &&
            Objects.equals(position.getWord(), word)) {
          return true;
        }
      }
      return false;
    }

    public String getPlayerNameAtPosition(int position) {
        final LeaderBoardItem leaderBoardItem = positions.get(position);
        if (Objects.isNull(leaderBoardItem)){
            return null;
        }
        return leaderBoardItem.getPlayerName();
    }

    public String getWordEntryAtPosition(int position) {
        final LeaderBoardItem leaderBoardItem = positions.get(position);
        if (Objects.isNull(leaderBoardItem)){
            return null;
        }
        return leaderBoardItem.getWord();
    }

    public Integer getScoreAtPosition(int position) {
        final LeaderBoardItem leaderBoardItem = positions.get(position);
        if (Objects.isNull(leaderBoardItem)){
            return null;
        }
        return leaderBoardItem.getScore();
    }
}
