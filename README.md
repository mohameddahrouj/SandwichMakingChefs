# Sandwich-Making Chefs Problem

### SYSC 3303- Assignment 2
The Classical Sandwich-Making Chefs Problem
Name: Mohamed Dahrouj


### File Structure:
| Class      | Description                                                                                                                          |
|------------|--------------------------------------------------------------------------------------------------------------------------------------|
| Agent      | Represents the agent thread class which places two ingredients on the table for a chef to grab and create a sandwich                 |
| Chef       | The chef class is also a thread that creates and eats a sandwich when all ingredients are satisfied                                  |
| Ingredient | An enumerated class that stores all the ingredients required to make a sandwich                                                      |
| Runner     | A GUI that initializes the table, an agent and three chefs                                                                           |
| Sandwiches | A common resources class that stores the counter of sandwiches created (20 required)                                                 |
| Table      | The table class represents the object where there is a synchronized lock only allowing one chef to use the ingredients on the table. |

### Set Up Instructions:
A GUI has been created to show the threads executing for the agent, and three chefs.
The output has also been displayed to the console.
The main method must be started from Runner.java
