# Coursework Project

## GitHub Repository

You can access the source code for this project at the following GitHub repository:

[GitHub Repository Link](https://github.com/tjp20/CW25.git)

This repository includes the complete source code, encompassing the implementation, test cases, and all resources utilized in the project. The key details are outlined below:

- **Branch Structure**:
    - `master`: The main branch containing the latest stable version of the project.
    - `additions`: This branch is used for all the changes and additions made to the project.
    - `fixBugs`: This branch is used for changes where bugs are fixed before merging them into `additions`.
    - `main`

- **Commits**:
    - Each commit is documented with a message describing the changes made in that particular commit.


## Compilation Instructions

Before compiling make sure it is java make sure java version "1.8.0_381". Sdk is SDK Oracle OpenJDK version 24.0.1
To compile and run the project, follow these steps:

1. Download Zip file from git repository.
2. Git Clone from repository
3. Open file in IntelliJ
4. Build file
5. Click on src
6. click on main
7. Click java
8. Click com.example.demo
9. Click Main
10. Click Run

## Implemented and Working Properly

**Refactoring**

1. Background

- Changed background of menu scene from white blank screen to 2048HomeBackground.jpg
- Changed background of login scene from purple to LoginBackground.jpg.
- Changed background of account scene from light pink to LoginBackground.jpg.
- Changed background of getAccount scene from pink to CreateBackground.png.
- Changed gameScene background from yellow to pink.
- Changed end game Scene from pink to green.

2. Rectangle

- removed Rectangle backgroundOfMenuForPlay as it was unnecessary for the game.
- removed Rectangle backgroundOfMenu as it was unnecessary for the game. This rectangle did not contribute any functionalities and aesthetic to the current background.


3. Full Screen

- made all screens full screen as it would be easier for users to play the game seamlessly.


4. Fixed bugs

- Fixed score increasing on movement without merging
  Initially, the score increased even when tiles were moved without performing any merges. This occurred because a method that summed all tile values (sumCellNumbersToScore) was previously used after every move, inflating the score incorrectly. This logic was removed, and score updates were restructured to occur only when an actual merge took place.

- Fixed merging logic
  The merging mechanism was corrected in the GameController class within the moveHorizontally() and moveVertically() methods. The logic now explicitly checks if two tiles can be merged (same value and not previously merged), calculates the new merged value, and updates the game board accordingly. A setModify(true) flag is used to ensure that tiles merge only once per move, which aligns with standard 2048 rules.

- Fixed score update logic during merge
  To ensure the score only increases during a valid merge, the merged tile value is now calculated and passed directly to the ScoreUpdater interface. This decouples score handling from movement logic and ensures precise score tracking.

- Fixed unnecessary tile spawning on invalid moves
  Previously, the game spawned a new tile even when no tiles were moved or merged, leading to illogical board states. This was resolved by introducing a moved flag in the GameController class, which tracks whether any tile movement or merging has occurred during a move. The LevelParent class checks this flag before deciding to spawn a new tile, ensuring that new tiles are only added after valid gameplay actions.

- Fixed repeated merging of the same tile in a single move
  The original logic allowed a tile to merge multiple times in one move, which is inconsistent with standard 2048 rules. To address this, a setModify(true) flag was applied to merged tiles to prevent them from merging again during the same move cycle. This ensured that each tile participates in only one merge per input, preserving expected behavior.

- Fixed incorrect end-game condition detection
  The previous implementation could fail to detect when the board was full and no further moves were possible. This was resolved by updating the canNotMove() method to check for adjacent equal tiles across the entire board. The game now accurately transitions to the end-game scene only when no valid moves or merges remain.

- Implemented ScoreDebugger for validation
  A custom ScoreDebugger utility was introduced to assist with debugging the score updates. Each time a merge occurs, the debugger logs the current total score and the individual merge value to the console. This made it easier to trace and confirm that score increments only happen during valid merges, improving confidence in the correctness of the logic.


5. Button position

- initially Quit button positions in End Game was not in the middle and it was difficult for users to see it as the font was pink. It was also placed at the bottom of the screen. So, the colour of the button was changed to green with white font colour. Hover effect was added for visual purposes.


6. Renaming of classes

- Controller.java to GameController.java. The class was renamed to explicitly reflect its purpose in handling the game logic, such as tile movement, merging, and score updates. This change improves clarity and avoids confusion with general UI controllers or JavaFX controllers.

- GameScene.java to LevelParent.java. The original name implied the class was solely responsible for rendering the game scene, whereas it also included shared logic for gameplay. Renaming it to LevelParent better represents its role as a reusable base class for multiple levels. This structure allows each level (e.g., Level1, Level2, Level3) to inherit from LevelParent, simplifying code maintenance and enabling level-specific customizations more efficiently.


7. Splitting of classes

- The original GameScene class was split into two separate classes LevelParent and GameController to improve modularity and maintainability. GameScene previously contained both the user interface setup and the core game logic, making it large and tightly coupled. By separating concerns, GameController now handles all logical operations such as tile movement, merging, score calculation, and endgame detection, while LevelParent focuses on UI rendering, scene setup, and user interactions. Additionally, LevelParent acts as a base class for level-specific implementations like Level1, Level2, and Level3, making it easier to manage shared functionality and customize each level independently. This restructuring enhances code clarity and supports scalable game development.


8. Classifying classes into meaningful packages

- To improve project structure and code organization, classes were grouped into meaningful packages based on their functionality. The controller package contains only the GameController class, which manages the core logic such as movement, merging, and score updates. The debug package was introduced to isolate debugging utilities, currently housing the ScoreDebugger.java class. The level package includes all game logic and level-specific classes, such as LevelParent, Level1, Level2, Level3, Cell, and TextMaker, reflecting its focus on in-game mechanics. The user package handles user-related functionalities, including Account.java, CreateAccountScreen.java, LoginScreen.java, and UserScore.java, covering account creation, login, and score tracking. For UI components, the view package was created to group visually related classes, and within it, a nested gamescreens package organizes screen-specific classes like EndGame, LeaderboardScreen, LevelSelectScreen, MenuScreen, PauseScreen, and RankScreen. The view package also contains shared UI elements such as ExitButton and UsernameDisplay. This classification enhances code readability, modularity, and scalability.


9. Encapsulation

- Applied encapsulation by using private attributes with public setters and getters across multiple classes. This ensures data protection and integrity, restricting direct access to class fields and allowing controlled modification.


10. Single responsibility classes

- Each class in the program is designed to handle just one specific responsibility. For example, Account.java manages the account logic, such as storing and processing user data. Meanwhile, CreateAccountScreen.java and LoginScreen.java focus solely on the user interface for creating accounts and logging in, respectively. This separation of concerns means each class has a clear, focused role. It makes the code easier to read, test, and maintain because changes to one part (like UI or logic) won’t unintentionally affect others. By keeping responsibilities separate, the program becomes more modular and scalable, allowing developers to update or expand features without causing widespread issues.


11. Code indentation

All code has been consistently indented, ensuring it is readable and easy to follow. Proper formatting enhances team collaboration and debugging.


12. Code Arrangement

Throughout the project, I ensured consistent and logical code arrangement by organizing each Java class in a readable and maintainable structure. I followed a standard convention where private fields are declared at the top, followed by public methods, and then private helper methods at the bottom. This structure makes the code easier to navigate, improves readability, and supports better understanding when maintaining or extending the application. It also aligns with common best practices in object-oriented programming, which contributes to a cleaner and more professional codebase.


13. Deleting unused resources

Cleaned the project by deleting unused files, images, classes, and redundant code that were no longer required, reducing clutter and potential confusion.



**Additions**

1. Added a "PLAY" button
- Added a play button in the MenuScreen class to allow users to view the beautiful background to increase the attraction of the users while playing the game.

2. Added debugging mechanisms in the game such as "play button clicked" in MenuScreen.java to indiciate the play button was clicked.

3. Added debugging mechanisms
- Added printBoardState which prints out the board using an array before and after the move. This helps me keep track of all the movement of the tiles and I can detect easily if something is not correct and can manually calculate as well.

4. Score debugging
   Added a class to solve this issue to keep track of the scores before and after merge.

5. Added Playable levels
   Made Level 1.java, Level2.java and Level3.java. These levels are all different as they vary in difficulty. Each class has its own grid size which are 5x5,4x4 and 3x3. 3X3 is the most difficult as the space is smaller to keep adding up the score.

6. Timer
   Added a timer feature in Level 1. This is because the grid is very large. This would cause users to easily merge tiles. To add a challenge, I added a 60 second timer. Once timer is up, game ends.

7. Back button
   Added a back button in CreateAccount screen,

8. Aesthetic design
   Made sure all the designs and theme was matching each other as this would attract the users attention as well as still maintained a simple design for users to navigate around easily.

9. Pause button and screen
   Implemented pause button and screen for users to click whenever they want to. Once pause button is clicked, no keys can move the tiles and in level 1, timer it stopped and can be resumed. The pause screen is then showed alongside 3 buttons for further options such as retry, resume and quit button.

10. Images added

- 2048HomeBackground.jpg: for menuScreen's background
- BackButton.png
- CreateAccountButton1 and CreateAccountButton2 : there is 2 buttons for the hover effect
- CreateBackground.png : for CreateAccountScreen
- EnterButton1.png and EnterButton2.png : for LoginScreen

11. Added feedback text.
    This is implemented when users are logging in or creating an account. Texts are in red to guide users. Statements such as "this account exisits" are implemented.


12. JUnit Testing added

13. PlayPopAnimation
    Added animation to the newly spawned tiles so that users are able to see clearly the tile that is spawned. Initially it was not very clear and difficult to know.

14. Save usernames in txt file
    To enable persistent account storage, I updated the Account class to read and write usernames to a text file. Specifically, I created a saveAccounts() method that writes all usernames in the static account list to a file called accounts.txt, and a loadAccounts() method that reads from this file when the program starts, recreating each account in memory. This allows users to create an account once and have it remembered even after closing and reopening the game. I called Account.loadAccounts(); at the start of the Main.java to ensure all saved usernames are loaded before login, ensuring a seamless user experience.

15. Added Javadocs for all classes to make sure everything is clear and easy for developer to understand and use later on.

16. Added leaderboard screen which shows top 10 scores within all levels which will keep updating as new best scores as created.

17. Added the UI elements for rank screen to display top 3 scores within that specific level unlike leaderboard where it displays top 10 scores within all the levels.




## Implemented but not working properly

1. Fullscreen
- All screens are in fullscreen. However, there is a little glitch when transitioning screens.
- Possible solution: use event listener

2. End Game glitch
- Not all the time, but sometimes, there is a glitch with the End Game screen where it pops up after user plays Level1 and quits to level screen.






## Features Not Implemented

1. Background music
- The game currently has no background music, which could enhance immersion and make gameplay more engaging.

- Possible solution: se JavaFX's Media and MediaPlayer classes to play looping background audio. Store the audio file in the resources folder and allow it to start when the main menu loads. Provide an option to toggle the music on or off in the settings or pause menu.



2. Change theme feature
- Players are limited to the default colour scheme, which may not suit all preferences. Allowing theme changes could improve accessibility and personalization.

- Possible solution: Create multiple CSS stylesheets or pre-defined colour palettes for tiles, backgrounds, and text. Add a "Change Theme" button in the main menu or pause menu to dynamically switch stylesheets at runtime.

3. Custom grid size
- Grid sizes are currently fixed to 3x3, 4x4, and 5x5 for the three levels. Players may want to play with larger or smaller grids for variety.

- Possible solution:  Allow players to select a custom grid size before starting a game. Update LevelParent and GameController to handle variable n values dynamically, ensuring proper scaling for tile sizes and spacing.

4. Undo Last move
- Players cannot undo a move, which could improve the strategic element of gameplay.

- Possible solution: Maintain a stack of previous game states including grid configuration and score. When "Undo" is pressed, pop the last saved state and restore it. Limit the number of undos to prevent abuse.

5. Multi-Player or Challenge Mode
- The game is currently single-player only. Adding a competitive or timed challenge mode could increase replay value.

- Possible solution: Implement a "Challenge" mode where two players play simultaneously on separate boards, racing to achieve the highest score within a set time limit. This could be done locally by splitting the game screen or online using sockets.

6. Next level button
- Does not include a button to the next level
- Possible solution: add the button in EndGame so that users can quickly switch to the next level seamlessly.
## New Java Classes

1. MenuScreen.java

This is to show the first page of the game. Used static method called addToMenu(Group menuRoot) to inject UI components such as "PLAY" button. Then modified Main.java after initialising menuRoot and added MenuScreen.addToMenu(menuRoot);.

2. ScoreDebugger.java

The ScoreDebugger class is a utility designed to assist with debugging score-related events during gameplay in the 2048 game. It verifies whether the score increases correctly after a tile merge by comparing the expected merged value with the actual score increment. The debugMergeScore() method logs the result of each merge, and if there's a mismatch between the merged value and the score change, it prints an error message to help identify inconsistencies. Additionally, the reset() method is used to initialize the score tracker at the start of a new game. This class helps developers validate score accuracy during development and testing.


3. Level1.java

The Level1 class extends LevelParent and represents a timed 2048 level with a 5x5 grid and a 60-second countdown. It sets up the scene, displays the level name and timer, and starts the game using the game() method. A Timeline handles the countdown, ending the game and saving the score when time runs out. It also includes methods to pause and resume the timer, supporting pause functionality. Due to the large 5x5 grid, the timer was added to increase difficulty


4. Level2.java

The Level2 class extends LevelParent and represents the second level of the game with a standard 4x4 grid. It sets the level name, initializes a new game scene with a pink background, and displays the level title on screen. The launch() method calls the inherited game() method to start gameplay and sets the scene to fullscreen. This level shows medium difficulty which is why the timer is removed.


5. Level3.java

The Level3 class extends LevelParent and represents the third level of the game, featuring a 3x3 grid for increased difficulty. It initializes a green-themed scene, displays the level name, and starts the game by calling the inherited game() method. The launch() function sets up the level-specific configuration and transitions the stage to fullscreen gameplay. This is the hardest level as it has the smallest space to merge tiles.


7. CreateAccountScreen.java
   This class is for users to create their account. It also includes features such as feedback text to ensure users dont create an account that already exists. It also redirects the users to the login screen when successful account creation.

8. LoginScreen.java
   Made an LoginScreen class to be able to view the screen that has already been set in Main.java class. I modified the MenuScreen so that clicking the "Play" button redirects users to the AccountScreen. I switched the initialisation order of the menuScene and accountScene so that accountScene could be passed into the MenuScreen method.This allows seamless scene switching when the user clicks "Play" from the main menu, leading directly to the account login interface.

9. LeaderBoardScreen.java
   The LeaderboardScreen class displays the leaderboard interface for the 2048 game, showing the top 10 highest scores across all levels. It retrieves and sorts player scores from the UserScore class in descending order and visually presents them in a tabular format with columns for rank, player name, level, and score. The screen includes a stylized title, column headers, and a back button that allows users to return to the previous scene. The UI features a retro arcade aesthetic, using custom fonts and colors for visual appeal.

10. LevelSelectScreen.java
    The LevelSelectScreen class sets up the level selection interface in the 2048 game, allowing users to choose between Level 1, Level 2, and Level 3, or view the leaderboard. It also has buttons for each level with hover effects and positions them dynamically based on screen size. It also displays the logged-in username and includes an exit button. The class connects each level button to its corresponding launch() method, enabling smooth transitions to gameplay or leaderboard scenes. The design combines usability and aesthetics, making level selection clear and visually appealing.

11. PauseScreen.java
    The PauseScreen class is for users to pause their screen during the game. When paused, a pause screen is displayed with 3 buttons, resume, retry and quit. Each button also includes the hover effect and also has their own actions such as resuming the game, restarting the level, or returning to level selection. The class also handles resizing to keep the overlay responsive and uses a paused boolean to track pause state.


12. RankScreen.java
    The RankScreen class displays the top 3 high scores for a selected level in the 2048 game. It filters and sorts scores from UserScore, showing player names and their scores in descending order. If no scores exist, it displays a message accordingly. The screen features a title, styled labels for each rank, and an "Exit" button that returns users to the level selection screen. This class helps players quickly see top performances for each level in a clean and organized layout.

13. ExitButton.java
    The exit button is for users to close the game whenever they intend to. It is red in colour and placed on the top left corner of the screen. It also has the hover effect.

14. UsernameDisplay.java
    The UsernameDisplay class provides a simple static method to show a username inside a styled profile box on any JavaFX scene. It creates a gray rounded rectangle as the background and displays the username text in white Arial font on top of it. The box and text are positioned based on given coordinates, allowing flexible placement. This class offers a clean and consistent way to visually display the logged-in user’s name within the game interface.


15. UserScore.java
    The UserScore class manages player scores by reading from and writing to individual text files stored in a directory named "scores". Each file is named after a username such as player1.txt and contains key-value pairs where each line stores a level and the corresponding best score, formatted as Level1:2048. When a score is updated using updateScore(), the system loads the existing scores from the user's file, compares them in memory, and saves the new best scores if applicable. The getAllScoresSimple() method scans all user files in the directory, reads their contents, and compiles a list of [username, level, score] entries. This data is then used by other classes like LeaderboardScreen and RankScreen to display scores. In summary, the text files act as a simple persistent storage system for high scores across levels and users.







## Modified Java Classes

1. ** Main.java **

a) Removed the rectangle in MenuScene as well as AccountScene.
- I removed the code by initially commenting it out and then towards the end removing the unused comments.

