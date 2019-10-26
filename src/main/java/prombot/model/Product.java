package prombot.model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.List;

@XmlRootElement(name = "products")
public class Product {
    private String name;
    private String price;
    private String availability;
    private String color;
    private String description;
    private String vendorCode;
    private List<Product> products;

    private Document JsoupConnect(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }

    public Product(String url) throws IOException {
        name = getTitle(url);
        price = getPrice(url);
        availability = getAvailability(url);
        color = getColor(url);
        description = getDescription(url);
        vendorCode = getVendorCode(url);
    }

    public String getTitle(String url) throws IOException {
        String named = JsoupConnect(url).getElementsByAttributeValue("class", "x-page x-product-page").select("h1").text();
        return named;
    }

    public String getPrice(String url) throws IOException {
        String price = JsoupConnect(url).getElementsByAttributeValue("class", "x-product-price").get(0).text();
        return price;
    }

    public String getAvailability(String url) throws IOException {
        String ava = JsoupConnect(url).getElementsByClass("x-product-presence").text();
        return ava;
    }

    public String getColor(String url) throws IOException {
        String color = JsoupConnect(url).getElementsByClass("x-attributes__value").get(6).text();
        return color;
    }

    public String getDescription(String url) throws IOException {
        String descriptions = JsoupConnect(url).getElementsByClass("x-user-content").text();
        return descriptions;
    }
    public String getVendorCode(String url) throws IOException {
        String vend = JsoupConnect(url).getElementsByClass("x-product-info__identity-item").select("span").get(1).text();
        return vend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "product")
    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    private static int count = 0;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("=============================================" + "\n");
        sb.append(count++ + ")");
        sb.append(name).append("\n");
        sb.append(price).append("\n");
        sb.append(availability).append("\n");
        sb.append(color).append("\n");
        sb.append(description).append("\n");
        sb.append(vendorCode).append("\n");
        sb.append("=============================================" + "\n");
        return sb.toString();
    }
}
