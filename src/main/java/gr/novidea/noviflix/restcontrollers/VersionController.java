package gr.novidea.noviflix.restcontrollers;

import gr.novidea.noviflix.constants.VersionConstant;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @GetMapping(value = "/version", produces = MediaType.TEXT_PLAIN_VALUE)
    public String version(){
        return VersionConstant.SERVICE_VERSION;
    }

}
