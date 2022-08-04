/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model.util;

import org.opensearch.common.ParseField;
import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.common.xcontent.ToXContentObject;
import org.opensearch.common.xcontent.XContentBuilder;
import org.opensearch.securityanalytics.model.Query;

import java.io.IOException;

import static org.opensearch.securityanalytics.resthandler.Tokens.QUERIES;

public interface ToXContentModel extends ToXContentObject {

    static NamedXContentRegistry.Entry createRegistryEntry(final Class<? extends ToXContentModel> modelClass) {
        return new NamedXContentRegistry.Entry(Query.class, new ParseField(QUERIES), parser -> ModelSerializer.read(parser, modelClass));
    }

    @Override
    default XContentBuilder toXContent(final XContentBuilder builder, final Params params) throws IOException {
        return ModelSerializer.write(builder, this);
    }
}
