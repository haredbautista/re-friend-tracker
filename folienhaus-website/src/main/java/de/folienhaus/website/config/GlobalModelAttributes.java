package de.folienhaus.website.config;

import de.folienhaus.website.domain.NavItem;
import de.folienhaus.website.service.ContentService;
import de.folienhaus.website.web.StructuredData;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.Year;
import java.util.List;

/**
 * Stellt Firmendaten und Navigation jedem Template zur Verfügung.
 */
@ControllerAdvice
public class GlobalModelAttributes {

    private final CompanyProperties company;
    private final ContentService content;

    public GlobalModelAttributes(CompanyProperties company, ContentService content) {
        this.company = company;
        this.content = content;
    }

    @ModelAttribute("company")
    public CompanyProperties company() {
        return company;
    }

    @ModelAttribute("navItems")
    public List<NavItem> navItems() {
        return content.navigation();
    }

    @ModelAttribute("allServices")
    public List<de.folienhaus.website.domain.ServiceOffering> allServices() {
        return content.services();
    }

    @ModelAttribute("allRegions")
    public List<de.folienhaus.website.domain.Region> allRegions() {
        return content.regions();
    }

    @ModelAttribute("currentPath")
    public String currentPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("structuredData")
    public String structuredData() {
        return StructuredData.localBusiness(company);
    }

    @ModelAttribute("currentYear")
    public int currentYear() {
        return Year.now().getValue();
    }
}
