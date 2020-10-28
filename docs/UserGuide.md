# User Guide

## Table of Contents

1. [Introduction](#1.-introduction)
1. [Quick Start](#2.-quick-start) 
1. [Features](#3.-features)<br>
&nbsp;&nbsp;3.1. [Project `project`](#31-project-project)<br> 
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.1. [Create a new project](#311-create-project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.2. [View project information](#312-view-project-information)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.3. [List all projects](#313-list-project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.1.4. [Select project](#314-select-project)<br>
&nbsp;&nbsp;3.2. [Project Member `member`](#32-project-member-member)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.1. [Add team members](#321-add-team-members-into-project)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.2.2. [Remove team members](#322-remove-team-members-from-project)<br>
&nbsp;&nbsp;3.3. [Project Backlog `task`](#33-project-backlog-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.1. [Add tasks](#331-add-tasks)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.2. [View tasks](#332-view-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.3. [Delete tasks](#333-delete-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.4. [Change the priority of tasks](#334-change-the-priority-of-a-task)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.3.5. [Mark tasks as complete](#335-mark-task-as-complete)<br>
&nbsp;&nbsp;3.4. [Sprint `sprint`](#34-sprint-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.1. [Create a new sprint](#341-create-a-new-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.2. [View sprint information](#342-view-current-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.3. [Add tasks to the sprint](#343-add-tasks-to-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.4. [Delete tasks from the sprint](#344-remove-tasks-from-sprint)<br>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3.4.5. [Allocate tasks to team members](#345-allocate-tasks-to-team-members)<br>
1. [Command Summary](#4-command-summary)


## 1. Introduction
This document is the User Manual of the SCRUMptious.
It is intended to provide all the necessary information to use this software.  

SCRUMptious is a command line project management software, designed for project managers who adopt the
[SCRUM](https://www.scrumguides.org/scrum-guide.html) methodology.  
SCRUMptious allows project managers to do the following:  
##### Manage multiple projects
##### Breakdown projects into smaller iterations known as *Sprints*
##### Manage tasks in the form of *Backlog* items
##### Assign tasks to different *Sprint* iterations
##### Assign tasks to team members


## 2. Quick Start

1. Ensure that you have Java 11 or above installed.
1. Download the latest version of `Scrumptious.jar` from [here](http://link.to/Scrumptious.jar)
1. Move the jar file into an empty folder.
1. Ensure that you have a functional Command Line Interface (CLI).
1. Run the command `java -jar C:\Scrumptious\Scrumptious.jar`. Ensure that the `Scrumptious.jar` 
filepath is specified 
correctly according to where you copied it to.
1. Type the command `project /create -title Scrumptious -desc A java project -end 30 -sd 10` 
into the command line and press `Enter` to execute it.
1. If the setup is correct, you should see something like this:
```
Project successfully created.
```

## 3. Features 
| :memo: | Words in `<userInput>` are parameters.|
|----------------|---------------------------------------|
### 3.1. Project `project`
#### 3.1.1. Create Project
Create a new project  
##### Format: `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>`  
##### Tags:  
* `-title`: Specify the title of the project
* `-desc`: Specify the project description
* `-dur`: Specify the duration of the project. (Number of days)
* `-sd`: Specify the duration of a sprint. (Number of days)
##### Constraints: 
* All tags are to be specified when creating a new project.
##### Example: `project /create -title MeTube -desc video streaming software -dur 90 -sd 10`
* Creates a project titled MeTube which is a video streaming software. 
* The project will last for 90 days and the sprints will run on a 10 day interval.

#### 3.1.2. View project information
View project information which includes title, description, time-period and team members of the project. 
It displays the project backlog, and the details of the ongoing sprint.
##### Format: `project /view`
##### Example: `project /view`
##### Expected outcome:
 ```
====================== PROJECT ======================
[Title: MeTube ]
[Description: video streaming software ]
[No members added]
[Project will start along with the first sprint]
[Project backlog is empty]
[There are no Sprints]
=====================================================
```
  
#### 3.1.3. List Project
Outputs a list of all projects added by the user. A summary page which shows the 
project `id`, project `description` and project `title`.
##### Format: `project /list`
##### Example: `project /list`
##### Expected outcome:
```
Following are the added projects: 
  	ID Title 		Description
  	2) Scrump 	CLI Software Development Project
  	1) MeTube 	video streaming software
```

#### 3.1.4. Select Project 
Select the project to execute all commands input by user. This is generally followed by `project \list` to
get the `id` of all the projects added by the user.
##### Format: `project /select <id>`
##### Example: `project /view 2`
##### Constraints: 
* A project corresponding to `id` exists.
##### Expected outcome:
```
Project 2 has been selected.
```

### 3.2. Project Member `member`
#### 3.2.1. Add team members into project
Add team member(s) into the project.  
##### Format: `member /add <username> [<username> ...]`
##### Constraints:
* At least one username must be specified
* Username must be an alphanumeric String without spaces
##### Example: `member /add john mary`
##### Expected outcome:
```
john has been added to the project.
mary has been added to the project.
```


#### 3.2.2. Remove team members from project 
Remove team member(s) from the project
##### Format: `member /del <username> [<username> ...]`
##### Constraints:
* At least one username must be specified
* Username must be an alphanumeric String
##### Example: `member /del john mary`
##### Expected outcome:
```
john has been removed from the project.
mary has been removed from the project.
```

### 3.3. Project Backlog `task`
#### 3.3.1. Add tasks
Add a task to the project backlog.
##### Format: `task /add -title <title> -desc <description> -priority <category>`
##### Example: `task /add -title Add UI -desc add an interactive UI -priority HIGH`
* Adds a task of title `Add UI`, description `add an interactive UI` and priority `HIGH` into the backlog.
##### Constraints:
* The title, description and category must be specified, otherwise the task is not added.
* The priority entered must belong to the standard options as prescribed by the enum: [“HIGH”, “MEDIUM”, “LOW”]
* The task will be added as "not done" status. Users need to manually mark a task as done after creation.
* Tasks can only be created if a project has been created.
##### Expected outcome:
`Add UI has been added.`

#### 3.3.2. View task
Display the information of the specified task.
##### Format: `task /view <taskid>`
##### Example: `task /view 3`
* Views the third task.
##### Constraints:
* The task ID entered must be a positive integer smaller or equal to the total number of tasks added, and must be specified.
##### Expected outcome:
(Assuming the third task have the following attributes: Title: `Add parser`, Description: `add UI`, Priority: `HIGH`, Done: `false`)
```
[Task]
    ID: 1
    Add parser 
    Description: add an interactive UI 
    Priority: High priority
    Completion: Completed
 ```

#### 3.3.3. Delete task
Delete the specified task from the project backlog.
##### Format: `task /del <taskid> [<taskid>...]`
##### Example: `task /del 5 7 9`
* Deletes the fifth, seventh and ninth task.
##### Constraints:
* There must be at least one supplied task ID for deletion.
* All task ID supplied must be a positive integer smaller or equal to the total number of tasks added.
##### Expected outcome:
(Assuming the tasks have the following titles, respectively: `Add parser`, `Del UI` and `UI`)
```
The corresponding task Add parser has been removed from project.
The corresponding task Del UI has been removed from project.
The corresponding task UI has been removed from project.
```

#### 3.3.4. Change the priority of a task
Change the priority of the specified task.
##### Format: `task /priority -priority <category> -id <taskid>`
##### Example: `task /priority -priority HIGH -id 1`
* Sets the first task with a priority of HIGH, regardless of its previous priority level
##### Constraints:
* Task ID and priority level must be entered.
* The Task ID entered must be a positive integer smaller or equal to the total number of tasks added.
* The priority entered must belong to the standard options as prescribed by the enum: [“HIGH”, “MEDIUM”, “LOW”]
##### Expected outcome: (Assuming task 1 has title `Add parser`)
```
The task Add parser has its priority changed to:
High priority
```

#### 3.3.5. Mark task as complete
Mark specified task as complete.
##### Format: `task /done <taskid>`
##### Example: `task /done 1`
* Marks the first task as done.
##### Constraints:
* The Task ID must be entered.
* The Task ID entered must be a positive integer smaller or equal to the total number of tasks added.
##### Expected outcome: (Assuming task 1 has title `Add parser`)
`Add parser has been marked as done.`

### 3.4. Sprint `sprint`
#### 3.4.1. Create a new sprint
Create a new sprint for the project.
##### Format: `sprint /create -goal <goal_input> -start <start_date>`
##### Tags:
* `-goal`: Specify the goal for the sprint
* `-start`: Specify the start date of the sprint (Only used for the first sprint)
##### Constraints:
* `goal_input` must be specified
* `start_date` will only be accepted in `YYYYMMDD` format
##### Optional field:
* `start_date` will be set as the date of command execution if not specified
##### Example: `sprint /create Shopping Cart -start 20201010`
##### Expected outcome:
```
Project will start along with the newly created sprint
Project period: 2020-10-10 to 2020-11-08

============================ SPRINT =============================
[ID: 1]
[Goal: Shopping Cart ]
[Period: 2020-10-10 - 2020-10-19] 
[No allocated tasks]
=================================================================
```
  
#### 3.4.2. View current sprint
Display the information of the current sprint.
##### Format: `sprint /view`
##### Example: `sprint /view`
##### Expected outcome:
```
========================= CURRENT SPRINT ========================
[ID: 1]
[Goal: Shopping Cart ]
[Period: 2020-10-10 - 2020-10-19] 
[Remaining: 4 days]
[No allocated tasks]
=================================================================
```
  
#### 3.4.3. Add tasks to sprint
Add task(s) from the project backlog to the current sprint
##### Format: `sprint /addtask <task_id> [<task_id> ...]`
##### Constraints:
* `task_id` must be a positive integer
* At least one `task_id` must be specified
##### Example: `sprint /addtask 1 3 4`
##### Expected outcome:
```
DummyTask1  added to sprint.
DummyTask3  added to sprint.
DummyTask4  added to sprint.
```
  
#### 3.4.4. Remove tasks from sprint
Remove task(s) from the current sprint
##### Format: `sprint /deltask <task_id> [<task_id> ...]`
##### Constraints:
* `task_id` must be a positive integer
* At least one `task_id` must be specified
##### Example: `sprint /deltask 1 3 4`
##### Expected outcome:
```
DummyTask1 removed from sprint.
DummyTask3 removed from sprint.
DummyTask4 removed from sprint.
```
  
#### 3.4.5. Allocate tasks to team members
Assign a task to a team member(s)
##### Format: `sprint /assign -task <task_id> -user <user_id> [<user_id> ...]`
##### Tags:
* `-task`: Specify the task to be allocated
* `-user`: Specify the user(s) to be assign with a task
##### Constraints:
* `task_id` must be a positive integer
* `task_id` must be specified
* At least one `user_id` must be specified
##### Example: `sprint /assign -task 1 -user johntan mary jane`
##### Expected outcome:
```
DummyTask1 assigned to [johntan, mary, jane]
```

## 4. Command Summary

| **Action** | **Format** | **Examples** |
| --- | --- | --- |
| Create Project | `project /create -title <title> -desc <description> -dur <duration> -sd <sprint interval>` | `project /create -title MeTube -desc video streaming software -dur 90 -sd 10` |
| View project information | `project /view` | `project /view` |
| Select project | `project /select <id>` | `project /select 2`|
| List projects | `project /list` | `project /list`|
| Add members | `member /add <username> [<username> ...]` | `member /add john mary` |
| Remove members | `member /del <username> [<username> ...]` | `member /del john mary` |
| Add tasks | `task /add -title <title> -desc <description> -priority <category>` | `task /add -title Add UI -desc add an interactive UI -priority HIGH` |
| View task | `task /view <taskid>` | `task /view 3` |
| Delete task | `task /del <taskid> [<taskid>...]` | `task /del 5 7 9` |
| Change task priority | `task /priority -priority <category> -id <taskid>` | `task /priority -priority HIGH -id 1` |
| Mark task as complete | `task /done <taskid>` | `task /done 1` |
| Create sprint | `sprint /create -goal <goal_input> -start <start_date>` | `sprint /create Shopping Cart -start 20201010` |
| View sprint  | `sprint /view` | `sprint /view` |
| Add tasks to sprint | `sprint /addtask <task_id> [<task_id> ...]` | `sprint /addtask 1 3 4` |
| Delete tasks from sprint | `sprint /deltask <task_id> [<task_id> ...]` | `sprint /deltask 1 3 4` |
| Allocate tasks to team members | `sprint /assign -task <task_id> -user <user_id> [<user_id> ...]` | `sprint /assign -task 1 -user johntan mary jane` |
| Exit program | `bye` | `bye` |

