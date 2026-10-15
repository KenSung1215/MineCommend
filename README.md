# MineCommend

**A Minecraft plugin that lets players recognize and appreciate each other for positive behavior.**

Have you ever played Minecraft with someone who was especially friendly, helpful, or a great teammate?

MineCommend gives players a simple way to show appreciation.

Players can commend others in three categories:

- ❤️ **Friendly** — For players who are kind and enjoyable to play with.
- ⚔️ **Teamwork** — For players who cooperate and work well with others.
- 🛏️ **Helpful** — For players who support and assist others.

Each commendation contributes to the player's profile, allowing everyone to see the positive recognition they have received.

---

## How to Use

### 1. Commend Another Player

Type the following command in Minecraft chat:

```text
/commend <player>
```

For example:

```text
/commend Steve
```

A menu will appear with three options:

**Friendly | Teamwork | Helpful**

Simply click one of the options to commend the player.

**Important rules:**
- The player you want to commend must be online.
- You cannot commend yourself.
- You can only commend the same player once every 24 hours, regardless of commendation type.

### 2. View a Player's Commendations

Want to see how many commendations someone has received?

Type:

```text
/commendprofile <player>
```

For example:

```text
/commendprofile Steve
```

You will see something like this:

```text
Friendly: 5
Teamwork: 3
Helpful: 2
Total Commend: 10
```

You can also view a player's profile when they are offline.

### Example: How a Commendation Works

```text
You play Minecraft with Steve
              |
              v
     Type /commend Steve
              |
              v
       Commend Menu Opens
              |
              v
   Choose Friendly, Teamwork,
          or Helpful
              |
              v
    Steve receives +1 commend
      in the selected category
              |
              v
       The result is saved
              |
              v
  View it with /commendprofile Steve
```

---

## Getting Started

MineCommend is designed for Minecraft Java Edition servers running **Paper 1.21.4**.

### Installation

You don't need to know Java or how to write code to use MineCommend.

1. **Download** the plugin from the [latest MineCommend release](https://github.com/KenSung1215/MineCommend/releases/latest). Under **Assets**, select the `.jar` file.
2. **Install** it by placing the downloaded `.jar` file into your Minecraft server's `plugins` folder.
3. **Restart** your Minecraft server.

That's it! MineCommend is ready to use.

Join your server and type:

```text
/commend Steve
```

**Requirements:**
- Minecraft Java Edition
- Paper 1.21.4 server
- Java 21

---

## Features

- **Player Recognition** — Commend other players for positive behavior.
- **Three Categories** — Friendly, Teamwork, and Helpful.
- **Interactive GUI** — Select commendations using a Minecraft inventory menu.
- **Cooldown Protection** — Prevents repeated commendations to the same player within 24 hours.
- **Self-Commendation Prevention** — Players cannot commend themselves.
- **Persistent Data** — Commendations and cooldowns remain saved after server restarts.
- **Player Profiles** — View individual and total commendation counts.
- **Offline Profile Lookup** — View profiles even when players are offline.
- **Console Support** — Server administrators can view profiles through the server console.

---

# Technical Details

This section explains how MineCommend was built and is intended for developers or anyone interested in the implementation.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java 21 | Main programming language |
| Paper API 1.21.4 | Minecraft server plugin development |
| Maven | Project build and dependency management |
| YAML | Persistent storage for commendations and cooldowns |

## Architecture

MineCommend separates command handling, GUI interactions, business rules, and data storage into different components.

```text
           Minecraft Player
                  |
                  | /commend <player>
                  v
           CommendCommand
                  |
                  | Opens the GUI
                  v
          Commend Menu
                  |
                  | Player selects a category
                  v
        CommendMenuListener
                  |
                  | Sends commend request
                  v
           CommendManager
                  |
                  | Checks commendation rules
                  | and 24-hour cooldown
                  v
             DataManager
                  |
                  | Saves commendation counts
                  | and cooldown timestamps
                  v
               data.yml
```

### Component Responsibilities

| Component | Responsibility |
|---|---|
| `MineCommend.java` | Initializes the plugin, commands, managers, and event listeners |
| `CommendCommand.java` | Validates the command and opens the commendation menu |
| `CommendMenuListener.java` | Handles GUI interactions and commendation selections |
| `CommendManager.java` | Handles commendation rules and cooldown validation |
| `DataManager.java` | Saves and retrieves commendation counts and cooldown data |
| `CommendProfileCommand.java` | Retrieves and displays player commendation statistics |

### Data Persistence

MineCommend stores player commendation data in `data.yml`.

- Player UUIDs are used instead of player names for persistent identification.
- Each commendation category is tracked separately.
- Cooldown timestamps are stored for each sender-target pair.
- Data remains available after the server restarts.

## Project Structure

```text
MineCommend/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/kenta/minecommend/
│       │       ├── MineCommend.java
│       │       ├── CommendCommand.java
│       │       ├── CommendProfileCommand.java
│       │       ├── CommendMenuListener.java
│       │       ├── CommendManager.java
│       │       └── DataManager.java
│       └── resources/
│           └── plugin.yml
├── README.md
├── SPEC.md
├── KNOWLEDGE.md
├── SKILLS.md
└── pom.xml
```

## Build from Source

For developers who want to build MineCommend themselves:

1. Install Java 21 and Maven.
2. Clone or download this repository.
3. Open a terminal in the project directory.
4. Run:

```bash
mvn package
```

The compiled plugin will be generated in the `target/` directory.

Copy the generated `.jar` file into your Paper server's `plugins/` folder and restart the server.

---

## Project Documentation

This repository includes additional documentation:

- **[SPEC.md](SPEC.md)** — Defines the plugin's functional requirements and expected behavior.
- **[KNOWLEDGE.md](KNOWLEDGE.md)** — Documents the current architecture and implementation.
- **[SKILLS.md](SKILLS.md)** — Defines the development workflow and code-change guidelines.

## Future Improvements

Potential improvements for future versions:

- More polished GUI design and visual effects.
- Improved presentation of player commendation profiles.
- More robust GUI identification and event handling.
- Additional customization options for server administrators.

## Project Status

**MineCommend v1.0.0 — First Release**

The core commendation system has been implemented and tested, including:

- Player commendations through an interactive GUI
- 24-hour cooldown validation
- Persistent data storage
- Player commendation profiles
- Offline profile lookup

**[Download the latest version](https://github.com/KenSung1215/MineCommend/releases/latest)**