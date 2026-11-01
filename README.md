# CENG 443 — Object-oriented programming languages and systems

Two Java assignments for **CENG 443 Introduction to Object-Oriented Programming Languages and Systems** at
METU: one on design patterns, one on concurrency. Each folder has the assignment PDF, the source and a short
`readme.txt`.

## Assignment 1 — Knight battle simulation (design patterns)

A Swing simulation in which two teams, El Morad and Karus, field Rogues, Priests and Mages that move around a
battlefield and fight; the window tracks each team's score, kills and damage. Each class
has its own health, damage and special skill. The assignment is about structuring this with design patterns:

| Pattern | Where |
|---|---|
| **Factory Method** | `KnightFactory` with `RogueFactory`, `PriestFactory`, `MageFactory` |
| **Strategy** | `Strategy` with `AttackClosest`, `AttackWeakest`, `RandomMove`, switched at runtime |
| **Decorator** | `KnightDecorator` with `Noob`, `Grade3`, `Grade2`, `Grade1`, which draw a badge for the knight's score |
| **Singleton** | `Simulation` and `Display` (the window) |

There is also a UML class diagram (`umldiagram.mdj` and `.pdf`).

**Run:** compile the files in `Assignment 1/Source/` and run `Simulation`.

## Assignment 2 — Musical chairs (concurrency)

The game of musical chairs, with every student as its own thread. A hand-written `Barrier` keeps the rounds in
step: round begins, music starts, music stops, players settle, results are reported. It starts with one
chair fewer than students, loops `Drum.wav` as the music, and plays rounds until a single student wins; each
student then reports the chairs they sat on.

**Run:** compile the files in `Assignment 2/Source/` and run `Teacher` with the student names as arguments:

```bash
java Teacher Alice Bob Carol Dave
```
