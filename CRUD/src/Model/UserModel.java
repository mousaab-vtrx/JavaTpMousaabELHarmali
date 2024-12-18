package Model;
import DAO.*;
public class UserModel {
	private UserDAOimplement dao;
	public UserModel(UserDAOimplement dao){
		this.dao = dao;
	}
	public boolean addUser(String username,String password) {
		if(password.length() != 8) {
			System.err.println("you have entered"+password.length()+" charcters!!! your password should consist of 8 characters");
			return false;
		}
		System.out.println(username+" "+password);
		User user = new User(username,password);
		dao.addUser(user);
		return true;
	}
	public boolean checkUser(String username,String password) {
		if(password.length() != 8) {
			System.err.println("you have entered"+password.length()+" charcters!!! your password should consist of 8 characters");
			return false;
		}
		User user = new User(username,password);
		return dao.checkUser(user);
	}

}
