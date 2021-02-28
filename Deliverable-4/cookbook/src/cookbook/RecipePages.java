package cookbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.Time;

/**
 * This project is the implementation of recipe database in which a 
 * user uses the program "Cookbook" to view, add, edit and delete 
 * recipes, ingredients and instructions, and suggestions.
 * 
 * SER 322
 * Team 20
 * Project Deliverable 4
 * @author - Kimberlee Gentry
 * @author - Edward Kim
 * @author - Kyle Stearns
 * @version
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
        String query5 = "Shopping List for a named recipe";
        String query6 = "Steps and Instructions for a named recipe";

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
                        + " WHERE recipe.recipeName = ?;");
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
	 * ingredient, add a suggestion, and add an instruction to a recipe.
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
        String colServings;

        do {
            System.out.println("Select a column to insert data into: ");
            System.out.println("1. Recipe, 2. Ingredients, 3. Instructions");
            while (!scan.hasNextInt()) {
                System.out.println("Bad!");
                scan.next();
            }
            integer = scan.nextInt();
        } while (integer < 1 || integer > 9);

        System.out.println("Good!" + "\n");
        try {
            stmt = conn.createStatement();
            switch (integer) {
                case 1:// recipe table
                    System.out.println("INSERT INTO RECIPE VALUES (recipeid, recipeName, category, description, servings, totalTime, prepTime, cookTime, totalCalories, classificationID)");
					System.out.println("Please enter the [recipeid] of recipe: ");
                    colInt4 = scan.nextInt();
                    System.out.println("Please enter the [recipeName] of recipe: ");
                    colStr1 = scan.next();
                    System.out.println("Please enter the [category] of the recipe: ");
                    colStr2 = scan.next();
                    System.out.println("Please enter a brief [description]: ");
                    colStr3 = scan.next();
                    System.out.println("Please enter the [servings] count: ");
                    colInt1 = scan.nextInt();
                    System.out.println("Please enter the [preparation time] needed to make the recipe: ");
                    colTim1 = scan.next();
                    System.out.println("Please enter the [total time] needed to make the recipe: ");
                    colTim2 = scan.next();
                    System.out.println("Please enter the [cook time] needed to make the recipe: ");
                    colTim3 = scan.next();
                    System.out.println("Please enter the [total calories]: ");
                    colInt2 = scan.nextInt();
                    System.out.println("Please enter the [classificationID]: ");
                    colInt3 = scan.nextInt();
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
                        System.out.println("Successful.");
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 2:// ingredients table
                    System.out.println("INSERT INTO INGREDIENTS VALUES (recipeID, foodGroup, unitOfMeasure, caloricContent)");
                    System.out.println("Please enter the [recipe identification number]: ");
                    colStr1 = scan.next();
                    System.out.println("Please enter the [foodGroup] of the ingredient: ");
                    colStr2 = scan.next();
                    System.out.println("Please enter the [unit of measurement] of the ingredient that is to be used: ");
                    colStr3 = scan.next();
                    System.out.println("Please enter the amount of [calories] in the ingredient: ");
                    colInt1 = scan.nextInt();
                    ps = conn.prepareStatement("INSERT INTO INGREDIENTS VALUES (?, ?, ?, ?);");
                    ps.setString(1, colStr1);
                    ps.setString(2, colStr2);
                    ps.setString(3, colStr3);
                    ps.setInt(4, colInt1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Successful.");
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 3:// instructions table
                    System.out.println("INSERT INTO INSTRUCTIONS VALUES (recipeID, instructionID, step, text)");
                    System.out.println("Please enter the [recipe identification number] ");
                    colInt1 = scan.nextInt();
                    System.out.println("Please enter the [instructionID] number: ");
                    colInt2 = scan.nextInt();
					System.out.println("Please enter a [step] description: ");
                    colInt3 = scan.nextInt();
                    System.out.println("Please enter a [text] description: ");
                    colStr1 = scan.next();
                    ps = conn.prepareStatement("INSERT INTO INSTRUCTIONS VALUES (?, ?, ?, ?)");
                    ps.setInt(1, colInt1);
                    ps.setInt(2, colInt1);
                    ps.setInt(3, colInt1);
                    ps.setString(4, colStr1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Successful.");
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
	 * The DELETE statements allow the user to delete a recipe, delete an
	 * ingredient, delete a suggestion, and delete an instruction from a 
	 * recipe.
	 * 
     * @param scan
     */
    private static void deleteQ(Scanner scan) {
        int integer = 0;
        String colStr1;
        int colInt1;
        
        do {
            System.out.println("Select a table to DELETE a tuple from: ");
            System.out.println("1. RECIPE, 2. INGREDIENTS, 3. SUGGESTION, 4. INSTRUCTIONS");
            while (!scan.hasNextInt()) {
                System.out.println("Error!");
                scan.next();
            }
            integer = scan.nextInt();
        } while (integer < 1 || integer > 4);
        //System.out.println("Success!" + "\n");
        try {
            stmt = conn.createStatement();
            switch (integer) {
				// case 1:// delete username
                	// System.out.println("DELETE FROM USER WHERE userID = ? AND username = ?)");
                    // System.out.println("Please enter userID: ");
                    // colInt1 = scan.nextInt();
                    // System.out.println("Please enter username: ");
                    // colStr1 = scan.next();
                    // ps = conn.prepareStatement("DELETE FROM USER WHERE USER.userID = ? AND USER.username = ?");
                    // ps.setInt(1, colInt1);
                    // ps.setString(2, colStr1);
                    // if (ps.executeUpdate() > 0) {
                        // System.out.println("Success!");
                    // }
                    // ps.close();
                    // conn.commit();
                    // break;
                case 1:// delete recipe
					System.out.println("Test recipeIDs include: 1. Omelette, 2. Roast Broccoli, 3. Avacado on Toast, "
					+ "4. Ham and Cheese Sandwich, and 5. Pan Seared Salmon\n");
                	System.out.println("DELETE FROM RECIPE WHERE recipeID = ?)");
                    System.out.println("Please enter recipeID number: ");
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
                    System.out.println("DELETE FROM INGREDIENTS WHERE ingredientsName = ?");
                    System.out.println("Please enter ingredientsName: ");
                    colStr1 = scan.next();
                    ps = conn.prepareStatement("DELETE FROM INGREDIENTS WHERE INGREDIENTS.ingredientsName = ?");
					ps.setString(1, colStr1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! The ingredient, " + colStr1 + ", has been deleted from the INGREDIENTS table." );
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 3:// delete suggestion
					System.out.println("Test suggestionIDs include: 1, 2, 3, 4, 5\n");
                    System.out.println("DELETE FROM SUGGESTION WHERE suggestionID = ?");
                    System.out.println("Please enter suggestionID: ");
                    colStr1 = scan.next();
                    ps = conn.prepareStatement("DELETE FROM SUGGESTION WHERE suggestionID = ?");
                    ps.setString(1, colStr1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success! " + colStr1 + " has been deleted from the SUGGESTION table.");
                    }
                    ps.close();
                    conn.commit();
                    break;
                case 4:// delete instruction
                    System.out.println("DELETE FROM INSTRUCTIONS WHERE instructionID = ?");
                    System.out.println("Please enter instructionID: ");
                    colInt1 = scan.nextInt();
                    ps = conn.prepareStatement("DELETE FROM INSTRUCTIONS WHERE INSTRUCTIONS.instructionID = ?");
                    ps.setInt(1, colInt1);
                    if (ps.executeUpdate() > 0) {
                        System.out.println("Success!");
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
