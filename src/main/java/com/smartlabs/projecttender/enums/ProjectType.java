package com.smartlabs.projecttender.enums;

public enum ProjectType {
    residentialBuildings("residentialBuildings"),
    commercialBuildings("commercialBuildings"),
    industrialFacilities("industrialFacilities"),
    infrastructureProjects("infrastructureProjects");

    public final String value;

    ProjectType(String value) {
        this.value = value;
    }
}
