package server;

import common.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import server.model.BasicModel;

@AllArgsConstructor
@Data
public class BasicModelDto {
    private BasicModel basicModel;
    private Type type;


}
