package earth.terrarium.cloche.api.target

import earth.terrarium.cloche.COMMON
import earth.terrarium.cloche.api.LazyConfigurable
import earth.terrarium.cloche.api.metadata.Metadata
import earth.terrarium.cloche.api.target.compilation.CommonSecondarySourceSets
import org.gradle.api.Action
import org.gradle.api.provider.Provider
import org.gradle.api.tasks.Optional

@JvmDefaultWithoutCompatibility
interface CommonTarget : ClocheTarget, CommonSecondarySourceSets {
    override val loaderName: String
        get() = COMMON

    val client: LazyConfigurable<CommonSecondarySourceSets>

    // Derived properties based on the dependant targets
    val dependents: Provider<List<MinecraftTarget>>

    val minecraftVersions: Provider<Set<String>>

    // Might be not set if there is not a common minecraft version
    override val minecraftVersion: Provider<String>
        @Optional get

    val metadata: Metadata

    fun withPublication()

    fun metadata(configure: Action<Metadata>) = configure.execute(metadata)
}
