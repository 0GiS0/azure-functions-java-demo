package com.function;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.sql.annotation.SQLInput;

import java.util.Optional;

public class SqlInputBinding {
    /**
     * Visit https://aka.ms/sqlbindingsinput to learn how to use this input binding
     * 
     * These tasks should be completed prior to running:
     * 1. Add com.microsoft.azure.functions:azure-functions-java-library-sql to your project dependencies
     * 2. Add an app setting named "SqlConnectionString" containing the connection string to use for the SQL connection
     * 3. Change the bundle name in host.json to "Microsoft.Azure.Functions.ExtensionBundle.Preview" and the version to "[4.*, 5.0.0)"
     * 4. check this samples: https://github.com/Azure/azure-functions-sql-extension/tree/main/samples/samples-java/src/main/java/com/function
     */
    @FunctionName("SqlInputBinding")
    public HttpResponseMessage run(
            @HttpTrigger(
                name = "req",
                methods = {HttpMethod.GET},
                authLevel = AuthorizationLevel.FUNCTION,
                route = "")
                HttpRequestMessage<Optional<String>> request,
            @SQLInput(
                name = "result",
                commandText = "SELECT * FROM Products",
                commandType = "Text",
                connectionStringSetting = "SqlConnectionString")
                Object[] result) {

        return request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "application/json").body(result).build();
    }
}