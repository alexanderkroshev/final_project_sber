package server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import server.ClientType;

@Data
@AllArgsConstructor
public class Client {
    private long id;
    private ClientType clientType;
}
