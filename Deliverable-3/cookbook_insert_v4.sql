INSERT INTO `cookbook` VALUES
('1'),
('2'),
('3'),
('4');

INSERT INTO `category` VALUES
('1'),
('2'),
('3'),
('4');

INSERT INTO `recipe`VALUES
(1,'Omellete','Breakfast','Delicious simple omellete made in ten minutes', 1, '00:10:00', '00:05:00','00:05:00',400,1),
(2,'Roast Brocolli','Dinner','Delicious and easy side dish for dinner', 2, '00:25:00', '00:05:00','00:20:00',120, 3),
(3,'Avacado on Toast','Breakfast','Simple and hardy breakfast meal that will keep you full', 1, '00:10:00', '00:03:00','00:07:00',200,1),
(4,'Ham and Cheese Sandwich','Lunch','Simple lunch item that even your kids will love', 1, '00:05:00', '00:01:00','00:04:00',400,2),
(5,'Pan Seared Salmon','Dinner','Tender Salmon dish that will leave your mouth wanting more', 1, '00:15:00', '00:05:00','00:10:00',400,3);

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

INSERT INTO `has_ingredients` VALUES
('1', 'Egg', '150'),
('1', 'Chives', '2'),
('1', 'Butter', '1'),
('2', 'Brocolli', '300'),
('2', 'Olive Oil', '1'),
('3', 'Bread', '80'),
('3', 'Avacado', '100'),
('4', 'Cheese', '200'),
('4', 'Ham', '180'),
('5', 'Salmon', '170');

INSERT INTO `suggestion` VALUES
( '1','1','40', 'Breakfast', 'French Toast'),
( '2','2','30', 'Lunch', 'Fried chicken leg'),
( '3','3','24', 'Dinner', 'Roasted Rainbow Carrots'),
( '4','4','11', 'Breakfast', 'Eggs Benedict'),
( '5','5','9', 'Dinner', 'Ahi Poke');

INSERT INTO `instructions` VALUES
('1','1', '1', 'Get 2 eggs, salt, pepper, 1 tbsp of butter, and chopped chives'),
('1','2', '2', 'Turn on stove to medium high heat and place the pan on top'),
('1','3', '3', 'Add in the butter onto the pan once heated up'),
('1','4', '4', 'Pour the egg onto the pan and fold it inwards slowly'),
('1','5', '5', 'Once the egg is cooked fold the egg in half and add the chopped chives'),
('2','6', '1', 'Preheat the oven to 350 degree farenheit and get 300 grams of brocolli'),
('2','7', '2', 'Pour the brocolli into an oven safe pan and drizzle 1 tbsp of olive oil on top'),
('2','8', '3', 'Add salt and pepper and mix the brocolli'),
('2','9', '4', 'Place the brocolli into the oven for 20 minutes after completed take it out and enjoy'),
('3','10', '1', 'Take a slice of bread and put it into the toaster'),
('3','11', '2', 'While toasting get half an avacado and slice tit length wise'),
('3','12', '3', 'Once the toast is complete take the avacado and place it on top of the toast, using a fork to spread it'),
('3','13', '4', 'Drizzle 1/4 tbsp of olive oil on top, and salt pepper to taste'),
('4','14', '1', 'Obtain two slices of bread and place them into the toaster'),
('4','15', '2', 'While it is toasting get 6 slices of ham and 2 slices of cheese'),
('4','16', '3', 'Once the toast is complete, add the slices of ham and cheese onto one toast'),
('4','17', '4', 'Using the final toast put in top and enjoy'),
('5','18', '1', 'Turn on the stove to medium high heat to preheat the pan'),
('5','19', '2', 'While the pan is preheating, get one salmon piece and pat it dry with a paper towel to dry it out'),
('5','20', '3', 'In the pan add in 1 tbsp of oil and while the oil is heating up salt and pepper the skin side of the salmon'),
('5','21', '4', 'Place the skin side down salmon onto the pan and then salt and pepper the top of the salmon'),
('5','22', '5', 'Cook skin side down for 7 minutes and flip, then cook for an extra 3 minutes and enjoy');

INSERT INTO `user` VALUES
('1', 'CharlieFactory', 'charlie1234'),
('2', 'AbbyTabby', 'Tabby4321'),
('3', 'FrowningSmile', 'Frown123456'),
('4', 'FredRick', 'Freddyrick54321'),
('5', 'TurtleHappy', 'happyturtle321'),
('6', 'TeddyBerg', 'teddyTeddy321'),
('7', 'CuteDoggo123', 'Dogiscute123'),
('8', 'SadMummy45', 'happyMummy132');