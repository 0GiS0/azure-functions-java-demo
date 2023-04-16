package com.function;

import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.sql.annotation.SQLOutput;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

public class SqlOutputBinding {
    /**
     * Visit Visit https://aka.ms/sqlbindingsoutput to learn how to use this output
     * binding
     * 
     * These tasks should be completed prior to running:
     * 1. Add com.microsoft.azure.functions:azure-functions-java-library-sql and
     * com.fasterxml.jackson.core:jackson-databind to your project dependencies
     * 2. Add an app setting named "SqlConnectionString" containing the connection
     * string to use for the SQL connection
     * 3. Change the bundle name in host.json to
     * "Microsoft.Azure.Functions.ExtensionBundle.Preview" and the version to "[4.*,
     * 5.0.0)"
     */
    @FunctionName("SqlOutputBinding")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req", methods = {
                    HttpMethod.POST }, authLevel = AuthorizationLevel.FUNCTION, route = "") HttpRequestMessage<Optional<String>> request,
            @SQLOutput(name = "output", commandText = "Products", connectionStringSetting = "SqlConnectionString") OutputBinding<Product> output)
            throws JsonParseException, JsonMappingException, IOException {

        String json = request.getBody().get();
        ObjectMapper mapper = new ObjectMapper();
        Product newProduct = mapper.readValue(json, Product.class);
        output.setValue(newProduct);

        return request.createResponseBuilder(HttpStatus.OK).header("Content-Type", "application/json").body(newProduct)
                .build();
    }

    public static class Product {
        public Integer Id;
        public String Name;
        public String Description;
        public Float Price;

        public Product() {
        }

        public Product(Integer id, String name, String description, Float price) {
            Id = id;
            Name = name;
            Description = description;
            Price = price;
        }

        public Integer getId() {
            return Id;
        }

        public void setId(Integer id) {
            Id = id;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String description) {
            Description = description;
        }

        public Float getPrice() {
            return Price;
        }

        public void setPrice(Float price) {
            Price = price;
        }
    }
}