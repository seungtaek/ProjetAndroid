package com.ynov.android.api;

import java.util.Arrays;

import com.ynov.android.model.Playersmatch;

/**
 *
 * @author TRISTAN
 *
 */
public class PlayersmatchReply {
	protected Playersmatch[] playersmatchList;

	public PlayersmatchReply() {
		super();
	}
	public PlayersmatchReply(Playersmatch[] playersmatchList) {
		super();
		this.playersmatchList = playersmatchList;
	}
	public Playersmatch[] getPlayersmatchList() {
		return playersmatchList;
	}
	public void setPlayersmatchList(Playersmatch[] playersmatchList) {
		this.playersmatchList = playersmatchList;
	}
	@Override
	public String toString() {
		final int maxLen = 1000;
		return "PlayersmatchReply [playersmatchList="
				+ (playersmatchList != null ? Arrays.asList(playersmatchList).subList(0,
						Math.min(playersmatchList.length, maxLen)) : null) + "]";
	}
}


