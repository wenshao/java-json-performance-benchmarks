package com.cowtowncoder.jsonperf.dzone.read;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import ca.fuzzlesoft.JsonParse;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJsonParse extends DZoneReadTestBase<Map<?,?>>
{
    protected final JsonParse parse;

    public DZoneReadMapJsonParse()
    {
        parse = new JsonParse();
    }

    @Override
    public Map<?,?> _readItems(byte[] input) throws Exception {
        // 04-Jan-2016, tatu: Alas, this library provides no other input source abstraction,
        //    so we need to do this...
        return _readItems(new String(input, "UTF-8"));
    }

    @Override
    public Map<?,?> _readItems(String input) throws Exception {
        return parse.map(input);
    }
}
