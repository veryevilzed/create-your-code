package ru.veryevilzed.tools;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.loader.StringLoader;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import com.mitchellbosecke.pebble.template.PebbleTemplateImpl;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.veryevilzed.tools.ext.Extensions;
import ru.veryevilzed.tools.ext.XRanges;

import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by zed on 21.08.16.
 */
@Controller
public class WebHandler {

    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public String index(ModelMap map) {
        return "index";
    }




    @RequestMapping(path = "/", method = {RequestMethod.POST})
    public String generate(@RequestParam(name="editor_code_for_send") String code, ModelMap map) throws IOException {


        map.put("code", code);

        try {
            PebbleEngine engine = new PebbleEngine.Builder()
                    .loader(new StringLoader())
                    .extension(new Extensions())
                    .build();

            val writer = new StringWriter();
            engine.getTemplate(code).evaluate(writer, map);
            val result = writer.toString();
            map.put("source", result);
        }catch (PebbleException pebbleException) {
            map.put("error", pebbleException.getMessage());
        }catch (Exception exception) {
            map.put("error", exception.getMessage());
        }

        return "index";
    }




}
