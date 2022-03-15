package com.lizhenhua.fast.plugin

import com.android.build.gradle.BaseExtension
import com.lizhenhua.fast.plugin.core.FastTransformer
import org.gradle.api.Plugin
import org.gradle.api.Project

class Fast : Plugin<Project> {
    override fun apply(project: Project) {
        project.extensions.create("fast", FastExtension::class.java)

        project.extensions.let {
//            println((it as ExtensionContainerInternal).asMap.values)

            // id 'com.android.library'
            // id 'com.android.application'
            val baseExtension = it.findByType(BaseExtension::class.java)
            if (baseExtension != null) {
                it.getByType(BaseExtension::class.java).registerTransform(FastTransformer(project))
            }
        }
    }
}
