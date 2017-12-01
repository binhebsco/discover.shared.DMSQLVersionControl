package com.ebsco.ese.dmsqlversioncontrol.dmsql;

public enum SPNames {
    //
    DMSQL_GetReleaseHistory	("DMSQL_GetReleaseHistory"),
    DMSQL_GetCurrentVersion ("DMSQL_GetCurrentVersion"),
    DMSQL_CheckIfUpgradeAllowed	("DMSQL_CheckIfUpgradeAllowed"),
    DMSQL_CheckIfRollbackAllowed ("DMSQL_CheckIfRollbackAllowed");

    private String spName;

    private SPNames(final String spName) {
        this.spName = spName;
    }

    public String getName() {
        return spName;
    }
}