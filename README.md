# Sliding Puzzle Game (Android)

## Overview

Sliding Puzzle Game is an Android application based on the classic **8-puzzle problem**.
The objective of the game is to arrange the numbered tiles in the correct order by sliding them into the empty space.

The application also calculates the **optimal number of steps** required to solve the puzzle using a puzzle solving algorithm.

---

## Features

* 3×3 Sliding Puzzle Grid
* Tile sliding mechanics
* Step counter
* Optimal solution calculation
* Win animation
* New Game button
* Simple and user-friendly UI

---

## Technologies Used

* **Java**
* **Android Studio**
* **GridLayout**
* **Puzzle Solver Algorithm**
* **Git & GitHub**

---

## Algorithm

The puzzle solver calculates the optimal number of moves required to solve the puzzle.
It explores different board states and determines the shortest path to reach the goal configuration.

---

## Project Structure

MainActivity.java
→ Start screen of the application

GameActivity.java
→ Handles puzzle gameplay and tile movement

PuzzleSolver.java
→ Calculates optimal solution steps

activity_main.xml
→ Layout for main screen

activity_game.xml
→ Layout for puzzle game screen

---

## How to Run the Project

1. Clone the repository
2. Open the project in **Android Studio**
3. Build and run the application on an emulator or Android device

---

## Author

Ayush Prakash
B.Tech Student

---

## Future Improvements

* Add timer
* Add leaderboard
* Multiple puzzle sizes
* Better animations
