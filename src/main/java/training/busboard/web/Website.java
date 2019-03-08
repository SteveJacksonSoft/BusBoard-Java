package training.busboard.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import training.busboard.exceptionUtil.PostcodeRequestException;
import training.busboard.exceptionUtil.TflRequestException;

@Controller
@EnableAutoConfiguration
public class Website {
    private WebManager webManager = new WebManager();
    private final int numBusesToPrint = 5;

    @RequestMapping("/")
    ModelAndView home() {
        return new ModelAndView("index", "indexInfo", new IndexInfo());
    }

    @RequestMapping("/busInfo")
    ModelAndView busInfo(@RequestParam("postcode") String postcode) {
        try {
            return new ModelAndView("info", "busInfo", webManager.getBusInfoFromPostcode(postcode, numBusesToPrint)) ;
        } catch (PostcodeRequestException e) {
            return this.error("You have entered an invalid postcode.");
        } catch (TflRequestException e) {
            return this.error("There was a problem getting information from TfL.");
        }
    }

    private ModelAndView error(String errorMessage) {
        return new ModelAndView("index", "indexInfo", new IndexInfo(errorMessage));
    }


    public static void main(String[] args) throws Exception {
        SpringApplication.run(Website.class, args);
    }

}