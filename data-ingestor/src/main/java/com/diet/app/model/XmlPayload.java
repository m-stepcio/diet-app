package com.diet.app.model;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

public class XmlPayload implements Payload{

    @Override
    public long getSize() {
        return 0;
    }

    @Override
    public Set<String> getKeySet() {
        return Set.of();
    }

    @Override
    public Stream<PayloadObject> stream() {
        return Stream.empty();
    }

    @Override
    public Iterator<PayloadObject> getIterator() {
        return null;
    }

    @Override
    public PayloadObject getFirst() {
        return null;
    }
}
