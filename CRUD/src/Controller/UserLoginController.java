package Controller;
import java.awt.event.*;
import javax.swing.JOptionPane;
import Model.*;
import VIEW.*;

public class UserLoginController {
    private UserView view;
    private UserModel model;

    public UserLoginController(UserView view, UserModel model, UserInterface application) {
        this.view = view;
        this.model = model;

        this.view.signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (view.usernameField.getText().isEmpty() || view.passwordField.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "Username or Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    return; 
                }

                String username = view.usernameField.getText();
                String password = String.valueOf(view.passwordField.getPassword());
                if (model.addUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "You have logged in successfully!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "You have entered an invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        this.view.loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = view.usernameField.getText();
                String password = String.valueOf(view.passwordField.getPassword());

                if (model.checkUser(username, password)) {
                    application.setVisible(true); 
                    view.setVisible(false); 
                } else {
                    JOptionPane.showMessageDialog(null, "Please log in first. If you are already logged in, you probably provided the wrong username or password. Please revise your credentials.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
