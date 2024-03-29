package cookbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

import java.sql.Time;

/**
 * This project is the implementation of recipe database in which a
 * user uses the program "Cookbook" to view, add, edit and delete
 * recipes, ingredients and instructions.
 *
 * SER 322
 * Team 20
 * Project Deliverable 4
 * 
 * @author - Kimberlee Gentry
 * @author - Edward Kim
 * @author - Kyle Stearns
 * @version 1.0 02/03/2021
 */
public class RecipePages {

    private static ResultSet rs = null;
    private static Statement stmt = null;
    private static Connection conn = null;
    private static PreparedStatement ps = null;

    /**
	 * The SELECT statements allow users to select all recipes,
	 * all recipes with a filter of a category (breakfast, lunch
	 * or dinner), select all ingredients, select some ingredients
	 * (based on food group), print out a shopping list of
	 * ingredients based on the name of the recipe, and show the
	 * steps and instructions for the name of a recipe.
	 *
     * @param scan
     */
    private static void selectQ(Scanner scan) {
		// select all recipes
        String query1 = "Show all recipes with general details";

		// select all recipes with a filter
		String query2 = "Show all recipes with a chosen category";

		// select all ingredients
		String query3 = "Show all ingredients";

		// select some ingredients
        String query4 = "Show all ingredients within in a food group";
        String query5 = "Create a shopping list for a named recipe";
        String query6 = "Show the steps and instructions for a named recipe";

        int select;
        do {
            System.out.println("Select a SELECT statement:");
            System.out.println("1. " + query1 + "\n");
            System.out.println("2. " + query2 + "\n");
            System.out.println("3. " + query3 + "\n");
            System.out.println("4. " + query4 + "\n");
            System.out.println("5. " + query5 + "\n");
            System.out.println("6. " + query6 + "\n");
            while (!scan.hasNextInt()) {
                System.out.println("Error!");
                scan.next();
            }
            select = scan.nextInt();
        } while (select < 1 || select > 6);
        //System.out.println("Success!" + "\n");

        try {
            stmt = conn.createStatement();
            switch (select) {
                case 1:// select all recipes
                    System.out.println("Recipe Name\t\tNeeded Time\tCaloriesDescription");
                    rs = stmt.executeQuery("SELECT recipe.recipeName, totalTime, totalCalories, description FROM recipe");
                    while (rs.next()) {
                        System.out.print(rs.getString(1) + "\t");
                        if (rs.getString(1).length() < 16){
                            System.out.print("\t");
                        }
                        System.out.print(rs.getString(2) + "\t");
                        System.out.print(rs.getInt(3) + "\t");
                        System.out.println(rs.getString(4));
                    }
                    break;
                case 2:// select all recipes of category
                    System.out.println("Enter name of category to filter by: ");
                    System.out.println("(Common options are Breakfast, Lunch, Dinner)");
                    String catName = scan.next();
                    ps = conn.prepareStatement("SELECT recipe.recipeName, totalTime, totalCalories, description FROM recipe"
                        + " WHERE recipe.category = ?;");
                    ps.setString(1, catName);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.print(rs.getString(1) + "\t");
                        if (rs.getString(1).length() < 16){
                            System.out.print("\t");
                        }
                        System.out.print(rs.getString(2) + "\t");
                        System.out.print(rs.getInt(3) + "\t");
                        System.out.println(rs.getString(4));
                    }
                    break;
                case 3:// select all ingredients
                    System.out.println("Name\t\tFood Group\tMeasure\tCalories");
                    rs = stmt.executeQuery("SELECT * FROM ingredients;");
                    while (rs.next()) {
                        System.out.print(rs.getString(1) + "\t");
                        if (rs.getString(1).length() < 8){
                            System.out.print("\t");
                        }
                        System.out.print(rs.getString(2) + "\t");
                        if (rs.getString(2).length() < 8){
                            System.out.print("\t");
                        }
                        System.out.print(rs.getString(3) + "\t");
                        System.out.println(rs.getInt(4));
                    }
                    break;
                case 4:// select ingredients by foodGroup
                    System.out.println("Enter name of Food Group to filter by: ");
                    System.out.println("(Common options are Dairy, Grain, Protein, Vegetable)");
                    String foodGroup = scan.next();
                    ps = conn.prepareStatement("SELECT * FROM ingredients"
                       + " WHERE ingredients.foodGroup = ?;");
                    ps.setString(1, foodGroup);
                    System.out.println("Name\t\tFood Group\tMeasure\tCalories");
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        System.out.print(rs.getString(1) + "\t");
                        if (rs.getString(1).length() < 8){
                            System.out.print("\t");
                        }
                        System.out.print(rs.getString(2) + "\t");
                        if (rs.getString(2).length() < 8){
                            System.out.print("\t");
                        }
                        System.out.print(rs.getString(3) + "\t");
                        System.out.println(rs.getInt(4));
                    }
                    break;
                case 5:// get shopping list
                    System.out.println("Enter name of recipe to shop for: ");
                    scan.nextLine();
                    String shopName = scan.nextLine();
                    ps = conn.prepareStatement("SELECT recipe.recipeName AS recipeName, has_ingredients.ingredientsName"
                        + " AS ingredientsName, amount, unitOfMeasure"
                        + " FROM has_ingredients"
                        + " INNER JOIN recipe"
                        + " ON recipe.recipeID = has_ingredients.recipeID"
                        + " INNER JOIN ingredients"
                        + " ON has_ingredients.ingredientsName = ingredients.ingredientsName"
                        + " WHERE recipe.recipeName = ?;");
                    ps.setString(1, shopName);
                    rs = ps.executeQuery();
                    System.out.println("Ingredient Name\tAmount\tUnit\t");
                    while (rs.next()) {
                        System.out.print(rs.getString(2) + "\t\t");
                        System.out.print(rs.getInt(3) + "\t");
			            System.out.println(rs.getString(4) + "\t");
                    }
                    break;
                case 6:// get instructions
                    System.out.println("Enter name of recipe to get instructions for: ");
                    scan.nextLine();
                    String insName = scan.nextLine();
                    ps = conn.prepareStatement("SELECT recipeName,step,text2"
                        + " FROM recipe"
                        + " INNER JOIN instructions"
                        + " ON recipe.recipeID = instructions.recipeID"
                        + " WHERE recipe.recipeName = ?"
                        + " ORDER BY step;");
                    ps.setString(1, insName);
                    rs = ps.executeQuery();
                    System.out.println("Step\tDirections");
                    while (rs.next()) {
                        System.out.print(rs.getInt(2) + "\t");
                        System.out.println(rs.getString(3));
                    }
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getUserInput();
    }

