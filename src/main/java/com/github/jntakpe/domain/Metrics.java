package com.github.jntakpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Bean représentant les métriques relatives à : JVM, mémoire, processors, gc, etc ...
 *
 * @author jntakpe
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Metrics extends MongoEntity {

    private long mem;

    @JsonProperty("mem.free")
    private long memFree;

    private int processors;

    private long uptime;

    private long heap;

    @JsonProperty("heap.committed")
    private long heapCommitted;

    @JsonProperty("heap.init")
    private long heapInit;

    @JsonProperty("heap.used")
    private long heapUsed;

    @JsonProperty("threads.peak")
    private int threadsPeak;

    @JsonProperty("threads.daemon")
    private int threadsDaemon;

    private int threads;

    private int classes;

    @JsonProperty("classes.loaded")
    private int classesLoaded;

    @JsonProperty("classes.unloaded")
    private int classesUnloaded;

    @JsonProperty("gc.ps_scavenge.count")
    private long gcScavengeCount;

    @JsonProperty("gc.ps_scavenge.time")
    private long gcScavengeTime;

    @JsonProperty("gc.ps_marksweep.time")
    private long gcMarkSweepTime;

    @JsonProperty("gc.ps_marksweep.count")
    private long gcMarkSweepCount;

    public long getMem() {
        return mem;
    }

    public void setMem(long mem) {
        this.mem = mem;
    }

    public long getMemFree() {
        return memFree;
    }

    public void setMemFree(long memFree) {
        this.memFree = memFree;
    }

    public int getProcessors() {
        return processors;
    }

    public void setProcessors(int processors) {
        this.processors = processors;
    }

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public long getHeap() {
        return heap;
    }

    public void setHeap(long heap) {
        this.heap = heap;
    }

    public long getHeapCommitted() {
        return heapCommitted;
    }

    public void setHeapCommitted(long heapCommitted) {
        this.heapCommitted = heapCommitted;
    }

    public long getHeapInit() {
        return heapInit;
    }

    public void setHeapInit(long heapInit) {
        this.heapInit = heapInit;
    }

    public long getHeapUsed() {
        return heapUsed;
    }

    public void setHeapUsed(long heapUsed) {
        this.heapUsed = heapUsed;
    }

    public int getThreadsPeak() {
        return threadsPeak;
    }

    public void setThreadsPeak(int threadsPeak) {
        this.threadsPeak = threadsPeak;
    }

    public int getThreadsDaemon() {
        return threadsDaemon;
    }

    public void setThreadsDaemon(int threadsDaemon) {
        this.threadsDaemon = threadsDaemon;
    }

    public int getThreads() {
        return threads;
    }

    public void setThreads(int threads) {
        this.threads = threads;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    public int getClassesLoaded() {
        return classesLoaded;
    }

    public void setClassesLoaded(int classesLoaded) {
        this.classesLoaded = classesLoaded;
    }

    public int getClassesUnloaded() {
        return classesUnloaded;
    }

    public void setClassesUnloaded(int classesUnloaded) {
        this.classesUnloaded = classesUnloaded;
    }

    public long getGcScavengeCount() {
        return gcScavengeCount;
    }

    public void setGcScavengeCount(long gcScavengeCount) {
        this.gcScavengeCount = gcScavengeCount;
    }

    public long getGcScavengeTime() {
        return gcScavengeTime;
    }

    public void setGcScavengeTime(long gcScavengeTime) {
        this.gcScavengeTime = gcScavengeTime;
    }

    public long getGcMarkSweepTime() {
        return gcMarkSweepTime;
    }

    public void setGcMarkSweepTime(long gcMarkSweepTime) {
        this.gcMarkSweepTime = gcMarkSweepTime;
    }

    public long getGcMarkSweepCount() {
        return gcMarkSweepCount;
    }

    public void setGcMarkSweepCount(long gcMarkSweepCount) {
        this.gcMarkSweepCount = gcMarkSweepCount;
    }
}
