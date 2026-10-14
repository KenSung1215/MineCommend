# MineCommend Specification

## Goal

Create a Minecraft plugin that allows players to commend other players.

## Core Flow

Player enters `/commend <player>`
→ Validate the target player
→ Open the Commend GUI
→ Select a commend type
→ Save the result

## Commend Types

1. Friendly
2. Teamwork
3. Helpful

## GUI Requirements

- Use a 27-slot inventory.
- Slot 11: Friendly
- Slot 13: Teamwork
- Slot 15: Helpful
- Players cannot move or take items from the Commend GUI.

## Commend Profile

Provide the following command:

`/commendprofile <player>`

Display:

- Friendly count
- Teamwork count
- Helpful count
- Total Commend count

Profile requirements:

- Commend profiles can be viewed even when the target player is offline.
- The command can be used by both players and the server console.
- A count of `0` should still be displayed.

## Restrictions

1. Players can only commend players who are currently online.
2. Players cannot commend themselves.
3. A player can only commend the same player once every 24 hours.
4. The 24-hour restriction applies regardless of commend type.

## Data Persistence

- Commend data must persist after a server restart.
- Cooldown data must persist after a server restart.