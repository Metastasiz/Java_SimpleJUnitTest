package eu.glowacki.utp.assignment10.dtos;

import java.util.LinkedList;
import java.util.List;

public class UserDTO extends DTOBase {

	private String _login;
	private String _password;
	private List<GroupDTO> _groups;

	public UserDTO() {
	}

	public UserDTO(int id, String login, String password) {
		super(id);
		_login = login;
		_password = password;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public List<GroupDTO> getGroups() {
		return _groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		_groups = groups;
	}

	public void addGroup(GroupDTO group) {
		if (_groups == null) {
			_groups = new LinkedList<GroupDTO>();
		}
		_groups.add(group);
	}

	public void deleteGroup(GroupDTO group) {
		if (_groups != null) {
			_groups.remove(group);
		}
	}

	@Override
	public String toString(){
		String out = "";
		if (_groups == null){out += "\n" + getId() + " " + getLogin() + " " + getPassword();}
		else {
			for (GroupDTO e : _groups)
				out += "\n" + getId() + " " + getLogin() + " " + getPassword() + " " + e.getName();
		}
		return out;
	}
	@Override
	public boolean equals(Object o){
		if (!(o instanceof UserDTO))return false;
		if (getId()==((UserDTO) o).getId() && getLogin().equals(((UserDTO) o).getLogin()) && getPassword().equals(((UserDTO) o).getPassword())){
			return true;
		}
		return false;
	}
}