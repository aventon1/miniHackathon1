import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalesPersonLookUp {
    public static void main(String[] args) throws IOException {

        String salesRepFilePath = "SalesReps.csv";
        String companiesFilePath = "Company.csv";

        // get sales rep data
        HashMap<String, SalesRep> salesRepsData = getSalesReps(salesRepFilePath);

        // get company data
        HashMap<String, ArrayList<Company>> companyData = getCompanies(companiesFilePath, salesRepsData);

        // get input from user
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello! Welcome to the sales representative lookup.");
        System.out.println("In order to look up by sales rep, you must have the sales rep last name and date of birth.");
        System.out.println();
        System.out.println("Please enter sales rep last name: ");
        String userInputLastName = sc.nextLine();
        System.out.println("Please enter sales rep dob in this format YYYY-DD-DD: ");
        String userInputDOB = sc.nextLine();

        // find company info based on user input
        ArrayList<Company> employeeCompanyList = findCompanyList(salesRepsData, companyData, userInputLastName, userInputDOB);

        // print company info to console
        printCompanyInfo(employeeCompanyList);
    }

    public static HashMap<String, SalesRep> getSalesReps(String file) throws IOException {
        // hashmap to store employee data
        HashMap<String, SalesRep> salesRepData = new HashMap();

        // read in sales rep file
        File salesRepFile = new File(file);

        BufferedReader reader = new BufferedReader(new FileReader(salesRepFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // get sales rep fields
            String userID = data[0];
            String firstName = data[1];
            String lastName = data[2];
            String email = data[3];
            String phoneNumber = data[4];
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

            // get company fields
            // ignore Index data[0]
            String companyId = data[1];
            String userId = data[2];
            String companyName = data[3];
            String website = data[4];
            String country = data[5];
            String description = data[6];
            String foundedDate = data[7];
            String industry = data[8];
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

    public static ArrayList<Company> findCompanyList(HashMap<String, SalesRep> salesRepsData,
                                                     HashMap<String, ArrayList<Company>> companyData,
                                                     String userInputLastName, String userInputDOB) {

        ArrayList<Company> employeeCompanyList = null;

        for (Map.Entry<String, SalesRep> element: salesRepsData.entrySet()) {
            String userID = element.getKey();
            SalesRep rep = element.getValue();
            String lastName = rep.getLastName();
            String dob = rep.getDob();

            if (lastName.equalsIgnoreCase(userInputLastName) && dob.equalsIgnoreCase(userInputDOB)) {
                employeeCompanyList = companyData.get(userID);

            }
        }
        return employeeCompanyList;
    }

    public static void printCompanyInfo(ArrayList<Company> employeeCompanyList) {

        if (employeeCompanyList != null) {
            System.out.println("Employee found!");
            for (Company comp : employeeCompanyList) {
                System.out.println("Organization Id: " + comp.getCompanyId());
                System.out.println("Company Name: " + comp.getCompanyName());
                System.out.println("Country: " + comp.getCountry());
                System.out.println("Industry: " + comp.getIndustry());
                System.out.println("Number of employees: " + comp.getNumEmployees());
                System.out.println();
            }
        } else {
            System.out.println("Employee not found.");
        }
    }
}