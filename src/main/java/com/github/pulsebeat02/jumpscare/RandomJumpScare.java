package com.github.pulsebeat02.jumpscare;

import javafx.beans.binding.Bindings;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.*;
import java.net.URISyntaxException;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RandomJumpScare {

  private static final Random RANDOM;
  private static final ScheduledExecutorService SCHEDULER;

  static {
    RANDOM = new Random();
    SCHEDULER = Executors.newScheduledThreadPool(1);
  }

  private final JFrame frame;

  public RandomJumpScare() {
    frame = new JFrame();
    init();
  }

  public synchronized void init() {
    SCHEDULER.scheduleAtFixedRate(
        () -> {
          try {
            final JFXPanel panel = new JFXPanel();
            final MediaPlayer player =
                new MediaPlayer(
                    new Media(
                        Objects.requireNonNull(
                            Objects.requireNonNull(
                                    getClass().getClassLoader().getResource("video.mp4"))
                                .toURI()
                                .toString())));
            final MediaView viewer = new MediaView(player);
            final StackPane root = new StackPane();
            root.setBackground(
                new Background(
                    new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
            viewer.setPreserveRatio(true);
            root.getChildren().add(viewer);
            panel.setScene(new Scene(root));
            viewer.fitWidthProperty().bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
            viewer
                .fitHeightProperty()
                .bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
            frame.setLayout(new BorderLayout());
            frame.add(panel, BorderLayout.CENTER);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setUndecorated(true);
            frame.setVisible(true);
            player.setStopTime(Duration.seconds(4));
            player.play();
            player.setOnEndOfMedia(
                () -> {
                  viewer.setMediaPlayer(null);
                  root.getChildren().remove(viewer);
                  frame.setVisible(false);
                });
          } catch (final URISyntaxException e) {
            e.printStackTrace();
          }
        },
        0,
        randomTime(),
        TimeUnit.MILLISECONDS);
  }

  public long randomTime() {
    return RANDOM.nextInt(2 * 60 * 60 * 1000);
  }
}
