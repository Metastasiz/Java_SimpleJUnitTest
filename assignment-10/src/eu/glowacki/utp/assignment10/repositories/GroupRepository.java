package eu.glowacki.utp.assignment10.repositories;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupRepository extends Repository<GroupDTO> implements IGroupRepository{
    private static final String tableName = "groups";
    public GroupRepository(Connection c) throws SQLException {
        super(c);
    }
    @Override
    public String getTableName(){return tableName;}
    @Override
    public void add(GroupDTO dto) {
        String SQL = "insert into "+tableName+"(id, name, description)" +
                "values (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, dto.getId());
            statement.setString(2, dto.getName());
            statement.setString(3, dto.getDescription());
            statement.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    @Override
    public void update(GroupDTO dto) {
        String SQL =
                "update "+tableName+" " +
                        "set name=?, description=?" +
                        "where id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, dto.getName());
            statement.setString(2, dto.getDescription());
            statement.setInt(3, dto.getId());
            statement.executeUpdate();
        }catch(SQLException e){e.printStackTrace();}
    }

    public GroupDTO findById(int id) {
        String SQL = "SELECT name, description " +
                "FROM "+tableName+" WHERE id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            ResultSet r = statement.executeQuery();
            String name;
            String description;
            if (r.next()) {
                name = r.getString("name");
                description = r.getString("description");
                return new GroupDTO(id, name, description);
            }
        }catch(SQLException e){e.printStackTrace();}
        return null;
    }

    public List<GroupDTO> findByName(String username) {
        String SQL = "SELECT id, name, description " +
                "FROM "+tableName+" " +
                "WHERE name LIKE ?";
        List<GroupDTO> groups = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SQL);
            statement.setString(1, "%" + username + "%");
            ResultSet r = statement.executeQuery();
            int id;
            String name;
            String description;
            while (r.next()) {
                id = r.getInt("id");
                name = r.getString("name");
                description = r.getString("description");
                groups.add(new GroupDTO(id, name, description));
            }
        }catch(SQLException e){e.printStackTrace();}
        return groups;
    }
}
