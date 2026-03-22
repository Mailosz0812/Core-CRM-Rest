package org.mailosz.crmrest.crmclient.response;

public class ClientShortResponse {
    private String name;
    private String id;

    public ClientShortResponse(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public ClientShortResponse() {
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
