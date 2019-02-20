package lk.ijse.dep.fx.dao;

import lk.ijse.dep.fx.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CrudUtill {

    private CrudUtill(){

    }

    public static <T> T execute(String sql,Object... params) throws Exception {
        Connection connection =DBConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        int parameterCount = getParameterCount(sql);

        if (params.length != parameterCount){
          throw  new RuntimeException("parameter count is mismatched error");
        }
        for (int i = 0; i < parameterCount; i++) {
                pstm.setObject(i+1,params[i]);
        }
        return sql.trim().startsWith("SELECT") ? (T) pstm.executeQuery() :(T)(Integer) pstm.executeUpdate();



    }
    public static int getParameterCount(String sql){
        return  sql.concat(" ").split("[?]").length - 1;
    }
}
