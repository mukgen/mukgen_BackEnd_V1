package oauth2.dto.response;

import lombok.Getter;

import java.util.List;

@Getter
public class ResourceResponse {
    Object data;
    List<String> scopeList;

}
