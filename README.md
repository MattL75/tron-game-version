# Tron
A self-made version of one of the games found on the retro arcade machine "Tron". Made with JavaFX. Follow-up to the snake project.

# Structure
TronMain is the controller of the application. It switches scenes, controls the styles and more. TronPane is the overarching pane that holds the game banner and current score. TronGamePane is nested within TronPane and holds all things related to the game itself. Cycler is simply the object representing the 'snakes'.

# Functionality
The backdrop of the game is an array of transparent/black (depending on version) rectangles. Whenever a cycler moves over a tile, it turns that rectangle into its own color with lowered opacity. These represent the walls, with which the cylers may collide. Every time a cycler moves, the game checks for collisions with walls, or with other cyclers. If one is found, a winner is declared for the round.

# Bugs
* When nobody moves at the start, the first cycler checked for collision loses. [FIXED]
* If the player presses the up key followed by the down key (same issue with right left) before the cycler moves another tile, the player effectively runs into itself and dies.
