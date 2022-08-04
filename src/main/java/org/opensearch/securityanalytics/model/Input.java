/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.securityanalytics.model.util.ModelSerializer;
import org.opensearch.securityanalytics.model.util.ToXContentModel;
import org.opensearch.securityanalytics.resthandler.Tokens;

import java.io.IOException;
import java.util.List;

public class Input implements ToXContentModel {

    public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = ToXContentModel.createRegistryEntry(Input.class);

    public static final String DOCUMENT_LEVEL_INPUT = "doc_level_input";

    public String description;
    public List<String> indices;
    public List<Query> queries;

    public Input() {

    }

    public Input(final String description, final List<String> indices, final List<Query> queries) {
        this.description = description;
        this.indices = indices;
        this.queries = queries;
    }

    @Override
    public int hashCode() {
        return ModelSerializer.getHashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return ModelSerializer.areEquals(this, other);
    }


    @Override
    public XContentBuilder toXContent(final XContentBuilder builder, final Params params) throws IOException {
        return builder.startObject(DOCUMENT_LEVEL_INPUT)
                .field(Tokens.DESCRIPTION, this.description)
                .field(Tokens.INDICES, this.indices)
                .field(Tokens.QUERIES, this.queries)
                .endObject();
    }

    /*public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = new NamedXContentRegistry.Entry(Input.class, new ParseField(Tokens.INPUTS), new CheckedFunction<XContentParser, Input, IOException>() {
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
    });*/
}
