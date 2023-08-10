package jdbc;

import org.junit.AfterClass;
import org.junit.Test;
import utils.JDBCUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsTest {

    @Test
    public void Testutils() throws SQLException {
        ResultSet resultSet= JDBCUtils.queryDB("select * from employees");

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String, Object>> javaResultSet = new ArrayList<>();
        Map<String , Object> rowMap = new HashMap<>();

        while (resultSet.next()){

            for (int i = 1; i<= resultSetMetaData.getColumnCount();i++ ){
                rowMap.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
            }
            javaResultSet.add(rowMap);
        }
        System.out.println(javaResultSet.get(0).get("FIRST_NAME"));
    }

    @AfterClass
    public static void closeTest(){
        JDBCUtils.closeConnection();
    }

}
