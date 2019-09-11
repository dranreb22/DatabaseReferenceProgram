package com.company;
import javax.xml.transform.Result;
import java.sql.*;
//CTRL + Q brings up documentation
//related to the methods that appear
//for auto fill
public class Main {

    public static void main(String[] args) {
	// write your code here

        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./res/hr";

        //Database credentials
        //dont publish database password to github
        final String USER = "";
        final String PASS = "";

        Connection conn = null;
        Statement stmt = null;

        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            stmt = conn.createStatement();


            //String sql = "SELECT * FROM JOBS";
            //ResultSet rs = stmt.executeQuery(sql);

            ResultSet rset = stmt.executeQuery("SELECT LAST_NAME, FIRST_NAME from EMPLOYEES");
            int colNum = getcolumnNames(rset);
            if (colNum>0)
                while (rset.next()){
                    for (int i = 0; i<colNum; i++){
                        if (i+1 == colNum)
                            System.out.println(rset.getString(i+1));
                        else
                            System.out.print(rset.getString(i+1)+ ", ");
                        //endif
                    }//endfor
                }//endwhile
            //endif
            /*while (rs.next()) {
                System.out.println(rs.getString(1));
            }*/

            /*while (rset.next()){
                System.out.println(rset.getString(1) + "-" + rset.getString(2));
            }*/

            // STEP 4: Clean-up environment
            //stmt.close();
            //rset.close();
            conn.close();
        }
        catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

public static int getcolumnNames(ResultSet rs) throws SQLException{
    int numberOfColumns = 0;
    if (rs != null) {
        //create an object based on the Metadata of the result set
        ResultSetMetaData rsMetaData = rs.getMetaData();
        //use the getColumn method to get the number of columns returned
        numberOfColumns = rsMetaData.getColumnCount();
        // get an dprint the column names, column indexes start from 1
        for (int i = 1; i < numberOfColumns + 1; i++){
            String columnName = rsMetaData.getColumnName(i);
            System.out.println(columnName + ", ");
        }//endfor
    }//endif
    System.out.println();// place the cursor on a new line in the console
    return numberOfColumns;
    }//end method getColumnNames
}
