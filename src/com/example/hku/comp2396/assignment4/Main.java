package com.example.hku.comp2396.assignment4;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;


public class Main {

  static final String path = "assets/images/";

  static final ArrayList<Card> allCards = new ArrayList<Card>(
      Arrays.asList(new Card(1, 1),
          new Card(1, 2), new Card(1, 3), new Card(1, 4), new Card(1, 5),
          new Card(1, 6), new Card(1, 7), new Card(1, 8), new Card(1, 9),
          new Card(1, 10),
          new Card(1, 11), new Card(1, 12), new Card(1, 13), new Card(2, 1),
          new Card(2, 2),
          new Card(2, 3), new Card(2, 4), new Card(2, 5), new Card(2, 6),
          new Card(2, 7),
          new Card(2, 8), new Card(2, 9), new Card(2, 10), new Card(2, 11),
          new Card(2, 12),
          new Card(2, 13), new Card(3, 1), new Card(3, 2), new Card(3, 3),
          new Card(3, 4),
          new Card(3, 5), new Card(3, 6), new Card(3, 7), new Card(3, 8),
          new Card(3, 9),
          new Card(3, 10), new Card(3, 11), new Card(3, 12), new Card(3, 13),
          new Card(4, 1),
          new Card(4, 2), new Card(4, 3), new Card(4, 4), new Card(4, 5),
          new Card(4, 6),
          new Card(4, 7), new Card(4, 8), new Card(4, 9), new Card(4, 10),
          new Card(4, 11),
          new Card(4, 12), new Card(4, 13)));

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
      new JLabel(), new JLabel(), new JLabel(), new JLabel(), new JLabel(),
      new JLabel(),
  };
  private ImageIcon[] cardImageIcons = {
      new ImageIcon(path + "card_back.gif"),
      new ImageIcon(path + "card_back.gif"),
      new ImageIcon(path + "card_back.gif"),
      new ImageIcon(path + "card_back.gif"),
      new ImageIcon(path + "card_back.gif"),
      new ImageIcon(path + "card_back.gif"),
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
  private ArrayList<Card> cardDeck;
  private ArrayList<Card> dealerCards;
  private ArrayList<Card> playerCards;
  private int replaceTimes = 0;
  private boolean showDealerCards = false;
  private int bet = 0;
  private boolean enableMoneyTextField = true;
  private boolean enableStartButton = true;
  private boolean enableResultButton = false;
  private boolean enableReplaceButtons = false;

  private void applyState() {
    balanceLabel.setText(String.valueOf(balance));
    if (cardLabels != null && dealerCards != null && playerCards != null) {
      cardLabels[0].setIcon(new ImageIcon(dealerCards.get(0).getPath()));
      cardLabels[1].setIcon(new ImageIcon(dealerCards.get(1).getPath()));
      cardLabels[2].setIcon(new ImageIcon(dealerCards.get(2).getPath()));
      cardLabels[3].setIcon(new ImageIcon(playerCards.get(0).getPath()));
      cardLabels[4].setIcon(new ImageIcon(playerCards.get(1).getPath()));
      cardLabels[5].setIcon(new ImageIcon(playerCards.get(2).getPath()));
    }
    if (cardLabels != null && !showDealerCards) {
      cardLabels[0].setIcon(new ImageIcon(path + "card_back.gif"));
      cardLabels[1].setIcon(new ImageIcon(path + "card_back.gif"));
      cardLabels[2].setIcon(new ImageIcon(path + "card_back.gif"));
    }
    if (replaceTimes >= 2) {
      replaceButtons[0].setEnabled(false && enableReplaceButtons);
      replaceButtons[1].setEnabled(false && enableReplaceButtons);
      replaceButtons[2].setEnabled(false && enableReplaceButtons);
    }
    if (replaceTimes < 2) {
      replaceButtons[0].setEnabled(true && enableReplaceButtons);
      replaceButtons[1].setEnabled(true && enableReplaceButtons);
      replaceButtons[2].setEnabled(true && enableReplaceButtons);
    }
    moneyTextField.setEnabled(enableMoneyTextField);
    startButton.setEnabled(enableStartButton);
    resultButton.setEnabled(enableResultButton);
  }


  public static void main(String[] args) {
    var main = new Main();
    main.start();
  }

  private ArrayList<Card> getShuffledCardDeck() {
    var cards = (ArrayList<Card>) allCards.clone();
    Collections.shuffle(cards);
    return cards;
  }


  public void start() {
    this.registerMenuBar();
    this.registerCards();
    this.registerButtons();
    this.registerGameControl();
    this.registerMsg();
    this.registerMenu();

    // Always put these in the last of start
    this.applyState();
    this.configureFrame();

    cardDeck = getShuffledCardDeck();
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
      final int n = i;
      replaceButtons[i].addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
          playerCards.set(n, cardDeck.remove(cardDeck.size() - 1));
          replaceTimes++;
          applyState();
        }
      });
      buttonPanel.add(replaceButtons[i]);
    }
    buttonPanel.setBackground(Color.YELLOW);
    jContentPane.add(buttonPanel);
  }

  private void onStart() {
    int b;
    try {
      b = Integer.parseInt(moneyTextField.getText());
    } catch (NumberFormatException e) {
      JOptionPane.showMessageDialog(frame,
          "WARNING: The bet you place must be a positive integer!");
      return;
    }
    if (b <= 0) {
      JOptionPane.showMessageDialog(frame,
          "WARNING: The bet you place must be a positive integer!");
      return;
    }

    dealerCards = new ArrayList<>();
    dealerCards.add(cardDeck.remove(cardDeck.size() - 1));
    dealerCards.add(cardDeck.remove(cardDeck.size() - 1));
    dealerCards.add(cardDeck.remove(cardDeck.size() - 1));
    playerCards = new ArrayList<>();
    playerCards.add(cardDeck.remove(cardDeck.size() - 1));
    playerCards.add(cardDeck.remove(cardDeck.size() - 1));
    playerCards.add(cardDeck.remove(cardDeck.size() - 1));
    replaceTimes = 0;
    showDealerCards = false;
    enableMoneyTextField = false;
    enableReplaceButtons = true;
    enableResultButton = true;
    enableStartButton = false;
    bet = b;
    applyState();
  }

  private void onResult() {
    showDealerCards = true;
    applyState();
    if (Card.determineWinner(playerCards, dealerCards)) {
      // Player wins
      JOptionPane
          .showMessageDialog(frame, "Congratulations! You win this round!");
      balance += bet;
    } else {
      // Dealer wins
      JOptionPane
          .showMessageDialog(frame, "Sorry! The Dealer wins this round!");
      balance -= bet;
    }
    if (balance <= 0) {
      JOptionPane.showMessageDialog(
          frame,
          new StringBuilder("Game over!\n")
              .append("You have no more money!\n")
              .append("Please start a new game!").toString()
      );
      msgPanel.removeAll();
      msgPanel.revalidate();
      msgPanel.repaint();
      msgPanel
          .add(new JLabel("You have no more money! Please start a new game!"));
      onEnd();
      return;
    }
    if (cardDeck.size() < 6) {
      JOptionPane.showMessageDialog(frame,
          "No more cards in card deck, the game ends now");
    }

    enableMoneyTextField = true;
    enableStartButton = true;
    enableResultButton = false;
    enableReplaceButtons = false;
    applyState();
  }

  private void onEnd() {

    enableReplaceButtons = false;
    enableStartButton = false;
    enableResultButton = false;

    applyState();

  }


  private void registerGameControl() {
    moneyTextField.setColumns(10);

    gameControlPanel.add(new JLabel("Bet: $ "));
    gameControlPanel.add(moneyTextField);
    gameControlPanel.add(startButton);
    gameControlPanel.add(resultButton);

    startButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        onStart();
      }
    });

    resultButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        onResult();
      }
    });

    jContentPane.add(gameControlPanel);
  }

  private void registerMsg() {
    msgPanel
        .add(new JLabel("Please place your bet! Amount of money you have: $"));
    msgPanel.add(balanceLabel);

    jContentPane.add(msgPanel);
  }

  private void registerMenu() {
    exitMenuItem.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent actionEvent) {
        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
      }
    });
    helpMenu.addMenuListener(new MenuListener() {
      @Override
      public void menuSelected(MenuEvent menuEvent) {
//        System.out.println("Open help dialog");
        JOptionPane.showMessageDialog(
            frame,
            new StringBuilder()
                .append("Rules to determine who has better cards:\n")
                .append("J, Q, K are regarded as special cards.\n")
                .append("Rule 1: The one with more special cards wins.\n")
                .append(
                    "Rule 2: If both have the same number of special cards, add the face values of the other "
                        + "card(s) and take the remainder after dividing the sum by 10. The one with a bigger "
                        + "remainder wins. (Note: Ace = 1).\n")
                .append(
                    "Rule 3: The dealer wins if both rule 1 and rule 2 cannot distinguish the winner.\n")
                .toString()
        );
      }

      @Override
      public void menuDeselected(MenuEvent menuEvent) {

      }

      @Override
      public void menuCanceled(MenuEvent menuEvent) {

      }
    });
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
    this(Integer.parseInt(code.substring(0, 1)),
        Integer.parseInt(code.substring(1)));
  }

  String getCode() {
    return String.valueOf(color) + String.valueOf(number);
  }

  String getPath() {
    return Main.path + "card_" + getCode() + ".gif";
  }

  int getNumber() {
    return number;
  }

  boolean isSpecial() {
    return this.number > 10;
  }

  static boolean determineWinner(ArrayList<Card> playerCards,
      ArrayList<Card> dealerCards) {
    // return true if player wins

    var playerSpecialCardAmount = playerCards.stream().filter(Card::isSpecial)
        .count();
    var dealerSpecialCardAmount = dealerCards.stream().filter(Card::isSpecial)
        .count();
    if (playerSpecialCardAmount != dealerSpecialCardAmount) {
      return playerSpecialCardAmount > dealerSpecialCardAmount;
    }

    var playerScore = playerCards.stream().map(Card::getNumber)
        .reduce(0, (a, b) -> a + b) % 10;
    var dealerScore = dealerCards.stream().map(Card::getNumber)
        .reduce(0, (a, b) -> a + b) % 10;

    return playerScore > dealerScore;
  }

}