package ru.veryevilzed.tools.ext;

import com.mitchellbosecke.pebble.extension.Function;

import java.util.List;
import java.util.Map;

/**
 * Created by zed on 21.08.16.
 */
public class CodeComment implements Function {

    final String code;

    @Override
    public Object execute(Map<String, Object> map) {
        return "/*\n" + this.code + "\n*/";
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }

    public CodeComment(String code) {
        super();
        this.code = code;
    }
}
