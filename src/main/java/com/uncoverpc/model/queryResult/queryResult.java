package com.uncoverpc.model.queryResult;

import java.util.ArrayList;
import java.util.Map;

public class queryResult {

        public String title;
        public String imageLink;
        public String purchaseLink;
        public String price;
        public String lastUpdated;
        public ArrayList<Map<String, String>> articles;

        public queryResult(String title, String imageLink, String purchaseLink, String price, String lastUpdated, ArrayList<Map<String, String>> articles) {
            this.title = title;
            this.imageLink = imageLink;
            this.purchaseLink = purchaseLink;
            this.price = price;
            this.lastUpdated = lastUpdated;
            this.articles = articles;
    }
}
