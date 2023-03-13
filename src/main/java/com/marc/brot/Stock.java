package com.marc.brot;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class Stock {

    public long userid;

    public long itemid;

    public Double quantity;

    public String unit;
}
