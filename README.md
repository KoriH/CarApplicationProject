# Car Database Application

## Car details tracker

#### *Kori Huen*

My application will track information of a car within the database,
allowing them to add or delete new cars and load previous databases
or save them to be opened later. The application's interface will let the 
user view the information of a particular vehicle in the database.  
It will also be possible to add, delete, or update the information
of a car in the database where an arbitrary number of cars may exist.

Car service centres, dealerships, mechanics, and car owners
will use this application to track the details and information
of the vehicles. It will also be useful for people shopping
for cars to see the history and details of a car. Moreover,
car owners can use this application to find out where their
car was last serviced and when it's due again.

This project interests me because I am interested in cars
and the maintenance of cars is a big portion of owning cars.
Taking proper care of a vehicle will allow it to last longer
and run more efficiently. Creating this application will allow
me to work on something that I am interested in as well as let
me create a program that can help individuals and corporations
in car ownership and maintenance.

## User Stories:

- As a user, I want to be able to add a car entry to the database
- As a user, I want to be able to delete a car entry from the database
- As a user, I want to be able to replace a car entry from the database
- As a user, I want to be able to see what the car's in the database
- As a user, I want to be able to save changes to the database
- As a user, I wish to be able to load a save file and open it 

## Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by pressing the add button and 
  entering values to create a new car and adding it to the list
- You can generate the second required event related to adding Xs to a Y by pressing the remove button and
  this will remove the selected element from the list
- You can locate my visual component by pressing the add car button
- You can save the state of my application closing the application and saying yes to the prompt
- You can reload the state of my application by starting the application and saying yes to the prompt

## Phase 4: Task 2

Thu Dec 01 22:40:52 PST 2022
Added car to list: 2020 Honda Civic


Thu Dec 01 22:41:09 PST 2022
Added car to list: 2022 Toyota Corolla


Thu Dec 01 22:41:16 PST 2022
Added car to list: 2019 Hyundai Elantra


Thu Dec 01 22:41:18 PST 2022
Removed car at index: 3

## Phase 4: Task 3

Refactoring reflection: 

- Split my processInput method in the CarDatabaseApplication class so that the method isn't overwhelmed
by the switch statement as it is very long.
- Remove dependency of GUI class on AddCarWindow to make the entire class self-contained. Or create a 
GUI package so that the GUI class is only calling methods so that it only does one thing and the
GUI package will have the classes to perform the actions of the current GUI class
- Make it so that the GUI and CarDatabaseApplication have the same functionality