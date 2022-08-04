/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.securityanalytics.model.util.ToXContentModel;

import java.util.List;

public class Query implements ToXContentModel {

    public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = ToXContentModel.createRegistryEntry(Query.class);

    public String id;
    public String name;
    public String query;
    public List<String> tags;

    public Query() {
    }

    public Query(final String id, final String name, final String query, final List<String> tags) {
        this.id = id;
        this.name = name;
        this.query = query;
        this.tags = tags;
    }


}
