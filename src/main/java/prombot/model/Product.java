package prombot.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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

    public Product(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public Product(){
        this(null,null,null,null,null,null);
    }

    public Product(String name, String price, String availability, String color, String description,String vendorCode) {
        this.name = name;
        this.price =price ;
        this.availability = availability;
        this.color = color;
        this.description =description;
        this.vendorCode=vendorCode;
    }

//    public Product()  {
//      name = new SimpleStringProperty(name);
//        price = new SimpleStringProperty(getPrice());
//        availability = new SimpleStringProperty(getAvailability());
//        color = new SimpleStringProperty(getColor());
//      description = new SimpleStringProperty(getDescription());
//    }

//    public String getName()  {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//             String a = element.select("h1").text();
//            System.out.println(a);
//        }
//        return null;
//    }
//
//    public String getPrice() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//           String  price = element.getElementsByAttributeValue("class", "x-product-price").get(0).text();
//            System.out.println(price);
//        }
//        return null;
//    }
//
//    public String getAvailability() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//           String availability = element.getElementsByClass("x-product-presence").text();
//            System.out.println(availability);
//        }
//        return null;
//    }
//
//    public String  getColor() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//           String  color = element.getElementsByClass("x-attributes__value").get(6).text();
//            System.out.println(color);
//        }
//        return null;
//    }
//
//    public String  getDescription() {
//        for (Element element : doc.getElementsByAttributeValue("class", "x-page x-product-page")) {
//            String description = element.getElementsByClass("x-user-content").text();
//            System.out.println(description);
//        }
//        return null;
//    }

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

    private static  int count =0;
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("\n");
        sb.append("============================================="+"\n");
        sb.append(count++ +")");
        sb.append(name).append("\n");
        sb.append(price).append("\n");
        sb.append(availability).append("\n");
        sb.append(color).append("\n");
        sb.append(description).append("\n");
        sb.append(vendorCode).append("\n");
        sb.append("============================================="+"\n");
        return sb.toString();
    }
}
