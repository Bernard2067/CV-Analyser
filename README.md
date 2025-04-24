--------------------------------README File for CV Analyser of Student 2406530------------------------------

Welcome to my CV analyser project!

When running the project it will prompt you with 3 options
[1] Enter a new CV - Job Description comparison to be added to the database
[2] View the database to see all the users added and their scores
[3] To exit the program once done with it

-------------------------------1. Entering a new CV - Job Description comparison----------------------------

Being prompted with 1 when in the menu will take the user to the CV analysis portion

When entering the CV/resume of the applicant it will need to include the full file path (including the file
itself) which will look like the following:
C:\Users\Usr\IdeaProjects\CV analyser\Sample txtFiles\CV.txt

The same can be said for the job description txt file, an example can be seen bellow:
C:\Users\Usr\IdeaProjects\CV analyser\Sample txtFiles\JobDescription.txt

Once having input the file path (for both CV and job description) it will repeat back the same file path 
given and then ask a Y/N prompt as to whether or not it is correct. If prompted with N (no) then it will ask
for the file path again, but if prompted with Y (yes) then it will move on with the next scripted event.

After recieving both file paths and verifying that they are correct it will then analyse both txt files and
compare the two to see how compatible the CV/resume is to the given job description. After it will output
the email found in the CV/resume and provide a percentage score as to how compatible they were to the job
description

A sample CV and job description can be found in the "Sample txtFiles" folder to be used if testing the code.

--------------------------------2. Viewing the database to see results--------------------------------------

Being prompted with 2 when in the menu will result in the user being shown the SqlLite database containing
the results of the CV analysis portion of the program. 

This is meant to be an easy way to see how well different applicants were when comparing them to the job 
application description as well as see their email that was found in the CV/resume to then be contacted if
their score was ideal for the job position.

-----------------------------------------3. Exiting the program---------------------------------------------

Being prompted with 3 when in the meny section will result in the program ending, the SqlLite database is
local meaning that even if the program is closed you will still be able to view the database when next
opening the program.
