package web.app.viago.services;

import web.app.viago.dao.company.CompanyDAO;
import web.app.viago.dao.company.CompanyDaoImpl;
import web.app.viago.model.Company;

import java.util.List;

public class CompanyService {
    private final CompanyDAO companyDAO;

    public CompanyService() {
        this.companyDAO = new CompanyDaoImpl();
    }


    public boolean createCompany(Company company) {
        try {
            return companyDAO.create(company);
        } catch (Exception e) {
            System.out.println("Error creating Company: " + e.getMessage());
            return false;
        }
    }

    public Company getCompanyById(int id) {
        try {
            return companyDAO.findById(id);
        } catch (Exception e) {
            System.out.println("Error retrieving company by ID: " + e.getMessage());
        }
        return null;
    }

    public Company getCompanyByEmail(String email) {
        try {
            return companyDAO.findByEmail(email);
        } catch (Exception e) {
            System.out.println("Error retrieving company by ID: " + e.getMessage());
        }
        return null;
    }

    public void updateCompany(Company company) {
        try {
            companyDAO.update(company);
        } catch (Exception e) {
            System.out.println("Error updating company: " + e.getMessage());
        }
    }

    public void deleteUser(int id) {
        try {
            companyDAO.delete(id);
        } catch (Exception e) {
            System.out.println("Error deleting company: " + e.getMessage());
        }
    }


    public List<Company> getAllCompanies() {
        try {
            List<Company> companies = companyDAO.getAllCompanies();
            if (companies != null) {
                return companies;
            } else {
                System.out.println("No companies found.");
            }

        } catch (Exception e) {
            System.out.println("Error retrieving all companies: " + e.getMessage());
        }
        return null;
    }

}
