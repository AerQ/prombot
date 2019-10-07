package prombot.controllers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import prombot.util.ProductParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<ProductParser> marshall(String url) {
        List<ProductParser>productInfos = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "x-page x-product-page");
            for (Element element : elements) {
                String name = element.select("h1").text();
                String price = element.getElementsByAttributeValue("class", "x-product-price").get(0).text();
                String availability = element.getElementsByClass("x-product-presence").text();
                String color = element.getElementsByClass("x-attributes__value").get(6).text();
                String description = element.getElementsByClass("x-user-content").text();
                productInfos.add(new ProductParser(name,price,availability,color,description));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productInfos;
    }
}
