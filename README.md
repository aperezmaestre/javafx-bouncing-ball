# JavaFX Bouncing Ball Animation

A small desktop animation project built with **Java, JavaFX and Maven** to demonstrate event-driven UI programming and animation timing.

## Features
- Continuously animates a ball horizontally using JavaFX `Timeline` and `KeyFrame` (~16 ms interval).
- Reverses direction at predefined boundaries.
- Cycles through three colors when the ball enters a new 100-pixel zone.
- Adjusts movement speed with a slider and pauses/resumes through a button.

## Tech stack
Java, JavaFX 21, Maven.

## Run
Requires JDK 17+ and Maven, with a graphical desktop session.

```bash
mvn clean javafx:run
```

The Maven launcher has been pointed to `com.lab5work.lab5.Driver` in the public package. The program demonstrates UI state, event listeners, and animation loops; it is an educational project, not a production animation engine.

## Key files
- `src/main/java/com/lab5work/lab5/NewTimelineExample.java` — animation and UI logic
- `src/main/java/com/lab5work/lab5/Driver.java` — application launcher
- `pom.xml` — JavaFX dependencies and Maven execution

## What I practiced
Event-driven programming, animation timing, GUI controls, state management, and handling boundary conditions.

## Status
Course/lab exercise. No automated test suite is included in the supplied archive.
