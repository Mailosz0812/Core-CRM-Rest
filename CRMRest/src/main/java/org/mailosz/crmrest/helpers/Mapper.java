package org.mailosz.crmrest.helpers;

public interface Mapper<A,B> {
    A mapTo(B b);
    B mapFrom(A a);
}
