package com.treetopcommons.models;

import java.util.List;

/**
 *
 */
public class OrganizationCollection {

    private List<Organization> organizations;

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
    }

    public OrganizationCollection(List<Organization> organizations) {
        this.organizations = organizations;
    }
}
