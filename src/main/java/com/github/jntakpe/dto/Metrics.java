package com.github.jntakpe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean représentant les résultats de Metrics
 *
 * @author jntakpe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics {

    private long mem;

    @JsonProperty("mem.free")
    private long memFree;

    private int processors;

    private long uptime;

    private long heap;

    @JsonProperty("heap.commited")
    private long headCommited;

    @JsonProperty("heap.init")
    private long heapInit;

    @JsonProperty("heap.used")
    private long heapUsed;

    @JsonProperty("threads.peak")
    private int threadsPeak;


}
