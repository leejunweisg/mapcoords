# mapcoords

A simple Minecraft plugin for saving coordinates.

## Features

- Save coordinates as named locations.
- Teleport to saved locations.
- Display a HUD that shows the current coordinates, direction and biome.

## Commands

| Command   Usage         | Description                                                                                                         |
|-------------------------|---------------------------------------------------------------------------------------------------------------------|
| `/coords delete <name>` | Delete a saved location.                                                                                            |
| `/coords help`          | Display the help message.                                                                                           |
| `/coords hud`           | Toggles the coordinates HUD.                                                                                        |
| `/coords list`          | Display a list of saved places.                                                                                     |
| `/coords save <name>`   | Save the current location.                                                                                          |
| `/coords show [name]`   | Show the coordinates of the current location.<br>If a name is provided, show the coordinates of the saved location. |
| `/coords tp <name>`     | Teleport to the saved location.                                                                                     |

## Todo

- Implement private saved locations.
    - Currently, all saved locations are global and can be modified by any player in the server.
- Add permissions support for teleporting (?)
