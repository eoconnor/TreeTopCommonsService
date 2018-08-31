package com.treetopcommons.models;

/**
 *
 */
public class Organization {

    private String id;
    private String name;
    private String city;
    private String state;
    private String postal;
    private String category;

    public Organization(String id, String name, String city, String state, String postal, String category) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.state = state;
        this.postal = postal;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Compare this organization to the input instance, which can have any subset of it's field populated.
     * Only compare fields that are populated; ignore those that are null. Comparison is case-insensitive,
     * with the exception of id, which is expected to be numeric.
     *
     * @param filterOrg An Organization instance with some subset of it's fields (including potentially none) populated.
     * @return A boolean indicating whether or not the fields in the input instance match the values on this instance.
     */
    public boolean matches(Organization filterOrg) {
        return (filterOrg.getId() == null || id.equals(filterOrg.getId())) &&
                (filterOrg.getName() == null || name.equalsIgnoreCase(filterOrg.getName())) &&
                (filterOrg.getCity() == null || city.equalsIgnoreCase(filterOrg.getCity())) &&
                (filterOrg.getState() == null || state.equalsIgnoreCase(filterOrg.getState())) &&
                (filterOrg.getPostal() == null || postal.equalsIgnoreCase(filterOrg.getPostal())) &&
                (filterOrg.getCategory() == null || category.equalsIgnoreCase(filterOrg.getCategory()));
    }

}