    /**
	 * The INSERT statements allow the user to add a recipe, add an
	 * ingredient, and add instructions with steps to a recipe.
	 *
     * @param scan
     */
    private static void insertQ(Scanner scan) {
        int integer = 0;
        String colStr1;
        String colStr2;
        String colStr3;
        String colTim1;
        String colTim2;
        String colTim3;
        int colInt1;
        int colInt2;
        int colInt3;
		int colInt4;

        do {
            System.out.println("Select a column to insert data into: ");
            System.out.println("1. Recipe\n\n2. Ingredients\n\n3. Instructions");
            while (!scan.hasNextInt()) {
                System.out.println("Bad!");
                scan.next();
            }
            integer = scan.nextInt();
        } while (integer < 1 || integer > 4);
		
        try {
            stmt = conn.createStatement();
            switch (integer) {
                case 1:// recipe table
                    System.out.println("INSERT INTO `RECIPE` VALUES (recipeID, recipeName, category, description, servings,"
                    + "totalTime, prepTime, cookTime, totalCalories, classificationID)");
					System.out.println("The current recipeIDs of include 1, 2, 3, 4, and 5. Please insert an integer larger than 5.");
					System.out.println("Please enter the >recipeid< of recipe: ");
                    colInt4 = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Please enter the >recipeName< of recipe: ");
                    colStr1 = scan.nextLine();
					System.out.println("The categories of Cookbook include: Breakfast, Brunch, Lunch, Dinner.");
                    System.out.println("Please enter the >category< of the recipe: ");
                    colStr2 = scan.nextLine();
                    System.out.println("Please enter a brief >description<: ");
                    colStr3 = scan.nextLine();
                    System.out.println("Please enter the >servings< count as an integer: ");
                    colInt1 = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Please enter the >total time< needed to make the recipe(ex.XX:XX:XX hr:min:sec): ");
                    colTim1 = scan.nextLine();
                    System.out.println("Please enter the >preparation time< needed to make the recipe(ex.XX:XX:XX hr:min:sec): ");
                    colTim2 = scan.nextLine();
                    System.out.println("Please enter the >cook time< needed to make the recipe(ex.XX:XX:XX hr:min:sec): ");
                    colTim3 = scan.nextLine();
                    System.out.println("Please enter the >total calories< of recipe: ");
                    colInt2 = scan.nextInt();
                    scan.nextLine();
					System.out.println("The classificationIDs of Cookbook include: 1 is Breakfast, 2 is Lunch, 3 is Dinner, 4 is Brunch ");
                    System.out.println("Please enter the >classificationID< as an integer: ");
                    colInt3 = scan.nextInt();
                    scan.nextLine();
                    ps = conn.prepareStatement("INSERT INTO RECIPE VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					ps.setInt(1, colInt4);
                    ps.setString(2, colStr1);
                    ps.setString(3, colStr2);
                    ps.setString(4, colStr3);
                    ps.setInt(5, colInt1);
                    ps.setString(6, colTim1);
                    ps.setString(7, colTim2);
                    ps.setString(8, colTim3);
                    ps.setInt(9, colInt2);
                    ps.setInt(10, colInt3);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! The recipe " + colStr1 + " has been added to the RECIPE table with the id of " 
                        		+ colInt4 + ",\n the category of " + colStr2 
                        		+ ", the description of " + colStr3 
                        		+ ",\n and the servings count of " + colInt1 
                        		+ ". The time needed to make the recipe is " + colTim1 
                        		+ ".\n The time needed to prepare is " + colTim2 
                        		+ ", and the time needed to cook is " + colTim3 
                        		+ ".\n The total calories is: " + colInt2 
                        		+ " and the classifcationID is " + colInt3 + ".");
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 2:// ingredients table
                    System.out.println("INSERT INTO `INGREDIENTS` VALUES (ingredientsName, foodGroup, unitOfMeasure, caloricContent)");
                    scan.nextLine();
                    System.out.println("Please enter the >ingredientName< of ingredient: ");
                    colStr1 = scan.nextLine();
					System.out.println("Examples of food groups include: Dairy, Oil, Grain, Protein, or Vegetable ");
                    System.out.println("Please enter the >foodGroup< of the ingredient: ");
                    colStr2 = scan.nextLine();
                    System.out.println("Please enter the >unit of measure< of the ingredient that is to be used (ex. Grams): ");
                    colStr3 = scan.nextLine();
                    System.out.println("Please enter the amount of >calories< in the ingredient per 1 serving: ");
                    colInt1 = scan.nextInt();
                    scan.nextLine();
                    ps = conn.prepareStatement("INSERT INTO INGREDIENTS VALUES (?, ?, ?, ?);");
                    ps.setString(1, colStr1);
                    ps.setString(2, colStr2);
                    ps.setString(3, colStr3);
                    ps.setInt(4, colInt1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! The ingredient " + colStr1 + " has been added to the INGREDIENTS table\n" 
                    + "with the foodGroup of " + colStr2 + ", the unit of measurement of " + colStr3 + ", and " + colInt1 + " calories.");
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 3:// instructions table
                    System.out.println("INSERT INTO `INSTRUCTIONS` VALUES (recipeID, instructionID, step, text2)");
					System.out.println("recipeID: 1 has 5 instructions.");
					System.out.println("recipeID: 2 has 4 instructions.");
					System.out.println("recipeID: 3 has 4 instructions.");
					System.out.println("recipeID: 4 has 4 instructions.");
					System.out.println("recipeID: 5 has 5 instructions.");
                    System.out.println("Please enter the >recipeID< of the recipe you are creating instructions for: ");
                    colInt1 = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Please enter the >instructionID< number (greater than 24): ");
                    colInt2 = scan.nextInt();
                    scan.nextLine();
					System.out.println("Please enter a >step< number for this recipe: ");
                    colInt3 = scan.nextInt();
                    scan.nextLine();
                    System.out.println("Please enter a >text< description: ");
                    colStr1 = scan.nextLine();
                    ps = conn.prepareStatement("INSERT INTO INSTRUCTIONS VALUES (?, ?, ?, ?)");
                    ps.setInt(1, colInt1);
                    ps.setInt(2, colInt2);
                    ps.setInt(3, colInt3);
                    ps.setString(4, colStr1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! Step number " + colInt3 + " has been added to recipeID " + colInt1 
                        		+ "\n with the instructionID of " + colInt2 + " and the description of:\n" + colStr1);
                    }
                    ps.close();
                    conn.commit();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getUserInput();
    }

    /**
	 * The EDIT statements allow the user to edit a recipe, edit an
	 * ingredient, and edit an instruction from a recipe.
	 *
     * @param scan
     */
    private static void editQ(Scanner scan)
    {
        int integer = 0;
        String colStr1;
        int colInt1;
        String selAtt = "";
        String eString1 = "";
        String target = "";
        
        do {
            System.out.println("Select a table to EDIT: ");
            System.out.println("1. RECIPE\n\n2. INGREDIENTS\n\n3. INSTRUCTIONS");
            while (!scan.hasNextInt()) {
                System.out.println("Error!");
                scan.next();
            }
            integer = scan.nextInt();
        } while (integer < 1 || integer > 3);
        try {
            stmt = conn.createStatement();
            switch (integer) {
                case 1:// edit recipe
                    target = "recipe";
                    System.out.println("Test >recipeIDs< include: 1. Omelette, 2. Roast Broccoli, 3. Avacado on Toast, "
					+ "4. Ham and Cheese Sandwich, and 5. Pan Seared Salmon\n");
                    System.out.println("Please enter >recipeID< number to edit: ");
                    colInt1 = scan.nextInt();
                    System.out.println("Please enter the attribute to be edited: ");
                    System.out.println(">recipeName<, >totalTime<, >totalCalories<, or >description<");
                    selAtt = scan.nextLine();
                    selAtt = scan.nextLine();
                    System.out.println("Please enter the change you wish to make:");
                    System.out.println("If you enter time, it should be in HH:MM:SS format.");
                    eString1 = scan.nextLine();
                    ps = conn.prepareStatement("UPDATE " + target + " SET " + selAtt + "=? WHERE RECIPE.recipeID=?");
                    ps.setString(1, eString1);
                    ps.setInt(2, colInt1);
                    ps.executeUpdate();
                    ps.close();
                    conn.commit();
                    break;
                
                case 2: 
                    target = "ingredients";
                    System.out.println("Test >ingredients< include: Avocado,, Bread, Broccoli, Butter, Cheese, Ham\n");
                    System.out.println("Please type >ingredient name< to edit: ");
                    colStr1 = scan.nextLine();
                    colStr1 = scan.nextLine();
                    System.out.println("Please type the attribute to be edited: ");
                    System.out.println(">foodGroup<, >unitOfMeasure<, or >caloricContent<");
                    selAtt = scan.nextLine();
                    System.out.println("Please enter the change you wish to make:");
                    eString1 = scan.nextLine();
                    ps = conn.prepareStatement("UPDATE " + target + " SET " + selAtt + "=? WHERE ingredients.ingredientsName = ?");
                    ps.setString(1,eString1);
                    ps.setString(2,colStr1);
                    ps.executeUpdate();
                    ps.close();
                    conn.commit();
                    break;
                case 3: 
                    target = "instructions";
                    System.out.println("Test >instructionID<s include 1-19\n");
                    System.out.println("Please type >instructionID< number to edit: ");
                    colInt1 = scan.nextInt();
                    System.out.println("Please type the attribute to be edited: ");
                    System.out.println(">recipeID<, >step<, or >text2<");
                    selAtt = scan.nextLine();
                    selAtt = scan.nextLine();
                    System.out.println("Please enter the change you wish to make.");
                    eString1 = scan.nextLine();
                    ps = conn.prepareStatement("UPDATE " + target + " SET " + selAtt + "=? WHERE instructions.instructionID = ? ORDER BY step;" );
                    ps.setInt(2,colInt1);
                    ps.setString(1,eString1);
                    ps.executeUpdate();
                    ps.close();
                    conn.commit();
                    break;
                }
                
            }catch (Exception e) {
                e.printStackTrace();
            }
            getUserInput();
    }
	
    /**
	 * The DELETE statements allow the user to delete a recipe, delete an
	 * ingredient, delete an instruction from a recipe.
	 *
     * @param scan
     */
    private static void deleteQ(Scanner scan) {
        int integer = 0;
        String colStr1;
        int colInt1;
		int colInt2;

        do {
            System.out.println("Select a table to DELETE a tuple from: ");
            System.out.println("1. RECIPE\n\n2. INGREDIENTS\n\n3. INSTRUCTIONS");
            while (!scan.hasNextInt()) {
                System.out.println("Error!");
                scan.next();
            }
            integer = scan.nextInt();
        } while (integer < 1 || integer > 3);
        //System.out.println("Success!" + "\n");
        try {
            stmt = conn.createStatement();
            switch (integer) {
                case 1:// delete recipe
					System.out.println("Test recipeIDs include: 1. Omelette, 2. Roast Broccoli, 3. Avacado on Toast, "
					+ "4. Ham and Cheese Sandwich, and 5. Pan Seared Salmon\n");
                	System.out.println("DELETE FROM RECIPE WHERE recipeID = ?;");
                    System.out.println("Please enter >recipeID< number: ");
                    colInt1 = scan.nextInt();
                    ps = conn.prepareStatement("DELETE FROM RECIPE WHERE RECIPE.recipeID = ?");
                    ps.setInt(1, colInt1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! The recipe with the recipeID of " + colInt1 + " has been deleted.");
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 2:// delete ingredients
				    System.out.println("Ingredients can be: egg, chives, butter, broccoli, olive oil, bread, avocado, "
					+ "cheese, ham or salmon.\n");
                    System.out.println("DELETE FROM INGREDIENTS WHERE ingredientsName = ?;");
                    System.out.println("Please enter >ingredientsName<: ");
                    colStr1 = scan.next();
                    ps = conn.prepareStatement("DELETE FROM INGREDIENTS WHERE INGREDIENTS.ingredientsName = ?");
					ps.setString(1, colStr1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! The ingredient, " + colStr1 + ", has been deleted from the INGREDIENTS table." );
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 3:// delete instruction
                    System.out.println("DELETE INSTRUCTIONS FROM INSTRUCTIONS WHERE instructionID = ?");
                    System.out.println("Please enter >instructionID<: ");
                    colInt1 = scan.nextInt();
                    System.out.println("INNER JOIN recipe ON (recipe.recipeID = instructions.recipeID) WHERE recipe.recipeID = ?");
                    System.out.println("Please enter >recipeID<: ");
                    colInt2 = scan.nextInt();
                    ps = conn.prepareStatement("DELETE instructions FROM instructions INNER JOIN recipe ON (recipe.recipeID = instructions.recipeID) WHERE recipe.recipeID = ? AND instructions.instructionID = ?;");
                    ps.setInt(1, colInt1);
                    ps.setInt(2, colInt2);
                    if (ps.executeUpdate() > 0) {
                    }
                    ps.close();
                    conn.commit();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        getUserInput();
    }

    /**
     * Main menu of the app.
     */
    private static void getUserInput() {
    	System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Main Menu");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("Please select from the following options:");
        System.out.println("1 - SELECT data");
        System.out.println("2 - INSERT data");
        System.out.println("3 - DELETE data");
        System.out.println("4 - UPDATE data");
        System.out.println("Q - Exit the app\n");
        try (Scanner scan = new Scanner(System.in)) {
            while (scan.hasNext()) {
                String in = scan.next();
                if (in.equals("1") || in.equalsIgnoreCase("Select")) {
                    selectQ(scan);
                } else if (in.equals("2") || in.equalsIgnoreCase("Insert")) {
                    insertQ(scan);
                } else if (in.equals("3") || in.equalsIgnoreCase("Delete")) {
                    deleteQ(scan);
                } else if (in.equals("4") || in.equalsIgnoreCase("Update")) {
                    editQ(scan);
                } else if (in.equalsIgnoreCase("Q") || in.equalsIgnoreCase("Exit")) {
                    exitProgram(scan);
                    break;
                } else {
                    System.out.println("Sorry, can't accept that input. Try again!\n");
                    getUserInput();
                }
            }
        } catch (Exception e) {
            System.out.println("Oh no! Didn't catch that. Re-input the same information please!\n");
            e.printStackTrace();
        }
    }

    /**
     * @param scan
     */
    private static void exitProgram(Scanner scan) {
        System.out.print("Toodle-loo!\n Thanks for using the Cookbook database by team 20!\n Goodbye.\n ");
        scan.close();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println("The secret ingredient is love,\n but for facts, here's a database full of "
		+ "recipes:\n the Cookbook by team 20!");
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=\n");
        System.out.print("Attempting to connect to the database.\n");
        String _url = args[0];
        try {
            Class.forName(args[3]);
            conn = DriverManager.getConnection(_url, args[1], args[2]);
            conn.setAutoCommit(false);
        } catch (ClassNotFoundException | SQLException se) {
            System.out.println("Cannot connect to the server.\n");
            se.printStackTrace();
        }
        System.out.println("Connected to the server.\n");
        try {
            getUserInput();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se) {
                System.out.println("Database resource leak!\n");
                se.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection finalized.\n");
                }
            } catch (Throwable se) {
                System.out.println("Definitely a resource leak!\n");
                se.printStackTrace();
            }
        }
    }
}
