/*
 * Created by JFormDesigner on Mon Mar 29 19:00:50 EDT 2021
 */

package com.github.pulsebeat02.gui;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class ClientMenu extends JFrame {

  private static final long serialVersionUID = 288497125860701488L;

  public ClientMenu() {
    initComponents();
  }

  private void initComponents() {

    final JLabel ipLabel = new JLabel();
    final JLabel portLabel = new JLabel();
    final JLabel backdoorLabel = new JLabel();
    final JSeparator leftSeparator = new JSeparator();
    final JSeparator rightSeparator = new JSeparator();
    final JSeparator topSeparator = new JSeparator();
    final JSeparator bottomSeparator = new JSeparator();
    final JButton exit = new JButton();
    final JButton start = new JButton();
    final JButton github = new JButton();
    final JTextField ipField = new JTextField();
    final JTextField portField = new JTextField();
    final JTextField dataInput = new JTextField();
    final JPanel backdoorData = new JPanel();

    final Container contentPane = getContentPane();
    contentPane.setLayout(null);

    ipLabel.setText("Enter IP Address to Hack");
    ipLabel.setBounds(30, 35, 210, 35);

    portLabel.setText("Enter Port to Hack");
    portLabel.setBounds(30, 85, 210, 30);

    backdoorLabel.setText("Backdoor Info");
    backdoorLabel.setBounds(30, 125, 210, 30);

    contentPane.add(ipLabel);
    contentPane.add(portLabel);
    contentPane.add(backdoorLabel);

    leftSeparator.setOrientation(SwingConstants.VERTICAL);
    leftSeparator.setBackground(Color.white);
    leftSeparator.setBounds(15, 26, 5, 320);

    rightSeparator.setOrientation(SwingConstants.VERTICAL);
    rightSeparator.setBackground(Color.white);
    rightSeparator.setBounds(575, 26, 5, 320);

    topSeparator.setBackground(Color.white);
    topSeparator.setBounds(16, 25, 560, 10);

    bottomSeparator.setBackground(Color.white);
    bottomSeparator.setBounds(16, 345, 561, 10);

    contentPane.add(leftSeparator);
    contentPane.add(rightSeparator);
    contentPane.add(topSeparator);
    contentPane.add(bottomSeparator);

    exit.setText("Exit");
    exit.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            System.exit(0);
          }
        });
    exit.setBounds(405, 360, 173, 40);

    start.setText("Start Hacking");
    exit.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            final String ip = ipLabel.getText();
            final String port = portLabel.getText();
          }
        });
    start.setBounds(15, 360, 173, 40);

    github.setText("Visit Github Repository");
    github.addMouseListener(
        new MouseAdapter() {
          @Override
          public void mouseClicked(final MouseEvent e) {
            try {
              Desktop.getDesktop()
                  .browse(new URI("https://github.com/PulseBeat02/Among-Us-Backdoor"));
            } catch (final IOException | URISyntaxException ioException) {
              ioException.printStackTrace();
            }
          }
        });
    github.setBounds(210, 360, 175, 40);

    ipField.setBounds(250, 35, 315, ipField.getPreferredSize().height);
    portField.setBounds(250, 85, 315, 30);

    contentPane.add(exit);
    contentPane.add(start);
    contentPane.add(github);

    contentPane.add(ipField);
    contentPane.add(portField);

    backdoorData.setBackground(new Color(51, 51, 51));
    backdoorData.setBorder(
        new CompoundBorder(
            new TitledBorder(
                new EmptyBorder(0, 0, 0, 0),
                "JF\u006frm\u0044es\u0069gn\u0065r \u0045va\u006cua\u0074io\u006e",
                TitledBorder.CENTER,
                TitledBorder.BOTTOM,
                new Font("D\u0069al\u006fg", Font.BOLD, 12),
                Color.red),
            backdoorData.getBorder()));
    backdoorData.addPropertyChangeListener(
        e -> {
          if ("\u0062or\u0064er".equals(e.getPropertyName())) {
            throw new RuntimeException();
          }
        });
    backdoorData.setLayout(null);
    backdoorData.add(dataInput);
    dataInput.setBounds(5, 5, 525, 170);

    backdoorData.setBounds(30, 155, 535, 180);

    contentPane.add(backdoorData);

    pack();
    setLocationRelativeTo(getOwner());
  }
}