b) Swithced to full-screen
- Replaced hard-coded WIDTH and HEIGHT with Screen.getPrimary().getBounds() so all scenes (menu, login, level select, end game, create account) scale to the display. Set app title and enabled full-screen by default. Used  primaryStage.setFullScreen(true); to enable fullscreen.

c) Scene architecture refactor
-  Instead of launching directly into gameplay, the app now initializes distinct scenes like menuScene, accountScene, levelScene, endGameScene, and getAccountScene and shows the Menu first for a clearer flow.

d) Integration of new screens:
- Connected MenuScreen.addToMenu(), LoginScreen.addToAccountScreen(), and CreateAccountScreen.addToCreateAccountScreen() to handle navigation between menu -> login -> create account -> level select.

e) Account
- Added Account.loadAccounts() in start() so saved users are available before login.

2. ** Cell.java **

a) Moved & renamed package
- elocated from com.example.demo to com.example.demo.level to group all board mechanics with level code.

b) Encapsulation
- Made the class and key methods public; added clear Javadocs. Kept modify flag with setModify/getModify to enforce the “merge once per move” rule.

c) UI behaviour
- Still draws the rectangle tile but no longer adds a “0” text node to the scene at construction (the TextMaker.madeText() now returns a Text without adding it). This avoids rendering zero tiles; text is only added when a real value such as 2 or 4 appears.

