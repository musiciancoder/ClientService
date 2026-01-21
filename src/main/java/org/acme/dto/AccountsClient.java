package org.acme.dto;

import jakarta.enterprise.context.ApplicationScoped;
import org.acme.rest.AccountServiceRest;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class AccountsClient {

    @RestClient
    AccountServiceRest rest;

    public void createAccount(Long id, String name) {
        rest.createAccount(new NewCustomerRequest(id, name));
    }

}