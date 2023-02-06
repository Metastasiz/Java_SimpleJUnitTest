package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.dtos.UserDTO;
import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;
import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository> {

    private static final String Gname = "IKES";
    private static final String Desc = "Description";
    private static final GroupDTO G = new GroupDTO(1, Gname, Desc);
    private static final String Gname2 = "YIKES";
    private static final String Desc2 = "Description2";
    private static final GroupDTO G_updated = new GroupDTO(1, Gname2, Desc2);
    private static final GroupDTO G2 = new GroupDTO(2, Gname2, Desc2);
    private static GroupRepository res;
    //
    private static final String NAME = "YEE";
    private static final String PASSWORD = "PW";
    private static final UserDTO USER = new UserDTO(1, NAME, PASSWORD);
    private static final String NAME2 = "YEET";
    private static final String PASSWORD2 = "PWW";
    private static final UserDTO USER_UPDATED = new UserDTO(1, NAME2, PASSWORD2);
    private static final UserDTO USER2 = new UserDTO(2, NAME2, PASSWORD2);
    //
    private static final Method _name, _description;
    static {
        _name = method(GroupDTO.class, "getName");
        _description = method(GroupDTO.class, "getDescription");
    }

    @Test
    public void testList(){
        USER.addGroup(G);
        USER.addGroup(G2);
        List<GroupDTO> tmp = new ArrayList<>();
        tmp.add(G);
        tmp.add(G2);
        int count = 0;
        try {
            for (GroupDTO e: USER.getGroups()){
                Assert.assertEquals(tmp.get(count).getName(), _name.invoke(null,e));
                Assert.assertEquals(tmp.get(count++).getDescription(), _description.invoke(null,e));
            }
        }catch (Exception e){}
    }

    @Test
    public void add() {
        Assert.assertFalse(_repository.exists(G));
        int tmp = _repository.getCount();
        _repository.add(G);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G));
    }

    @Test
    public void update() {
        Assert.assertFalse(_repository.exists(G));
        Assert.assertFalse(_repository.exists(G_updated));
        int tmp = _repository.getCount();
        _repository.add(G);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G));
        _repository.update(G_updated);
        tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G_updated));
    }

    @Test
    public void addOrUpdate() {
        Assert.assertFalse(_repository.exists(G));
        Assert.assertFalse(_repository.exists(G_updated));
        int tmp = _repository.getCount();
        _repository.addOrUpdate(G);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G));
        _repository.addOrUpdate(G_updated);
        tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G_updated));
    }

    @Test
    public void delete() {
        Assert.assertFalse(_repository.exists(G));
        int tmp = _repository.getCount();
        _repository.add(G);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G));
        _repository.delete(G);
        tmp2 = _repository.getCount();
        Assert.assertEquals(tmp2, tmp);
        Assert.assertFalse(_repository.exists(G));
    }


    @Test
    public void findById() {
        Assert.assertEquals(USER.getGroups().get(0),G);
        Assert.assertEquals(USER.getGroups().get(1),G2);
        Assert.assertFalse(_repository.exists(G));
        int tmp = _repository.getCount();
        _repository.add(G);
        int tmp2 = _repository.getCount();
        Assert.assertEquals(tmp + 1, tmp2);
        Assert.assertTrue(_repository.exists(G));
        GroupDTO group = _repository.findById(G.getId());
        Assert.assertEquals(group, G);
    }

    @Test
    public void findByName() {
        Assert.assertFalse(_repository.exists(G));
        Assert.assertFalse(_repository.exists(G2));
        int countBefore = _repository.getCount();
        _repository.add(G);
        _repository.add(G2);
        int countAfter = _repository.getCount();
        Assert.assertEquals(countBefore + 2, countAfter);
        Assert.assertTrue(_repository.exists(G));
        Assert.assertTrue(_repository.exists(G2));
        List<GroupDTO> groups = _repository.findByName("KES");
        Assert.assertEquals(2, groups.size());
        groups = _repository.findByName("YIKES");
        Assert.assertEquals(1, groups.size());
        groups = _repository.findByName("ASDF");
        Assert.assertEquals(0, groups.size());
    }


    @Override
    protected IGroupRepository Create() {

        try {
            res = new GroupRepository(connect);
            return res;
        }catch (SQLException e){}
        return res;
    }
}