d) Merge logic adder
- Computes the sum once, writes it to the target cell, zeroes the source, removes the source text node, and refreshes colors. This ensures a clean state after merges and plays nicely with score updates handled in GameController.

e) changeCell refined
- Uses TextMaker.changeTwoText(), then re-adds text nodes only if their value is non-zero. Updates colors for both cells to keep visuals consistent after shifts.

f) Accessors
- Provided getNumber(), getX(), getY(), and (package-visible) getTextClass() for controlled interaction from controller or level code.


3. ** Controller.java **
   a) Refactored name to GameController.java
- owns all game rules such as movement (UP, DOWN, LEFT AND RIGHT), merging, score updates, end-game checks.

b) Score handling corrected
- Computes the merged value and calls ScoreUpdater.updateScore(merged) only on valid merges. So there is no score change on pure movement like initially coded in the original project.

c) One-merge-per-move enforced
- Uses the cell’s modify flag to prevent double merges in a single move.

d) Spawn tracker
- Tracks moved to indicate if any tile actually moved/merged; the UI only spawns a new tile when hasMoved() is true.

e) Debug
- Adds printBoardState() before/after each directional move to aid debugging. Shows the array of the board state before and after to track game easily.

4. ** GameScene.java **

a) refactored name to LevelParent.java

