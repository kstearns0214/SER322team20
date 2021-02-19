CREATE DATABASE cookbook;
USE cookbook;

create table cookbook.cookbook(
cookbookID           INT           NOT NULL,    
primary key(cookbookID)   
);

create table cookbook.recipe(
recipeID             INT           NOT NULL		AUTO_INCREMENT,
Name                 VARCHAR(25)   NOT NULL,
category             VARCHAR(25)   NOT NULL,
description          VARCHAR(250)  NOT NULL,
servings             INT,
totalTime           TIME,
prepTime            TIME,
cookTime            TIME,
totalCalories       INT, 
primary key(recipeID),
foreign key(category) references category(classificationID)
);

create table cookbook.ingredients(
name                 VARCHAR(25)   NOT NULL,
foodGroup            VARCHAR(25)   NOT NULL,
unitOfMeasurement  VARCHAR(25)   NOT NULL,
caloricContent      INT,
primary key(name),
foreign key(recipeID) references recipe(recipeID),
foreign key(name) references ingredients(name)
);

create table cookbook.suggestion(
suggestionID        INT            NOT NULL		AUTO_INCREMENT,
upVote              INT(5)         NOT NULL,
category            VARCHAR(25)    NOT NULL,
Text                VARCHAR(250)   NOT NULL,
primary key(suggestionID)
);

create table cookbook.instructions(
recipeID             INT           NOT NULL,
instructionID        INT           NOT NULL		AUTO_INCREMENT,
Step                 INT           NOT NULL		AUTO_INCREMENT,
Text                 VARCHAR(250)  NOT NULL,
primary key(recipeID, instructionID),
foreign key(recipeID) references recipe(recipeID)
);

create table cookbook.category(
classificationID     INT           NOT NULL
);

create table cookbook.users(
userID              INT            NOT NULL		AUTO_INCREMENT,
username           VARCHAR(25)    NOT NULL,
password            VARCHAR(25)    NOT NULL,
primary key(userID)
);



INSERT INTO `cookbook` VALUES
(1);

INSERT INTO `recipe` VALUES
('1','Omellete','Breakfast','Delicious simple omellete made in ten minutes', '1', '00:10:00', '00:05:00','00:05:00','400'),
('2','Roast Brocolli','Dinner','Delicious and easy side dish for dinner', '2', '00:25:00', '00:05:00','00:20:00','120'),
('3','Avacado on Toast','Breakfast','Simple and hardy breakfast meal that will keep you full', '1', '00:10:00', '00:03:00','00:07:00','200'),
('4','Ham and Cheese Sandwich','Lunch','Simple lunch item that even your kids will love', '1', '00:05:00', '00:01:00','00:04:00','400'),
('5','Pan Seared Salmon','Dinner','Tender Salmon dish that will leave your mouth wanting more', '1', '00:15:00', '00:05:00','00:10:00','400');

INSERT INTO `ingredients` VALUES
('Egg', 'Dairy' , 'Grams', '75'),
('Olive Oil', 'Oil' , 'Tbsp', '100'),
('Bread', 'Grain' , 'Grams', '80'),
('Cheese', 'Dairy' , 'Grams', '100'),
('Ham', 'Protein' , 'Grams', '20'),
('Brocolli', 'Vegetable' , 'Grams', '60'),
('Avacado', 'Vegetable' , 'Grams', '100'),
('Salmon', 'Protein' , 'Ounces', '170'),
('Butter', 'Oil' , 'tbsp', '120'),
('Chives', 'Vegetable' , 'tbsp', '0');

INSERT INTO `instructions` VALUES
('1','1', '1', 'Get 2 eggs, salt, pepper, 1 tbsp of butter, and chopped chives'),
('2','2', '2', 'Turn on stove to medium high heat and place the pan on top'),
('3','3', '3', 'Add in the butter onto the pan once heated up'),
('4','4', '4', 'Pour the egg onto the pan and fold it inwards slowly');


INSERT INTO `suggestion` VALUES
( '40', 'Breakfast', 'French Toast'),
( '30', 'Lunch', 'Fried chicken leg'),
( '24', 'Dinner', 'Roasted Rainbow Carrots'),
( '11', 'Dinner', 'Roast Chicken'),
( '9', 'Dessert', 'Strawberry IceCream'),
( '4', 'Dinner', 'Ahi Poke'),
( '3', 'Breakfast', 'Eggs Benedict');

INSERT INTO `category` VALUES
('1'),
('2'),
('3'),
('4');

INSERT INTO `users` VALUES
('1', 'CharlieFactory', 'charlie1234'),
('2', 'AbbyTabby', 'Tabby4321'),
('3', 'FrowningSmile', 'Frown123456'),
('4', 'FredRick', 'Freddyrick54321'),
('5', 'TurtleHappy', 'happyturtle321'),
('6', 'TeddyBerg', 'teddyTeddy321'),
('7', 'CuteDoggo123', 'Dogiscute123'),
('8', 'SadMummy45', 'happyMummy132');

SELECT * FROM cookbook;