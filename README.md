# MineCommend

MineCommend is a Minecraft server plugin built with Java and the Paper API that allows players to commend other players for positive interactions.

Players can recognize others in three categories: **Friendly**, **Teamwork**, and **Helpful**. Commendations are stored persistently and can be viewed through a player profile command.

## Features

- Commend other online players using `/commend <player>`
- Interactive 27-slot commendation GUI
- Three commendation categories:
    - Friendly
    - Teamwork
    - Helpful
- Prevents players from commending themselves
- 24-hour cooldown for commending the same player
- Cooldown applies regardless of commendation type
- Persistent commendation and cooldown data
- View commendation statistics with `/commendprofile <player>`
- Supports profile lookup for offline players
- Profile command can be used by both players and the server console

## Commands

### `/commend <player>`

Opens the commendation GUI for the selected player.

The target player must be online, and players cannot commend themselves.

After opening the GUI, the player can select one of three commendation types:

- Friendly
- Teamwork
- Helpful

A player can only commend the same player once every 24 hours.

### `/commendprofile <player>`

Displays the selected player's commendation statistics.

Example:

```text
Friendly: 5
Teamwork: 3
Helpful: 2
Total Commend: 10
```

Profiles can be viewed even when the target player is offline.

## How It Works

MineCommend separates command handling, GUI events, business logic, and data persistence into different components.

```text
/commend <player>
        |
        v
CommendCommand
        |
        v
Commend GUI
        |
        v
CommendMenuListener
        |
        v
CommendManager
        |
        v
DataManager
        |
        v
data.yml
```

`CommendManager` handles the commendation rules, including the 24-hour cooldown, while `DataManager` is responsible for storing commendation counts and cooldown data.

Player UUIDs are used for persistent player identification.

## Project Structure

```text
src/main/java/com/kenta/minecommend/
├── MineCommend.java
├── CommendCommand.java
├── CommendProfileCommand.java
├── CommendMenuListener.java
├── CommendManager.java
└── DataManager.java
```

### Main Components

**MineCommend**  
Initializes the plugin and connects the commands, managers, and event listeners.

**CommendCommand**  
Handles `/commend <player>` and opens the commendation GUI.

**CommendProfileCommand**  
Handles `/commendprofile <player>` and displays player commendation statistics.

**CommendMenuListener**  
Handles player interactions with the commendation GUI.

**CommendManager**  
Handles commendation rules and the 24-hour cooldown.

**DataManager**  
Handles persistent storage of commendation and cooldown data.

## Tech Stack

- Java 21
- Paper API 1.21.4
- Maven
- YAML

## Requirements

- Java 21
- Paper 1.21.4

## Build

Clone the repository and run:

```bash
mvn package
```

Maven will generate the plugin JAR inside the `target` directory.

## Installation

1. Build the project with Maven.
2. Copy the generated JAR file into the Paper server's `plugins` folder.
3. Start or restart the Paper server.
4. Use `/commend <player>` in Minecraft to open the commendation GUI.

## Development Approach

The project uses separate specification and development documentation to keep implementation decisions organized.

- `SPEC.md` defines the expected plugin behavior and requirements.
- `KNOWLEDGE.md` documents the current architecture and implementation.
- `SKILLS.md` defines development and code-change guidelines.

Important architecture changes are reviewed before implementation, and changes are tested against the project specification.

## Future Improvements

Possible improvements for future versions include:

- Improved GUI design and visual polish
- Stronger GUI identification instead of relying on inventory titles
- Additional profile presentation improvements
- More customization options for server administrators

## Status

**V1.0 core functionality is complete.**

The core commendation flow, GUI interaction, cooldown system, persistent storage, and player profile system have been implemented and tested on a Paper server.