b) Separation of responsibility
- focuses on UI input, pause buttons and level logic

c) Input handling
- Arrow-key handler now delegates to gameController.move*() and checks hasMoved() before spawning a tile.

d) Score updates
- Removed the old board-wide sumCellNumbersToScore() call. Score now updates only on merges via the ScoreUpdater callback (accumulating score and updating scoreText).

e) UI additions
-  Adds username badge, Best Score and Current Score boxes, and integrates PauseScreen including pause and resume hooks for timed levels (LEVEL1 ).

f) End-game path
- On no moves left, persists best score via UserScore, clears roots, and transitions cleanly to EndGame.

g) Tile spawn
- Keeps existing spawn logic but adds a pop animation on new tiles for clarity.

5. ** EndGame.java **
   a) Relocated package to com.example.demo.view.gameScreens

b) UX UI
- Use VBox layout, and removed earlier ux ui elemtents. Used consistent fonts and green background. Buttons also have hoever effect and is dark green in colout with white font.

c) New actions
- Added button to View Rank which navigates to RankScreen which shows top 3 ranks of that level. Also has a button called LEVEL which leads to LevelSelectScreen and a Quit button as well. This turns the end screen into a navigation hub instead of a dead end.

d) Quit behavior
- Previously cleared the root, now confirms and closes the stage on OK, which is clearer and avoids orphaned scenes.

