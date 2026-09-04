package com.diet.app.model;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public interface Payload {

    long getSize();

    Set<String> getKeySet();

    Stream<PayloadObject> stream();

    Iterator<PayloadObject> getIterator();

    PayloadObject getFirst();
}
