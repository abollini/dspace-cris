package org.dspace.app.cris.statistics.bean;

import java.util.Hashtable;

public class TwoKeyMap extends Hashtable<Object, Hashtable<Object, Object>>
{
    public TwoKeyMap()
    {
        super();
    }

    public void addValue(Object key1, Object key2, Object value)
    {
        if (this.get(key1) == null)
        {
            this.put(key1, new Hashtable<Object, Object>());
        }
        Hashtable<Object, Object> tmpMap = this.get(key1);
        tmpMap.put(key2, value);
        this.put(key1, tmpMap);
    }
}
