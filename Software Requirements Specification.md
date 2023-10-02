# Software Requirements Specification for Personal Studying Scheduling Application

	Version 1.1 approved

	Prepared by Vincent Serrano, Zuxin Chen, Brian Espinoza Lozano

	Farmingdale State College

	9/23/2023
## Table of Contents

Table of Contents	ii

Revision History	ii

1.	Introduction	1

  	1.1	Purpose	1

  	1.2	Document Conventions	1
	
 	1.3	Intended Audience and Reading Suggestions	1
	
 	1.4	Product Scope	1
	
 	1.5	References	1

2.	Overall Description	2
	
 	2.1	Product Perspective	2
	
 	2.2	Product Functions	2
	
 	2.3	User Classes and Characteristics	2
	
 	2.4	Operating Environment	2
	
 	2.5	Design and Implementation Constraints	2
	
 	2.6	User Documentation	2
	
 	2.7	Assumptions and Dependencies	3

4.	External Interface Requirements	3
	
 	3.1	User Interfaces	3
	
 	3.2	Hardware Interfaces	3
	
 	3.3	Software Interfaces	3
	
 	3.4	Communications Interfaces	3
5.	System Features	4
	
 	4.1	System Feature 1	4
	
 	4.2	System Feature 2 (and so on)	4
6.	Other Nonfunctional Requirements	4
	
 	5.1	Performance Requirements	4
	
 	5.2	Safety Requirements	5
	
 	5.3	Security Requirements	5
	
 	5.4	Software Quality Attributes	5
	
 	5.5	Business Rules	5
7.	Other Requirements	5

Appendix A: Glossary	5

Appendix B: Analysis Models	5

Appendix C: To Be Determined List	6


## Revision History
|Name		|Date		|Reason For Changes	|Version
|---		|---		|---			|---	
|Zuxinchen 	|9/28/2023	|change format		|1.1
|ZuXinchen      |10/1/20223     |Complete 1 and 2	|1.1
|Vincent	|10/1/2023	|Edits			|1.1


 
## 1.	Introduction
	
1.1	Purpose 

	The purpose of this document is to present a detailed description of the Personal Studying Scheduling Application.
	It will explain the purpose and features of the software, the interfaces of the software, what the software will do, 
 	and the constraints under which it must operate. This document is intended for users of the software and also potential developers.

1.2	Document Conventions

	This Document was created based on the IEEE template for System Requirement Specification Documents.

1.3	Intended Audience and Reading Suggestions

	~Typical users, made for students who would like to use the application to better schedule their time for studying.
	~Programmers that would like to implement changes to the code to improve the program to suit their needs better.

1.4	Product Scope

 	This software will be a personal scheduling system that is made for students who would to optimize
  	their efficiency by allowing the software to allocate time based on their schedule for the student to study. By doing so
   	it allows the users of the program to sufficiently allow for time to learn and retain their material
    	better.
	
1.5	References

	Not yet

## 2.	Overall Description

2.1	Product Perspective

	The program is designed to be used by anyone who would like to better optimize and schedule their time
 	by using our software to automatically allocate time to study different topics. The program was developed
  	to be used on Windows, Mac OS X, and Linux.
 
2.2	Product Functions

	Login - Allows the user to log in to their account.
 
 	Create an Account - Allows the user to create a new account.
  
  	Create an Event - Allows the user to create a singular event on the schedule. Takes the time, duration, and date of the event.
   
   	Create a Recurring Event - Allows the user to create a recurring event on the schedule. Takes the time of the event, days, 
    	duration, and how long the recurring event will last(The end date of the event).
     
     	Set Study Topics - Allows the user to set topics they would like to study for along with the amount of time they
      	would like to study this topic per week and duration (The end date of the topic).
       
       	Set user preferences - Allows the user to set the time the program is able to allocate study times for (The user is able
	to set the time they would like to be in bed and how long into their day they would like to study), preferred study times,
 	the maximum duration of study times, and the ability to customize the appearance of the GUI.
  
       	Generate - After the user is done setting their events the generate function will automatically generate study times
	based on their preferences.
 
 	Save - Saves the user's changes and writes them to the user data.
  
  	Exit - Exits the function.

2.3	User Classes and Characteristics

	~Typical users, mostly students or adults would like to better allocate time for studying.
 
 	~Programmers who are interested in working on the project by further developing it
	or fixing existing bugs.
 

