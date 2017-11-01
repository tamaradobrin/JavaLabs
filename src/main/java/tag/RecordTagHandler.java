package tag;

import model.Country;
import repository.CountryRepository;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class RecordTagHandler extends SimpleTagSupport {

    private String key;
    private String category;
    private CountryRepository repository;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public RecordTagHandler() {
        repository = new CountryRepository();
    }

    @Override
    public void doTag() throws JspException, IOException {
        Country country = repository.getCountryByName(this.key);
        JspWriter out = getJspContext().getOut();
        out.print("<td>" + country.getCountryName() + "</td><td>" + country.getCapital()
                + "</td><td>" + country.getContinent() + "</td>");
    }
}
