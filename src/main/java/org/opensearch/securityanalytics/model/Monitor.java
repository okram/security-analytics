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

import static org.opensearch.securityanalytics.resthandler.Tokens.MONITOR_TYPE;

public class Monitor implements ToXContentObject {

    public static final String DOCUMENT_LEVEL_MONITOR = "doc_level_monitor";

    private final String monitorId;
    private final Long version;
    private final String name;
    private final boolean enabled;
    private final Long interval;
    private final String unit;
    private final List<Input> inputs;

    public Monitor(final String monitorId, final Long version, final String name, final Boolean enabled, final Long interval, final String unit, final List<Input> inputs) {
        this.monitorId = monitorId;
        this.version = version;
        this.name = name;
        this.enabled = enabled;
        this.interval = interval;
        this.unit = unit;
        if (this.enabled && null == this.interval)
            throw new IllegalArgumentException("Interval must not be null if monitor is enabled");
        this.inputs = inputs;
    }

    public Monitor(final StreamInput in) throws IOException {
        this(in.readString(), in.readLong(), in.readString(), in.readBoolean(), in.readLong(), in.readString(), in.readList(new Input.Reader()));
    }

    public static NamedXContentRegistry.Entry XCONTENT_REGISTRY = new NamedXContentRegistry.Entry(Monitor.class, new ParseField(MONITOR_TYPE), new CheckedFunction<XContentParser, Monitor, IOException>() {
        @Override
        public Monitor apply(XContentParser parser) throws IOException {
            String monitorId = null;
            Long version = null;
            String name = null;
            Boolean enabled = null;
            Long interval = null;
            String unit = null;
            List<Input> inputs = new ArrayList<>();

            while (parser.nextToken() != XContentParser.Token.END_OBJECT) {
                final String fieldName = parser.currentName();
                parser.nextToken();
                switch (fieldName) {
                    case Tokens.TYPE:
                        if (!parser.text().equals(Tokens.MONITOR))
                            throw new IllegalArgumentException("Only monitor types are supported");
                        break;
                    case Tokens.MONITOR_TYPE:
                        if (!parser.text().equals(DOCUMENT_LEVEL_MONITOR))
                            throw new IllegalArgumentException("Only document level monitors are supported");
                        break;
                    case Tokens.NAME:
                        name = parser.text();
                        break;
                    case Tokens.ENABLED:
                        enabled = parser.booleanValue();
                        break;
                    case Tokens.VERSION:
                        version = parser.longValue();
                        break;
                    case Tokens.INTERVAL:
                        interval = parser.longValue();
                        break;
                    case Tokens.UNIT:
                        unit = parser.text();
                        break;
                    case Tokens.INPUTS:
                        inputs = (List) parser.list();
                        break;
                }
            }
            return new Monitor(monitorId, version, name, enabled, interval, unit, inputs);
        }
    });

    public void writeTo(final StreamOutput out) throws IOException {
        out.writeString(this.monitorId);
        out.writeLong(this.version);
        out.writeString(this.name);
        out.writeBoolean(this.enabled);
        out.writeLong(this.interval);
        out.writeString(this.unit);
        out.writeCollection(this.inputs, new Input.Writer());
    }

    @Override
    public XContentBuilder toXContent(final XContentBuilder builder, final Params params) throws IOException {
        return builder.startObject()
                .field(Tokens.TYPE, Tokens.MONITOR)
                .field(MONITOR_TYPE, DOCUMENT_LEVEL_MONITOR)
                .field(Tokens.NAME, this.name)
                .field(Tokens.ENABLED, this.enabled)
                .startObject(Tokens.SCHEDULE)
                .startObject(Tokens.PERIOD)
                .field(Tokens.INTERVAL, this.interval)
                .field(Tokens.UNIT, this.unit)
                .endObject()
                .endObject()
                .field(Tokens.INPUTS, this.inputs)
                .endObject();
    }

    public static class Reader implements Writeable.Reader<Monitor> {

        @Override
        public Monitor read(final StreamInput in) throws IOException {
            return new Monitor(in);
        }
    }

    public static class Writer implements Writeable.Writer<Monitor> {

        @Override
        public void write(final StreamOutput out, final Monitor monitor) throws IOException {
            monitor.writeTo(out);
        }
    }
}
