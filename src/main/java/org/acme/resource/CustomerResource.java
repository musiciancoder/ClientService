package org.acme.resource;

import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.dto.AccountsClient;
import org.acme.dto.CustomerRequest;
import org.acme.entity.Customer;
import org.acme.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    AccountsClient accounts;
    private final Logger logger = LoggerFactory.getLogger(AccountsClient.class);
    private final CustomerRepository repo;

    public CustomerResource(CustomerRepository repo) {
        this.repo = repo;
    }

    @GET
    @Path("/{id}")
    @PermitAll
    public Response get(@PathParam("id") Long id) {
        return repo.findByIdOptional(id).map(Response::ok)
                .orElse(Response.status(404))
                .build();
    }

    @POST
    @Path("/create")
    @Transactional
    @PermitAll
    public Response create(CustomerRequest request) {
        Customer customer = new Customer();
        customer.name = request.name;
        customer.nationalId = request.nationalId;
        customer.email = request.email;
        customer.status = request.status;
        repo.persist(customer);
        logger.info("Creating account for customer id {} name {}", customer.id, customer.name);
        accounts.createAccount(customer.id, customer.name);
        return Response.created(URI.create("/customers/" + customer.id))
                .entity(customer)
                .build();
    }

}