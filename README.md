# Gravity Simulation
A small interactable simulation of gravity, where you can change attributes of different planets or starts.

## How To Run
1. Download source code
2. Open in IDE
3. Build and Run

## Controls
- **Mouse Wheel**: Zoom in to or out from the position of the mouse.
- **Dragging Mouse**: Moves the World Screen.
- **M1 Click on a SpaceObject**: Shows information about the clicked object.
- **Double M1 Click**: Hides the SpaceObject information panel.

### Space Object info Panel:
- **Name**: Name of the space object. 
- **Mass**: Mass of the space object.
- **Distance To**: States distance from the selected space object center.
- **Orbit Realative To**: Changes the path of orbit to travel with the selected space object.

### Time Management Panel:
- **Time Since Start**: Shows real time since start of the program.
- **Simulation Time**: Show simulated time that has passed inside the simulation.
- **Sim. Freq.** Changeable. Amount of calculations per simulated second. 
- **Sim. Speed** Changeable. Amount of seconds passed in simulation per a real second.

### SpaceCraft Control Panel (Instructions):
- **Thrust**: Force done during the duration. Sets thrust to max thrust if above Max thrust.
- **Direction**: Vector direction of thrust. 0° is east, 90° is north, 180° is west, 270° is south.
- **Wait**: Amount of simulation seconds before activation of the instruction.
- **Duration**: Amount of simulation seconds before deactivation of the instruction.
- **Add**: Add the instruction to a front. More Instructions are possible. After duration goes to 0 the instruction disappears.


## Configuration
Simply add your chosen space object by filling in the next line of the CSV in `res/planets.csv`.

Currently Earth and Sun have to be in the CSV. Also there is only one Spacecraft (ISS). 

Example:
```csv
name,static,mass,position_x,position_y,velocity_x,velocity_y,radius,color
Mercury,false,3.3011e23,5.791e10,0,0,47360,2.4397e6,#ff5512
```


