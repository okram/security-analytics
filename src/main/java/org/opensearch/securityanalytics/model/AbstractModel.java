/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.securityanalytics.model.util.ModelSerializer;
import org.opensearch.securityanalytics.model.util.ToXContentModel;

public class AbstractModel implements ToXContentModel {
    @Override
    public int hashCode() {
        return ModelSerializer.getHashCode(this);
    }

    @Override
    public boolean equals(final Object other) {
        return ModelSerializer.areEquals(this, other);
    }

    @Override
    public String toString() {
        return ModelSerializer.getString(this);
    }
}
