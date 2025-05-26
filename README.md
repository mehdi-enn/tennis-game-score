# Tennis Game Scoring System

## Overview
This project is a simple tennis scoring system for a single game written in Java.  
It follows the basic rules of tennis and prints the score after each point.  
At the end, it shows who won the game.
---

## How the scoring works
- Both players start at 0.
- First point -> 15
- Second point -> 30
- Third point -> 40
- If a player wins a point at 40 and the other player has less than 40, they win the game.
- If both players reach 40, the score is **deuce**.
- From deuce, the next point gives a player **advantage**.
- If that player wins again, he/she wins the game.
- If the other player wins the point, the score goes back to deuce.
---

## How to use
You give the program a string like `"ABABAA"`:
- `"A"` means Player A won the point
- `"B"` means Player B won the point

After each point, the program prints the current score.  
When someone wins, it prints the winner.
---

## Project Structure
- **ScoreBoard** and **ScoreModel**: track player scores and the current game state (Normal, Deuce, Advantage).
- **GameState** (with `NormalState`, `DeuceState`, `AdvantageState`): contains the logic for each game phase
- **Player** enum: defines Player A and Player B
- **TennisGame**: reads the input string and updates the game
- **ScoreOutput** and **ScoreDisplay**: handle printing the score and winner
- **Main**: runs a sample game
- **Unit Tests**: JUnit tests that check if everything works correctly
---

## Development Approach
I didn’t follow strict TDD, but I did write tests early to check my logic and catch mistakes.  
As the project grew, I used the tests to guide refactoring and make sure everything kept working as expected.  
This helped me keep the code clean and easy to maintain.

Steps:
1. **Create the game model**
    - Built basic classes to track players and scores.
    - Wrote tests to check that scores update properly.

2. **Add scoring rules and game flow**
    - Added rules for normal play, deuce, advantage, and winning.
    - Wrote tests to make sure the rules work.

3. **Display the score**
    - Built a simple way to show the score after each point.
    - Tested the output for all situations.

4. **Test the full game**
    - Ran full examples to make sure everything works together.
---

## Technologies Used
- Java 17+
- JUnit 5 for testing
- Mockito for mocking in unit tests
- Lombok annotations to reduce boilerplate
---

## References
- [Tennis Scoring Rules - Wikipedia](http://en.wikipedia.org/wiki/Tennis#Scoring)

---