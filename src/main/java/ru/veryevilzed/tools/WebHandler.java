package ru.veryevilzed.tools;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.StringLoader;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.veryevilzed.tools.ext.Extensions;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Date;

/**
 * Created by zed on 21.08.16.
 */
@Controller
@Scope("session")
@SessionAttributes(types = ModelMap.class)
@Slf4j
public class WebHandler {

    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public String index(ModelMap map, HttpSession session) {
        if (session !=null) {
            map.put("code", session.getAttribute("code"));
            map.put("source", session.getAttribute("source"));
            log.info("SessionMapIs NOT Null: {}", session.getId());

        }else
            log.info("SessionMapIsNull");
        return "index";
    }


    @RequestMapping(path = "/", method = {RequestMethod.POST})
    public String generate(@RequestParam(name="editor_code_for_send") String code, ModelMap map, HttpSession session) {

        map.put("code", code);
        map.put("date", new Date());

        try {
            PebbleEngine engine = new PebbleEngine.Builder()
                    .loader(new StringLoader())
                    .extension(new Extensions(map))
                    .build();

            val writer = new StringWriter();
            engine.getTemplate(code).evaluate(writer, map);
            val result = writer.toString();
            map.put("source", result);

            if (session!=null) {
                session.setAttribute("code", code);
                session.setAttribute("source", result);
            }

        }catch (PebbleException pebbleException) {
            map.put("error", pebbleException.getMessage());
        }catch (IOException exception) {
            map.put("error", exception.getMessage());
        }


        return "index";
    }


    @RequestMapping(path = "/test", method = {RequestMethod.GET})
    public String redirectTest(ModelMap map) {
        return "redirect:" + "/";
    }



}
