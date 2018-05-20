package com.ynov.android.api;

import com.ynov.android.model.Player;

import java.util.Arrays;

/**
 * @author TRISTAN
 */
public class PlayerReply {
    protected Player[] playerList = {};

    public PlayerReply() {
        super();
    }

    public PlayerReply(Player[] playerList) {
        super();
        this.playerList = playerList;
    }

    public Player[] getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Player[] playerList) {
        this.playerList = playerList;
    }

    @Override
    public String toString() {
        final int maxLen = 1000;
        return "PlayerReply [playerList="
                + (playerList != null ? Arrays.asList(playerList).subList(0,
                Math.min(playerList.length, maxLen)) : null) + "]";
    }
}


