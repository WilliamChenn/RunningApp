
Figure 1: Relationships between the classes and text files

|<p></p><p></p>|
| :- |
**Functionality of each class:**

- GUI Class: Creates the main UI for users to enter data. Passes inputted data into designated text files. Data is divided by “|” in the text files to sort different categories of each run.
- Text files: ​​
- History Class: Creates the Running Log UI and takes data from the text files to create a JTable displaying the run log. Calculations of streaks and PR are also displayed in this window. 
- Data Class: Includes methods that are necessary for the functionality of the other two classes.


Figure 2 UML diagram for classes.

||
| :- |






**User Interface Prototype**

Figure 3: Main UI for entering running Data

||
| :- |

Main UI that will allow users to enter their running data.

- The Record Run button will process user inputted data and store it into a text file.





















Figure 3: Running Log UI for entering running Data: 

||
| :- |

Run Log Panel will allow users to view their previous runs as well as their PRs and Streak.

- Different PRs will be displayed based on different distances.








**Process Description**

Figure 4: Recording Runs Flowchart

||
| :- |










Figure 5: Streak-Counting System Flowchart

||
| :- |










Figure 6: PR Calculating System Flowchart

||
| :- |










Figure 7: Find longest Distance Flowchart

||
| :- |












Figure 7: Display Running Stats Flowchart

||
| :- |
- Runs are stored in different text files so that it is easier to display sorted runs on the table.
- Storing Runs in separate text files also allows Personal records to be calculated easily.







Figure 8: Writing and Retrieving Data Flowchart

||
| :- |











**Text Files**

Specific Distances:

- All files are .txt files

All Distances:

- All files are .txt files

Streak:

- streakFile.txt: stores an integer that represents the running streak.










Word Count: 192
