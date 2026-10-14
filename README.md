# MineCommend

MineCommend is a Minecraft plugin that lets players recognize other players for positive behavior.

For example, after playing together, a player can commend a teammate for being **Friendly**, showing good **Teamwork**, or being **Helpful**.

It works like a simple player reputation system inside a Minecraft server.

## How to Use

### 1. Commend Another Player

Enter:

```text
/commend <player>
```

For example:

```text
/commend Steve
```

A menu will open with three choices:

- ❤️ **Friendly** — for players who are friendly and enjoyable to play with
- ⚔️ **Teamwork** — for players who cooperate well with others
- 🛏️ **Helpful** — for players who help other players

Select one of the three options to commend the player.

A player cannot commend themselves and can only commend the same player once every 24 hours.

## View a Player's Commendations

Enter:

```text
/commendprofile <player>
```

For example:

```text
/commendprofile Steve
```

The plugin displays how many commendations the player has received:

```text
Friendly: 5
Teamwork: 3
Helpful: 2
Total Commend: 10
```

Profiles can still be viewed when the player is offline.

## Example

```text
Player A plays with Player B
            ↓
Player A enters /commend PlayerB
            ↓
      Commend Menu Opens
            ↓
   ┌─────────────────────┐
   │ Friendly            │
   │ Teamwork            │
   │ Helpful             │
   └─────────────────────┘
            ↓
    Player A selects
        "Teamwork"
            ↓
Player B receives +1 Teamwork
            ↓
     Result is saved
```

## Screenshots

### Commend Menu

> Screenshot coming soon.

### Commend Profile

> Screenshot coming soon.

## Features

- Three commendation types: Friendly, Teamwork, and Helpful
- Interactive Minecraft inventory GUI
- 24-hour cooldown between the same two players
- Self-commendation prevention
- Persistent commendation data after server restarts
- Persistent cooldown data after server restarts
- Player commendation profiles
- Offline player profile lookup
- Server console support for profile lookup

---

# Technical Details

MineCommend was built as a Java project using the Paper API.

## Tech Stack

- Java 21
- Paper API 1.21.4
- Maven
- YAML

## How It Works Internally

The project separates user commands, GUI events, game rules, and data storage into different components.

```text
Player
  │
  │ /commend <player>
  ▼
CommendCommand
  │
  │ Opens GUI
  ▼
CommendMenuListener
  │
  │ Player selects a commend type
  ▼
CommendManager
  │
  │ Validates rules and cooldown
  ▼
DataManager
  │
  │ Saves data
  ▼
data.yml
```

### Project Structure

```text
src/main/java/com/kenta/minecommend/
├── MineCommend.java
├── CommendCommand.java
├── CommendProfileCommand.java
├── CommendMenuListener.java
├── CommendManager.java
└── DataManager.java
```

**MineCommend.java**  
Starts the plugin and connects the commands, managers, and event listeners.

**CommendCommand.java**  
Handles `/commend <player>` and opens the commend menu.

**CommendProfileCommand.java**  
Handles `/commendprofile <player>` and displays commendation statistics.

**CommendMenuListener.java**  
Handles player interactions with the commend menu.

**CommendManager.java**  
Handles commendation rules and the 24-hour cooldown.

**DataManager.java**  
Stores commendation counts and cooldown data.

Player UUIDs are used as persistent player identifiers.

## Installation

### Requirements

- Java 21
- Paper 1.21.4 server

### Build

Clone the repository and run:

```bash
mvn package
```

The compiled plugin JAR will be generated in:

```text
target/
```

Copy the JAR file into your Paper server's:

```text
plugins/
```

Then start or restart the server.

The plugin is now ready to use:

```text
/commend <player>
```

## Project Documentation

This repository also contains development documentation:

- `SPEC.md` — plugin requirements and expected behavior
- `KNOWLEDGE.md` — current architecture and implementation
- `SKILLS.md` — development and code-change guidelines

## Future Improvements

- Improved GUI design
- Better visual presentation for player profiles
- Stronger GUI identification instead of relying on inventory titles
- Additional customization options for server administrators

## Status

**MineCommend V1.0**

The core commendation system, GUI, cooldown system, persistent storage, and player profiles are implemented and working.