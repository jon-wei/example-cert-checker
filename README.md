# clarity-emitter
Druid emitter that sends events via HTTP POST to [clarity-collector](https://github.com/implydata/clarity-collector)

To bind, set `druid.emitter=clarity` in common.runtime.properties

## Configuration Options

All keys are prefixed by `druid.emitter.clarity.`, i.e. `druid.emitter.clarity.recipientBaseUrl`:

|Field|Type|Description|Default|Required|
|-----|----|-----------|-------|--------|
|`recipientBaseUrl`|String|HTTP endpoint events will be posted to, e.g. `http://<clarity collector host>:<port>/d/<username>`|[required]|yes|
|`basicAuthentication`|String|Basic auth credentials, typically `<username>:<password>`|null|no|
|`clusterName`|String|Cluster name used to tag events|null|no|
|`anonymous`|Boolean|Should hostnames be scrubbed from events?|false|no|
|`maxBufferSize`|Integer|Maximum size of event buffer|min(250MB, 10% of heap)|no|
|`maxBatchSize`|Integer|Maximum size of HTTP event payload |5MB|no|
|`flushCount`|Integer|Number of events before a flush is triggered|500|no|
|`flushBufferPercentFull`|Integer|Percentage of buffer fill that will trigger a flush (byte-based)|25|no|
|`flushMillis`|Integer|Period between flushes if not triggered by flushCount or flushBufferPercentFull|60s|no|
|`flushTimeOut`|Integer|Flush timeout|Long.MAX_VALUE|no|
|`timeOut`|ISO8601 Period|HTTP client response timeout|PT1M|no|
|`batchingStrategy`|String [ARRAY, NEWLINES]|How events are batched together in the payload|ARRAY|no|
|`compression`|String [NONE, LZ4, GZIP]|Compression algorithm used|LZ4|no|
|`lz4BufferSize`|Integer|Block size for the LZ4 compressor in bytes|65536|no|
|`samplingRate`|Integer|For sampled metrics, what percentage of metrics will be emitted|100|no|
|`sampledMetrics`|List<String>|Which event types are sampled|["query/wait/time", "query/segment/time", "query/segmentAndCache/time"]|no|
|`sampledNodeTypes`|List<String>|Which node types are sampled|["druid/historical", "druid/peon", "druid/realtime"]|no|
|`context`|Map<String, Object>|Whatever else you'd like to say. The keys in this map will be added as top-level keys and ingested as dimensions. They will not override other keys if there's a collision.|null|no|

## SSL Context Configuration
All keys are prefixed by `druid.emitter.clarity.ssl`, e.g. `druid.emitter.clarity.ssl.trustStorePath`.

If `trustStorePath` is not specified, a custom SSL context will not be created, and the default SSL context will be used instead.

|Property|Description|Default|Required|
|--------|-----------|-------|--------|
|`protocol`|SSL protocol to use.|`TLSv1.2`|no|
|`trustStoreType`|The type of the key store where trusted root certificates are stored.|`java.security.KeyStore.getDefaultType()`|no|
|`trustStorePath`|The file path or URL of the TLS/SSL Key store where trusted root certificates are stored.|none|no|
|`trustStoreAlgorithm`|Algorithm to be used by TrustManager to validate certificate chains|`javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()`|no|
|`trustStorePassword`|The [Password Provider](https://github.com/implydata/druid/blob/0.10.1-csp/docs/content/operations/password-provider.md) or String password for the Trust Store.|none|yes, if `trustStorePath` is specified.|
