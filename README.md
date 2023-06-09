# IntensTask
 HR platform for adding and monitoring job candidates and their skills 
Documentation
HR platform for adding and monitoring job candidates and their skills in order to facilitate HR processes within a company
This is a Spring boot application and in the model package are the Candidate and Skill classes that form the tables in the MySql database.
There is a data.sql file in resources folder where I put 10 skills, 9 candidates, and candidate-skills connections as initial data for the database.
There is exposed functionalities through REST web services with following operations: add job candidate, add skills, update job candidate with skill, 
remove skill from job candidate, remove candidate, search candidate by name, search all candidates with given skill(s) (for e.g. Java)
I have made React UI to be functional and easy to use.
When you search candidate by name, you will get a list of candidates if there is more candidates with same name, 
and beside each candidate attributes there are buttons for direct deleting candidate, and buttons for redirection to adding and deleting skills for that candidate.
On the add skills page, you can choose from a list of skills that the candidate doesn’t yet possess.
On the delete skills page, you can choose from a list of skills that the candidate does possess.
On the search by skill page, you will get a list of candidates with a skill you choose, and beside each candidate, 
there are buttons for add new skill and delete skill. I didn't put delete button there, because there is no sense for me to have option to delete candidate when you search them by skill.
