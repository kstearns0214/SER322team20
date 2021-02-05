Application Domain: 
Recipes / Food

Application Name: 
A simple recipe database for storing recipes, ingredients, and measurements.

Description of Database Application: 
The database application organizes recipes, including ingredients, measurements of the ingredients, cooking instructions, preparation time, 
cooking time, category, and calories.  A user will be able to open the database application, expect recipes to be complete and expect the 
above listed qualities for a recipe.  Recipes can be added, edited, listed, and deleted.

Requirements Statement:
 We organize food recipes.  The database will hold individual recipes.
- A recipe is defined by its unique ID (recipeID) to distinguish it from other recipes.  
- Recipes are made up of ingredients, the amount of each ingredient, and cooking instructions.  All recipes must have at least one ingredient and one cooking instruction.
 - An ingredient is defined by its unique ID (ingredientID) to distinguish it from other ingredients.  
- Ingredients are made up of a food group, standard unit of measurement, and caloric content.  
- The number of calories in an ingredient will be stored in the ingredientID, then derived based on the quantity used in the recipe.
- The amount of ingredient uses any of the following measurement types:  grams, pounds, teaspoons, tablespoons, cup, fluid cup or ounces.
- Cooking instructions will be organized sequentially.  The cooking instructions will start at 1 and the second instruction for the same recipe will 
be listed as 2, and so on.  Ingredients will be listed in the order in which they are first used within the cooking instructions.  For example, if a 
recipe calls for 3 cups of water, but the total amount of water used is divided in half and water is added in step 3 and step 7  -  the ingredient of 
“water” will be listed ONCE and be listed in the order of its first appearance in the recipe.
- We need to keep a database with the prep time, the cook time, total time, and the yield in servings, as well as the total calories of the dish.  
- The total time will be derived by adding the prep time and the cooking time together.
- The yield in servings will be calculated based on the calories listed in ingredientID and multiplied based on the quantity of ingredients used.
- A recipe has a category, such as breakfast, lunch, dinner, or brunch.
 


