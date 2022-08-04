/*
 * Copyright OpenSearch Contributors
 * SPDX-License-Identifier: Apache-2.0
 */

package org.opensearch.securityanalytics.model;

import org.opensearch.common.io.stream.BytesStreamInput;
import org.opensearch.common.io.stream.BytesStreamOutput;
import org.opensearch.securityanalytics.model.util.ModelSerializer;
import org.opensearch.test.OpenSearchTestCase;

import java.util.HashSet;
import java.util.List;

public class MonitorTests extends OpenSearchTestCase {

    public void testWriterReader() throws Exception {
        final Monitor monitor = new Monitor("monitorId", "doc_level_monitor", 1L, "name", 10L, "seconds", List.of(new Input("description", List.of("a", "b", "c"), List.of(new Query("1", "2", "3", List.of("tagA", "tagB"))))));
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, monitor);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Monitor monitor2 = ModelSerializer.read(input, Monitor.class);
        assertEquals(monitor.id, monitor2.id);
        assertEquals(monitor.monitorType, monitor2.monitorType);
        assertEquals(monitor.version, monitor2.version);
        assertEquals(monitor.name, monitor2.name);
        assertEquals(monitor.interval, monitor2.interval);
        assertEquals(monitor.unit, monitor2.unit);
        assertEquals(monitor.inputs, monitor2.inputs);
        assertEquals(monitor, monitor2);
    }

    public void testWriterReaderWithEmptyLists() throws Exception {
        final Monitor monitor = new Monitor("monitorId", "doc_level_monitor", 1L, "name", 10L, "seconds", List.of());
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, monitor);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Monitor monitor2 = ModelSerializer.read(input, Monitor.class);
        assertEquals("monitorId", monitor2.id);
        assertEquals("doc_level_monitor", monitor2.monitorType);
        assertEquals(1L, monitor2.version);
        assertEquals("name", monitor2.name);
        assertEquals(10L, monitor2.interval);
        assertEquals("seconds", monitor2.unit);
        assertEquals(List.of(), monitor2.inputs);
    }

    public void testNullFields() throws Exception {
        final Monitor monitor = new Monitor("monitorId", null, 1L, "name", 10L, "seconds", List.of());
        final BytesStreamOutput output = new BytesStreamOutput();
        ModelSerializer.write(output, monitor);
        final BytesStreamInput input = new BytesStreamInput(output.bytes().toBytesRef().bytes);
        final Monitor monitor2 = ModelSerializer.read(input, Monitor.class);
        assertEquals("monitorId", monitor2.id);
        assertNull(monitor2.monitorType);
        assertEquals(1L, monitor2.version);
        assertEquals("name", monitor2.name);
        assertEquals(10L, monitor2.interval);
        assertEquals("seconds", monitor2.unit);
        assertEquals(List.of(), monitor2.inputs);
    }

    public void testHashCodeEquality() throws Exception {
        final Monitor a = new Monitor("monitorId", "doc_level_monitor", 1L, "name", 10L, "seconds", List.of(new Input("description", List.of("a", "b", "c"), List.of(new Query("1", "2", "3", List.of("tagA", "tagB"))))));
        final Monitor b = new Monitor("monitorId", "doc_level_monitor", 1L, "name", 10L, "seconds", List.of(new Input("description", List.of("a", "b", "c"), List.of(new Query("1", "2", "3", List.of("tagA", "tagB"))))));
        final Monitor c = new Monitor("monitorId", "doc_level_monitor", 1L, "noName", 10L, "seconds", List.of(new Input("description", List.of("a", "b", "c"), List.of(new Query("1", "2", "3", List.of("tagA", "tagB"))))));
        final Monitor d = new Monitor("monitorId", "doc_level_monitor", 1L, "name", 10L, "seconds", List.of(new Input("description", List.of("a", "b", "c"), List.of(new Query("1", "2", "3", List.of("tagA", "tagC"))))));
        //
        assertEquals(3, new HashSet<>(List.of(a, b, c, d)).size());
        //
        assertEquals(a, b);
        assertNotEquals(a, c);
        assertNotEquals(a, d);
        assertNotEquals(b, c);
        assertNotEquals(b, d);
        assertNotEquals(c, d);
        //
        assertEquals(a.hashCode(), b.hashCode());
    }
}
