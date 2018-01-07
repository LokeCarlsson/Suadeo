package suadeo;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SuadeoController {

    private Users users = new Users();

    @RequestMapping("/users")
    public String users() throws IOException {
        if (users.size() <= 0) {
            users.generate();
        }
        return "hej";
    }

    @RequestMapping("/euclidean/user")
    public String userEuclidean(@RequestParam(value="user", defaultValue="") int user) throws IOException, URISyntaxException {
        Recomender recomender = new Recomender();
        recomender.userEuclidean(user);

        // Generate the display
        SuadeoView view = new SuadeoView();
        return view.show();
    }

    @RequestMapping("/euclidean/item")
    public String itemEuclidean(@RequestParam(value="user", defaultValue="") int user) throws IOException, URISyntaxException {
        Recomender recomender = new Recomender();
        recomender.itemEuclidean(user);

        // Generate the display
        SuadeoView view = new SuadeoView();
        return view.show();
    }
}
