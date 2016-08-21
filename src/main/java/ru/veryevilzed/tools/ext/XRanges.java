package ru.veryevilzed.tools.ext;

import com.mitchellbosecke.pebble.extension.Function;

import java.util.List;
import java.util.Map;

/**
 * Created by zed on 21.08.16.
 */
public class XRanges implements Function {

    @Override
    public Object execute(Map<String, Object> map) {
        return new int[] {0,1,2,3};
    }

    @Override
    public List<String> getArgumentNames() {
        return null;
    }
}