2.4	Operating Environment
	
 	This software was made to run on computers. Supported operating systems are Windows, Linux, and Mac OS X.

2.5	Design and Implementation Constraints

	N/A

2.6	User Documentation

	N/A
 
2.7	Assumptions and Dependencies

	The application is developed using Java so systems using the software require Java to be installed.
 	

## 3.	External Interface Requirements

3.1	User Interfaces

	<Describe the logical characteristics of each interface between the software product and the users. This may include sample screen images, any GUI standards or product family style guides that are to be followed, screen layout constraints, standard buttons and functions (e.g., help) that will appear on every screen, keyboard shortcuts, error message display standards, and so on. Define the software components for which a user interface is needed. Details of the user interface design should be documented in a separate user interface specification.>

3.3	Software Interfaces

 	The application requires Java to be installed on the system

3.4	Communications Interfaces

 	Internet is required in order to update the application's components.

## 4.	System Features

 	This section demonstrates the application’s most prominent features and explains how they can
	be used and the results will give back to the user

4.1	Add an Event

	Allows the user to add an event to the schedule.	

4.1.1	Description and Priority

 	This feature allows the user to add events to the schedule.
 	<Provide a short description of the feature and indicate whether it is of High, Medium, or Low priority. You could also include specific priority component ratings, such as benefit, penalty, cost, and risk (each rated on a relative scale from a low of 1 to a high of 9).>

4.1.2	Stimulus/Response Sequences
	
 	<List the sequences of user actions and system responses that stimulate the behavior defined for this feature. These will correspond to the dialog elements associated with use cases.>

4.1.3	Functional Requirements
	
 	<Itemize the detailed functional requirements associated with this feature. These are the software capabilities that must be present in order for the user to carry out the services provided by the feature, or to execute the use case. Include how the product should respond to anticipated error conditions or invalid inputs. Requirements should be concise, complete, unambiguous, verifiable, and necessary. Use “TBD” as a placeholder to indicate when necessary information is not yet available.>
	
	<Each requirement should be uniquely identified with a sequence number or a meaningful tag of some kind.>
	
	REQ-1:	

	REQ-2:	

4.2	System Feature 2 (and so on)

## 5.	Other Nonfunctional Requirements

5.1	Performance Requirements

	<If there are performance requirements for the product under various circumstances, state them here and explain their rationale, to help the developers understand the intent and make suitable design choices. Specify the timing relationships for real time systems. Make such requirements as specific as possible. You may need to state performance requirements for individual functional requirements or features.>

5.2	Safety Requirements

	<Specify those requirements that are concerned with possible loss, damage, or harm that could result from the use of the product. Define any safeguards or actions that must be taken, as well as actions that must be prevented. Refer to any external policies or regulations that state safety issues that affect the product’s design or use. Define any safety certifications that must be satisfied.>

5.3	Security Requirements

	<Specify any requirements regarding security or privacy issues surrounding use of the product or protection of the data used or created by the product. Define any user identity authentication requirements. Refer to any external policies or regulations containing security issues that affect the product. Define any security or privacy certifications that must be satisfied.>

5.4	Software Quality Attributes

	<Specify any additional quality characteristics for the product that will be important to either the customers or the developers. Some to consider are: adaptability, availability, correctness, flexibility, interoperability, maintainability, portability, reliability, reusability, robustness, testability, and usability. Write these to be specific, quantitative, and verifiable when possible. At the least, clarify the relative preferences for various attributes, such as ease of use over ease of learning.>

5.5	Business Rules

	<List any operating principles about the product, such as which individuals or roles can perform which functions under specific circumstances. These are not functional requirements in themselves, but they may imply certain functional requirements to enforce the rules.>

## 6.	Other Requirements

	<Define any other requirements not covered elsewhere in the SRS. This might include database requirements, internationalization requirements, legal requirements, reuse objectives for the project, and so on. Add any new sections that are pertinent to the project.>

Appendix A: Glossary
	
 	<Define all the terms necessary to properly interpret the SRS, including acronyms and abbreviations. You may wish to build a separate glossary that spans multiple projects or the entire organization, and just include terms specific to a single project in each SRS.>

Appendix B: Analysis Models

	<Optionally, include any pertinent analysis models, such as data flow diagrams, class diagrams, state-transition diagrams, or entity-relationship diagrams.>

Appendix C: To Be Determined List

	<Collect a numbered list of the TBD (to be determined) references that remain in the SRS so they can be tracked to closure.>
