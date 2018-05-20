package com.ynov.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.GameDao;
import com.ynov.android.database.MatchDao;
import com.ynov.android.database.PlayerDao;
import com.ynov.android.database.PlayersmatchDao;
import com.ynov.android.model.Game;
import com.ynov.android.model.Match;
import com.ynov.android.model.Player;
import com.ynov.android.model.Playersmatch;

import java.sql.SQLException;

//Imports for the local model

public class MainActivity extends Activity {

    // The DAO objects
    private PlayerDao playerDao = null;
    private GameDao gameDao = null;
    private MatchDao matchDao = null;
    private PlayersmatchDao playersmatchDao = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Database call sample
//        DatabaseManager.init(getBaseContext());
//        playerDao = DatabaseManager.getInstance().getHelper().getPlayerDao();
//        gameDao = DatabaseManager.getInstance().getHelper().getGameDao();
//        matchDao = DatabaseManager.getInstance().getHelper().getMatchDao();
//        playersmatchDao = DatabaseManager.getInstance().getHelper().getPlayersmatchDao();
//
//        try {
//
//            playerDao.create(new Player("name", "firstname", "comment", "picture"));
//            gameDao.create(new Game("name", 0));
//            matchDao.create(new Match(0, "description", 0, 0, "datetime"));
//            playersmatchDao.create(new Playersmatch(0, 0, 0));
//
//        } catch (SQLException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }

        ((Button) findViewById(R.id.activity_main_button_launch_player)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListPlayerActivity.class);
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.activity_main_button_launch_game)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListGameActivity.class);
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.activity_main_button_launch_match)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListMatchActivity.class);
                startActivity(intent);
            }
        });
        ((Button) findViewById(R.id.activity_main_button_launch_playersmatch)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ListPlayersmatchActivity.class);
                startActivity(intent);
            }
        });


        ((Button) findViewById(R.id.activity_main_button_launch_network_status)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NetworkStatusActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.activity_main_button_launch_network_call)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, NetworkCallActivity.class);
                startActivity(intent);
            }
        });

        ((Button) findViewById(R.id.activity_main_button_a_propos)).setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, activity_a_propos.class);
                startActivity(intent);
            }
        });

    }
}
