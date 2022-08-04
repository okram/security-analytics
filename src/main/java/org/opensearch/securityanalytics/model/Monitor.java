/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.xcontent.NamedXContentRegistry;
import org.opensearch.securityanalytics.model.util.ToXContentModel;

import java.util.List;

public class Monitor extends AbstractModel {

    public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = ToXContentModel.createRegistryEntry(Monitor.class);

    public String id;
    public String monitorType;
    public long version;
    public String name;
    public long interval;
    public String unit;
    public List<Input> inputs;

    public Monitor() {
        // for serialization
    }

    public Monitor(final String id, final String monitorType, final long version, final String name, final long interval, final String unit, final List<Input> inputs) {
        this.id = id;
        this.monitorType = monitorType;
        this.version = version;
        this.name = name;
        this.interval = interval;
        this.unit = unit;
        this.inputs = inputs;
    }
}
