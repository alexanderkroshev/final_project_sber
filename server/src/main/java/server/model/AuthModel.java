package server.model;

import server.Role;
import server.Status;

public interface AuthModel {

    String getLogin();

    String getPassword();

    Status getStatus();

    Role getRole();
}
