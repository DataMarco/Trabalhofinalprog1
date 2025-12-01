package com.vicenzo.kiosk.model.atendimento;

import java.util.concurrent.atomic.AtomicInteger;

public final class NumeroAtendimentoGenerator {
    private static final AtomicInteger SEQ = new AtomicInteger(1000);
    private NumeroAtendimentoGenerator() {}
    public static int proximo() { return SEQ.incrementAndGet(); }
}

