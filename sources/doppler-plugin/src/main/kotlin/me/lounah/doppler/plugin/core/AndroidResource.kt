package me.lounah.doppler.plugin.core

public class AndroidResource(
    public val name: String,
    public val project: String,
    public val path: String,
    public val type: String
) {

    public val shortenPath: String
        get() = path.substringAfter(project)

    public val sourceSet: String
        get() = path.substringAfter("src/").substringBefore("/")
}
