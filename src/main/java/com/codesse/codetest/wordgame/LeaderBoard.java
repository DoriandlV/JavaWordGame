package com.codesse.codetest.wordgame;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class LeaderBoard {
    private final Map<Integer, LeaderBoardItem> positionToLeader = new ConcurrentHashMap<>();

    public boolean add(String playerName, String word, int score) {
        if (isWordPresent(word)){
            return false;
        }
        final int positionToScore = findPositionToScore(score);
        moveAllItemsToOnePositionFrom(positionToScore);
        positionToLeader.put(positionToScore, new LeaderBoardItem(playerName, word, score));
        return true;
    }

    private int findPositionToScore(int score){
        for (int i = 0; i < 10; i++) {
            final LeaderBoardItem item = positionToLeader.get(0);
            if (Objects.isNull(item)){
                return i;
            }
            if (score > item.getScore()){
                return i;
            }
        }
        return 11;
    }

    private void moveAllItemsToOnePositionFrom(Integer fromInclude){
        //TODO need to change from include
        for (int i = fromInclude; i >= 0; i--) {
            if (i + 1 > 10){
                continue;
            }
            LeaderBoardItem item = positionToLeader.get(i);
            if (Objects.nonNull(item)){
                positionToLeader.put(i + 1, item);
            }
        }
    }

    private boolean isWordPresent(String word) {
        for (Map.Entry<Integer, LeaderBoardItem> entry : positionToLeader.entrySet()) {
            if (Objects.equals(entry.getValue().getWord(), word)){
                return true;
            }
        }
        return false;
    }

    public String getPlayerNameAtPosition(int position) {
        final LeaderBoardItem leaderBoardItem = positionToLeader.get(position);
        if (Objects.isNull(leaderBoardItem)){
            return null;
        }
        return leaderBoardItem.getPlayerName();
    }

    public String getWordEntryAtPosition(int position) {
        final LeaderBoardItem leaderBoardItem = positionToLeader.get(position);
        if (Objects.isNull(leaderBoardItem)){
            return null;
        }
        return leaderBoardItem.getWord();
    }

    public Integer getScoreAtPosition(int position) {
        final LeaderBoardItem leaderBoardItem = positionToLeader.get(position);
        if (Objects.isNull(leaderBoardItem)){
            return null;
        }
        return leaderBoardItem.getScore();
    }
}
