# SMARTY - An Exam System in Java

__Smarty__ is an online, cross-platform and user-friendly exam system application developed in JAVA.
This is a project developed with focus on conducting various types of Intra-Organisatinal exams/ assessments.

It just Requires a MySQL Server hosted on the Local Area Nework (intra-net). And then, this application can be installed (copied) on the system of candidates. Students can start the assessments just login with the crendentials provided by Administrators. 

## Features & Highlights

* Quick & Efficient scheduling of Exams
* Instant results and solutions after exams
* Easy storage and retrieval of information 
* Individual Feedback to candidates
* Result Analysis & Exam Report Generation
* Input Validation & Sanitization
* Visulalization of Evaluation Report for Instructors

## External Java dependancies used

The following extenal java dependencies are required
1. [JDBC Connector](https://dev.mysql.com/downloads/connector/j/5.1.html) - for MySQL database connection
2. [JFreeCharts](http://www.jfree.org/jfreechart/) - for result/ report visualizations 

## Tools Used

This Project is developed using Eclipse IDE.

To run project - build and execute file : `Exam System\Exam System\src\Login\User_Login.java`

## Project Description

The Project is divided into three modules, as per the various kinds of users of the platform. The functionality of each module is as follows:

* __Candidate module:__ The candidate will login to the software and appear in the examinations. He can also check his previous examinations marks and his details. Candidates can also check upcoming/ scheduled exams for their batch/group.


* __Instructor module:__ The exam questions are prepared & loaded into the application databse, using an interactive User Interface. Instructors can schedule/ prepare exams for any batch/ group they are affliated with.

* __Administrator module:__ The administrator collects all the results after successful completion of the examination and sends to the server as and when required. Administrator also has some special previleges for the system.

### Features for Candidates

* Can view the current & upcoming exams
* Can appear for Mock/Practice Test
* Can view their previous exam scores
* Can change their login crendentials
* Can view and modify their profile

### Features for Instructors

* Can schedule & prepare exams for affiliated groups
* Can Edit questions, date, time & limits for scheduled exams  
* Can do the analysis of the different types of test results.
* Can Visualize Evaluation Reports
* Can change their login crendentials
* Can view and modify their profile

### Features for Administrator: 

* The administrator has the full-fledged rights over the exam system
* Can assign/add/remove a instructor/student to a group
* Can access all the accounts details of the instructors/ students
* Can view details of a particular group
* Can Insert/delete/Edit the information of available on exam system.

## Screeshots

You can check the project screeshots in the `Screenshots` directory of this repository.

## Further Improvements Required

* Overall UI of Application can be enhanced
* Sending Automated emails results to registered Email IDs of candidates
* Exam consisting of automatic evaluation for programming(code) & subjective answers
* Data Analysis & Visulization can be improved
* Video monitoring of the student system during the exam can be done in order to verify that the correct student is giving the exam and/or also to avoid unfair activities during the exam (using image processing)
* Password retrieval/change methodology can be based on retrieval from email/otp.
* Data Validation & Verification can be Improved 
* Image Captcha can be identified for preventing misuse
* Some measures for Plagiarism Detection can be adopted