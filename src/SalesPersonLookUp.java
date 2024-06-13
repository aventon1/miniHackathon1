import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
@author Amina Venton
 *
**/

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
        sc.close();

        // get company info based on user input
        ArrayList<Company> employeeCompanyList = findCompanyList(salesRepsData, companyData, userInputLastName, userInputDOB);

        // print company info to console
        printCompanyInfo(employeeCompanyList);
    }

    public static HashMap<String, SalesRep> getSalesReps(String file) throws IOException {

        HashMap<String, SalesRep> salesRepData = new HashMap();

        File salesRepFile = new File(file);

        BufferedReader reader = new BufferedReader(new FileReader(salesRepFile));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            // create salesRep object
            SalesRep salesRep = new SalesRep(data[0], data[1], data[2], data[3], data[4], data[5]);

            // store in HashMap
            salesRepData.put(data[0], salesRep);
        }
        reader.close();

        return salesRepData;
    }

    public static HashMap<String, ArrayList<Company>> getCompanies(String file, HashMap<String,
            SalesRep> salesReps) throws IOException {

        HashMap<String, ArrayList<Company>> companyData = new HashMap();

        File companyFile = new File(file);

        BufferedReader reader = new BufferedReader(new FileReader(companyFile));
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");

            // make Company Object
            Company company = new Company(data[1], data[2], data[3], data[4], data[5],
                    data[6], data[7], data[8], data[9]);

            // store in HashMap
            if (companyData.containsKey(data[2])) {
                ArrayList<Company> companyList = companyData.get(data[2]);
                companyList.add(company);
            } else {
                ArrayList<Company> companies = new ArrayList<Company>();
                companies.add(company);
                companyData.put(data[2], companies);
            }
        }
        reader.close();

        return companyData;
    }

    public static ArrayList<Company> findCompanyList(HashMap<String, SalesRep> salesRepsData,
                                                     HashMap<String, ArrayList<Company>> companyData,
                                                     String userInputLastName, String userInputDOB) {

        ArrayList<Company> employeeCompanyList = null;

        // find user input
        for (Map.Entry<String, SalesRep> element: salesRepsData.entrySet()) {

            if (element.getValue().getLastName().equalsIgnoreCase(userInputLastName)
                    && element.getValue().getDob().equals(userInputDOB)) {

                employeeCompanyList = companyData.get(element.getKey());
            }
        }
        return employeeCompanyList;
    }

    public static void printCompanyInfo(ArrayList<Company> employeeCompanyList) {

        // get company details and print
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