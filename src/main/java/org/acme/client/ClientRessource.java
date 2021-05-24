package org.acme.client;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import org.acme.reactive.crud.Fruit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientRessource {
    private final PgPool client;

    public ClientRessource(PgPool client) {
        this.client = client;
    }

    @GET
    public Multi get() {
        return Client.findAll(client);
    }

}

