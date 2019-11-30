package com.example.game.gamecode;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.MainMenuScreen;
import com.example.game.R;
import com.example.game.leaderboardcode.LeaderboardManager;
import com.example.game.leaderboardcode.Saver;
import com.example.game.settingscode.SettingsManager;

public abstract class GameActivity extends AppCompatActivity implements Saver {
  protected GameView gameView;
  protected LeaderboardManager leaderboardManager;
  protected SettingsManager settingsManager;
  protected String username;

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  protected void addRunningButton() {
    final ImageButton toggleRunningButton = findViewById(R.id.toggleRunningButton);
    gameView.setPauseButton(toggleRunningButton);
    toggleRunningButton.setOnClickListener(
        new GameView.OnClickListener() {
          public void onClick(View view) {
            gameView.togglePause();
          }
        });
    toggleRunningButton.bringToFront();
  }

  @Override
  public void onBackPressed() {
    Intent intent = new Intent(GameActivity.this, MainMenuScreen.class);
    gameView.thread.terminateThread();
    startActivity(intent);
  }

  protected abstract GameView setView();

  public void saveScore() {
    final GameActivity currentGame = this;
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        AlertDialog.Builder builder = new AlertDialog.Builder(currentGame);
        builder.setCancelable(false);
        builder.setTitle("Save Score?");

        LinearLayout rows = new LinearLayout(currentGame);
        rows.setOrientation(LinearLayout.VERTICAL);
        builder.setView(rows);

        LinearLayout bottomButtons = new LinearLayout(currentGame);
        bottomButtons.setOrientation(LinearLayout.HORIZONTAL);
        bottomButtons.setLayoutParams(
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        1f));

        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                0.5f);

        final ToggleButton saveToggle = new ToggleButton(currentGame);
        final Button retryButton = new Button(currentGame);
        final Button mainMenuButton = new Button(currentGame);
        retryButton.setLayoutParams(buttonParams);
        mainMenuButton.setLayoutParams(buttonParams);

        saveToggle.setText("Save On");
        saveToggle.setTextOn("Save On");
        saveToggle.setTextOff("Save Off");

        retryButton.setText("Retry");
        mainMenuButton.setText("Main Menu");

        retryButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(currentGame, currentGame.getClass());
            intent.putExtra("username", username);
            intent.putExtra("leaderboardManager", leaderboardManager);
            intent.putExtra("settingsManager", settingsManager);
            if (saveToggle.isChecked()) {
              saveData();
            }
            currentGame.startActivity(intent);
          }
        });

        mainMenuButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(currentGame, MainMenuScreen.class);
            intent.putExtra("username", username);
            intent.putExtra("leaderboardManager", leaderboardManager);
            intent.putExtra("settingsManager", settingsManager);
            if (saveToggle.isChecked()) {
              saveData();
            }
            currentGame.startActivity(intent);
          }
        });

        bottomButtons.addView(retryButton);
        bottomButtons.addView(mainMenuButton);

        rows.addView(saveToggle);
        rows.addView(bottomButtons);

        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
      }
    });
  }

  protected abstract void saveData();
}
