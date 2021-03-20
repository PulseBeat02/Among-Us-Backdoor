package com.github.pulsebeat02.jumpscare;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
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

public class RandomJumpScare {

  private static final Random RANDOM;

  static {
    RANDOM = new Random();
  }

  private final JFrame frame;
  private final Timer timer = null;
  private final int count = 0;

  public RandomJumpScare() throws URISyntaxException {
    frame = new JFrame();
    init();
  }

  public void init() throws URISyntaxException {
    final JFXPanel panel = new JFXPanel();
    final MediaPlayer player =
        new MediaPlayer(
            new Media(
                Objects.requireNonNull(
                    Objects.requireNonNull(getClass().getClassLoader().getResource("video.mp4"))
                        .toURI()
                        .toString())));
    final MediaView viewer = new MediaView(player);
    final StackPane root = new StackPane();
    root.setBackground(
        new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, new Insets(0, 0, 0, 0))));
    final DoubleProperty width = viewer.fitWidthProperty();
    final DoubleProperty height = viewer.fitHeightProperty();
    width.bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
    height.bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
    viewer.setPreserveRatio(true);
    root.getChildren().add(viewer);
    panel.setScene(new Scene(root));
    frame.setLayout(new BorderLayout());
    frame.add(panel, BorderLayout.CENTER);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    frame.setUndecorated(true);
    frame.setVisible(true);
    player.play();
    player.setOnEndOfMedia(
        () -> {
          frame.dispose();
          frame.setVisible(false);
          try {
            Thread.sleep(randomTime());
          } catch (final InterruptedException e) {
            e.printStackTrace();
          }
          try {
            init();
          } catch (final URISyntaxException e) {
            e.printStackTrace();
          }
          player.seek(Duration.ZERO);
          player.play();
        });
  }

  public long randomTime() {
    return RANDOM.nextInt(1000 * 25/*2 * 60 * 60 * 1000*/);
  }
}
