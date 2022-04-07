package server.model;

import server.Role;
import server.Status;

public interface BasicAuthModel {

    String getLogin();

    String getPassword();

    Status getStatus();

    Role getRole();
}
