package com.treetopcommons.resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.treetopcommons.models.Organization;
import com.treetopcommons.models.OrganizationCollection;
import com.treetopcommons.persistence.OrganizationDatabase;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;

/**
 *
 */
@Path("organizations")
public class OrganizationsResource {

    private ObjectMapper jsonMapper = new ObjectMapper();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrganizations(
            @QueryParam("id") String id,
            @QueryParam("name") String name,
            @QueryParam("city") String city,
            @QueryParam("state") String state,
            @QueryParam("postal") String postalCode,
            @QueryParam("category") String category,
            @QueryParam("orderBy") String orderBy,
            @QueryParam("direction") String direction
    ) {
        Response response;

        // Create an instance comprised of the input query params; use it to filter the organizations
        Organization filterOrg = new Organization(id, name, city, state, postalCode, category);
        List<Organization> orgList = OrganizationDatabase.getInstance().getOrganizations(filterOrg);

        if (orderBy != null) {
            // Sort the results if an orderBy is specified
            Comparator<Organization> orgComparator = getComparator(orderBy);
            if (direction != null && direction.equalsIgnoreCase("dsc")) {
                orgList.sort(orgComparator.reversed());
            } else {
                // Default is ASC
                orgList.sort(orgComparator);
            }
        }

        try {
            // Build the success response
            OrganizationCollection orgCollection = new OrganizationCollection(orgList);
            response = Response
                    .status(Response.Status.OK)
                    .entity(jsonMapper.writeValueAsString(orgCollection))
                    .build();
        } catch (JsonProcessingException e) {
            // TODO: Better error handling
            e.printStackTrace();
            response = Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }

        return response;
    }

    /**
     * Create a Comparator based on the sort field that the client specified
     *
     * @param orderBy The field that the client wants to sort the results by
     * @return A Comparator that will sort Organizations by the specified field
     */
    private Comparator<Organization> getComparator(String orderBy) {
        Comparator<Organization> orgComparator;

        switch (orderBy.toLowerCase()) {
            case "id":
                orgComparator = Comparator.comparing(o -> Integer.valueOf(o.getId()));
                break;
            case "name":
                orgComparator = Comparator.comparing(Organization::getName);
                break;
            case "city":
                orgComparator = Comparator.comparing(Organization::getCity);
                break;
            case "state":
                orgComparator = Comparator.comparing(Organization::getState);
                break;
            case "postal":
                orgComparator = Comparator.comparing(Organization::getPostal);
                break;
            case "category":
                orgComparator = Comparator.comparing(Organization::getCategory);
                break;
            default:
                // TODO: Bypass ordering if the orderBy parameter does not match one of the field names
                orgComparator = (o1, o2) -> 0;
        }

        return orgComparator;
    }
}
