package org.javadelight.log.values;

import java.util.List;

/**
 * A log which logs simple strings.
 * 
 * @author <a href="http://www.mxro.de">Max Rohde</a>
 *
 */
public interface StringLog {

    /**
     * <p>
     * Retrieve the logged entries.
     * 
     * @return
     */
    public List<String> entries();

    public void add(String message);

}