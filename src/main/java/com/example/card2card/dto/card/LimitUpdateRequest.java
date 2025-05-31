package com.example.card2card.dto.card;

import java.math.BigDecimal;

public class LimitUpdateRequest {

    private BigDecimal newLimit;

    public BigDecimal getNewLimit() {
        return newLimit;
    }

    public void setNewLimit(BigDecimal newLimit) {
        this.newLimit = newLimit;
    }
}
