/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.CheckedFunction;
import org.opensearch.common.ParseField;
import org.opensearch.common.io.stream.StreamInput;
import org.opensearch.common.io.stream.StreamOutput;
import org.opensearch.common.io.stream.Writeable;
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.common.xcontent.ToXContentObject;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.common.xcontent.XContentParser;
import org.opensearch.securityanalytics.resthandler.Tokens;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.opensearch.securityanalytics.resthandler.Tokens.QUERIES;

public class Query implements ToXContentObject {

    private final String id;
    private final String name;
    private final String query;
    private final List<String> tags;

    public Query(final String id, final String name, final String query, final List<String> tags) {
        this.id = id;
        this.name = name;
        this.query = query;
        this.tags = tags;
    }

    public Query(final StreamInput in) throws IOException {
        this(in.readString(), in.readString(), in.readString(), in.readStringList());
    }

    public void writeTo(final StreamOutput out) throws IOException {
        out.writeString(this.id);
        out.writeString(this.name);
        out.writeString(this.query);
        out.writeStringCollection(this.tags);
    }

    @Override
    public XContentBuilder toXContent(final XContentBuilder builder, final Params params) throws IOException {
        return builder.startObject()
                .field(Tokens.ID, this.id)
                .field(Tokens.NAME, this.name)
                .field(Tokens.QUERY, this.query)
                .field(Tokens.TAGS, this.tags)
                .endObject();
    }

    public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = new NamedXContentRegistry.Entry(Query.class, new ParseField(QUERIES), new CheckedFunction<XContentParser, Query, IOException>() {
        @Override
        public Query apply(XContentParser parser) throws IOException {
            String id = null;
            String name = null;
            String query = null;
            List<String> tags = new ArrayList<>();

            while (parser.nextToken() != XContentParser.Token.END_OBJECT) {
                final String fieldName = parser.currentName();
                parser.nextToken();
                switch (fieldName) {
                    case Tokens.ID:
                        id = parser.text();
                        break;
                    case Tokens.NAME:
                        name = parser.text();
                        break;
                    case Tokens.QUERY:
                        query = parser.text();
                        break;
                    case Tokens.TAGS:
                        tags = (List) parser.list();
                        break;
                }
            }
            return new Query(id, name, query, tags);
        }
    });

    public static class Reader implements Writeable.Reader<Query> {

        @Override
        public Query read(final StreamInput in) throws IOException {
            return new Query(in);
        }
    }

    public static class Writer implements Writeable.Writer<Query> {

        @Override
        public void write(final StreamOutput out, final Query query) throws IOException {
            query.writeTo(out);
        }
    }

}
