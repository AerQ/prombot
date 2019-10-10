package prombot.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import prombot.model.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchService {

    public List<Product> marshall(String url) {
        List<Product>productInfos = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "x-page x-product-page");
            for (Element element : elements) {
                String name = element.select("h1").text();
                String price = element.getElementsByAttributeValue("class", "x-product-price").get(0).text();
                String availability = element.getElementsByClass("x-product-presence").text();
                String color = element.getElementsByClass("x-attributes__value").get(6).text();
                String description = element.getElementsByClass("x-user-content").text();
                String vendorCode = element.select("span").get(1).text()+element.select("span").get(2).text();
                productInfos.add(new Product(name,price,availability,color,description,vendorCode));
                return productInfos;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return productInfos;
    }

    public void vendorCode(String url){
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.getElementsByAttributeValue("class", "x-page x-product-page");
            for (Element element : elements) {

                String vendorCode = element.select("span").get(1).text()+element.select("span").get(2).text();
                System.out.println(vendorCode);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
