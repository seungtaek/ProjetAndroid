package com.ynov.android.api;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Service API
 * @author TRISTAN
 *
 */
public interface APIServiceInterface {

/** Load Player, GET I=null, O= PlayerReply */
@GET("api_load_player.php")
Call<PlayerReply> loadPlayer();
/** Load Game, GET I=null, O= GameReply */
@GET("api_load_game.php")
Call<GameReply> loadGame();
/** Load Match, GET I=null, O= MatchReply */
@GET("api_load_match.php")
Call<MatchReply> loadMatch();
/** Load Playersmatch, GET I=null, O= PlayersmatchReply */
@GET("api_load_playersmatch.php")
Call<PlayersmatchReply> loadPlayersmatch();
	
//	Samples
//	/** Authentication request, POST I=AuthenticationRequest, O= AuthenticationReply */
//	@POST("authenticate/post")
//	Call<AuthenticationReply> authenticateUser(@Body AuthenticationRequest request);

// /** Object image download, GET Params=sessionId,idValue */
//	// http://stackoverflow.com/questions/32878478/how-to-download-file-in-android-using-retrofit-library
//	@GET("media/download/object/{sessionId}/{idValue}")
//	@Streaming
//	public Call<ResponseBody> downloadImage(@Path("sessionId") String sessionId, @Path("idValue") String idValue);

//	/** Object image upload, POST Params=sessionId,idValue,filename,file */
//	@Multipart
//	@POST ("media/upload/object")
//	Call<ResponseBody> uploadImage (@Part("sessionId") RequestBody sessionId, @Part("idValue") RequestBody idValue, @Part("filename") RequestBody filename, @Part("contentType") RequestBody contentType, @Part("file") RequestBody file);
}


