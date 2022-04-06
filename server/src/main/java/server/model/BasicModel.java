package server.model;

import server.auth.Role;
import server.auth.Status;

public interface BasicModel {

    String getLogin();

    String getPassword();

    Status getStatus();

    Role getRole();
}
