create schema cookbook;

create table cookbook.cookbook(
cookbookID           INT           NOT NULL   AUTO_INCREMENT,   
primary key(cookbookID)   
);

create table cookbook.recipe(
recipeID             INT           NOT NULL    AUTO_INCREMENT,
name                 VARCHAR(25)   NOT NULL,
category             VARCHAR(25)   NOT NULL,
description          VARCHAR(250)  NOT NULL,
servings             INT,
totalTime            TIME,
prepTime             TIME,
cookTime             TIME,
totalCalories        INT, 
primary key(recipeID)
);

create table cookbook.ingredients(
name                 VARCHAR(25)   NOT NULL,
foodGroup            VARCHAR(25)   NOT NULL,
unitOfMeasure        VARCHAR(25)   NOT NULL,
caloricContent       INT,
primary key(name)
);

create table cookbook.suggestion(
suggestionID        INT            NOT NULL    AUTO_INCREMENT,
upVote              INT(5)         NOT NULL,
category            VARCHAR(25)    NOT NULL,
text                VARCHAR(250)   NOT NULL,
primary key(suggestionID)
);

create table cookbook.instructions(
instructionID        INT           NOT NULL    AUTO_INCREMENT,
step                 INT           NOT NULL    AUTO_INCREMENT,
text                 VARCHAR(250)  NOT NULL,
primary key(instructionID)
);

create table cookbook.category(
classificationID     INT           NOT NULL
);

create table cookbook.user(
userID              INT            NOT NULL    AUTO_INCREMENT,
username            VARCHAR(25)    NOT NULL,
password            VARCHAR(25)    NOT NULL,
primary key(userID)
);
