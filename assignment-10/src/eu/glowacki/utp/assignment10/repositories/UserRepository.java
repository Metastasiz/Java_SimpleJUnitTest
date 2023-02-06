package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends Repository<UserDTO> implements IUserRepository{
    private static final String tableName = "users";
    public UserRepository(Connection c) throws SQLException {
        super(c);
    }
    @Override
    public String getTableName(){return tableName;}

    @Override
    public void add(UserDTO dto) {
        String SQL = "insert into "+tableName+"(id, login, password)" +
                "values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getLogin());
            statement.setString(3, dto.getPassword());
            statement.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    @Override
    public void update(UserDTO dto) {
        String SQL =
                "update "+tableName+" " +
                        "set login=?, password=?" +
                        "where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, dto.getLogin());
            statement.setString(2, dto.getPassword());
            statement.setInt(3, dto.getId());
            statement.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    @Override
    public UserDTO findById(int id) {
        String SQL = "SELECT login, password " +
                "FROM "+tableName+" WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            String login;
            String password;
            if (result.next()) {
                login = result.getString("login");
                password = result.getString("password");
                return new UserDTO(id, login, password);
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    @Override
    public List<UserDTO> findByName(String username) {
        String SQL = "SELECT id, login, password " +
                "FROM "+tableName+" " +
                "WHERE login LIKE ?";
        List<UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, "%" + username + "%");
            ResultSet r = statement.executeQuery();
            int id;
            String login;
            String password;
            while (r.next()) {
                id = r.getInt("id");
                login = r.getString("login");
                password = r.getString("password");
                users.add(new UserDTO(id, login, password));
            }
        }catch(SQLException e){e.printStackTrace();}
        return users;
    }
}
