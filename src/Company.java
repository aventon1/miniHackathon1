public class Company {
    private String companyId;
    private String userId;
    private String companyName;
    private String website;
    private String country;
    private String description;
    private String yearFounded;
    private String industry;
    private String numEmployees;

    // Constructor
    public Company(String companyId, String userId, String companyName, String website, String country, String description,
                   String yearFounded, String industry, String numEmployees) {

        this.companyId = companyId;
        this.userId = userId;
        this. companyName = companyName;
        this.website = website;
        this.country = country;
        this.description = description;
        this.yearFounded = yearFounded;
        this.industry = industry;
        this.numEmployees = numEmployees;
    }

    public String getCompanyId() {
        return companyId;
    }

    public String getUserId() {
        return userId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getWebsite() {
        return website;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public String getYearFounded() {
        return yearFounded;
    }

    public String getIndustry() {
        return industry;
    }


    public String getNumEmployees() {
        return numEmployees;
    }

    public void setNumEmployees(String numEmployees) {
        this.numEmployees = numEmployees;
    }

}