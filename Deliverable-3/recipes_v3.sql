create schema recipes;

create table recipes.recipe(
recipeID             INT           NOT NULL    AUTO_INCREMENT,
RecipeName           VARCHAR(25)   NOT NULL,
Category             VARCHAR(25)   NOT NULL,
description          VARCHAR(250)  NOT NULL,
Servings             INT,
total_time           TIME,
prep_time            TIME,
cook_time            TIME,
total_calories       INT, 
primary key(recipeID)
);

create table recipes.ingredients(
Name                 VARCHAR(25)   NOT NULL,
FoodGroup            VARCHAR(25)   NOT NULL,
unit_of_measurement  VARCHAR(25)   NOT NULL,
caloric_content      INT,
primary key(Name)
);

create table recipes.instructions(
instructionID        INT           NOT NULL    AUTO_INCREMENT,
Step                 INT           NOT NULL    AUTO_INCREMENT,
Text                 VARCHAR(250)  NOT NULL,
primary key(instructionID)
);

create table recipes.review(
reviewID            INT            NOT NULL    AUTO_INCREMENT,
reviewer_name       VARCHAR(25)    NOT NULL,
rating              INT(5),
comment             VARCHAR(250)   NOT NULL,
primary key(reviewID)
);

create table recipes.suggestion(
suggestionID        INT            NOT NULL    AUTO_INCREMENT,
upVote              INT(5)         NOT NULL,
Category            VARCHAR(25)    NOT NULL,
Text                VARCHAR(250)   NOT NULL,
primary key(suggestionID)
);

create table recipes.user(
userID              INT            NOT NULL    AUTO_INCREMENT,
user_name           VARCHAR(25)    NOT NULL,
password            VARCHAR(25)    NOT NULL,
primary key(userID)
);
