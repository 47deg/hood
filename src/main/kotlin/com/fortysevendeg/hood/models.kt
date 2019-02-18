package com.fortysevendeg.hood

data class Benchmark(
  val key: String,
  val score: Double
)

sealed class BenchmarkResult {
  abstract fun symbol(): String
  abstract fun icon(): String

  object OK : BenchmarkResult() {
    override fun symbol(): String = "✓"
    override fun icon(): String = ":heavy_check_mark:"
  }

  object WARN : BenchmarkResult() {
    override fun symbol(): String = "⚠"
    override fun icon(): String = ":warning:"
  }

  object FAILED : BenchmarkResult() {
    override fun symbol(): String = "✗"
    override fun icon(): String = ":red_circle:"
  }

  data class ERROR(val error: Throwable) : BenchmarkResult() {
    override fun symbol(): String = "☠"
    override fun icon(): String = ":skull_and_crossbones:"
  }

}

enum class FileFormat {
  MD, JSON;

  override fun toString(): String {
    return super.toString().toLowerCase()
  }
}

data class BenchmarkComparison(
  val key: String,
  val benchmark: List<Benchmark>,
  val result: BenchmarkResult
)

object BenchmarkInconsistencyError :
  Throwable("Benchmarks have differents formats and cannot be compared")

enum class GhStatusState(val value: String) {
  Succeed("success"), Pending("pending"), Failed("failure")
}

//Github

data class GhInfo(val owner: String, val repo: String, val token: String)

data class GhStatus(
  val state: GhStatusState,
  val description: String,
  val context: String = "benchmark-ci/hood"
)

data class GhUser(val login: String)

data class GhComment(val id: Long, val user: GhUser, val body: String)

data class GhFileSha(val sha: String)

data class GhCreateCommit(val message: String, val content: String, val branch: String)

data class GhUpdateCommit(
  val message: String,
  val content: String,
  val sha: String,
  val branch: String
)
