package com.ynov.android.api;

import java.util.Arrays;

import com.ynov.android.model.Match;

/**
 *
 * @author TRISTAN
 *
 */
public class MatchReply {
	protected Match[] matchList;

	public MatchReply() {
		super();
	}
	public MatchReply(Match[] matchList) {
		super();
		this.matchList = matchList;
	}
	public Match[] getMatchList() {
		return matchList;
	}
	public void setMatchList(Match[] matchList) {
		this.matchList = matchList;
	}
	@Override
	public String toString() {
		final int maxLen = 1000;
		return "MatchReply [matchList="
				+ (matchList != null ? Arrays.asList(matchList).subList(0,
						Math.min(matchList.length, maxLen)) : null) + "]";
	}
}


