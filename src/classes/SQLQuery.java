package classes;

import java.sql.*;

public class SQLQuery {

    private String getNumberOfValues(String[] array){
        String str = "";
        String car = "?,";
        int i=0;
        int size = array.length;
        while(i<size) {
            str = String.format("%s%s", str, car);
            i++;
        }
        return str.substring(0,str.length()-1);
    }

    public ResultSet getResultSet(Connection con,String tableName){
        try {
            Statement stmt = con.createStatement();
            String select = String.format("Select * from %s",tableName);
            return stmt.executeQuery(select);
        }catch (Exception io) {
            System.out.println("error" + io);
        }
        return null;
    }

    public ResultSet getCustomerOrderResultSet(Connection con){
        try {
            Statement stmt = con.createStatement();
            String select = "select c.Cno, c.FirstName, c.LastName,co.Ono,o.Pno,o.Quantity " +
                    "From customer c join customerorder co on c.Cno = co.Cno join orderdetail o on co.Ono = o.Ono";
            return stmt.executeQuery(select);
        }catch (Exception io) {
            System.out.println("error" + io);
        }
        return null;
    }

    public void deleteQuery(Connection con,String tableName,String colName, String text){
        try {
            Statement deleteStmt = con.createStatement();
            String delete = String.format("Delete from %s where %s ='%s'",tableName , colName, text);
            deleteStmt.executeUpdate(delete);
            deleteStmt.close();
        }catch (Exception io) {
            System.out.println("error" + io);
        }
    }

    public void insertQuery(Connection con,String tableName, String... array){
        System.out.println("Database connection established1");
        String values = getNumberOfValues(array);
        try {
            String insert = String.format("INSERT INTO %s VALUES (%s)",tableName, values);
            PreparedStatement stmt = con.prepareStatement(insert);
            int i=1;
            for(String s : array){
                try{
                    stmt.setInt(i, Integer.parseInt(s));
                }catch(NumberFormatException nfe){
                    stmt.setString(i,s);
                }
                i++;
            }
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.printf("%s table updated",tableName);
    }

    public void updateQuery(Connection con,String tableName, String colName, String newValue, String idNo){
        try {
            //update
            colName = colName.replaceAll("\\s+", "");
            String id = tableName.charAt(0)+"no";
            Statement updateStmt = con.createStatement();
            String updateSQL = String.format("Update %s set %s = '%s' where %s ='%s'"
                    ,tableName,colName,newValue,id,idNo);
            int res = updateStmt.executeUpdate(updateSQL);
            System.out.println("The Number or records updated is      " +res);
            // You May need to uncomment if Autocommit is not set
            //con.commit();
            updateStmt.close();
        }catch (Exception io) {
            System.out.println("error"+io);
        }
    }

    public ResultSet joinQuery(Connection con, String tableName1, String tableName2, String colNameJoin, String colNameSearch){
        ResultSet rs = null;
        try{
            String select = colNameSearch.equalsIgnoreCase("all") ?
                    "*" : String.format("%s.%s",tableName2, colNameSearch);
            String no = tableName2.charAt(0)+"No";
            String query = String.format("SELECT %s FROM %s INNER JOIN %s ON %s.%s=%s.%s"
                    ,select, tableName2, tableName1, tableName2,no,tableName1,no);

            System.out.println(query);

            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }catch(SQLException sql){
            System.out.println("FAILED JOIN");
            sql.printStackTrace();
        }

        return rs;
    }
}
