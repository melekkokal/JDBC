package jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCAdvanced {
    @Test
    public void test2() throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@codefish.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student", "codefish385");
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");
        ResultSetMetaData metaData = resultSet.getMetaData();

        //saving resultset in a list
        List<Map<String, Object>> resultSetData = new ArrayList<>();

        while (resultSet.next()) { //iterating row
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getCatalogName(i), resultSet.getObject(i));
            }
            resultSetData.add(rowMap);
        }
        System.out.println(resultSetData.get(4).get("salary"));

    }

    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student",
                "codefish385"
        );
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> resultSetData = new ArrayList<>();


        while (resultSet.next()) {// ITERATING ROW
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            resultSetData.add(rowMap);
        }
        System.out.println(resultSetData.get(4).get("SALARY"));


    }

    //Validate TJ has salary 2100

    @Test
    public void test3() throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:oracle:thin:@codefish.cfxmtijfjb4b.us-east-2.rds.amazonaws.com:1521/ORCL",
                "student",
                "codefish385"
        );
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        ResultSet resultSet = statement.executeQuery("select * from employees");

        ResultSetMetaData metaData = resultSet.getMetaData();

        List<Map<String, Object>> resultSetData = new ArrayList<>();
        Map<String, Object> dataMap = new HashMap<>();


        while (resultSet.next()) {// ITERATING ROW
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                rowMap.put(metaData.getColumnName(i), resultSet.getObject(i));
            }
            resultSetData.add(rowMap);
        }
        for (Map<String, Object> eachMap: resultSetData
             ) {
            String firstName=eachMap.get("FIRST_NAME").toString();
            if(firstName.equals("TJ")){
                int salary= Integer.parseInt(eachMap.get("SALARY").toString()); //Integer.parseInt(eachMap.get("SALARY"))
                Assert.assertEquals(2100,salary);
            }
        }
    }
}
