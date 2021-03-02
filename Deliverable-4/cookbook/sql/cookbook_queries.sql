
UPDATE recipe
SET totalCalories = 500
WHERE recipeID = 4;

Select * From recipe; /* used to check update of the fix to total amount of calories in recipeID 4 (ham and cheese sandwich)*/

/*inserts a 'French Toast' recipe*/
INSERT INTO recipe(name,category,description,servings,totalTime,prepTime,cookTime,totalCalories,classificationID)
Values ('French Toast', 'Breakfast', 'Delicious and easy to make in the morning French Toast','1', '00:10:00', '00:02:00', '00:08:00', 350, 1);

/*connects ingredients to 'French Toast' recipe*/
INSERT INTO has_ingredients(recipeID,name,amount)
Values ((SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast'), 'Egg', '150'),
((SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast'), 'Bread', '80'),
((SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast'), 'Butter', '1');

/*creates and connects instructions to 'French Toast' recipe*/
INSERT INTO instructions(recipeID, step, text)
Values ((SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast'), '1', 'Break egg in bowl'),
((SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast'), '2', 'Heat butter in pan'),
((SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast'), '3', 'Fry both sides of bread');

SELECT recipeID FROM recipe WHERE recipe.name = 'French Toast';

Select * From recipe; /* used to check if French toast got added into the recipe*/

SELECT name, totalCalories /* result is recipes that are less than 300 calories */
FROM recipe
WHERE totalCalories < 300;

SELECT name,step,text		/*Result is the steps and directions for Roast Broccoli */
FROM recipe 
INNER JOIN instructions
ON recipe.recipeID = instructions.recipeID
WHERE recipe.name = 'French Toast';

SELECT recipe.name AS recipeName, has_ingredients.name AS ingredientName, amount, unitOfMeasure		/* Result is a shopping list of ingredients needed for omelet*/
FROM has_ingredients
INNER JOIN recipe
ON recipe.recipeID = has_ingredients.recipeID
INNER JOIN ingredients
ON has_ingredients.name = ingredients.name
WHERE recipe.name = ' Omelette ';

Select COUNT(has_ingredients.name) AS NumberOfIngredients		/* Result is number of ingredients in a recipe for Omelette */
FROM has_ingredients
INNER JOIN recipe
ON recipe.recipeID = has_ingredients.recipeID
WHERE recipe.name = 'Omelet';

/*due to table construction, this delete removes all associated data with the recipe*/
delete from recipe 
where recipeID = 5; 

select * from has_ingredients;

select * from instructions;
