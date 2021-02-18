import java.util.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class mouseDBAccessor {
	
    public void viewMouse() throws SQLException{

        Scanner input = new Scanner(System.in);
        String filter;

        System.out.println("Please select the parameter(s) you would like to search by:");
        System.out.println("1. Alphanumerical ID");
        System.out.println("2. Birthday");
        System.out.println("3. Parent");
        System.out.println("4. Genotype");

        filter = input.nextLine();
        if(filter.equals("1")){
            System.out.print("Please enter the name you wish to search:");
            filter = input.nextLine();
            System.out.println();

            //access the mysql server given the specified name, return the correct mouse here
            search(filter, "id_an");
            if (mouseExists(filter)) {
            	String mouse = filter;
            	System.out.println("Do you wish to update this mouse? (y/n)");
            	
            	filter = input.nextLine();
            	if (filter.equalsIgnoreCase("y")) updateMouse(mouse);
            }

        }
        
        if(filter.equals("2")){
            System.out.print("Please enter the birthday you wish to search:");
            filter = input.nextLine();
            System.out.println();

            //access the mysql server given the specified name, return the correct mouse here
            search(filter, "dob");

        }
        
        if(filter.equals("3")){
            System.out.print("Do you wish to search by mother or father? (m/f)");
            String temp = input.nextLine();
            if (temp.equalsIgnoreCase("m"))
            	temp = "mother";
            else if (temp.equalsIgnoreCase("f"))
            	temp = "father";
            System.out.print("Please enter the Alphanumerical ID:");
            filter = input.nextLine();
            System.out.println();
            search(filter, temp);

        }
        
        if(filter.equals("4")){
            System.out.print("Please enter genotype below:");
            System.out.println();
            search(Genotype.buildGenotype(), "genotype");
        }
    }
    
    public void updateMouse(String mouse) throws SQLException{
    	boolean goer = true;
        Scanner input = new Scanner(System.in);
        String[] data =  new String[10];
        String[] dataLabels =  new String[8];
        dataLabels[0] = "Alphanumerical ID: ";
        dataLabels[1] = "Sex (m/f): ";
        dataLabels[2] = "Date of Birth (mm/dd/yyyy): ";
        dataLabels[3] = "Date of Death (mm/dd/yyyy): ";
        dataLabels[4] = "Status (int 0-4): ";
        dataLabels[5] = "Mother (A/ID): ";
        dataLabels[6] = "Father (A/ID): ";
        dataLabels[7] = "Genotype: ";

        do {
            String decision;
            System.out.println("Please input the data needed to update the mouse. Simply press enter if you do not want to update that field.");

            for (int i = 0; i < 8; i++) {
                System.out.print(dataLabels[i]);
                if (i == 7) {
                	System.out.println("");
                	String gen;
                	do {
	                	System.out.println("Do you wish to update the Genotype? (y/n)");
	                	gen = input.nextLine();
                	} while (!(gen.equalsIgnoreCase("y")) && !(gen.equalsIgnoreCase("n")));
                	
                	if (gen.equalsIgnoreCase("y")) {
                		data[7] = Genotype.buildGenotype();
                	}
                }
                else
                data[i] = input.nextLine();
                System.out.println();
            }
            
            for (int i = 0; i < 8; i++) {
                System.out.println(dataLabels[i] + data[i]);
            }
            System.out.println("=====================================================");
            System.out.println("Is this data correct? Y/N");
            decision = input.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                goer = false;
                //INSERT CODE FOR ADDING NEW MOUSE TO MYSQL, CAN PASS THE ARRAY "data" TO FILL FIELDS
                
                String sql = "SELECT * FROM mouselab";
    			
    			try (
    					Connection con = mouseDBconnect.getConnection();
    					Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
    					ResultSet rs = stmt.executeQuery(sql);
    					)
    				{
    					rs.beforeFirst();
    					while (rs.next()) {
    						
    						rs.getInt("id");
    						if (rs.getString("id_an").equalsIgnoreCase(mouse)) {
    							
    							if (!(data[0].equals("")))
    								rs.updateString("id_an", data[0]);
    							if (!(data[1].equals("")))
    								rs.updateString("sex", data[1]);
    							if (!(data[2].equals("")))
    								rs.updateString("dob", data[2]);
    							if (!(data[3].equals("")))
    								rs.updateString("dod", data[3]);
    							if (!(data[4].equals("")))
    								rs.updateString("status", data[4]);
    							if (!(data[5].equals("")))
    								rs.updateString("mother", data[5]);
    							if (!(data[6].equals("")))
    								rs.updateString("father", data[6]);
    							if (!(data[7].equals("")))
    								rs.updateString("genotype", data[7]);
    							
    							rs.updateRow(); //looks for 'id'
    							System.out.println("You successfully updated this mouse!");
    						
    						}
    					
    					}
    				}
                
            }
            else if (decision.equals("N") || decision.equals("n")){
                //LOOPS AGAIN
            }
        } while(goer);
    }
    
    public void search(String mouse, String field) throws SQLException {
    	String sql = "SELECT * FROM mouselab";
		
		try (
				Connection con = mouseDBconnect.getConnection();
				Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet rs = stmt.executeQuery(sql);
				)
			{
				rs.beforeFirst();
				int count = 0;
				
				while (rs.next()) {
					if (rs.getString(field).equalsIgnoreCase(mouse)) {
						System.out.println("ID: " + rs.getString("id_an") + 
								"\tSex: " + rs.getString("sex") +
								"\tDay of Birth: " + rs.getString("dob") +
								"\tDay of Death: " + rs.getString("dod") +
								"\tStatus: " + rs.getString("status") +
								"\tMother: " + rs.getString("mother") +
								"\tFather: " + rs.getString("father") +
								"\tGenotype: " + Genotype.toString(rs.getString("genotype")));
						count++;
					}
				}
				
				if (count == 0) System.out.println("No such mouse exists.");
			}
    }


    public void addMouse() throws SQLException {

        boolean goer = true;
        Scanner input = new Scanner(System.in);
        String[] data =  new String[10];
        String[] dataLabels =  new String[8];
        dataLabels[0] = "Alphanumerical ID: ";
        dataLabels[1] = "Sex (m/f): ";
        dataLabels[2] = "Date of Birth (mm/dd/yyyy): ";
        dataLabels[3] = "Date of Death (mm/dd/yyyy): ";
        dataLabels[4] = "Status (int 0-4): ";
        dataLabels[5] = "Mother (A/ID): ";
        dataLabels[6] = "Father (A/ID): ";
        dataLabels[7] = "Genotype: ";

        do {
            String decision;
            System.out.println("Please input the data needed for a new mouse when prompted");

            for (int i = 0; i < 8; i++) {
                System.out.print(dataLabels[i]);
                if (i == 7) {
                	System.out.println("");
                	data[7] = Genotype.buildGenotype();
                }
                else
                data[i] = input.nextLine();
                System.out.println();
            }

            //check user input
            //checks to see if mouse sex is correctly entered & will eliminate all spaces & change to lowercase
            data[1].trim().toLowerCase();
            while (!(data[1].length() == 1)){
                System.out.println("Error for sex. Please try again: (m/f)");
                data[1] = input.nextLine();
            }

            //splits birthdate into month, day & checks
            while (!checkDate(data[2])) {
            	System.out.println("Error for the birth date. Please try again: (mm/dd/yyyy)");
            	data[2] = input.nextLine();
            }

            //splits deathdate into month, day & checks
            while (!checkDate(data[3])) {
            	System.out.println("Error for the death date. Please try again: (mm/dd/yyyy)");
            	data[3] = input.nextLine();
            }
         /*   int count;
            
            do {
            	String[] splitDateBirth = data[2].split("/");
                int dateMonthBirth = Integer.parseInt(splitDateBirth[0]);
                int dateDayBirth = Integer.parseInt(splitDateBirth[1]);
                int dateYearBirth = Integer.parseInt(splitDateBirth[2]);
                
                String[] splitDateDeath = data[3].split("/");
                int dateMonthDeath = Integer.parseInt(splitDateDeath[0]);
                int dateDayDeath = Integer.parseInt(splitDateDeath[1]);
                int dateYearDeath = Integer.parseInt(splitDateDeath[2]);
                
            	count = 0;
            	
            	if (dateYearDeath < dateYearBirth) {
            		count++;
            		if ((dateMonthDeath < dateMonthBirth) && count == 1) {
            			count++;
            			if ((dateDayDeath < dateDayBirth) && count == 2) {
            				count++;
            			}
            		}
            	}
            	if (count > 0) {
            		System.out.println("There is an incoherence with the birth date and death date.");
            		System.out.println("Please re-enter birth date:");
            		data[2] = input.nextLine();
            		while (!checkDate(data[2])) {
                    	System.out.println("Error for the birth date. Please try again: (mm/dd/yyyy)");
                    	data[2] = input.nextLine();
                    }
            		System.out.println("Please re-enter death date:");
            		data[3] = input.nextLine();
            		System.out.println("1");
            		while (!checkDate(data[3])) {
                    	System.out.println("Error for the death date. Please try again: (mm/dd/yyyy)");
                    	data[3] = input.nextLine();
                    	System.out.println("2");
                    }
            		System.out.println("3");
            		count = 0;
            	}
            } while (count >= 1 || count <= 3);*/
            //input.close();
            
            //checks mouse status
            
            int mouseStatus = Integer.parseInt(data[4]);
            while ((!(mouseStatus <= 4) || !(mouseStatus >= 0))){
            	System.out.println("Error for status. Please try anything between 1-4:");
            	data[4] = input.nextLine();
            	mouseStatus = Integer.parseInt(data[4]);
            }



            System.out.println("=====================================================");

            for (int i = 0; i < 8; i++) {
                System.out.println(dataLabels[i] + data[i]);
            }
            System.out.println("=====================================================");
            System.out.println("Is this data correct? Y/N");
            input = new Scanner(System.in);
            decision = input.nextLine();
            if (decision.equals("Y") || decision.equals("y")) {
                goer = false;
                //INSERT CODE FOR ADDING NEW MOUSE TO MYSQL, CAN PASS THE ARRAY "data" TO FILL FIELDS
                
                String sql = "INSERT INTO mouselab(id_an, sex, dob, dod, status, mother, father, genotype) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    			
    			try (
    					Connection conn = mouseDBconnect.getConnection();
    					PreparedStatement stmt = conn.prepareStatement(sql);
    					)
    				{
    					for (int i = 0; i < 8; i++) {
    						stmt.setString(i+1, data[i]);
    					}
    					stmt.execute();
    					System.out.println("Mouse was added successfully.");
    				}
                
            }
            else if (decision.equals("N") || decision.equals("n")){
                //LOOPS AGAIN
            }

        }while(goer);
    }
    
    public boolean checkDate(String temp) throws SQLException {
    	String[] splitDate = temp.split("/");
        int dateMonth = Integer.parseInt(splitDate[0]);
        int dateDay = Integer.parseInt(splitDate[1]);
        int dateYear = Integer.parseInt(splitDate[2]);
        while (!(dateMonth <= 12 && dateMonth > 0) || !(dateDay <= 31 && dateDay > 0)){
            return false; //wrong date
        }
        return true; //correct date
    }
    public void deleteMouse() throws SQLException {

        Scanner input = new Scanner(System.in);
        String filter;

        do {
        	System.out.println("Please enter the alphanumerical ID of the mouse you wish to delete");
        	filter = input.nextLine();
        } while (!mouseExists(filter));
        
        System.out.println("Are you sure you want to delete this mouse? (y/n)");
        String temp = input.nextLine();
        if (temp.equalsIgnoreCase("n"))
        	System.out.println("The mouse won't be deleted.");
        else if (temp.equalsIgnoreCase("y")) {
	        //MYSQL code goes here
	        
	        String sql = "DELETE FROM mouselab WHERE (id_an = ?)";
			
			try (
					Connection conn = mouseDBconnect.getConnection();
					PreparedStatement stmt = conn.prepareStatement(sql);
					)
			{
				stmt.setString(1, filter);
				stmt.execute();
				System.out.println(filter + " was successfully deleted.");
			}
        }

    }
    
	public static boolean mouseExists(String mouse) throws SQLException {
			
			String sql = "SELECT * FROM mouselab";
				
				try (
						Connection con = mouseDBconnect.getConnection();
						Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
						ResultSet rs = stmt.executeQuery(sql);
						)
					{
						rs.beforeFirst();
						boolean exists = false;
						
						while (rs.next()) {
							
							if (rs.getString("id_an").equals(mouse)) {
								//mouse exists
								exists = true;
							}
						
						}
						
						if (exists) return true;
						else return false; //course doesn't exist
					}
		
		}
}