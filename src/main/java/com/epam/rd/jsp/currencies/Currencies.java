package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Currencies {
    private static final byte BIG_DECIMAL_SCALE = 5;
    private final Map<String, BigDecimal> curs = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        curs.put(currency, weight);
    }

    public Collection<String> getCurrencies() {
        return curs.keySet();
    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        BigDecimal refCurr = curs.get(referenceCurrency);
        return curs
            .entrySet()
            .stream()
            .collect(Collectors.toMap(Map.Entry::getKey,
                value -> refCurr.divide(value.getValue(), BIG_DECIMAL_SCALE, RoundingMode.HALF_UP),
                (m1, m2) -> m1, TreeMap::new));
    }

    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        return curs.get(sourceCurrency).multiply(amount)
            .divide(curs.get(targetCurrency), BIG_DECIMAL_SCALE, RoundingMode.HALF_UP);
    }
}
