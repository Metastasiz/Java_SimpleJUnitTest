package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import eu.glowacki.utp.assignment10.repositories.IUserRepository;
import eu.glowacki.utp.assignment10.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;


import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository> {

    private static final String NAME = "YEE";
    private static final String PASSWORD = "PW";
    private static final UserDTO USER = new UserDTO(1, NAME, PASSWORD);
    private static final String NAME2 = "YEET";
    private static final String PASSWORD2 = "PWW";
    private static final UserDTO USER_UPDATED = new UserDTO(1, NAME2, PASSWORD2);
    private static final UserDTO USER2 = new UserDTO(2, NAME2, PASSWORD2);
    private static UserRepository res;
    //
    private static final String Gname = "IKES";
    private static final String Desc = "Description";
    private static final GroupDTO G = new GroupDTO(1, Gname, Desc);
    private static final String Gname2 = "YIKES";
    private static final String Desc2 = "Description2";
    private static final GroupDTO G_updated = new GroupDTO(1, Gname2, Desc2);
    private static final GroupDTO G2 = new GroupDTO(2, Gname2, Desc2);
    //
    private static final Method _login, _password;
    static {
        _login = method(UserDTO.class, "getLogin");
        _password = method(UserDTO.class, "getPassword");
    }

    @Test
    public void testList(){
        G.addUser(USER);
        G.addUser(USER2);
        List<UserDTO> tmp = new ArrayList<>();
        tmp.add(USER);
        tmp.add(USER2);
        int count = 0;
        try {
            for (UserDTO e: G.getUsers()){
                Assert.assertEquals(tmp.get(count).getLogin(), _login.invoke(null,e));
                Assert.assertEquals(tmp.get(count++).getPassword(), _password.invoke(null,e));
            }
        }catch (Exception e){}
    }
    @Test
    public void add() {
        Assert.assertFalse(_repository.exists(USER));
        int countBefore = _repository.getCount();
        _repository.add(USER);
        int countAfter = _repository.getCount();
        Assert.assertEquals(countBefore + 1, countAfter);
        Assert.assertTrue(_repository.exists(USER));
    }

    @Test
    public void update() {
        Assert.assertFalse(_repository.exists(USER));
        Assert.assertFalse(_repository.exists(USER_UPDATED));
        int tmp = _repository.getCount();
        _repository.add(USER);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(USER));
        _repository.update(USER_UPDATED);
        tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(USER_UPDATED));
    }

    @Test
    public void addOrUpdate() {
        Assert.assertFalse(_repository.exists(USER));
        Assert.assertFalse(_repository.exists(USER_UPDATED));
        int tmp = _repository.getCount();
        _repository.addOrUpdate(USER);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(USER));
        _repository.addOrUpdate(USER_UPDATED);
        tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(USER_UPDATED));
    }

    @Test
    public void delete() {
        Assert.assertFalse(_repository.exists(USER));
        int tmp = _repository.getCount();
        _repository.add(USER);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(USER));
        _repository.delete(USER);
        tmp2 = _repository.getCount();
        Assert.assertEquals(tmp2, tmp);
        Assert.assertFalse(_repository.exists(USER));
    }

    @Test
    public void findById() {
        Assert.assertFalse(_repository.exists(USER));
        int tmp = _repository.getCount();
        _repository.add(USER);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(USER));
        UserDTO user = _repository.findById(USER.getId());
        Assert.assertEquals(user, USER);
    }

    @Test
    public void findByName() {
        Assert.assertFalse(_repository.exists(USER));
        Assert.assertFalse(_repository.exists(USER2));
        int tmp = _repository.getCount();
        _repository.add(USER);
        _repository.add(USER2);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 2, tmp2);
        Assert.assertTrue(_repository.exists(USER));
        Assert.assertTrue(_repository.exists(USER2));
        List<UserDTO> users = _repository.findByName("YE");
        Assert.assertEquals(2, users.size());
        users = _repository.findByName("ET");
        Assert.assertEquals(1, users.size());
        users = _repository.findByName("Admin");
        Assert.assertEquals(0, users.size());
    }

    @Override
    protected IUserRepository Create() {
        try {
            res = new UserRepository(connect);
            return res;
        }catch (SQLException e){}
        return res;
    }
}