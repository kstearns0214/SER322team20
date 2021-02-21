select *
from recipe
inner join has_ingredients on has_ingredients.recipeID = recipe.recipeID;
