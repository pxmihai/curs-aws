package cursaws.webexample.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cursaws.webexample.service.Greeting;
import cursaws.webexample.service.SampleService;
import cursaws.webexample.service.SampleServiceImpl;

@Controller
@EnableAutoConfiguration
@ComponentScan
public class SampleController extends WebMvcConfigurerAdapter {

  private SampleService sampleService = new SampleServiceImpl();
  private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {"classpath:/static/"};

  @RequestMapping(value = "/greet", method = RequestMethod.GET)
  @ResponseBody
  public Greeting greet(@RequestParam(value = "name", required = false) String name) {
    if (name == null || name.isEmpty()) {
      return sampleService.createGreeting("world");
    } else {
      return sampleService.createGreeting(name);
    }
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    if (!registry.hasMappingForPattern("/static/**")) {
      registry.addResourceHandler("/static/**").addResourceLocations(CLASSPATH_RESOURCE_LOCATIONS);
    }
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(SampleController.class, args);
  }
}