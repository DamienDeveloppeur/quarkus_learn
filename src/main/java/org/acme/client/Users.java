package org.acme.client;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

public class Users {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getAge() {
        return age;
    }

    public void setAge(long age) {
        this.age = age;
    }

    private Long id;

    private String name;

    private long age;

    public Users() {
        // default constructor.
    }

    public Users(String name) {
        this.name = name;
    }

    public Users(Long id, String name, long age) {
        this.id = id;
        this.name = name;
        this.age = age;

    }
    public static Multi<Users> findAll(PgPool client) {
        return client.query("SELECT id, name, age FROM users ORDER BY id ASC").execute()
                .onItem().transformToMulti(set -> Multi.createFrom().iterable(set))
                .onItem().transform(Users::from);
    }
    public static Uni<Users> findById(PgPool client, Long id) {
        return client.preparedQuery("SELECT id, name, age FROM users WHERE id = $1").execute(Tuple.of(id))
                .onItem().transform(RowSet::iterator)
                .onItem().transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
    }
    private static Users from(Row row) {
        return new Users(row.getLong("id"), row.getString("name"),row.getLong("age"));
    }

}
