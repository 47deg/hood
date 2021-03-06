---
layout: docs
title: Benchmarks
permalink: gradle/benchmarks/

---

# Compare Benchmarks

The **Hood** basic task allows you to compare a set of benchmarks with the reference one,
 printing the result to the console and failing if one of the benchmarks has downgraded values.

The `compareBenchmarks` tasks have the following parameters:
 - **previousBenchmarkPath**: File with previous or master benchmark location. By default: `master.csv`.
 - **currentBenchmarkPath**: List of files with current or pull request benchmark location. By default: an `empty list`.
 - **keyColumnName**: Column name to distinguish each benchmark on the comparison. By default: `Benchmark`.
 - **compareColumnName**: Column name of the column to compare (the values must to be a `Double`). By default: `Score`.
 - **thresholdColumnName**: Column name to get the threshold per benchmark. By default: `Score Error (99.9%)`.
 - **generalThreshold**: Common threshold to all benchmarks overriding the value coming from `thresholdColumnName`. Optional.
 - **benchmarkThreshold**: `Map` with a custom threshold per benchmark key overriding the value coming from `thresholdColumnName` or `generalThreshold`. Optional.
 - **include**: Regular expression to include only the benchmarks with a matching key. Optional.
 - **exclude**: Regular expression to exclude the benchmarks using its key. Optional.

The `include/exclude` feature and `benchmarkThreshold` param use the cleaned key from benchmarks.
This means the key for `hood.comparing` will be `Comparing` with the capitalization.

***Note***: Currently **Hood** only supports `CSV` and `JSON` based benchmarks with cross comparison available.

## Send output to a file

The task can send the result to a file with the following parameters:
 - **outputToFile**: Sends the output to a file. By default: `false`.
 - **outputPath**: The path to the output file. By default: `./hood/comparison`.
 - **outputFormat**: The output file format; we support two formats: `MD` and `JSON`. By default: `MD`.

***Note***: To print a `JSON` output file, all the benchmarks must be in `JSON` format. `CSV` benchmarks will be ignored.

## Configuration example

<fortyseven-codetab data-languages='["Groovy", "Kotlin"]' markdown="block">
```groovy
compareBenchmarks {
  previousBenchmarkPath = file("$rootDir/benchmarks/master_benchmark.json")
  currentBenchmarkPath = [file("$rootDir/build/reports/hood_benchmark.json")]
  outputFormat = "json"
  benchmarkThreshold = ["Parsing": 500.00]
}
```

```kotlin
tasks.compareBenchmarks {
  previousBenchmarkPath = file("$rootDir/benchmarks/master_benchmark.json")
  currentBenchmarkPath = listOf(file("$rootDir/build/reports/hood_benchmark.json"))
  outputFormat = "json"
  benchmarkThreshold = mapOf("Parsing" to 500.00)
}
```
</fortyseven-codetab>
