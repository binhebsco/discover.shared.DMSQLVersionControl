package com.ebsco.ese.dmsqlversioncontrol.dmsql;

import org.apache.ibatis.jdbc.ScriptRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.util.Map;

@Component
public class DMSQLDao {

    private final Logger LOGGER;
    private DataSource sqlDataSource;
//    private Connection sqlConnection;
    private SimpleJdbcCall jdbcCall_DMSQL_GetReleaseHistory;
    private SimpleJdbcCall jdbcCall_DMSQL_GetCurrentVersion;
    private SimpleJdbcCall jdbcCall_DMSQL_CheckIfUpgradeAllowed;
    private SimpleJdbcCall jdbcCall_DMSQL_CheckIfRollbackAllowed;

    @Autowired
    public DMSQLDao(JdbcTemplate template) {
        this.LOGGER = LoggerFactory.getLogger(this.getClass());
        sqlDataSource = template.getDataSource();

        jdbcCall_DMSQL_GetReleaseHistory = new SimpleJdbcCall(sqlDataSource).withProcedureName(SPNames.DMSQL_GetReleaseHistory.getName());
        jdbcCall_DMSQL_GetCurrentVersion = new SimpleJdbcCall(sqlDataSource).withProcedureName(SPNames.DMSQL_GetCurrentVersion.getName());
        jdbcCall_DMSQL_CheckIfUpgradeAllowed = new SimpleJdbcCall(sqlDataSource).withProcedureName(SPNames.DMSQL_CheckIfUpgradeAllowed.getName());
        jdbcCall_DMSQL_CheckIfRollbackAllowed = new SimpleJdbcCall(sqlDataSource).withProcedureName(SPNames.DMSQL_CheckIfRollbackAllowed.getName());
    }

    public void executeSQLScript(String sqlScriptFilePath) {

        try {
            Connection sqlConnection = sqlDataSource.getConnection();
            // Initialize object for ScripRunner
            ScriptRunner scriptRunner = new ScriptRunner(sqlConnection);  //, false, false);

            // Give the input file to Reader
            Reader reader = new BufferedReader(new FileReader(sqlScriptFilePath));

            // Exctute script
            scriptRunner.runScript(reader);

        } catch (Exception e) {
            System.err.println("Failed to Execute" + sqlScriptFilePath
                    + " The error is " + e.getMessage());
        }

    }

}
