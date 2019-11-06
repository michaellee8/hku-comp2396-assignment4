package com.example.hku.comp2396.assignment4;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// flower = 1
// kwai sin = 2
// kite = 3
// heart = 4

public class MainTests {

  static final Card[][][] dealerWinCases = {
      {
          {
              new Card(2, 9),
              new Card(4, 4),
              new Card(4, 12)
          },
          {
              new Card(3, 1),
              new Card(1, 13),
              new Card(2, 11)
          }
      },
      {
          {
              new Card(2, 12),
              new Card(3, 9),
              new Card(1, 1)
          },
          {
              new Card(3, 6),
              new Card(1, 11),
              new Card(4, 6)
          }
      },
      {
          {
              new Card(4, 11),
              new Card(3, 4),
              new Card(1, 5)
          },
          {
              new Card(3, 12),
              new Card(1, 3),
              new Card(4, 6)
          }
      }
  };

  static final Card[][][] playerWinCases = {
      {
          {
              new Card(2, 2),
              new Card(1, 4),
              new Card(4, 3)
          },
          {
              new Card(1, 5),
              new Card(1, 6),
              new Card(1, 7)
          }
      }
  };

  @Test
  public void examplePlayerWinCases() {
    for (var testCase : playerWinCases) {
      var playerCards = new ArrayList<Card>(Arrays.asList(testCase[0]));
      var dealerCards = new ArrayList<Card>(Arrays.asList(testCase[1]));
      Assertions.assertTrue(Card.determineWinner(playerCards, dealerCards));
    }
  }

  @Test
  public void exampleDealerWinCases() {
    for (var testCase : dealerWinCases) {
      var playerCards = new ArrayList<Card>(Arrays.asList(testCase[0]));
      var dealerCards = new ArrayList<Card>(Arrays.asList(testCase[1]));
      Assertions.assertFalse(Card.determineWinner(playerCards, dealerCards));
    }
  }
}
