package com.ynov.android.api;

import java.util.Arrays;

import com.ynov.android.model.Game;

/**
 *
 * @author TRISTAN
 *
 */
public class GameReply {
	protected Game[] gameList;

	public GameReply() {
		super();
	}
	public GameReply(Game[] gameList) {
		super();
		this.gameList = gameList;
	}
	public Game[] getGameList() {
		return gameList;
	}
	public void setGameList(Game[] gameList) {
		this.gameList = gameList;
	}
	@Override
	public String toString() {
		final int maxLen = 1000;
		return "GameReply [gameList="
				+ (gameList != null ? Arrays.asList(gameList).subList(0,
						Math.min(gameList.length, maxLen)) : null) + "]";
	}
}


