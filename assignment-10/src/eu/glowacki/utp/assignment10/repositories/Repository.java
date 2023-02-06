package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.DTOBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Repository<TDTO extends DTOBase> implements IRepository<TDTO>{
    public static Connection connection;

    public Repository(Connection connection) throws SQLException {
        this.connection = connection;
        connection.setAutoCommit(false);
    }

    public String getTableName() {
        return null;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }

    @Override
    public void add(TDTO dto) {

    }

    @Override
    public void update(TDTO dto) {

    }

    @Override
    public TDTO findById(int id) {
        return null;
    }

    public final void beginTransaction() {
        try {
            connection.setSavepoint();
        }catch(SQLException e){e.printStackTrace();}
    }

    public final void commitTransaction() {
        try {
            connection.commit();
        }catch(SQLException e){e.printStackTrace();}
    }

    public final void rollbackTransaction() {
        try {
            connection.rollback();
        }catch(SQLException e){e.printStackTrace();}
    }

    public final boolean exists(TDTO dto) {
        if (dto.hasExistingId() && findById(dto.getId())!=null)return true;
        return false;
    }

    public final int getCount() {
        String SQL = "SELECT count(1) from "+ getTableName();
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            ResultSet r = statement.executeQuery();
            if (r.next())
                count = r.getInt(1);
        }catch(SQLException e){e.printStackTrace();}
        return count;
    }

    public final void delete(TDTO dto) {
        String SQL =
                "delete from "+ getTableName() +" "+
                        "where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, dto.getId());
            statement.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    public final void addOrUpdate(TDTO dto) {
        if(exists(dto))update(dto);
        else add(dto);
    }

    public final void closeConnection() {
        try {
            this.connection.close();
        } catch (SQLException var2) {
            var2.printStackTrace();
        }

    }
}
