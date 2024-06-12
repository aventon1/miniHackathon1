import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SalesRepLookUp {
    public static void main(String[] args) throws IOException {

        String salesRepFilePath = "C:\\Users\\AminaVenton\\IdeaProjects\\Classwork\\src\\miniHackathon\\SalesReps.csv";
        String companiesFilePath = "C:\\Users\\AminaVenton\\IdeaProjects\\Classwork\\src\\miniHackathon\\Company.csv";

        // get sales rep data
        HashMap<String, SalesRep> salesReps = getSalesReps(salesRepFilePath);

        // get company data
        HashMap<String, ArrayList<Company>> companyData = getCompanies(companiesFilePath, salesReps);

        // add company data to

        /*
        for (Map.Entry<String, SalesRep> element: salesReps.entrySet()) {
            String userID = element.getKey();
            SalesRep rep = element.getValue();
            String lastName = rep.getLastName();
            String dob = rep.getDob();
            System.out.println(userID + " " + rep.getLastName() + " " + dob);

            for (Map.Entry<String, Company> element: companyData.entrySet()) {
                String userID = element.getKey();
                Company comp = element.getValue();
                System.out.println(userID + " " + comp.getCompanyId() + " " + comp.getCountry() + " " + comp.getIndustry() + " " + comp.getNumEmployees());
            }
        }
        */

        String lastName = "Andrews";
        String dob = "1953-12-21";


        // TODO: get user input, iterate through data structures to find company info
        // CODE GOES HERE

        // TODO: print company info to console
        // CODE GOES HERE

    }

    public static HashMap<String, SalesRep> getSalesReps(String file) throws IOException {
        // hashmap for to store employee data
        HashMap<String, SalesRep> salesRepData = new HashMap();

        // read in sales rep file
        File salesRepFile = new File(file);

        BufferedReader reader = new BufferedReader(new FileReader(salesRepFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // User Id
            String userID = data[0];
            // First Name,
            String firstName = data[1];
            // Last Name
            String lastName = data[2];
            // Email,
            String email = data[3];
            // Phone,
            String phoneNumber = data[4];
            // Date of birth
            String dateOfBirth = data[5];

            // create salesRep object
            SalesRep salesRep = new SalesRep(userID, firstName, lastName, email, phoneNumber, dateOfBirth);

            // store in HashMap
            salesRepData.put(userID, salesRep);
        }
        reader.close();

        return salesRepData;
    }

    public static HashMap<String, ArrayList<Company>> getCompanies(String file, HashMap<String, SalesRep> salesReps) throws IOException {
        HashMap<String, ArrayList<Company>> companyData = new HashMap();

        // read in company file
        File companyFile = new File(file);

        BufferedReader reader1 = new BufferedReader(new FileReader(companyFile));
        String line1 = "";
        while ((line1 = reader1.readLine()) != null) {
            String[] data = line1.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            // Ignore Index data[0]
            // Organization Id,
            String companyId = data[1];
            // Sales RepId,
            String userId = data[2];
            // Name,
            String companyName = data[3];
            // Website,
            String website = data[4];
            // Country,
            String country = data[5];
            // Description,
            String description = data[6];
            // Founded,
            String foundedDate = data[7];
            // Industry,
            String industry = data[8];
            // Number of employees
            String numOfEmployees = data[9];

            // make Company Object
            Company company = new Company(companyId, userId, companyName, website, country,
                    description, foundedDate, industry, numOfEmployees);

            // store in HashMap
            if (companyData.containsKey(userId)) {
                // get list and add
                ArrayList<Company> companyList = companyData.get(userId);
                companyList.add(company);
            } else {
                // add Company to a list
                ArrayList<Company> companies = new ArrayList<Company>();
                companies.add(company);
                companyData.put(userId, companies);
            }
        }
        reader1.close();

        return companyData;
    }

}