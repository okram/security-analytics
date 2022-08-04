/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.opensearch.client.Request;
import org.opensearch.client.Response;
import org.opensearch.client.RestClient;
import org.opensearch.rest.RestRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public final class HttpTools {

    private static final Logger LOG = LogManager.getLogger(HttpTools.class);

    final RestClient client;

    public HttpTools(final RestClient client) {
        this.client = client;
    }

    public Response GET(final String route) throws IOException {
        return this.sendRequest(RestRequest.Method.GET.name(), route, null);
    }

    public Response GET(final String route, String json) throws IOException {
        return this.sendRequest(RestRequest.Method.GET.name(), route, json);
    }

    public Response PUT(final String route, final String json) throws IOException {
        return this.sendRequest(RestRequest.Method.PUT.name(), route, json);
    }

    public Response POST(final String route, final String json) throws IOException {
        return this.sendRequest(RestRequest.Method.POST.name(), route, json);
    }

    public Response POST(final String route) throws IOException {
        return this.sendRequest(RestRequest.Method.POST.name(), route, null);
    }

    public Response DELETE(final String route) throws IOException {
        return this.sendRequest(RestRequest.Method.DELETE.name(), route, null);
    }

    public Response sendRequest(final String method, final String route, final String jsonEntity) throws IOException {
        final Request request = new Request(method, route);
        if (null != jsonEntity) request.setJsonEntity(jsonEntity);
        final Response response = this.client.performRequest(request);

        LOG.info("\n" + method + " " + route + (null == jsonEntity ? "" : "\n" + new JSONObject(jsonEntity).toString(4)) +
                "\n\t===>\n[STATUS: " + response.getStatusLine().getStatusCode() + "]" +
                (null == response.getEntity() ? "" :
                        "\n" + HttpTools.prettyString(response.getEntity().getContent())));

        return response;
    }

    public static String asString(final InputStream stream) {
        try {
            final BufferedReader b = new BufferedReader(new InputStreamReader(stream, Charset.defaultCharset()));
            String temp = "";
            while (b.ready()) {
                temp = temp + "\n" + b.readLine();
            }
            return temp.trim();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject asJSON(final InputStream stream) {
        return new JSONObject(asString(stream));
    }

    public static String prettyString(final InputStream stream) {
        return new JSONObject(asString(stream)).toString(4);
    }
}
