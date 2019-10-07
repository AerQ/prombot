package prombot.util;

import prombot.controllers.SearchService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
@XmlRootElement(name = "products")
public class JaxBWriter {

        private List<SearchService> products;

        @XmlElement(name = "product")
        public List<SearchService> getPersons() {
            return products;
        }

        public void setProducts(List<SearchService> products) {
            this.products = products;
        }
    }