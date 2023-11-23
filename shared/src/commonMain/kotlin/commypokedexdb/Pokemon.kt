package commypokedexdb

public data class Pokemon(
    public val name: String,
    public val url: String
) {
    public override fun toString(): String = """
  |Pokemon [
  |  name: $name
  |  url: $url
  |]
  """.trimMargin()
}