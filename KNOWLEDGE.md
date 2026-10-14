# Project
MineCommend

# Environment
- Java 21
- Maven
- Paper API 1.21.4
- Minecraft Paper Server 1.21.4

# Current Architecture

## MineCommend.java
- Main plugin class.
- Extends JavaPlugin.
- Registers /commend and /commendprofile.
- Creates DataManager and CommendManager.
- Connects CommendManager to CommendMenuListener.
- Registers event listeners.

## CommendCommand.java
- Handles /commend <player>.
- Checks whether the command sender is a player.
- Checks whether the target player exists and is online.
- Prevents self-commendation.
- Opens the Commend GUI.

## CommendMenuListener.java
- Handles InventoryClickEvent.
- Handles InventoryDragEvent.
- Processes interactions with the Commend GUI.
- Identifies Friendly, Teamwork, and Helpful selections.
- Sends valid commend requests to CommendManager.
- Prevents players from modifying the Commend GUI.
- Allows players to normally use their own inventory.
- Prevents player inventory items from being moved into the Commend GUI.

## CommendManager.java
- Handles commendation rules and logic.
- Validates the commend type.
- Enforces the 24-hour sender-target cooldown.
- The cooldown applies regardless of commend type.
- Sends commend and cooldown data to DataManager.

## DataManager.java
- Handles persistent commend data.
- Stores player data in data.yml.
- Uses player UUIDs as persistent player identifiers.
- Stores and retrieves commend counts.
- Stores and retrieves sender-target cooldown timestamps.
- Saves commend and cooldown data so they persist after server restart.

## CommendProfileCommand.java
- Handles /commendprofile <player>.
- Can be used by players or the server console.
- Supports looking up players who are currently offline.
- Reads commend counts through DataManager.
- Displays Friendly, Teamwork, Helpful, and Total Commend counts.
- Displays 0 when a commend type has no commendations.
- Shows an error message when the player name is missing or the target cannot be found.

# Current GUI
- 27 inventory slots.
- Slot 11 = Friendly.
- Slot 13 = Teamwork.
- Slot 15 = Helpful.
- Players cannot move or take items from the Commend GUI.
- Players can normally use their own inventory.
- Players cannot move items from their own inventory into the Commend GUI.

# Data
- Persistent data is stored in data.yml.
- Commend counts are stored by target player UUID.
- Cooldowns are stored by sender UUID and target UUID.
- Friendly, Teamwork, and Helpful counts are stored separately.
- Data persists after server restart.

# Development Rules
- Use UUID instead of player name for persistent player identity.
- Keep command handling, GUI event handling, commendation logic, and data persistence separated.
- Do not put unrelated responsibilities into one class.
- Important architecture changes should be decided before implementation.

# Current Development Status
- /commend <player> works.
- Online-player validation works.
- Self-commendation prevention works.
- Commend GUI opens successfully.
- Friendly, Teamwork, and Helpful selections work.
- GUI inventory protection works.
- Players can normally use their own inventory while the Commend GUI is open.
- Commend counts are saved successfully to data.yml.
- 24-hour sender-target cooldown has been implemented successfully.
- Changing the commend type does not bypass the 24-hour cooldown.
- Cooldown data persists after server restart.
- /commendprofile <player> has been implemented successfully.
- /commendprofile can display Friendly, Teamwork, Helpful, and Total counts.
- Offline player profiles can be queried.
- /commendprofile can be used from the server console.
- Core V1 functionality has been tested successfully on the Paper server.

# Known Technical Limitations
- The Commend GUI is currently identified using its title beginning with "Commend ".
- The target player for a Commend GUI is currently recovered from the GUI title.
- A custom InventoryHolder or another stronger GUI identification method may be considered in a future refactor.

# Version Status
MineCommend V1.0 core functionality is complete.