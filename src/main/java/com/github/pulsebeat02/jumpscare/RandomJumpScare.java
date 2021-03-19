package com.github.pulsebeat02.jumpscare;

import com.github.pulsebeat02.utility.FileUtilities;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;

public class RandomJumpScare {

  private final JFrame frame;

  public RandomJumpScare() {
    frame = new JFrame();
    final JFXPanel VFXPanel = new JFXPanel();
    final MediaView viewer =
        new MediaView(
            new MediaPlayer(new Media(FileUtilities.getFile("sample.mp4").toURI().toString())));
    final StackPane root = new StackPane();
    final Rectangle2D screen = Screen.getPrimary().getVisualBounds();
    viewer.setX((screen.getWidth() - frame.getWidth()) / 2);
    viewer.setY((screen.getHeight() - frame.getHeight()) / 2);
    viewer.fitWidthProperty().bind(Bindings.selectDouble(viewer.sceneProperty(), "width"));
    viewer.fitHeightProperty().bind(Bindings.selectDouble(viewer.sceneProperty(), "height"));
    viewer.setPreserveRatio(true);
    root.getChildren().add(viewer);
    VFXPanel.setScene(new Scene(root));
    frame.setLayout(new BorderLayout());
    frame.add(VFXPanel, BorderLayout.CENTER);
    frame.setUndecorated(true);
    frame.setVisible(true);
  }

  public static void main(final String[] args) {
    new RandomJumpScare();
  }

}
