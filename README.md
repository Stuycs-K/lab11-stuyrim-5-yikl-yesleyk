[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/KprAwj1n)
# APCS - Stuyrim

## Features

### Working Features
- :white_check_mark: Group of 3 different adventurers
- :white_check_mark: Play against 1-3 randomly chosen adventurer opponents
- :white_check_mark: use attack/special operations on your opponents
- :white_check_mark: program ends when user chooses to quit, or all enemies are defeated, or entire party is defeated. Win/lose screen is included.
- :white_check_mark: Displayed results of attack/special/support inside border

### Extra Working Features
- Display several past actions using a list :ballot_box_with_check: 

### Partially Working Features
-  Peach's special ability (putting a character to sleep) only works when the party uses it. :question:
 
### Not Working Features
- :x: 

### Bugging Features
- Character Deaths :beetle: 
- double logging actions sometimes :beetle: 

## Adventurer Subclasses

replace this with your documentation for your two Adventurer subclasses. If you modify or replace the provided CodeWarrior class, please provide documentation for that as well.

### Mario (18 HP)
- **Attack**: Bonk (1-3 dmg)
    + Mario whacks the target character over the head
- **Support (self)**: Bubble Barrier (Blocks 60% of incoming damage for 3 turns)
    + Mario uses a bubble on himself, blocking incoming damage
- **Support (other)**: Bubble Barrier (Blocks 60% of incoming damage for 3 turns)
    + Mario uses a bubble on the target character, blocking incoming damage to that character
- **Special Attack**: Stealing Mushrooms (consumes 1 mushroom)
    + Uses one mushroom to steal up to 2 of the target players special 
- **Special Resource**: 1-UP Mushroom (2)
### Luigi (12 HP)
- **Attack**: Exhaust Pipe Boomerang (1-3 damage, hits twice)
    + Luigi throws an exhaust pipe like a boomerang, hitting the target twice
- **Support (self)**: Mystery Box (+1 wrench)
    + Luigi opens an item box, restoring one wrench
- **Support (other)**: Hype Man (target character gets an extra move on their next turn)
    + Luigi cheers on the target character, giving them an extra move on their next tern
- **Special Attack**: Pipe Down! (6 dmg, 70% chance to hit, consumes 1 Wrench)
    + Using his handy wrench, Luigi unscrews a pipe to throw at the target character
- **Special Resource**: Wrenches (4)
### Princess Peach (14 HP)
- **Attack**: Piercing Parasol (1-2 dmg)
    + Princess Peach pierces an enemy with her colorful bright pink parasol 
- **Support (self)**: Peach's Blessing (heals 3-5 hp)
    + Princess Peach blesses herself with the power of peach nectar
- **Support (other)**:Peach's Blessing (heals 3-5 hp)
    + Princess Peach blesses another character with the power of peach nectar
- **Special Attack**: Sleepy time spell (makes the target character inactive for one turn, consumes 1 star )
    + Princess Peach uses the power of the beautiful night sky to lull the target character to sleep
- **Special Resource**: Stars (3)
### Bowser (BOSS) (35 hp)
- **Attack**: Bowser SMASH! (3-5 dmg to all adventurers on the opposing team)
    + Bowser uses his immense strength to smash the ground sending a damage dealign earthquake through the opposing players
- **Support (self)**: Bowser's Ballad (heal 1-2 hp)
    + Bowser uses the powerful singing powers of the turtle to heal himself
- **Support (other)**: N/A
    + N/A
- **Special Attack**: Shell Shocker (35% attack and healing buff 25% more vulnerable to attacks for 3 turns, consumes one shell spin)
    + Bowser crawls into his shell and spins assuming a more vulnerable state that deals more damage
- **Special Resource**: Shell spins (3)