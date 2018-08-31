package com.treetopcommons.persistence;

import com.treetopcommons.models.Organization;
import com.treetopcommons.util.CSVOrganizationParser;

import javax.inject.Singleton;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 */
@Singleton
public class OrganizationDatabase {

    private static final OrganizationDatabase instance = new OrganizationDatabase();

    private static final Logger logger = Logger.getLogger(OrganizationDatabase.class.getName());

    private static List<Organization> organizations;

    private OrganizationDatabase() {}

    public static OrganizationDatabase getInstance() {
        return instance;
    }

    /**
     * Retrieve a list of Organization instances from the in-memory list, filtered according to the
     * input instance. If the in-memory list has not yet been initialized yet (i.e., because this is
     * our first time accessing it), parse the CSV file to populate it.
     *
     * @param filterOrg An Organization instance with some subset of it's fields set. Used to filter the results.
     * @return A List of Organization instances that match the input filter instance.
     */
    public List<Organization> getOrganizations(Organization filterOrg) {
        if (organizations == null) {
            // Initialize in-memory database
            initializeDatabase();
        }

        // Filter organizations by input query params
        return organizations
                .stream()
                .filter(organization -> organization.matches(filterOrg))
                .collect(Collectors.toList());
    }

    /**
     * Parse the organization CSV file and persist the results to the in-memory List.
     *
     */
    private void initializeDatabase() {
        organizations = CSVOrganizationParser.parseFile();
        logger.log(Level.INFO, String.format("Initialized database with %s organizations", organizations.size()));
    }
}
