package org.acme.client;

import io.smallrye.mutiny.Multi;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import org.acme.reactive.crud.Fruit;


public class Client {
    public Long id;

    public String name;

    public String lastName;

    public Client() {
        // default constructor.
    }

    public Client(String name) {
        this.name = name;
    }

    public Client(Long id, String name, String lastName) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
    }
    public static Multi<Client> findAll(PgPool client) {
        return client.query("SELECT id, name, lastName FROM client ORDER BY name ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Client::from);
    }

    private static Client from(Row row) {
        return new Client(row.getLong("id"), row.getString("name"),row.getString("lastName"));
    }

}
