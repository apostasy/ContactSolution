import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class ContactSolutions {
	

	//main method calls MainMenu method on an infinite loop until the user quits
  public static void main (String args[]) throws IOException
	{
		Scanner keyboard = new Scanner(System.in);
		
    //BackUp method saves a copy of the original list incase something goes wrong
    BackUp("contacts.txt");

    //GetFile2D takes a preformatted text file and places it into a 2-dimensional array
		String[][] aryTest = GetFile2D("contacts.txt");
		
		while (true)
      	{
      		//starts the MainMenu method
          aryTest = MainMenu(aryTest);
      	}      
	}

	public static String[][] MainMenu(String[][] aryPassed) throws IOException
	{

		for (int i = 0; i <= 25; i++)
		{
			System.out.println("\n");
		}
          Scanner keyboard = new Scanner(System.in);

		System.out.println(   "JJJJJ   RR        RR  EE           EE" +
							"\nJJJJJJ   RR       RR  EE           EE" +
							"\nJJ   JJ   RR     RR    EE   EEE   EE" +
							"\nJJ   JJ    RR    RR    EE  EEEE  EE" +
							"\nJJ   JJ     RR   RR    EE EE EE EE" +
							"\nJJJJJJ       RR RR      EEE  EEEE" +
							"\nJJJJ          RRRR      EE   EEE" +
						   "\n\n  C O N T A C T   S O L U T I O N S");
		System.out.println("\n\n\n[1] Search contacts" +
              "\n[2] Add contact" +
              "\n[3] Delete contact" +
                     "\n\n[9] Save Contacts" +
                     "\n[0] Exit");

    System.out.println("Please choose an option.");

    String strOption = keyboard.nextLine();

    //defines what options you can use on the main menu
    if (strOption.equalsIgnoreCase("search") || strOption.equalsIgnoreCase("search Contacts") || strOption.equalsIgnoreCase("search Contact") || strOption.equalsIgnoreCase("View Contacts") || strOption.equalsIgnoreCase("View Contact") || strOption.equalsIgnoreCase("View appointments") || strOption.equalsIgnoreCase("View appointment") || strOption.equalsIgnoreCase("Edit") || strOption.equalsIgnoreCase("View") || strOption.equalsIgnoreCase("Edit appointments") || strOption.equalsIgnoreCase("edit appointment") || strOption.equalsIgnoreCase("Edit contacts") || strOption.equalsIgnoreCase("edit contact") || strOption.equalsIgnoreCase("add appointment") || strOption.equalsIgnoreCase("add appointments") || strOption.equalsIgnoreCase("delete appointment") || strOption.equalsIgnoreCase("delete appointments") || strOption.equalsIgnoreCase("1") || strOption.equalsIgnoreCase("(1)") || strOption.equalsIgnoreCase("[1]"))
    {
      Search(aryPassed);

    } 
    else if (strOption.equalsIgnoreCase("add") || strOption.equalsIgnoreCase("add contact") || strOption.equalsIgnoreCase("add contacts") || strOption.equalsIgnoreCase("2") || strOption.equalsIgnoreCase("(2)") || strOption.equalsIgnoreCase("[2]"))
    {
      aryPassed = addItem(aryPassed);
    }
    else if (strOption.equalsIgnoreCase("delete") || strOption.equalsIgnoreCase("delete contact") || strOption.equalsIgnoreCase("delete contacts") || strOption.equalsIgnoreCase("3") || strOption.equalsIgnoreCase("(3)") || strOption.equalsIgnoreCase("[3]"))
    {
      aryPassed = removeItem(aryPassed);
    }
    else if (strOption.equalsIgnoreCase("exit") || strOption.equalsIgnoreCase("quit") || strOption.equalsIgnoreCase("0") || strOption.equalsIgnoreCase("(0)") || strOption.equalsIgnoreCase("[0]"))
    {
      System.exit(0);

    } else if (strOption.equalsIgnoreCase("save") || strOption.equalsIgnoreCase("9") || strOption.equalsIgnoreCase("(9)") || strOption.equalsIgnoreCase("[9]"))
    {
         SaveFile(aryPassed);
    }

    return aryPassed;
  }

	//GetFile reads a designated file and writes it to a 2 dimensional array
	public static String[][] GetFile2D(String strInputFile) throws IOException
	{
		//Begins a counter to check how many lines are in a text file
		int intLineCounter = 0;

		//File takes data from the text file and scanner reads it for processing
		File file = new File(strInputFile);
		Scanner inputFile = new Scanner(file);

		
    //Counts the number of lines in the text file so we can make an appropriately sized array
		while (inputFile.hasNext())
		{
			
			if (inputFile.nextLine().equals("$%&"))
			{
				//inputFile.nextLine();
				intLineCounter++;
			}			
			
			
		}

		//Resets the Scanner so that it's looking at the first line of the text file again
		inputFile = new Scanner(file);

		//Creates a new array based on the amount of lines determined from the text file
		String[][] aryGetFile = new String[intLineCounter][17];
		
		//Writes the text file to the array
		for (int i = 0; i < intLineCounter && inputFile.hasNext(); i++)	
		{
			
			//System.out.println(i);

			for (int j = 0; j < 17 && inputFile.hasNext(); j++)
			{
				String strNextLine = inputFile.nextLine();

				//System.out.println(strNextLine);

				if (strNextLine.equals("$%&"))
				{
					i--;
					break;
				}
				
				aryGetFile[i][j] = strNextLine;

			}
		}

		//Closes and saves the input file
		inputFile.close();

		return aryGetFile;		
	}

 	//takes 2 consecutive items in the array and compares them, if they're out of order the first one
  //is sent to the end of the list
  public static String[][] ColumnSort(String[][] arySort, int intColumn)
 	{
    //System.out.println(intColumn);
    for (int i = 0; i < arySort.length - 1; i++)
    {
       if (arySort[i][intColumn].compareTo(arySort[i + 1][intColumn]) > 0 )
       {
          arySort = RowCycleUp(arySort, i);
          //System.out.println(i);
       }
       for (int j = 0; j < arySort.length - 1; j++)
       {
          if (arySort[j][intColumn].compareTo(arySort[j + 1][intColumn]) > 0 )
          {
             arySort = RowCycleUp(arySort, j);
             //System.out.println(j);
             i = 0;
             j = 0;
          }
       }
    }
    return arySort;
	}

  //Method in which columnsort throws items to the end of the array
	public static String[][] RowCycleUp(String[][] aryCycle, int intPosition)
	{
    String[] strHolder;
  	//Saves the first string into a holder
    strHolder = aryCycle[intPosition];
  
    //System.out.println(Arrays.toString(strHolder));
  				
  	//Shifts all of the contents of the array (0,1,2,3,4,5 becomes 1,2,3,4,5,5)
    for (int i = intPosition; i < aryCycle.length - 1; i++)
    {
       aryCycle[i] = aryCycle[i + 1];
    }
 
 		//Replaces the last value in the array to the held value (1,2,3,4,5,5 becomes 1,2,3,4,5,0)
        aryCycle[aryCycle.length - 1] = strHolder;
 
        return aryCycle;
 	}

	//Menu asking the user how they would like to search for the contact
  public static void Search(String[][] arySearch)
	{
   	  String strCriteria;
     	System.out.println("\nWhat is the search criteria you would like to search by?");
     	System.out.println("\n[1] Last Name \n[2] Next Appointment Date \n[3] ID Number \n[4] Group");
     	Scanner keyboard = new Scanner(System.in);
     	strCriteria = keyboard.nextLine();

     	//defines what criteria the user may search for
      while(!SearchValid(strCriteria))
     	{
     		System.out.println("Please enter a valid search criteria.");
     		strCriteria = keyboard.nextLine();
     	}

     	if (strCriteria.equalsIgnoreCase("last") || strCriteria.equalsIgnoreCase("last name") || strCriteria.equalsIgnoreCase("name") || strCriteria.equalsIgnoreCase("1"))
     	{
     		System.out.println("\nPlease enter the last name of the customer.");

        	strCriteria = keyboard.nextLine();

        	SearchBy(arySearch, 2, 1, strCriteria);

     	} else if (strCriteria.equalsIgnoreCase("date") || strCriteria.equalsIgnoreCase("appointment") || strCriteria.equalsIgnoreCase("appointment date") || strCriteria.equalsIgnoreCase("2"))
     	{
     		System.out.println("\nPlease enter the date of the next appointment.");

        	strCriteria = keyboard.nextLine();

        	SearchBy(arySearch, 15, 14, strCriteria);

     	} else if (strCriteria.equalsIgnoreCase("ID") || strCriteria.equalsIgnoreCase("ID Number") || strCriteria.equalsIgnoreCase("pin") || strCriteria.equalsIgnoreCase("3"))
     	{
  		    System.out.println("\nPlease enter the ID number of the customer.");

        	strCriteria = keyboard.nextLine();

          if(Integer.parseInt(strCriteria) < 10)
          {
            strCriteria = "0" + strCriteria;
          }

        	SearchBy(arySearch, 0, 0, strCriteria);

     	} else if (strCriteria.equalsIgnoreCase("group") || strCriteria.equalsIgnoreCase("4"))
      {
          System.out.println("\nPlease enter the group you would like to view.");

          strCriteria = keyboard.nextLine();

          SearchBy(arySearch, 1, 10, strCriteria);

      }
  }

	//Searches through the contact list with the criteria the user asked for
  public static void SearchBy(String[][] arySearch, int intSortBy, int intSearchBy, String strQuery)
	{
		//sorts the array by a preditermined key column
    arySearch = ColumnSort(arySearch, intSortBy);
		boolean booSearchCheck = false;
    Scanner keyboard = new Scanner(System.in);

		/*
      Sequential sort through the array displaying any relevant data
      if data is found the check is set to true and asks the user if they want 
      to continue with one of the listed contacts
      if no data is found it informs the user
    */
    for (int i = 0; i < arySearch.length; i++)
		{
			//System.out.println("Flag 2");

			if (arySearch[i][intSearchBy].equalsIgnoreCase(strQuery))
			{
				//System.out.println(j);
				GetInfo(arySearch, i);
				booSearchCheck = true;
			}

		}

		if (booSearchCheck == false)
		{
			System.out.println("\nNo records match that search criteria. Type 'OK' to return to the main menu.");

            String strEdit = keyboard.nextLine();
		} else
    {
      System.out.println("\n\nWould you like to view one of these contacts?");

      String strEdit = keyboard.nextLine();

      if (strEdit.equalsIgnoreCase("no"))
      {

      } else if (strEdit.equalsIgnoreCase("yes"))
      {
        System.out.println("\n\nWhich contact would you like to view? \nPlease enter ID");

        strEdit = keyboard.nextLine();

        if (Integer.parseInt(strEdit) < 10)
        {
          strEdit = "0" + strEdit;
        }

        EditContact(strEdit, arySearch);
      }
    }
	}

  //displays a preview of the contact for the user
	public static void GetInfo(String[][] aryGet, int intGet)
	{
		System.out.println(
					"\nID Number: " +
					aryGet[intGet][0] +
					"\nName: " + 
					aryGet[intGet][1] +
					", " + aryGet[intGet][2] +
					"\nNext appointment: " + aryGet[intGet][14] +
					"\nat: " + ConvertMilitary(aryGet[intGet][15])
					);
	}

	//BackUp reads a designated file and saves it to a backup incase anything goes wrong
	public static void BackUp(String strInputFile) throws IOException
	{
		String[][] aryList = GetFile2D(strInputFile);
		String strBackUpFile = strInputFile.replaceFirst(".txt", "_backup.txt");

		//PrintWriter would overwrite the file if had already existed without the FileWriter
		PrintWriter outputFile = new PrintWriter(strBackUpFile);

		for (int i = 0; i < aryList.length; i++)
		{
			for (int j = 0; j < aryList[i].length; j++)
			{
				outputFile.println(aryList[i][j]);
			}

			outputFile.println("$%&");
		}
		
		
		outputFile.close();
	}

  //defines the criteria the user can search for
  public static boolean SearchValid(String strPassed) 
  {
  	boolean booSearchValid = false;

  	if (strPassed.equalsIgnoreCase("last") || strPassed.equalsIgnoreCase("last name") || strPassed.equalsIgnoreCase("name") || strPassed.equalsIgnoreCase("1") )
  	{
  		booSearchValid = true;

  	} else if (strPassed.equalsIgnoreCase("date") || strPassed.equalsIgnoreCase("appointment") || strPassed.equalsIgnoreCase("appointment date") || strPassed.equalsIgnoreCase("2") )
  	{
  		booSearchValid = true;

  	} else if (strPassed.equalsIgnoreCase("ID") || strPassed.equalsIgnoreCase("ID Number") || strPassed.equalsIgnoreCase("pin") || strPassed.equalsIgnoreCase("3") )
  	{
  		booSearchValid = true;

  	} else if (strPassed.equalsIgnoreCase("group") || strPassed.equalsIgnoreCase("4"))
    {
      booSearchValid = true;

    }

  	return booSearchValid;
  }
    
  //converts military time to standard time for display
  public static String ConvertMilitary (String inputTime)
  {
    
    
    //ignores the input if it is blank
    if (inputTime.equals(""))
    {
      return "";
    } else 
    {
      //Converts the time that is inputted from a string to an integer
    int militaryTimeInt = Integer.parseInt(inputTime);
      //Checks if time is after 12:59
    if(militaryTimeInt > 1259)
    {
       //Formats time to 1-12 style
       militaryTimeInt = militaryTimeInt - 1200;
       String militaryTime = Integer.toString(militaryTimeInt);
       String militaryTimeStr = militaryTime;
       //Splits time in half
       int midpoint = militaryTimeStr.length() / 2;
       String firstHalf = militaryTimeStr.substring(0, midpoint);
       String secondHalf = militaryTimeStr.substring(midpoint);
       //Adds semicolon and PM
       String regularTime = (firstHalf + ":" + secondHalf + " PM");
       return regularTime;
    }
       else if(militaryTimeInt < 1300 && militaryTimeInt >= 1200)
       {
          //Coverts integer to a string that can be split in half
          String militaryTimeStr = Integer.toString(militaryTimeInt);
          //Splits string in half
          int midpoint = militaryTimeStr.length() / 2;
          String firstHalf = militaryTimeStr.substring(0, midpoint);
          String secondHalf = militaryTimeStr.substring(midpoint);
          //Adds semicolon and PM
          String regularTime = (firstHalf + ":" + secondHalf + " PM");
          return regularTime;  
       }
          else if(militaryTimeInt >= 0000 && militaryTimeInt < 0100)
          {
             militaryTimeInt = militaryTimeInt + 1200;
             String militaryTimeStr = Integer.toString(militaryTimeInt);
             //Splits time in half
             int midpoint = militaryTimeStr.length() / 2;
             String firstHalf = militaryTimeStr.substring(0, midpoint);
             String secondHalf = militaryTimeStr.substring(midpoint);
             //Adds semicolon and AM
             String regularTime = (firstHalf + ":" + secondHalf + " AM");
             return regularTime;
          }
             else
             {
             String militaryTimeStr = Integer.toString(militaryTimeInt);
             //Splits time in half
             int midpoint = militaryTimeStr.length() / 2;
             String firstHalf = militaryTimeStr.substring(0, midpoint);
             String secondHalf = militaryTimeStr.substring(midpoint);
             //Adds semicolon and AM
             String regularTime = (firstHalf + ":" + secondHalf + " AM");
             return regularTime;
             }
    }
  }

  //menu for user to edit a contact
  public static void EditContact(String strID, String[][] aryPassed)
  {
        Scanner keyboard = new Scanner(System.in);
        
        int intID = PrintContact(aryPassed, strID);

        boolean booRepeat = true;
        boolean booSearchCheck = false;

        for (int i = 0; i < aryPassed.length; i++)
        {
          if (aryPassed[i][0].equalsIgnoreCase(strID))
          {
           booSearchCheck = true;
          }
        }

        if (booSearchCheck == false)
        {
          System.out.println("\nNo records match that search criteria. Type 'OK' to return to the main menu.");
        }


        while (booSearchCheck == true)
        {

          System.out.println( "-----------------------------" +
                              "\n[1] Edit Contact" +
                              "\n[2] Add Appointment" +
                              "\n[3] Delete Appointment" +
                              "\n\n[0] Cancel"
            );
          
          String strEdit = keyboard.nextLine();

          //defines the options the user can choose from this menu
          if (strEdit.equalsIgnoreCase("1") || strEdit.equalsIgnoreCase("edit") || strEdit.equalsIgnoreCase("edit contact"))
          {
               while (booRepeat)
               {
                    System.out.println("Please enter which attribute you would like to edit. \n(Please enter the number designation)");
                    int intEdit = keyboard.nextInt();

                    while(intEdit > 16 || intEdit < 1)
                    {
                      System.out.println("Please enter a proper attribute designation (1-16)");
                      intEdit = keyboard.nextInt();
                    }
                    
                    EditAttribute(intEdit);

                    System.out.println("\nCurrent Value: " + aryPassed[intID][intEdit] +
                                        "\nWhat would you like to change it to?");

                    keyboard.nextLine();
                    aryPassed[intID][intEdit] = keyboard.nextLine();

                    System.out.println("Would you like to edit this contact further?");

                    String strRepeat = keyboard.nextLine();
                    //System.out.println(strRepeat + " --- This is what I typed");
                    if (strRepeat.equalsIgnoreCase("no"))
                    {
                         booRepeat = false;
                         booSearchCheck = false;
                    }

               }
          } else if (strEdit.equalsIgnoreCase("2") || strEdit.equalsIgnoreCase("add") || strEdit.equalsIgnoreCase("add appointment"))
          {
            if (aryPassed[intID][14].equals(""))
            {
              AddAppt(aryPassed, intID);

            } else
            {
              System.out.println("This contact's next appointment is:" + 
                                  "\n" + aryPassed[intID][14] +
                                  "\n\nHas this appointment already happened?" +
                                  "\nIf yes, the contact's current appointment will be set as previous appointment.");
              strEdit = keyboard.nextLine();

              if(strEdit.equalsIgnoreCase("yes"))
              {
                aryPassed[intID][11] = aryPassed[intID][14];
                aryPassed[intID][12] = aryPassed[intID][15];
                aryPassed[intID][13] = aryPassed[intID][16];
                AddAppt(aryPassed, intID);
              } else 
              {
                AddAppt(aryPassed, intID);
              }
              booSearchCheck = false;
            }
          } else if (strEdit.equalsIgnoreCase("3") || strEdit.equalsIgnoreCase("delete") || strEdit.equalsIgnoreCase("delete appointment"))
          {
            if (aryPassed[intID][14].equals(""))
            {
              System.out.println("\nThere is no appointment for this contact. Type 'OK' return to the main menu.");

              strEdit = keyboard.nextLine();

            } else
            {
              aryPassed[intID][14] = "";
              aryPassed[intID][15] = "";
              aryPassed[intID][16] = "";

              System.out.println("\nThe appointment has been deleted. Type 'OK' return to the main menu.");

              strEdit = keyboard.nextLine();
            }
            booSearchCheck = false;
          } else if (strEdit.equalsIgnoreCase("0") || strEdit.equalsIgnoreCase("[0]") || strEdit.equalsIgnoreCase("(0)") || strEdit.equalsIgnoreCase("cancel"))
          {
            booSearchCheck = false;
          }
        }
  }

  //prints the contacts full information to the screen
  public static int PrintContact(String[][] aryPassed, String strID)
  {
        int intID = 0;

        for (int i = 0; i < aryPassed.length; i++)
        {

             if (aryPassed[i][0].equalsIgnoreCase(strID))
             {
                  System.out.println(
                                      "\nID Number: " + aryPassed[i][0] +
                                      "\n[1] Last Name: " + aryPassed[i][1] +
                                      "\n[2] First Name: " + aryPassed[i][2] +
                                      "\n[3] Address: " + aryPassed[i][3] +
                                      "\n[4] City: " + aryPassed[i][4] +
                                      "\n[5] Zip Code: " + aryPassed[i][5] +
                                      "\n[6] E-mail: " + aryPassed[i][6] +
                                      "\n[7] Phone Number: " + aryPassed[i][7] +
                                      "\n[8] Mobile Number: " + aryPassed[i][8] +
                                      "\n[9] Work Number: " + aryPassed[i][9] +
                                      "\n[10] Group: " + aryPassed[i][10] +
                                      "\n\nLast Appointment:" + 
                                      "\n[11] Date: " + aryPassed[i][11] +
                                      "\n[12] Time: " + ConvertMilitary(aryPassed[i][12]) +
                                      "\n[13] Description: " + aryPassed[i][13] +
                                      "\n\nNext Appointment:" +
                                      "\n[14] Date: " + aryPassed[i][14] +
                                      "\n[15] Time: " + ConvertMilitary(aryPassed[i][15]) +
                                      "\n[16] Description: " + aryPassed[i][16]
                                      );
                  intID = i;
             }
        }

        return intID;
  }

  //defines special formatting for certain attributes
  public static void EditAttribute(int intAttribute)
  {
        if (intAttribute == 7 || intAttribute == 8 || intAttribute == 9)
        {
             System.out.println("Please enter phone numbers in the following format: ###-###-####");

        } else if (intAttribute == 11 || intAttribute == 14)
        {
             System.out.println("Please enter dates in the following format: MM.DD.YY");

        } else if (intAttribute == 12 || intAttribute == 15) 
        {
             System.out.println("Please enter time in the following format: HHMM \n(in military time 8:00 am would be 0800, 8:00 pm would be 2000");

        }
  }

  //exports array to a text file
  public static void SaveFile(String[][] aryPassed) throws IOException
  {
        String strSaveFile = "contacts.txt";
        BackUp("contacts.txt");
        //PrintWriter would overwrite the file if had already existed without the FileWriter
        PrintWriter outputFile = new PrintWriter(strSaveFile);

        aryPassed = ColumnSort(aryPassed, 0);

        for (int i = 0; i < aryPassed.length; i++)
        {
             for (int j = 0; j < aryPassed[i].length; j++)
             {
                  outputFile.println(aryPassed[i][j]);
             }

             outputFile.println("$%&");
        }
        
        
        outputFile.close();
  }

  //RemoveSpaces takes a string and removes all spaces from it. Mostly used to check if the user
  //inputted an actual character and not just a space or spaces
  public static String RemoveSpaces(String strPassed)
  {
        strPassed = strPassed.replace(" ", "");

        return strPassed;
  }

  //checks the input to verify it is not blank
  public static boolean ValidInput(String strPassed)
  {
      boolean booValid = true;

      if (RemoveSpaces(strPassed).equals(""))
      {
          booValid = false;
      }

      return booValid;
  }

  //method for adding an appt to a designated contact
  public static void AddAppt(String[][] aryPassed, int intID)
  {
    Scanner keyboard = new Scanner(System.in);

    System.out.println("What is the date for their next appointment? (MM.DD.YY)");

            aryPassed[intID][14] = keyboard.nextLine();

            System.out.println("What time is the appointment? (HHMM)");

            aryPassed[intID][15] = keyboard.nextLine();

            System.out.println("What is the topic of the appointment?");

            aryPassed[intID][16] = keyboard.nextLine();
  }

  /*
    The removeItem Method deletes a current contact  
       
    @param oldIdArray Current contact list
    
    @return newIdArray Contact list with contact removed
  
  */
  
  public static String[][] removeItem(String[][] oldIdArray)
  {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Which Account should we remove (Enter ID Number): ");
    String removeIdInput = keyboard.nextLine();
    //User is asked which ID and information they wish to delete
    
    String[][] newIdArray = new String[oldIdArray.length - 1][17];
    
    int remove = Integer.parseInt(removeIdInput);
    if (remove < 10 && removeIdInput.charAt(0) != 0)
    {
      removeIdInput = "0" + remove + "";
    }
    
    int y = 0;  
    for(int x = 0; x < oldIdArray.length; x++)
    {
       if (!(oldIdArray[x][0].equalsIgnoreCase(removeIdInput)))
       {
             for(int i = 0; i < oldIdArray[x].length; i++)
             {
                if (oldIdArray[x][i] != null)
                {
                   newIdArray[y][i] = oldIdArray[x][i];
                   
                }
             }
             y++;
       }
       /*
          In copying the old array into the new array, when the entered ID number is matched to a array, 
             that array is not copied into the new contact array
          Any array items with a null value are not copied into the new array
       
       */
       
       else if(oldIdArray[x][0].equalsIgnoreCase(null))
       {
          break;
       }
    }

    /*for (int i = 0; i < newIdArray.length; i++)
     {
      for (int j = 0; j < newIdArray[i].length; j++)
       {
        System.out.println(newIdArray[i][j]);
       }
     }*/

    return newIdArray;
  }
        
  /*
    The addItem Method creates a new contact and adds it onto the contact list.  
       Asks for Last and First Name, Address, City, Zip, Email address, Work Phone, Home Phone, 
             Cell Phone, Group, Appointment Date, Time, and Description
    
    @param oldIdArray Current contact list
    
    @return newIdArray Contact list with new contact added on 
  
  */
  public static String[][] addItem(String[][] oldIdArray)
  {
         
     Scanner keyboard = new Scanner(System.in);
                
     String[][] newIdArray = new String[oldIdArray.length + 1][17];
            
     String nextIdNum = "";
     if(oldIdArray.length == 0)
     {
       nextIdNum = "1"; // If no contacts exist, the id number 1 is assigned
     }
     else
     {
       int nextId = Integer.parseInt(oldIdArray[oldIdArray.length - 1][0]);
       if(nextId < 10)
       {
          nextIdNum = "0" + ((Integer.parseInt(oldIdArray[oldIdArray.length - 1][0])) + 1) + "";
          // If the ID number is less than 10, a 0 is placed in front of the single digit Id number
       }
       else
       {
          nextIdNum = "" + ((Integer.parseInt(oldIdArray[oldIdArray.length - 1][0])) + 1) + "";
       }        
     }
           
     for(int x = 0; x < newIdArray.length ; x++)
     {
         if(x < newIdArray.length - 1)
         {
             for(int i = 0; i < newIdArray[x].length; i++)
             {
                newIdArray[x][i] = oldIdArray[x][i];
             }
         }
         /*
             All the current contact information from the old array is copied into the new array item by item.
             
             Once the current information is transfered, the user will enter the information for the new contact
         
         */
         
         else if (x == newIdArray.length - 1)
         {
           newIdArray[x][0] = nextIdNum;
           System.out.println("Enter Info for ID Number: " + nextIdNum);
           System.out.println("Items marked with \"*\" are required!");
           System.out.println("");
           
           do
           {
             System.out.println("Enter Last Name* : ");
             newIdArray[x][1] = RemoveSpaces(keyboard.nextLine());
           }while(ValidInput(newIdArray[x][1])== false);
           
           do
           {
             System.out.println("Enter First Name* : ");
             newIdArray[x][2] = keyboard.nextLine();
           }while(ValidInput(newIdArray[x][2])== false);
           
           
            System.out.println("Enter Street Address : ");
            newIdArray[x][3] = keyboard.nextLine();
         
         
         
            System.out.println("Enter City : ");
            newIdArray[x][4] = keyboard.nextLine();
         
         
         
            System.out.println("Enter Zip Code : ");
            newIdArray[x][5] = keyboard.nextLine();
         
         
         
            System.out.println("Enter Email Address : ");
            newIdArray[x][6] = keyboard.nextLine();
         
         
         
            System.out.println("Enter Home Phone Number (###-###-####) : ");
            newIdArray[x][7] = keyboard.nextLine();
         
         
         
            System.out.println("Enter Cell Phone Number (###-###-####) : ");
            newIdArray[x][8] = keyboard.nextLine();
             
             
            
            System.out.println("Enter Work Phone Number (###-###-####) : ");
            newIdArray[x][9] = keyboard.nextLine();
           
           do
           {
             System.out.println("Enter Group* : ");
             newIdArray[x][10] = keyboard.nextLine();
           }while(ValidInput(newIdArray[x][10])== false);
           
           
           System.out.println("Enter Previous Appointment Date (MM.DD.YY): ");
           newIdArray[x][11] = keyboard.nextLine();
           
           System.out.println("Enter Previous Appointment Time (Enter in 24 hr Format (Ex: 3 PM is 1500)): ");
           newIdArray[x][12] = keyboard.nextLine();
         
           System.out.println("Enter Previous Appointment Description: ");
           newIdArray[x][13] = keyboard.nextLine();
            
           System.out.println("Enter Next Appointment Date (MM.DD.YY): ");
           newIdArray[x][14] = keyboard.nextLine();
          
           System.out.println("Enter Next Appointment Time: ");
           newIdArray[x][15] = keyboard.nextLine();
                             
           System.out.println("Enter Next Appointment Description: ");
           newIdArray[x][16] = keyboard.nextLine();

           /*for (int i = 0; i < newIdArray[x].length - 1; i++)
           {
            System.out.println(newIdArray[x][i]);
           }

           for (int i = 0; i < newIdArray.length; i++)
           {
            for (int j = 0; j < newIdArray[i].length; j++)
             {
              System.out.println(newIdArray[i][j]);
             }
           }*/
                 
                 
         }
     }
      
     return newIdArray;
  }

}