e) Level 1 timer integration
- Accepts an optional Level1 reference and stops the countdown before leaving the screen both for Rank and Levels, preventing timer leaks after game over.

6. ** Account.java **

a) Relocated package to com.example.demo.user
- Groups all account-related logic in a dedicated package for better organization and separation from game/UI code.

b) Persistence support
- Added loadAccounts() to read usernames from accounts.txt at startup. Updated makeNewAccount() to append the new username to accounts.txt, ensuring accounts persist across sessions.

c) Current session tracking
- Introduced public static String currentUsername to store the logged-in user globally, allowing consistent access across screens.

d) API cleanup and encapsulation
- Removed unused JavaFX imports and UI-related code from the original version, making Account a pure data/persistence class. Exposed getUserName() publicly while keeping getScore() private, allowing controlled score updates via addToScore().

e) Sorting
- Preserved compareTo() to sort accounts by descending score for ranking features.

7. ** TextMaker.java **


a) Relocated package to com.example.demo.level
- Keeps tile text utilities with the rest of the level/board code for better cohesion.

b) Decoupled from scene class
- Replaced GameScene.getLENGTH() with LevelParent.getLENGTH(), removing the dependency on the old scene class and aligning with the new LevelParent base.

c) Singleton + API retained
- Preserved the lightweight singleton (getSingleInstance()), madeText() for consistently styled/positioned tile numbers, and changeTwoText() for swapping values/positions during moves.

d) Behavior clarified
- madeText() returns a styled Text but does not auto-add it to the scene graph, avoiding zero-value node churn; callers addor remove as needed.




## Unexpected Problem
1. Scene glitch after end game ocassionally but not often.