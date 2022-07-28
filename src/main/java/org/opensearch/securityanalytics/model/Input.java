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

public class Input implements ToXContentObject {

    public static final String DOCUMENT_LEVEL_INPUT = "doc_level_input";

    private final String description;
    private final List<String> indices;
    private final List<Query> queries;


    public Input(final String description, final List<String> indices, final List<Query> queries) {
        this.description = description;
        this.indices = indices;
        this.queries = queries;
    }

    public Input(final StreamInput in) throws IOException {
        this(in.readString(), in.readStringList(), in.readList(new Query.Reader()));
    }

    public void writeTo(final StreamOutput out) throws IOException {
        out.writeString(this.description);
        out.writeStringCollection(this.indices);
        out.writeCollection(this.queries, new Query.Writer());
    }

    @Override
    public XContentBuilder toXContent(final XContentBuilder builder, final Params params) throws IOException {
        return builder.startObject(DOCUMENT_LEVEL_INPUT)
                .field(Tokens.DESCRIPTION, this.description)
                .field(Tokens.INDICES, this.indices)
                .field(Tokens.INDICES, this.queries)
                .endObject();
    }

    public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = new NamedXContentRegistry.Entry(Input.class, new ParseField(Tokens.INPUTS), new CheckedFunction<XContentParser, Input, IOException>() {
        @Override
        public Input apply(XContentParser parser) throws IOException {
            String description = null;
            List<String> indices = new ArrayList<>();
            List<Query> queries = new ArrayList<>();

            while (parser.nextToken() != XContentParser.Token.END_OBJECT) {
                final String fieldName = parser.currentName();
                parser.nextToken();
                switch (fieldName) {
                    case DOCUMENT_LEVEL_INPUT:
                        while (parser.nextToken() != XContentParser.Token.END_OBJECT) {
                            final String fieldName2 = parser.currentName();
                            parser.nextToken();
                            if (fieldName2.equals(Tokens.DESCRIPTION))
                                description = parser.text();
                            else if (fieldName2.equals(Tokens.INDICES))
                                indices = (List) parser.list();
                        }
                        break;
                    case Tokens.QUERIES:
                        queries = (List) parser.list();
                        break;
                }
            }
            return new Input(description, indices, queries);
        }
    });

    public static class Reader implements Writeable.Reader<Input> {

        @Override
        public Input read(final StreamInput in) throws IOException {
            return new Input(in);
        }
    }

    public static class Writer implements Writeable.Writer<Input> {

        @Override
        public void write(final StreamOutput out, final Input input) throws IOException {
            input.writeTo(out);
        }
    }

}
