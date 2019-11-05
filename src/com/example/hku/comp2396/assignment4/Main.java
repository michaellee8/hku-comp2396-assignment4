package com.example.hku.comp2396.assignment4;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;


public class Main {

  static final Card[] allCards = {
      new Card(1, 1), new Card(1, 2), new Card(1, 3), new Card(1, 4), new Card(1, 5),
      new Card(1, 6), new Card(1, 7), new Card(1, 8), new Card(1, 9), new Card(1, 10),
      new Card(1, 11), new Card(1, 12), new Card(1, 13), new Card(2, 1), new Card(2, 2),
      new Card(2, 3), new Card(2, 4), new Card(2, 5), new Card(2, 6), new Card(2, 7),
      new Card(2, 8), new Card(2, 9), new Card(2, 10), new Card(2, 11), new Card(2, 12),
      new Card(2, 13), new Card(3, 1), new Card(3, 2), new Card(3, 3), new Card(3, 4),
      new Card(3, 5), new Card(3, 6), new Card(3, 7), new Card(3, 8), new Card(3, 9),
      new Card(3, 10), new Card(3, 11), new Card(3, 12), new Card(3, 13), new Card(4, 1),
      new Card(4, 2), new Card(4, 3), new Card(4, 4), new Card(4, 5), new Card(4, 6),
      new Card(4, 7), new Card(4, 8), new Card(4, 9), new Card(4, 10), new Card(4, 11),
      new Card(4, 12), new Card(4, 13),
  };

  // Main frame
  private JFrame frame = new JFrame();

  // content panel
  private JPanel jContentPane = new JPanel();

  // Menu bar
  private JMenuBar menuBar = new JMenuBar();
  private JMenu controlMenu = new JMenu("Control");
  private JMenu helpMenu = new JMenu("Help");
  private JMenuItem exitMenuItem = new JMenuItem("Exit");

  // Cards
  private JPanel cardPanel = new JPanel();
  private JLabel[] cardLabels = {
      new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),
  };
  private ImageIcon[] cardImageIcons = {
      new ImageIcon("assets/images/card_back.gif"),
      new ImageIcon("assets/images/card_back.gif"),
      new ImageIcon("assets/images/card_back.gif"),
      new ImageIcon("assets/images/card_back.gif"),
      new ImageIcon("assets/images/card_back.gif"),
      new ImageIcon("assets/images/card_back.gif"),
  };

  // Buttons
  private JPanel buttonPanel = new JPanel();
  private JButton[] replaceButtons = {
      new JButton("Report Card 1"),
      new JButton("Report Card 2"),
      new JButton("Report Card 3"),
  };

  // Game control
  private JPanel gameControlPanel = new JPanel();
  private JTextField moneyTextField = new JTextField();
  private JButton startButton = new JButton("Start");
  private JButton resultButton = new JButton("Result");

  // Important messages
  private JPanel msgPanel = new JPanel();
  private JLabel balanceLabel = new JLabel();

  // Game state
  private int balance = 100;
  private Card[] cardDeck = {};

  private void applyState() {
    balanceLabel.setText(String.valueOf(balance));
  }


  public static void main(String[] args) {
    var main = new Main();
    main.start();
  }

  private Card[] getShuffledCardDeck() {
    var cards = allCards.clone();
    var list = Arrays.asList(cards);
    Collections.shuffle(list);
    Card[] arr = {};
    return list.toArray(arr);
  }

  public void start() {
    this.registerMenuBar();
    this.registerCards();
    this.registerButtons();
    this.registerGameControl();
    this.registerMsg();

    // Always put these in the last of start
    this.applyState();
    this.configureFrame();
  }

  private void registerMenuBar() {
    controlMenu.add(exitMenuItem);
    menuBar.add(controlMenu);
    menuBar.add(helpMenu);
    frame.setJMenuBar(menuBar);

  }

  private void configureFrame() {
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("A Simple Card Game");
    frame.setContentPane(jContentPane);
    jContentPane.setLayout(new BoxLayout(jContentPane, BoxLayout.Y_AXIS));
    frame.pack();
    frame.setVisible(true);
  }

  private void registerCards() {
    cardPanel.setBackground(Color.YELLOW);
    for (var i = 0; i < 6; i++) {
      cardLabels[i].setIcon(cardImageIcons[i]);
    }
    cardPanel.setLayout(new GridBagLayout());
    for (var i = 0; i < 6; i++) {
      cardPanel.add(
          cardLabels[i],
          new GridBagConstraints(
              i % 3,
              i / 3,
              1,
              1,
              0,
              0,
              GridBagConstraints.CENTER,
              GridBagConstraints.NONE,
              new Insets(4, 4, 4, 4),
              0,
              0
          )
      );
    }
    jContentPane.add(cardPanel);
  }

  private void registerButtons() {
    for (var i = 0; i < 3; i++) {
      replaceButtons[i].setBackground(Color.YELLOW);
      buttonPanel.add(replaceButtons[i]);
    }
    buttonPanel.setBackground(Color.YELLOW);
    jContentPane.add(buttonPanel);
  }

  private void registerGameControl() {
    moneyTextField.setColumns(10);

    gameControlPanel.add(new JLabel("Bet: $ "));
    gameControlPanel.add(moneyTextField);
    gameControlPanel.add(startButton);
    gameControlPanel.add(resultButton);

    jContentPane.add(gameControlPanel);
  }

  private void registerMsg() {
    msgPanel.add(new JLabel("Please place your bet! Amount of money you have: $"));
    msgPanel.add(balanceLabel);

    jContentPane.add(msgPanel);
  }
}

class Card {

  final int color;
  final int number;

  Card(int color, int number) {
    this.color = color;
    this.number = number;
  }

  Card(String code) {
    this(Integer.parseInt(code.substring(0, 1)), Integer.parseInt(code.substring(1)));
  }

  String getCode() {
    return String.valueOf(color) + String.valueOf(number);
  }


}