package com.coder4.mvndeptree.flatparser;

import java.util.ArrayList;
import java.util.List;

public class FlatParseResult {

    List<FlatParseItem> items = new ArrayList<>();

    public List<FlatParseItem> getItems() {
        return items;
    }

    public void addItem(FlatParseItem item) {
        items.add(item);
    }
}
