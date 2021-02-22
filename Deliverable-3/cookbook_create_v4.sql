create schema cookbook;

create table cookbook.cookbook(
cookbookID           INT           NOT NULL   AUTO_INCREMENT,   
primary key(cookbookID)   
);

create table cookbook.category(
classificationID     INT           NOT NULL,
primary key(classificationID)
);

create table cookbook.recipe(
recipeID             INT           NOT NULL		AUTO_INCREMENT,
name                 VARCHAR(25)   NOT NULL,
category             VARCHAR(25)   NOT NULL,
description          VARCHAR(250)  NOT NULL,
servings             INT,
totalTime            TIME,
prepTime             TIME,
cookTime             TIME,
totalCalories        INT, 
classificationID 	INT		NOT NULL,
primary key(recipeID),
foreign key(classificationID) references category(classificationID) on delete cascade on update cascade
);

create table cookbook.ingredients(
name                 VARCHAR(25)   NOT NULL,
foodGroup            VARCHAR(25)   NOT NULL,
unitOfMeasure        VARCHAR(25)   NOT NULL,
caloricContent       INT,
primary key(name)
);

create table cookbook.suggestion(
recipeID           INT            NOT NULL,
suggestionID        INT            NOT NULL    AUTO_INCREMENT,
upVote              INT(5)         NOT NULL,
category            VARCHAR(25)    NOT NULL,
text                VARCHAR(250)   NOT NULL,
primary key(suggestionID),
foreign key(recipeID) references recipe(recipeID) on delete cascade on update cascade
);

create table cookbook.instructions(
recipeID             INT           NOT NULL,
instructionID        INT           NOT NULL		AUTO_INCREMENT,
step                 INT           NOT NULL,
text                 VARCHAR(250)  NOT NULL,
primary key(instructionID),
foreign key(recipeID) references recipe(recipeID) on delete cascade on update cascade
);

create table cookbook.has_ingredients(
recipeID             INT           NOT NULL,
name                 VARCHAR(25)   NOT NULL,
amount               INT           NOT NULL,
primary key(name, recipeID), /*updated to both, required for ingredients to be standalone*/
foreign key(recipeID) references recipe(recipeID) on delete cascade on update cascade,
foreign key(name) references ingredients(name) on delete cascade on update cascade
);
/*cascades allow for full deletion and updating*/

create table cookbook.user(
userID              INT            NOT NULL    AUTO_INCREMENT,
username            VARCHAR(25)    NOT NULL,
password            VARCHAR(25)    NOT NULL,
primary key(userID)
);