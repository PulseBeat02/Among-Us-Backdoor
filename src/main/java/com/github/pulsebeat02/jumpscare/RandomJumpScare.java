package com.github.pulsebeat02.jumpscare;

import com.github.pulsebeat02.utility.FileUtilities;
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

import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Random;

public class RandomJumpScare {

  private static final Random RANDOM;

  static {
    RANDOM = new Random();
  }

  public RandomJumpScare() {
    final JFrame frame = new JFrame();
    final JFXPanel panel = new JFXPanel();
    final MediaPlayer player =
        new MediaPlayer(
            new Media(
                Objects.requireNonNull(FileUtilities.getURIFromResource("video.mp4")).toString()));
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
    frame.setUndecorated(true);
    frame.setType(Window.Type.UTILITY);
    frame.setVisible(true);
    player.play();
    player.setOnEndOfMedia(
        () -> {
          frame.setVisible(false);
          try {
            Thread.sleep(randomTime());
          } catch (final InterruptedException e) {
            e.printStackTrace();
          }
          player.play();
        });
  }

  public long randomTime() {
    return RANDOM.nextInt(2 * 60 * 60 * 1000);
  }
}
